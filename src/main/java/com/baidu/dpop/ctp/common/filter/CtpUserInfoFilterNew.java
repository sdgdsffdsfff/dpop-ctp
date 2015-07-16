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

public class CtpUserInfoFilterNew implements Filter {

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
		
		String userName = null;

		// -------------- 是否有配置excludePaths ------------------
		if (!CollectionUtils.isEmpty(excludePathSet)) {
			String servletPath = httpRequest.getServletPath();
			String pathInfo = httpRequest.getPathInfo();

			if (servletPath.equals("/")) {
				httpResponse.sendRedirect("/main.html");
				return;
			}
			
			// 属于exclude path不要验证，直接跳过
			if (UrlUtils.urlMatch(excludePathSet, pathInfo)
					|| UrlUtils.urlMatch(excludePathSet, servletPath)) {
				chain.doFilter(request, response);
				return;
			}
		}

		try {
			userName = getUserInfo(httpRequest);
			
			UserService userService = SpringContextUtil
					.getBean(UserService.class);
			
			// 用户已经登录，信息已经被保存
			if (userName != null) {
				chain.doFilter(httpRequest, httpResponse);
				return;
			}

			// 以下为用户还未登录，信息没有保存
			// 外部用户的登录请求:外部用户只有在登录的时候才会处理这些问题
			Object loginTypeObj = httpRequest.getParameter("loginType");
			if (loginTypeObj != null) {
				Byte loginType = Byte.valueOf(loginTypeObj.toString());
				if (UserLoginType.OUTSIDE_USER_LOGIN.getId().equals(loginType)) {
					Object loginNameObj = httpRequest.getParameter("loginName");
					Object loginPwdObj = httpRequest.getParameter("loginPwd");
					if (loginNameObj != null && loginPwdObj != null
							&& userService.isOutsideUser(loginNameObj.toString(), loginPwdObj.toString())
							&& userService.isVaildUser(loginNameObj.toString())) {
						userName = loginNameObj.toString();
						saveUserInfo(httpRequest, httpResponse, userName);	
					}
					
					chain.doFilter(httpRequest, httpResponse);
					return;
				}
			}

			// 内部用户不一定要经过登录页面，只要认证通过即可进入主页
			Object assertionObj = httpRequest.getSession().getAttribute(
					DpopConstants.CONST_CAS_ASSERTION);
			if (assertionObj != null && assertionObj instanceof Assertion) {
				// 内部用户，并且已经完成了uuap验证
				Assertion assertion = (Assertion) assertionObj;
				userName = assertion.getPrincipal().getName();
				if (userService.isVaildUser(userName)) { // 用户是系统的合法用户
					saveUserInfo(httpRequest, httpResponse, userName);
					httpResponse.sendRedirect("/main.html");
					return;
				} else {
					httpResponse.sendRedirect("/login.html#error02");
					return;
				}
			}

			// 不是登录请求，并且用户还未登录
			httpResponse.sendError(403, "User has logout. Please login again.");
			return;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 删除ThreadLocal中用户信息
			ThreadLocalInfo.removeAll();
		}

	}

	private String getUserInfo(HttpServletRequest httpRequest) {
		Cookie myCookie = CookieUtils.findCookieByName(httpRequest,
				DpopSession.USER_DPOP_SESSION_ID);
		String uuid = null;
		String userName = null;
		if (myCookie != null) {
			uuid = myCookie.getValue();
			ThreadLocalInfo.setAttribute(
					DpopConstants.DPOP_USER_UUID_ATTRIBUTE, uuid);
		}
		if (uuid != null) {
			DpopSession session = DpopSessionFactory.getMySession(uuid);
			Object temp = session.getAttribute("userName");
			if (temp != null) {
				userName = temp.toString();
				ThreadLocalInfo.setAttribute(
						DpopConstants.DPOP_USER_NAME_ATTRIBUTE, userName);
			}
		}
		return userName;
	}

	private void saveUserInfo(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse, String userName) {

		String uuid = null;

		// 获取sessionID相关cookie
		Cookie myCookie = CookieUtils.findCookieByName(httpRequest,
				DpopSession.USER_DPOP_SESSION_ID);
		if (myCookie != null) {
			uuid = myCookie.getValue();
		}

		// 没有uuid则new一个（是否有cookie信息）
		if (uuid == null) {
			uuid = UUIDUtils.getUUID();
			// 新增对应cookie信息
			Cookie cookie = new Cookie(DpopSession.USER_DPOP_SESSION_ID, uuid);
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
		ThreadLocalInfo.setAttribute(DpopConstants.DPOP_USER_NAME_ATTRIBUTE,
				userName);
		ThreadLocalInfo.setAttribute(DpopConstants.DPOP_USER_UUID_ATTRIBUTE,
				uuid);
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
