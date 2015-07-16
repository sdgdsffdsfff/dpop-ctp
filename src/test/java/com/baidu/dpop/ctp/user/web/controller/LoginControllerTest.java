package com.baidu.dpop.ctp.user.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baidu.dpop.ctp.base.DefaultBOUtils;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.constant.UserLoginType;
import com.baidu.dpop.ctp.user.constant.UserRoleType;
import com.baidu.dpop.ctp.user.service.UserService;
import com.baidu.dpop.ctp.user.vo.UserLoginForm;
import com.baidu.dpop.frame.core.context.DpopPropertyUtils;
import com.baidu.dpop.frame.core.util.CookieUtils;
import com.baidu.dpop.frame.core.web.DpopSession;
import com.baidu.dpop.frame.core.web.DpopSessionFactory;

import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.NonStrictExpectations;
import mockit.Tested;

public class LoginControllerTest {

	@Tested
	private LoginController loginController;

	@Injectable
	private UserService userService;

	@Test
	public void testLogout() {

		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = DefaultBOUtils.getUser(1, "user1",
						UserRoleType.OUTSIDE_TAG_USER.getId());
			}
		};

		Assert.assertEquals("redirect:/login.html", loginController.logout());

		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = DefaultBOUtils.getUser(1, "user1",
						UserRoleType.INNER_ADMIN_USER.getId());
			}
		};

		Assert.assertEquals(
				"redirect:http://itebeta.baidu.com:8100/logout?service=http://dpop-ctp.baidu.com:8080/login.html",
				loginController.logout());
	}

	@Test
	public void testLoginNull() {
		// 参数错误的情况
		UserLoginForm loginForm = new UserLoginForm();
		Assert.assertEquals(loginController.login(loginForm),
				"redirect:/login.html#error01");

		Assert.assertEquals(loginController.login(null),
				"redirect:/login.html#error01");
	}

	@Test
	public void testLoginRight() {

		loginController.setMAXLOGINTIMES(5);

		new MockUp<RequestContextHolder>() {
			@Mock
			public RequestAttributes getRequestAttributes() {
				MockHttpServletRequest request = new MockHttpServletRequest();
				request.getSession().setAttribute(
						com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY,
						"test");
				RequestAttributes requestAttributes = new ServletRequestAttributes(
						request);
				return requestAttributes;
			}
		};

		new NonStrictExpectations() {
			{
				userService.getUserByName((String) any);
				result = DefaultBOUtils.getUser(1, "user1",
						UserRoleType.OUTSIDE_TAG_USER.getId());
			}
		};

		UserLoginForm loginForm = new UserLoginForm();
		loginForm.setLoginName("user1");
		loginForm.setLoginPwd(DefaultBOUtils.DEFAULT_PASSWORD);
		loginForm.setReceiveCode("test");
		loginForm.setLoginType(UserLoginType.OUTSIDE_USER_LOGIN.getId());
		Assert.assertEquals(loginController.login(loginForm),
				"redirect:/main.html");

		loginForm.setLoginType(UserLoginType.INNER_USER_LOGIN.getId());
		Assert.assertEquals(loginController.login(loginForm),
				"redirect:/main.html");
	}

	@Test
	public void testLoginWrongPwdOrName() {

		loginController.setMAXLOGINTIMES(5);

		new MockUp<RequestContextHolder>() {
			@Mock
			public RequestAttributes getRequestAttributes() {
				MockHttpServletRequest request = new MockHttpServletRequest();
				request.getSession().setAttribute(
						com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY,
						"test");
				RequestAttributes requestAttributes = new ServletRequestAttributes(
						request);
				return requestAttributes;
			}
		};

		new NonStrictExpectations() {
			{
				userService.getUserByName((String) any);
				result = DefaultBOUtils.getUser(1, "user1",
						UserRoleType.OUTSIDE_AUDIT_USER.getId());
			}
		};

		UserLoginForm loginForm = new UserLoginForm();
		loginForm.setLoginType(UserLoginType.OUTSIDE_USER_LOGIN.getId());

		loginForm.setLoginName(null);
		Assert.assertEquals(loginController.login(loginForm),
				"redirect:/login.html#error01");

		loginForm.setLoginName("user1");
		loginForm.setLoginPwd(null);
		Assert.assertEquals(loginController.login(loginForm),
				"redirect:/login.html#error01");

		loginForm.setLoginName("user1");
		loginForm.setLoginPwd("222222");
		loginForm.setReceiveCode("test");
		Assert.assertEquals(loginController.login(loginForm),
				"redirect:/login.html#error03");
	}
	
	@Test
	public void testCodeWrong() {
		new MockUp<RequestContextHolder>() {
			@Mock
			public RequestAttributes getRequestAttributes() {
				MockHttpServletRequest request = new MockHttpServletRequest();
				request.getSession().setAttribute(
						com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY,
						"test");
				RequestAttributes requestAttributes = new ServletRequestAttributes(
						request);
				return requestAttributes;
			}
		};
		
		UserLoginForm loginForm = new UserLoginForm();
		loginForm.setLoginType(UserLoginType.OUTSIDE_USER_LOGIN.getId());
		loginForm.setLoginName("user1");
		loginForm.setLoginPwd("pwd");
		loginForm.setReceiveCode("worongCode");
		Assert.assertEquals(loginController.login(loginForm),
				"redirect:/login.html#error05");
		
		loginForm.setLoginName("user1");
		loginForm.setLoginPwd("pwd");
		loginForm.setReceiveCode(null);
		Assert.assertEquals(loginController.login(loginForm),
				"redirect:/login.html#error01");
		
		new MockUp<RequestContextHolder>() {
			@Mock
			public RequestAttributes getRequestAttributes() {
				MockHttpServletRequest request = new MockHttpServletRequest();
				RequestAttributes requestAttributes = new ServletRequestAttributes(
						request);
				return requestAttributes;
			}
		};
		
		loginForm = new UserLoginForm();
		loginForm.setLoginType(UserLoginType.OUTSIDE_USER_LOGIN.getId());
		loginForm.setLoginName("user1");
		loginForm.setLoginPwd("pwd");
		loginForm.setReceiveCode("test");
		Assert.assertEquals(loginController.login(loginForm),
				"redirect:/login.html#error05");
	}

	@Test
	public void testLoginNullUser() {
		new MockUp<RequestContextHolder>() {
			@Mock
			public RequestAttributes getRequestAttributes() {
				MockHttpServletRequest request = new MockHttpServletRequest();
				request.getSession().setAttribute(
						com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY,
						"test");
				RequestAttributes requestAttributes = new ServletRequestAttributes(
						request);
				return requestAttributes;
			}
		};

		new NonStrictExpectations() {
			{
				userService.getUserByName((String) any);
				result = null;
			}
		};

		UserLoginForm loginForm = new UserLoginForm();
		loginForm.setLoginName("user1");
		loginForm.setLoginPwd(DefaultBOUtils.DEFAULT_PASSWORD);
		loginForm.setReceiveCode("test");
		loginForm.setLoginType(UserLoginType.OUTSIDE_USER_LOGIN.getId());
		Assert.assertEquals(loginController.login(loginForm),
				"redirect:/login.html#error02");
		
		new NonStrictExpectations() {
			{
				userService.getUserByName((String) any);
				User u = DefaultBOUtils.getUser(1, "user1", UserRoleType.OUTSIDE_AUDIT_USER.getId());
				u.setDeleteFlag(true);
			}
		};
		Assert.assertEquals(loginController.login(loginForm),
				"redirect:/login.html#error02");
	}
	
	@Test
	public void testLoginTryTimes() {
		
		new MockUp<RequestContextHolder>() {
			@Mock
			public RequestAttributes getRequestAttributes() {
				MockHttpServletRequest request = new MockHttpServletRequest();
				request.getSession().setAttribute(
						com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY,
						"test");
				RequestAttributes requestAttributes = new ServletRequestAttributes(
						request);
				return requestAttributes;
			}
		};
		
		new MockUp<CookieUtils>() {
			@Mock
			public Cookie findCookieByName(HttpServletRequest request, String cookieName) {
				Cookie cookie = new Cookie("test", "test");
				return cookie;
			}
		};
		
		new MockUp<DpopSessionFactory>() {
			@Mock
			public DpopSession getMySession(String name) {
				return null;
			}
		};
		
		loginController.setMAXLOGINTIMES(5);
		
		new NonStrictExpectations() {
			{
				userService.getUserByName((String) any);
				User user = DefaultBOUtils.getUser(1, "user1",
						UserRoleType.OUTSIDE_AUDIT_USER.getId());
				user.setLoginTryTimes(5);
				result = user;
			}
		};
		
		UserLoginForm loginForm = new UserLoginForm();
		loginForm.setLoginName("user1");
		loginForm.setLoginPwd(DefaultBOUtils.DEFAULT_PASSWORD);
		loginForm.setReceiveCode("test");
		loginForm.setLoginType(UserLoginType.OUTSIDE_USER_LOGIN.getId());
		Assert.assertEquals("redirect:/login.html#error06", loginController.login(loginForm));
	}

	@Test
	public void testLoginException() {
		new MockUp<RequestContextHolder>() {
			@Mock
			public RequestAttributes getRequestAttributes() {
				MockHttpServletRequest request = new MockHttpServletRequest();
				request.getSession().setAttribute(
						com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY,
						"test");
				RequestAttributes requestAttributes = new ServletRequestAttributes(
						request);
				return requestAttributes;
			}
		};

		new NonStrictExpectations() {
			{
				userService.getUserByName((String) any);
				result = new Exception("test");
			}
		};

		UserLoginForm loginForm = new UserLoginForm();
		loginForm.setLoginName("mading01");
		loginForm.setLoginPwd("111111");
		loginForm.setReceiveCode("test");
		loginForm.setLoginType(UserLoginType.OUTSIDE_USER_LOGIN.getId());
		Assert.assertEquals(loginController.login(loginForm),
				"redirect:/login.html#error04");
	}

	@Test
	public void testLogOut() {
		new MockUp<DpopPropertyUtils>() {
			@Mock
			public String getProperty(String test) {
				if (test.equals("casSignOutURL")) {
					return "casSignOutURL";
				}
				;

				if (test.equals("serverSignOutURL")) {
					return "serverSignOutURL";
				}
				;

				return null;
			}
		};
		String result = loginController.logout();
		Assert.assertEquals("redirect:casSignOutURL?service=serverSignOutURL",
				result);
	}

}
