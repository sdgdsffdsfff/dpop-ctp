
/*
* Copyright 2014 baidu dpop
* All right reserved.
*
*/
package com.baidu.dpop.ctp.user.bo;

import java.util.Date;

/**
* 
*/	
public class UserBase implements java.io.Serializable{


   /**
    * 主键 
    */	
    private Integer id;

   /**
    * 用户名称 
    */	
    private String userName;

   /**
    * 用户类别 
    */	
    private Byte roleType;

   /**
    * 密码 
    */	
    private String password;

   /**
    * 添加时间 
    */	
    private Date addTime;

   /**
    * 更新时间 
    */	
    private Date updateTime;

   /**
    * 更改人 
    */	
    private Integer updateUser;

   /**
    * 是否删除 0-未删除 1-删除 
    */	
    private Boolean deleteFlag;

   /**
    * 登录尝试次数 
    */	
    private Integer loginTryTimes;


	/**
    * 主键
	* @param id the value for id
    */	
	public void setId(Integer id){
		this.id = id;
	}
	
	/**
    * 主键
	* @return id the value for id
    */
	public Integer getId(){
		return this.id;
	}

	/**
    * 用户名称
	* @param userName the value for user_name
    */	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	/**
    * 用户名称
	* @return userName the value for user_name
    */
	public String getUserName(){
		return this.userName;
	}

	/**
    * 用户类别
	* @param roleType the value for role_type
    */	
	public void setRoleType(Byte roleType){
		this.roleType = roleType;
	}
	
	/**
    * 用户类别
	* @return roleType the value for role_type
    */
	public Byte getRoleType(){
		return this.roleType;
	}

	/**
    * 密码
	* @param password the value for password
    */	
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
    * 密码
	* @return password the value for password
    */
	public String getPassword(){
		return this.password;
	}

	/**
    * 添加时间
	* @param addTime the value for add_time
    */	
	public void setAddTime(Date addTime){
		this.addTime = addTime;
	}
	
	/**
    * 添加时间
	* @return addTime the value for add_time
    */
	public Date getAddTime(){
		return this.addTime;
	}

	/**
    * 更新时间
	* @param updateTime the value for update_time
    */	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	/**
    * 更新时间
	* @return updateTime the value for update_time
    */
	public Date getUpdateTime(){
		return this.updateTime;
	}

	/**
    * 更改人
	* @param updateUser the value for update_user
    */	
	public void setUpdateUser(Integer updateUser){
		this.updateUser = updateUser;
	}
	
	/**
    * 更改人
	* @return updateUser the value for update_user
    */
	public Integer getUpdateUser(){
		return this.updateUser;
	}

	/**
    * 是否删除 0-未删除 1-删除
	* @param deleteFlag the value for delete_flag
    */	
	public void setDeleteFlag(Boolean deleteFlag){
		this.deleteFlag = deleteFlag;
	}
	
	/**
    * 是否删除 0-未删除 1-删除
	* @return deleteFlag the value for delete_flag
    */
	public Boolean getDeleteFlag(){
		return this.deleteFlag;
	}

	/**
    * 登录尝试次数
	* @param loginTryTimes the value for login_try_times
    */	
	public void setLoginTryTimes(Integer loginTryTimes){
		this.loginTryTimes = loginTryTimes;
	}
	
	/**
    * 登录尝试次数
	* @return loginTryTimes the value for login_try_times
    */
	public Integer getLoginTryTimes(){
		return this.loginTryTimes;
	}
}
