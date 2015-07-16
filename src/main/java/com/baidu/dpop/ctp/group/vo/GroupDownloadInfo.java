package com.baidu.dpop.ctp.group.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GroupDownloadInfo {

    private Long id;
    private Long groupId;
    private Integer taskId;
    private Integer adTradeIdLevel3;
    private Integer adNum;
    private Integer taskType;
    private Date startTime;
    private Date doneTime;
    private String modifyUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getAdTradeIdLevel3() {
        return adTradeIdLevel3;
    }

    public void setAdTradeIdLevel3(Integer adTradeIdLevel3) {
        this.adTradeIdLevel3 = adTradeIdLevel3;
    }

    public Integer getAdNum() {
        return adNum;
    }

    public void setAdNum(Integer adNum) {
        this.adNum = adNum;
    }
    
    public Integer getTaskType() {
        return taskType;
    }
    
    public void setTaskType(Integer taskType) {
        this.taskId = taskType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String genFullInfo() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        sb.append(groupId + ",");
        sb.append(taskId + ",");
        sb.append(adTradeIdLevel3 + ",");
        sb.append(adNum + ",");
        sb.append(sdf.format(startTime) + ",");
        sb.append(sdf.format(doneTime) + ",");
        sb.append(modifyUser + "\n");

        return sb.toString();
    }

    public static String genFullTitle() {
        return "GroupId,TaskId,三级行业,创意数量,标注时间,提交时间,标注人\n";
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object another) {
        if (!(another instanceof GroupDownloadInfo)) {
            return false;
        }

        return this.id.longValue() == ((GroupDownloadInfo) another).id.intValue();
    }
}
