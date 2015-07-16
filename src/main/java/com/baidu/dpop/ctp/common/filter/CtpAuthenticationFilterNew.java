package com.baidu.dpop.ctp.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.authentication.AuthenticationFilter;

import com.baidu.dpop.ctp.user.constant.UserLoginType;
import com.baidu.dpop.frame.core.filter.DpopFilterConfig;

public class CtpAuthenticationFilterNew implements Filter {

	private AuthenticationFilter authenticationFilter;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		DpopFilterConfig dpopFilterConfig = new DpopFilterConfig(filterConfig);
		authenticationFilter = new AuthenticationFilter();
		authenticationFilter.init(dpopFilterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		// ----------------- 是否为登录校验请求 ----------------
		Object loginTypeObj = httpRequest.getParameter("loginType");

		if (loginTypeObj != null) {
			Byte loginType = Byte.valueOf(loginTypeObj.toString());
			// 内部用户登录, 走UUAP中的Auth验证过滤
			if (UserLoginType.INNER_USER_LOGIN.getId().equals(loginType)) {
				authenticationFilter.doFilter(request, response, chain);
				return;
			}
		}
		chain.doFilter(request, response);
		return;

	}

	@Override
	public void destroy() {
		if (authenticationFilter != null) {
			authenticationFilter.destroy();
		}
	}

}
