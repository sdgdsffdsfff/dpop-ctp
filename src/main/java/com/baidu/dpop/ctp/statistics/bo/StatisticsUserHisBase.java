
/*
* Copyright 2014 baidu dpop
* All right reserved.
*
*/
package com.baidu.dpop.ctp.statistics.bo;

import java.util.Date;

/**
* 
*/	
public class StatisticsUserHisBase implements java.io.Serializable{


   /**
    * 主键ID 
    */	
    private Long id;

   /**
    * 任务ID 
    */	
    private Integer taskId;

   /**
    * 标注人员账户名 
    */	
    private String userName;

   /**
    * 已标注推广组数 
    */	
    private Integer isDoneGroups;

   /**
    * 已标注创意数 
    */	
    private Integer isDoneAds;


	/**
    * 主键ID
	* @param id the value for id
    */	
	public void setId(Long id){
		this.id = id;
	}
	
	/**
    * 主键ID
	* @return id the value for id
    */
	public Long getId(){
		return this.id;
	}

	/**
    * 任务ID
	* @param taskId the value for task_id
    */	
	public void setTaskId(Integer taskId){
		this.taskId = taskId;
	}
	
	/**
    * 任务ID
	* @return taskId the value for task_id
    */
	public Integer getTaskId(){
		return this.taskId;
	}

	/**
    * 标注人员账户名
	* @param userName the value for user_name
    */	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	/**
    * 标注人员账户名
	* @return userName the value for user_name
    */
	public String getUserName(){
		return this.userName;
	}

	/**
    * 已标注推广组数
	* @param isDoneGroups the value for is_done_groups
    */	
	public void setIsDoneGroups(Integer isDoneGroups){
		this.isDoneGroups = isDoneGroups;
	}
	
	/**
    * 已标注推广组数
	* @return isDoneGroups the value for is_done_groups
    */
	public Integer getIsDoneGroups(){
		return this.isDoneGroups;
	}

	/**
    * 已标注创意数
	* @param isDoneAds the value for is_done_ads
    */	
	public void setIsDoneAds(Integer isDoneAds){
		this.isDoneAds = isDoneAds;
	}
	
	/**
    * 已标注创意数
	* @return isDoneAds the value for is_done_ads
    */
	public Integer getIsDoneAds(){
		return this.isDoneAds;
	}
}
