/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.mainTask.bo;

import java.util.Date;

public class TaskBase implements java.io.Serializable {

    private static final long serialVersionUID = 2174609245997766404L;

    /**
     * 主键，由上游给出
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
     * 任务状态，0-未开始，1-进行中，2-已完成，3-已关闭
     */
    private Byte status;

    /**
     * 内部/外部任务，0-内部， 1-外部
     */
    private Integer moduserLevel;

    /**
     * 任务类型
     */
    private Byte taskType;

    /**
     * 主键，由上游给出
     * 
     * @param id the value for id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 主键，由上游给出
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
     * 任务状态，0-未开始，1-进行中，2-已完成，3-已关闭
     * 
     * @param status the value for status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 任务状态，0-未开始，1-进行中，2-已完成，3-已关闭
     * 
     * @return status the value for status
     */
    public Byte getStatus() {
        return this.status;
    }

    /**
     * 内部/外部任务，0-内部， 1-外部
     * 
     * @param moduserLevel the value for moduser_level
     */
    public void setModuserLevel(Integer moduserLevel) {
        this.moduserLevel = moduserLevel;
    }

    /**
     * 内部/外部任务，0-内部， 1-外部
     * 
     * @return moduserLevel the value for moduser_level
     */
    public Integer getModuserLevel() {
        return this.moduserLevel;
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
