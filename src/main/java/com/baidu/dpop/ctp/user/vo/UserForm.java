/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.user.vo;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;

import com.baidu.dpop.frame.core.base.web.IForm;
import com.baidu.dpop.ctp.user.bo.User;

public class UserForm implements IForm<User, Long> {

	// 主键
	@NotBlank(message = "user.id.notnull")
	private Integer id;
	// 用户名称
	@NotBlank(message = "user.userName.notnull")
	@Range(min = 0, max = 255, message = "user.userName.range")
	private String userName;
	// 用户类别
	@NotBlank(message = "user.roleType.notnull")
	private Byte roleType;
	// 密码
	@Range(min = 0, max = 255, message = "user.password.range")
	private String password;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setRoleType(Byte roleType) {
		this.roleType = roleType;
	}

	public Byte getRoleType() {
		return this.roleType;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	@Override
	public User transformBO() {
		User bo = new User();
		BeanUtils.copyProperties(this, bo);
		return bo;
	}

}
