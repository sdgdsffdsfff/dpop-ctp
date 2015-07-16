
/*
* Copyright 2014 baidu dpop
* All right reserved.
*
*/
package com.baidu.dpop.ctp.review.bo;

import java.util.Date;

/**
* 
*/	
public class ReviewGroupBase implements java.io.Serializable{


   /**
    * 主键ID 
    */	
    private Long id;

   /**
    * 原主键ID 
    */	
    private Long tagGroupId;

   /**
    * 推广组ID 
    */	
    private Long groupId;

   /**
    * Group类型，0 - 北斗， 1 - 秋实，2-DSP 
    */	
    private Byte dataType;

   /**
    * 所属标注任务id 
    */	
    private Integer taskId;

   /**
    * 原任务名称 
    */	
    private String taskName;

   /**
    * 所属的审核任务id 
    */	
    private Integer taskIdReview;

   /**
    * 审核人物名称 
    */	
    private String taskNameReview;

   /**
    * 组内创意数量 
    */	
    private Integer adNum;

   /**
    * 生成的审核任务所包含的创意数量 
    */	
    private Integer adNumReview;

   /**
    * 状态, 0-未开始, 1-处理中, 2-已完成 
    */	
    private Byte status;

   /**
    * 标注用户id 
    */	
    private Integer modifyUserId;

   /**
    * 正在处理的用户id 
    */	
    private Integer modifyUserIdReview;

   /**
    * 开始标注时间 
    */	
    private Date startTime;

   /**
    * 提交时间 
    */	
    private Date doneTime;


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
    * 原主键ID
	* @param tagGroupId the value for tag_group_id
    */	
	public void setTagGroupId(Long tagGroupId){
		this.tagGroupId = tagGroupId;
	}
	
	/**
    * 原主键ID
	* @return tagGroupId the value for tag_group_id
    */
	public Long getTagGroupId(){
		return this.tagGroupId;
	}

	/**
    * 推广组ID
	* @param groupId the value for group_id
    */	
	public void setGroupId(Long groupId){
		this.groupId = groupId;
	}
	
	/**
    * 推广组ID
	* @return groupId the value for group_id
    */
	public Long getGroupId(){
		return this.groupId;
	}

	/**
    * Group类型，0 - 北斗， 1 - 秋实，2-DSP
	* @param dataType the value for data_type
    */	
	public void setDataType(Byte dataType){
		this.dataType = dataType;
	}
	
	/**
    * Group类型，0 - 北斗， 1 - 秋实，2-DSP
	* @return dataType the value for data_type
    */
	public Byte getDataType(){
		return this.dataType;
	}

	/**
    * 所属标注任务id
	* @param taskId the value for task_id
    */	
	public void setTaskId(Integer taskId){
		this.taskId = taskId;
	}
	
	/**
    * 所属标注任务id
	* @return taskId the value for task_id
    */
	public Integer getTaskId(){
		return this.taskId;
	}

	/**
    * 原任务名称
	* @param taskName the value for task_name
    */	
	public void setTaskName(String taskName){
		this.taskName = taskName;
	}
	
	/**
    * 原任务名称
	* @return taskName the value for task_name
    */
	public String getTaskName(){
		return this.taskName;
	}

	/**
    * 所属的审核任务id
	* @param taskIdReview the value for task_id_review
    */	
	public void setTaskIdReview(Integer taskIdReview){
		this.taskIdReview = taskIdReview;
	}
	
	/**
    * 所属的审核任务id
	* @return taskIdReview the value for task_id_review
    */
	public Integer getTaskIdReview(){
		return this.taskIdReview;
	}

	/**
    * 审核人物名称
	* @param taskNameReview the value for task_name_review
    */	
	public void setTaskNameReview(String taskNameReview){
		this.taskNameReview = taskNameReview;
	}
	
	/**
    * 审核人物名称
	* @return taskNameReview the value for task_name_review
    */
	public String getTaskNameReview(){
		return this.taskNameReview;
	}

	/**
    * 组内创意数量
	* @param adNum the value for ad_num
    */	
	public void setAdNum(Integer adNum){
		this.adNum = adNum;
	}
	
	/**
    * 组内创意数量
	* @return adNum the value for ad_num
    */
	public Integer getAdNum(){
		return this.adNum;
	}

	/**
    * 生成的审核任务所包含的创意数量
	* @param adNumReview the value for ad_num_review
    */	
	public void setAdNumReview(Integer adNumReview){
		this.adNumReview = adNumReview;
	}
	
	/**
    * 生成的审核任务所包含的创意数量
	* @return adNumReview the value for ad_num_review
    */
	public Integer getAdNumReview(){
		return this.adNumReview;
	}

	/**
    * 状态, 0-未开始, 1-处理中, 2-已完成
	* @param status the value for status
    */	
	public void setStatus(Byte status){
		this.status = status;
	}
	
	/**
    * 状态, 0-未开始, 1-处理中, 2-已完成
	* @return status the value for status
    */
	public Byte getStatus(){
		return this.status;
	}

	/**
    * 标注用户id
	* @param modifyUserId the value for modify_user_id
    */	
	public void setModifyUserId(Integer modifyUserId){
		this.modifyUserId = modifyUserId;
	}
	
	/**
    * 标注用户id
	* @return modifyUserId the value for modify_user_id
    */
	public Integer getModifyUserId(){
		return this.modifyUserId;
	}

	/**
    * 正在处理的用户id
	* @param modifyUserIdReview the value for modify_user_id_review
    */	
	public void setModifyUserIdReview(Integer modifyUserIdReview){
		this.modifyUserIdReview = modifyUserIdReview;
	}
	
	/**
    * 正在处理的用户id
	* @return modifyUserIdReview the value for modify_user_id_review
    */
	public Integer getModifyUserIdReview(){
		return this.modifyUserIdReview;
	}

	/**
    * 开始标注时间
	* @param startTime the value for start_time
    */	
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	/**
    * 开始标注时间
	* @return startTime the value for start_time
    */
	public Date getStartTime(){
		return this.startTime;
	}

	/**
    * 提交时间
	* @param doneTime the value for done_time
    */	
	public void setDoneTime(Date doneTime){
		this.doneTime = doneTime;
	}
	
	/**
    * 提交时间
	* @return doneTime the value for done_time
    */
	public Date getDoneTime(){
		return this.doneTime;
	}
}
