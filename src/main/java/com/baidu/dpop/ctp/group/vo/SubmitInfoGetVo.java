package com.baidu.dpop.ctp.group.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SubmitInfoGetVo {

    private String groupId;
    private String taskId;
    private Integer taskIdNumber;
    private String newAdTradeId;
    private String wNumMin;
    private String wNumMax;
    private String markerTime;
    private String submitTime;
    private String name;
    private Integer userId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    
    public Integer getTaskIdNumber() {
        return taskIdNumber;
    }
    
    public void setTaskIdNumber(Integer taskIdNumber) {
        this.taskIdNumber = taskIdNumber;
    }

    public String getNewAdTradeId() {
        return newAdTradeId;
    }

    public void setNewAdTradeId(String newAdTradeId) {
        this.newAdTradeId = newAdTradeId;
    }

    public String getwNumMin() {
        return wNumMin;
    }

    public void setwNumMin(String wNumMin) {
        this.wNumMin = wNumMin;
    }

    public String getwNumMax() {
        return wNumMax;
    }

    public void setwNumMax(String wNumMax) {
        this.wNumMax = wNumMax;
    }

    public String getMarkerTime() {
        return markerTime;
    }

    public void setMarkerTime(String markerTime) {
        this.markerTime = markerTime;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public Long getGroupIdNumber() {
        if (groupId == null) {
            return null;
        }
        return Long.valueOf(groupId);
    }
    
    public Integer getNewAdTradeIdNumber() {
        if (newAdTradeId == null) {
            return null;
        }
        return Integer.valueOf(newAdTradeId);
    }

    public Integer getNumMinNumber() {
        if (wNumMin == null) {
            return null;
        }
        return Integer.valueOf(wNumMin);
    }
    
    public Integer getNumMaxNumber() {
        if (wNumMax == null) {
            return null;
        }
        return Integer.valueOf(wNumMax);
    }
    
    public Date getMarkerTimeDate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(markerTime);
        } catch (Exception e) {
            return null;
        }
    }
    
    public Date getSubmitTimeDate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(submitTime);
        } catch (Exception e) {
            return null;
        }
    }
}
