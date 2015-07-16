/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.review.bo;

import java.util.Date;

/**
 * 审核任务实体类
 */
public class ReviewTaskBase implements java.io.Serializable {

    private static final long serialVersionUID = 4877710177421893818L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 创建人
     */
    private String addUser;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 选择开始时间
     */
    private Date beginTime;

    /**
     * 选择结束时间
     */
    private Date endTime;

    /**
     * 包含任务条目，使用“,”分隔
     */
    private String taskList;

    /**
     * 任务状态，0-未开始，1-进行中，2-已完成
     */
    private Byte status;

    /**
     * 内部/外部任务，0-内部， 1-外部，2-全体
     */
    private Integer moduserLevel;

    /**
     * 是否盲审，0-盲审，1-非盲审
     */
    private Boolean blind;

    /**
     * 设定审核group数量
     */
    private Integer groupNum;

    /**
     * 实际完成数量
     */
    private Integer groupNumActual;

    /**
     * 任务属性id
     */
    private Integer taskCondition;

    /**
     * 任务类型
     */
    private Byte taskType;

    /**
     * 主键
     * 
     * @param id the value for id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 主键
     * 
     * @return id the value for id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 任务名称
     * 
     * @param taskName the value for task_name
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 任务名称
     * 
     * @return taskName the value for task_name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * 创建人
     * 
     * @param addUser the value for add_user
     */
    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    /**
     * 创建人
     * 
     * @return addUser the value for add_user
     */
    public String getAddUser() {
        return this.addUser;
    }

    /**
     * 创建时间
     * 
     * @param addTime the value for add_time
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 创建时间
     * 
     * @return addTime the value for add_time
     */
    public Date getAddTime() {
        return this.addTime;
    }

    /**
     * 选择开始时间
     * 
     * @param beginTime the value for begin_time
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 选择开始时间
     * 
     * @return beginTime the value for begin_time
     */
    public Date getBeginTime() {
        return this.beginTime;
    }

    /**
     * 选择结束时间
     * 
     * @param endTime the value for end_time
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 选择结束时间
     * 
     * @return endTime the value for end_time
     */
    public Date getEndTime() {
        return this.endTime;
    }

    /**
     * 包含任务条目，使用“,”分隔
     * 
     * @param taskList the value for task_list
     */
    public void setTaskList(String taskList) {
        this.taskList = taskList;
    }

    /**
     * 包含任务条目，使用“,”分隔
     * 
     * @return taskList the value for task_list
     */
    public String getTaskList() {
        return this.taskList;
    }

    /**
     * 任务状态，0-未开始，1-进行中，2-已完成
     * 
     * @param status the value for status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 任务状态，0-未开始，1-进行中，2-已完成
     * 
     * @return status the value for status
     */
    public Byte getStatus() {
        return this.status;
    }

    /**
     * 内部/外部任务，0-内部， 1-外部，2-全体
     * 
     * @param moduserLevel the value for moduser_level
     */
    public void setModuserLevel(Integer moduserLevel) {
        this.moduserLevel = moduserLevel;
    }

    /**
     * 内部/外部任务，0-内部， 1-外部，2-全体
     * 
     * @return moduserLevel the value for moduser_level
     */
    public Integer getModuserLevel() {
        return this.moduserLevel;
    }

    /**
     * 是否盲审，0-盲审，1-非盲审
     * 
     * @param blind the value for blind
     */
    public void setBlind(Boolean blind) {
        this.blind = blind;
    }

    /**
     * 是否盲审，0-盲审，1-非盲审
     * 
     * @return blind the value for blind
     */
    public Boolean getBlind() {
        return this.blind;
    }

    /**
     * 设定审核group数量
     * 
     * @param groupNum the value for group_num
     */
    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    /**
     * 设定审核group数量
     * 
     * @return groupNum the value for group_num
     */
    public Integer getGroupNum() {
        return this.groupNum;
    }

    /**
     * 实际完成数量
     * 
     * @param groupNumActual the value for group_num_actual
     */
    public void setGroupNumActual(Integer groupNumActual) {
        this.groupNumActual = groupNumActual;
    }

    /**
     * 实际完成数量
     * 
     * @return groupNumActual the value for group_num_actual
     */
    public Integer getGroupNumActual() {
        return this.groupNumActual;
    }

    /**
     * 任务属性id
     * 
     * @param taskCondition the value for task_condition
     */
    public void setTaskCondition(Integer taskCondition) {
        this.taskCondition = taskCondition;
    }

    /**
     * 任务属性id
     * 
     * @return taskCondition the value for task_condition
     */
    public Integer getTaskCondition() {
        return this.taskCondition;
    }

    /**
     * 任务类型
     * 
     * @param taskType the value for task_type
     */
    public void setTaskType(Byte taskType) {
        this.taskType = taskType;
    }

    /**
     * 任务类型
     * 
     * @return taskType the value for task_type
     */
    public Byte getTaskType() {
        return this.taskType;
    }
}
