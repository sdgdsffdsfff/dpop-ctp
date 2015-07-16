package com.baidu.dpop.ctp.common.filter;

import java.io.IOException;
import java.util.TreeSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.util.CollectionUtils;

import com.baidu.dpop.ctp.user.constant.UserLoginType;
import com.baidu.dpop.frame.core.constant.DpopConstants;
import com.baidu.dpop.frame.core.filter.DpopFilterConfig;
import com.baidu.dpop.frame.core.util.CookieUtils;
import com.baidu.dpop.frame.core.util.UrlUtils;
import com.baidu.dpop.frame.core.web.DpopSession;
import com.baidu.dpop.frame.core.web.DpopSessionFactory;

/**
 * Ctp AuthenticationFilter
 * 
 * @author cgd
 * @date 2014年12月25日 上午11:17:11
 */
public class CtpAuthenticationFilter implements Filter {

	private AuthenticationFilter authenticationFilter;

	/**
	 * 不做拦截的路径
	 * */
	private TreeSet<String> excludePathSet;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		DpopFilterConfig dpopFilterConfig = new DpopFilterConfig(filterConfig);
		// 初始化SSO Auth拦截器
		authenticationFilter = new AuthenticationFilter();
		authenticationFilter.init(dpopFilterConfig);

		// 获取配置的excludePath
		String excludePaths = dpopFilterConfig.getInitParameter("excludePaths");
		excludePathSet = this.getExcludePathSet(excludePaths);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// -------------- 是否有配置excludePaths ------------------
		if (!CollectionUtils.isEmpty(excludePathSet)) {
			String servletPath = httpRequest.getServletPath();
			String pathInfo = httpRequest.getPathInfo();

			// 属于exclude path不要验证，直接跳过
			if (UrlUtils.urlMatch(excludePathSet, pathInfo)
					|| UrlUtils.urlMatch(excludePathSet, servletPath)) {
				chain.doFilter(request, response);
				return;
			}
		}

		// ----------------- 用户 cookie中是否存在uuid --------------
		String uuid = null;
		Cookie myCookie = CookieUtils.findCookieByName(httpRequest,
				DpopSession.USER_DPOP_SESSION_ID);
		if (myCookie != null) {
			uuid = myCookie.getValue();
			DpopSession session = DpopSessionFactory.getMySession(uuid);
			// 用户已登录
			if (session != null && session.getAttribute("userName") != null) {
				chain.doFilter(request, response);
				return;
			}
		}

		// （内部用户）获取SSO Assertion信息中UserName
		Object assertionObj = httpRequest.getSession().getAttribute(
				DpopConstants.CONST_CAS_ASSERTION);
		if (assertionObj != null && assertionObj instanceof Assertion) {
			chain.doFilter(request, response);
			return;
		}

		// ----------------- 是否为登录校验请求 ----------------
		Object loginTypeObj = httpRequest.getParameter("loginType");
		Object ticketObj = httpRequest.getParameter("ticket");

		if (loginTypeObj != null) {
			Byte loginType = Byte.valueOf(loginTypeObj.toString());
			// 内部用户登录, 走UUAP中的Auth验证过滤
			if (UserLoginType.INNER_USER_LOGIN.getId().equals(loginType)) {
				authenticationFilter.doFilter(request, response, chain);
			} else {
				chain.doFilter(request, response);
			}
		} else if (ticketObj != null) {
			chain.doFilter(request, response);
		} else {
			// redirect 到登录页
			httpResponse.sendRedirect("/login.html");
		}

		return;
	}

	@Override
	public void destroy() {
		if (authenticationFilter != null) {
			authenticationFilter.destroy();
		}
	}

	/**
	 * 将配置的excludePath转换成TreeSet结构
	 * */
	private TreeSet<String> getExcludePathSet(String excludePaths) {
		TreeSet<String> ret = new TreeSet<String>();
		if (excludePaths != null) {
			String[] paths = excludePaths.split(";");
			for (String p : paths) {
				if (p != null && p.length() > 0)
					ret.add(p);
			}
		}

		return ret;
	}

	public static void main(String[] args) {
	}
}
