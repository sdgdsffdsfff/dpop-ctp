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

import org.jasig.cas.client.validation.Assertion;
import org.springframework.util.CollectionUtils;

import com.baidu.dpop.ctp.user.constant.UserLoginType;
import com.baidu.dpop.ctp.user.service.UserService;
import com.baidu.dpop.frame.core.constant.DpopConstants;
import com.baidu.dpop.frame.core.context.SpringContextUtil;
import com.baidu.dpop.frame.core.filter.DpopFilterConfig;
import com.baidu.dpop.frame.core.util.CookieUtils;
import com.baidu.dpop.frame.core.util.ThreadLocalInfo;
import com.baidu.dpop.frame.core.util.UUIDUtils;
import com.baidu.dpop.frame.core.util.UrlUtils;
import com.baidu.dpop.frame.core.web.DpopSession;
import com.baidu.dpop.frame.core.web.DpopSessionFactory;

/**
 * 用户信息相关拦截器
 * */
public class CtpUserInfoFilter implements Filter {

	/**
	 * 不做拦截的路径
	 * */
	private TreeSet<String> excludePathSet;

	/**
	 * 初始化
	 * */
	public void init(FilterConfig filterConfig) throws ServletException {
		// 获取配置的excludePath
		DpopFilterConfig dpopFilterConfig = new DpopFilterConfig(filterConfig);
		String excludePaths = dpopFilterConfig.getInitParameter("excludePaths");
		excludePathSet = this.getExcludePathSet(excludePaths);
	}

	/**
	 * 存储当前登录用户的用户信息
	 * */
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

		String uuid = null;
		String userName = null;

		try {
			// （外部用户）
			UserService userService = SpringContextUtil
					.getBean(UserService.class);
			Object loginTypeObj = httpRequest.getParameter("loginType");
			if (loginTypeObj != null) {
				Byte loginType = Byte.valueOf(loginTypeObj.toString());
				if (UserLoginType.OUTSIDE_USER_LOGIN.getId().equals(loginType)) {
					Object loginNameObj = httpRequest.getParameter("loginName");
					Object loginPwdObj = httpRequest.getParameter("loginPwd");
					if (loginNameObj != null
							&& loginPwdObj != null
							&& userService.isOutsideUser(
									loginNameObj.toString(),
									loginPwdObj.toString())) {
						userName = loginNameObj.toString();
					} else { // 外部用户校验失败
						chain.doFilter(request, response);
						return;
					}
				}
			}

			// 获取sessionID相关cookie
			Cookie myCookie = CookieUtils.findCookieByName(httpRequest,
					DpopSession.USER_DPOP_SESSION_ID);
			if (myCookie != null) {
				uuid = myCookie.getValue();
			}

			// （内部用户）获取SSO Assertion信息中UserName
			Object assertionObj = httpRequest.getSession().getAttribute(
					DpopConstants.CONST_CAS_ASSERTION);
			if (assertionObj != null && assertionObj instanceof Assertion) {
				Assertion assertion = (Assertion) assertionObj;
				userName = assertion.getPrincipal().getName();
			}

			// 没有uuid则new一个（是否有cookie信息）
			if (uuid == null) {
				uuid = UUIDUtils.getUUID();
				// 新增对应cookie信息
				Cookie cookie = new Cookie(DpopSession.USER_DPOP_SESSION_ID,
						uuid);
				cookie.setMaxAge(-1);
				cookie.setPath("/");
				httpResponse.addCookie(cookie);
			}

			// 用户信息存session
			DpopSession session = DpopSessionFactory.getMySession(uuid);
			if (userName != null) {
				session.setAttribute("userName", userName);
			}
			if (session.getAttribute("userName") != null) {
				userName = session.getAttribute("userName").toString();
			}

			// ThreadLocal中保存userName && uuid
			ThreadLocalInfo.setAttribute(
					DpopConstants.DPOP_USER_NAME_ATTRIBUTE, userName);
			ThreadLocalInfo.setAttribute(
					DpopConstants.DPOP_USER_UUID_ATTRIBUTE, uuid);

			String pathInfo = httpRequest.getPathInfo();
			if (pathInfo != null && pathInfo.indexOf("login") >= 0) {
				httpResponse.sendError(403, "User has logout. Please login again.");
				return;
			}

			chain.doFilter(request, response);
			return;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 删除ThreadLocal中用户信息
			ThreadLocalInfo.removeAll();
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

	/**
	 * 资源清理
	 * */
	public void destroy() {
	}

}
