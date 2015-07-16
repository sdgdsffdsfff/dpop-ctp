package com.baidu.dpop.ctp.user.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.constant.UserLoginType;
import com.baidu.dpop.ctp.user.constant.UserRoleType;
import com.baidu.dpop.ctp.user.service.UserService;
import com.baidu.dpop.ctp.user.vo.UserLoginForm;
import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.constant.DpopConstants;
import com.baidu.dpop.frame.core.context.DpopPropertyUtils;
import com.baidu.dpop.frame.core.util.CookieUtils;
import com.baidu.dpop.frame.core.web.DpopSession;
import com.baidu.dpop.frame.core.web.DpopSessionFactory;

@Controller
public class LoginController extends JsonBaseController {

    @Autowired
    private UserService userService;
    
    @Value("${dpop.ctp.user.logintrytimes}")
    private Integer MAX_LOGIN_TRY_TIMES;
    
    public void setMAXLOGINTIMES(Integer times) {
    	this.MAX_LOGIN_TRY_TIMES = times;
    }

    /**
     * 登出函数
     * 
     * @return 登出后重定向至登陆页面
     */
    @RequestMapping(value = "/logout.action")
    public String logout() {
        // 获取配置的SSO signout URL & Server signout URL

        boolean isOut = false;
        User u = userService.getCurrentLoginUser();

        if (u != null && UserRoleType.isOutUserRoleType(u.getRoleType())) {
            isOut = true;
        }

        String casSignOutURL = DpopPropertyUtils.getProperty("casSignOutURL");
        String serverSignOutURL = DpopPropertyUtils.getProperty("serverSignOutURL");

        // 未配置的话访问线下的URL, 只有内部用户
        if (!isOut && (casSignOutURL == null || serverSignOutURL == null)) {
            return "redirect:http://itebeta.baidu.com:8100/logout?service=http://dpop-ctp.baidu.com:8080/login.html";
        }

        // 用户退出后对应的跳转地址（配置的是首页）
        StringBuilder address = new StringBuilder();
        address.append("redirect:");
        address.append(casSignOutURL);
        address.append("?service=");
        address.append(serverSignOutURL);

        return isOut ? "redirect:/login.html" : address.toString();
    }

    /**
     * 用户登录
     * 
     * @param loginForm 登录表单
     * @return 登陆后的重定向页面地址
     */
    @RequestMapping(value = "/login.action", method = RequestMethod.POST)
    public String login(UserLoginForm loginForm) {
        // 传入参数有误
        if (loginForm == null || loginForm.getLoginType() == null) {
            return "redirect:/login.html#error01";
        }

        try {
            // 外部用户走DB验证（内部用户走uuap）
            if (UserLoginType.OUTSIDE_USER_LOGIN.getId().equals(loginForm.getLoginType())) {
                String loginName = loginForm.getLoginName();
                String loginPwd = loginForm.getLoginPwd();
                String receiveCode = loginForm.getReceiveCode();
                if (loginName == null || loginPwd == null || receiveCode == null) {
                    return "redirect:/login.html#error01";
                }

                // 验证码是否ok
                HttpServletRequest request =
                        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String expectedCode =
                        (String) request.getSession().getAttribute(
                                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
                
                if(expectedCode == null || !expectedCode.equals(receiveCode)) {
                    return "redirect:/login.html#error05";
                }

                User user = this.userService.getUserByName(loginName);
				if (user == null || user.getDeleteFlag()) {
					return "redirect:/login.html#error02";
				} else {
					int tryTimes = user.getLoginTryTimes();
					if (tryTimes >= MAX_LOGIN_TRY_TIMES) {
						removeUserInfo(request);
						return "redirect:/login.html#error06";
					}
					
					if (!user.getPassword().equals(loginPwd)) {
						user.setLoginTryTimes(tryTimes + 1);
						userService.updateByIdSelective(user);
						return "redirect:/login.html#error03";
					}
				}
            }
        } catch (Exception e) {
            return "redirect:/login.html#error04";
        }

        return "redirect:/main.html";
    }
    
    private void removeUserInfo(HttpServletRequest request) {
    	String uuid = null;
    	Cookie myCookie = CookieUtils.findCookieByName(request, DpopSession.USER_DPOP_SESSION_ID);
        if(myCookie != null) {
            uuid = myCookie.getValue();
        }
        
        request.getSession().removeAttribute(DpopConstants.CONST_CAS_ASSERTION);
        DpopSession session = DpopSessionFactory.getMySession(uuid);
        if (session != null) {
			session.removeAll();
		}
    }
}
