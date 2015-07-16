package com.baidu.dpop.ctp.user.vo;

import java.io.Serializable;

/**
 * UserLoginForm
 * 
 * @author cgd
 * @date 2014年12月26日 下午2:24:20
 */
public class UserLoginForm implements Serializable {

    private static final long serialVersionUID = -5351106815328466200L;

    private String loginName; // 登录名
    private String loginPwd; // 登录密码
    private String receiveCode; // 输入的验证码
    private Byte loginType; // 登录类型（0：内部用户，1：外部用户）

    // ------------------------------------------------
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public Byte getLoginType() {
        return loginType;
    }

    public void setLoginType(Byte loginType) {
        this.loginType = loginType;
    }

    public String getReceiveCode() {
        return receiveCode;
    }

    public void setReceiveCode(String receiveCode) {
        this.receiveCode = receiveCode;
    }
}
