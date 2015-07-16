package com.baidu.dpop.ctp.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.session.SingleSignOutFilter;

import com.baidu.dpop.ctp.user.constant.UserRoleType;
import com.baidu.dpop.ctp.user.service.UserService;
import com.baidu.dpop.frame.core.constant.DpopConstants;
import com.baidu.dpop.frame.core.context.SpringContextUtil;
import com.baidu.dpop.frame.core.filter.DpopFilterConfig;
import com.baidu.dpop.frame.core.util.CookieUtils;
import com.baidu.dpop.frame.core.util.ThreadLocalInfo;
import com.baidu.dpop.frame.core.web.DpopSession;
import com.baidu.dpop.frame.core.web.DpopSessionFactory;

/**
 * 用户退出Filter
 * 
 * @author cgd
 * @date 2014年12月25日 上午11:19:49
 */
public class CtpSingleSignOutFilter implements Filter {
	// uuap原始退出filter
	private SingleSignOutFilter singleSignOutFilter;

	/**
	 * 初始化
	 * 
	 * @param filterConfig
	 * @throws ServletException
	 *             参数
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		singleSignOutFilter = new SingleSignOutFilter();
		DpopFilterConfig dpopFilterConfig = new DpopFilterConfig(filterConfig);
		singleSignOutFilter.init(dpopFilterConfig);
	}

	/**
	 * UUAP用户登录退出Filter
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 *             参数
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// 内部用户才需要走uuap
		boolean flag = false;

		try {
			String uuid = null;
			Cookie myCookie = CookieUtils.findCookieByName(httpRequest,
					DpopSession.USER_DPOP_SESSION_ID);
			if (myCookie != null) {
				uuid = myCookie.getValue();
			}

			httpRequest.getSession().removeAttribute(
					DpopConstants.CONST_CAS_ASSERTION);
			String userName = null;

			if (uuid != null) {
				DpopSession session = DpopSessionFactory.getMySession(uuid);
				userName = session.getAttribute("userName").toString();
				UserService userService = SpringContextUtil
						.getBean(UserService.class);
				if (UserRoleType.isInnerUserRoleType(userService.getUserByName(
						userName).getRoleType())) {
					flag = true;
				}

				if (session != null) {
					session.removeAll();
				}
			}

			ThreadLocalInfo.setAttribute(
					DpopConstants.DPOP_USER_NAME_ATTRIBUTE, userName);
			ThreadLocalInfo.setAttribute(
					DpopConstants.DPOP_USER_UUID_ATTRIBUTE, uuid);

			if (flag) {
				singleSignOutFilter.doFilter(request, response, chain);
			} else {
				chain.doFilter(httpRequest, httpResponse);
			}

		} finally {
			ThreadLocalInfo.removeAll();
		}
	}

	/**
	 * 资源清理
	 */
	@Override
	public void destroy() {
		singleSignOutFilter.destroy();
	}
}
