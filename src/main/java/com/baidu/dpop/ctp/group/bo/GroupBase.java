/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.group.bo;

import java.util.Date;

public class GroupBase implements java.io.Serializable {

    private static final long serialVersionUID = 7990716484616823377L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 推广组ID，不同task下的推广组id可能不同
     */
    private Long groupId;

    /**
     * Group类型，0 - 北斗， 1 - 秋实
     */
    private Byte dataType;

    /**
     * 所属任务名称
     */
    private String taskName;

    /**
     * 所属任务id
     */
    private Integer taskId;

    /**
     * 组内创意数量
     */
    private Integer adNum;

    /**
     * 状态, 0-未开始, 1-处理中, 2-已完成
     */
    private Byte status;

    /**
     * 优先级
     */
    private Long priority;

    /**
     * 正在处理的用户id
     */
    private Integer modifyUserId;

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
     * 
     * @param id the value for id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 主键ID
     * 
     * @return id the value for id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 推广组ID，不同task下的推广组id可能不同
     * 
     * @param groupId the value for group_id
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * 推广组ID，不同task下的推广组id可能不同
     * 
     * @return groupId the value for group_id
     */
    public Long getGroupId() {
        return this.groupId;
    }

    /**
     * Group类型，0 - 北斗， 1 - 秋实
     * 
     * @param dataType the value for data_type
     */
    public void setDataType(Byte dataType) {
        this.dataType = dataType;
    }

    /**
     * Group类型，0 - 北斗， 1 - 秋实
     * 
     * @return dataType the value for data_type
     */
    public Byte getDataType() {
        return this.dataType;
    }

    /**
     * 所属任务名称
     * 
     * @param taskName the value for task_name
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 所属任务名称
     * 
     * @return taskName the value for task_name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * 所属任务id
     * 
     * @param taskId the value for task_id
     */
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * 所属任务id
     * 
     * @return taskId the value for task_id
     */
    public Integer getTaskId() {
        return this.taskId;
    }

    /**
     * 组内创意数量
     * 
     * @param adNum the value for ad_num
     */
    public void setAdNum(Integer adNum) {
        this.adNum = adNum;
    }

    /**
     * 组内创意数量
     * 
     * @return adNum the value for ad_num
     */
    public Integer getAdNum() {
        return this.adNum;
    }

    /**
     * 状态, 0-未开始, 1-处理中, 2-已完成
     * 
     * @param status the value for status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 状态, 0-未开始, 1-处理中, 2-已完成
     * 
     * @return status the value for status
     */
    public Byte getStatus() {
        return this.status;
    }

    /**
     * 优先级
     * 
     * @param priority the value for priority
     */
    public void setPriority(Long priority) {
        this.priority = priority;
    }

    /**
     * 优先级
     * 
     * @return priority the value for priority
     */
    public Long getPriority() {
        return this.priority;
    }

    /**
     * 正在处理的用户id
     * 
     * @param modifyUserId the value for modify_user_id
     */
    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    /**
     * 正在处理的用户id
     * 
     * @return modifyUserId the value for modify_user_id
     */
    public Integer getModifyUserId() {
        return this.modifyUserId;
    }

    /**
     * 开始标注时间
     * 
     * @param startTime the value for start_time
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 开始标注时间
     * 
     * @return startTime the value for start_time
     */
    public Date getStartTime() {
        return this.startTime;
    }

    /**
     * 提交时间
     * 
     * @param doneTime the value for done_time
     */
    public void setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
    }

    /**
     * 提交时间
     * 
     * @return doneTime the value for done_time
     */
    public Date getDoneTime() {
        return this.doneTime;
    }
}
