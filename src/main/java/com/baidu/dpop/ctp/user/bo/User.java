/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.user.bo;

import java.util.Date;

public class User extends UserBase implements Cloneable {

    private static final long serialVersionUID = 1891250837469627292L;

    private static final int MAX_LOGIN_TRY_TIMES = 5;

    private Boolean isFreezed;

    public void setIsFreezed(Boolean isFreezed) {
        this.isFreezed = isFreezed;
    }

    public Boolean getIsFreezed() {
        return isFreezed;
    }

    @Override
    public void setLoginTryTimes(Integer times) {
        super.setLoginTryTimes(times);
        setIsFreezed(getLoginTryTimes() > MAX_LOGIN_TRY_TIMES);
    }

    /**
     * 返回一个User数据类的副本
     */
    public User clone() {
        User result = new User();
        result.setAddTime(this.getAddTime() == null ? null : (Date) this.getAddTime().clone());
        result.setDeleteFlag(this.getDeleteFlag() == null ? null : this.getDeleteFlag().booleanValue());
        result.setId(this.getId() == null ? null : this.getId().intValue());
        result.setPassword(this.getPassword());
        result.setRoleType(this.getRoleType() == null ? null : this.getRoleType().byteValue());
        result.setUpdateTime(this.getUpdateTime() == null ? null : (Date) this.getUpdateTime().clone());
        result.setUpdateUser(this.getUpdateUser() == null ? null : this.getUpdateUser().intValue());
        result.setUserName(this.getUserName());
        return result;
    }

}