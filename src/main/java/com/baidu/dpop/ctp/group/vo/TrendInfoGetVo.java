package com.baidu.dpop.ctp.group.vo;

import java.util.Date;

/**
 * 查询趋势时使用
 * 
 * 包含userName, taskId, startTime, endTime参数
 * 
 * @author mading01
 * 
 */
public class TrendInfoGetVo {

    private String userName;
    private Integer taskId;
    private Date startTime;
    private Date endTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
