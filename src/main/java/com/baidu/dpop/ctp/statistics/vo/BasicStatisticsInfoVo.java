package com.baidu.dpop.ctp.statistics.vo;

/**
 * 标注任务统计基本信息VO
 * 
 * @author cgd
 * @date 2015年3月17日 上午10:17:46
 */
public class BasicStatisticsInfoVo {

    private Integer taskId;     // 任务ID
    private String taskName; // 标注任务名称
    private String taskStatusDesc; // 任务状态
    private Byte taskStatus; // 任务状态
    private String addTime; // 创建时间
    private String addUser; // 创建人

    private Integer isDoneGroups; // 标注已完成推广组数
    private Integer notDoneGroups; // 标注未完成推广组数
    private Integer totalGroups; // 总的推广组数

    private Integer isDoneAds; // 标注已完成创意数
    private Integer notDoneAds; // 标注未完成创意数
    private Integer totalAds; // 总的创意数

    // -------------------------------------------------------------
    public String getTaskName() {
        return taskName;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStatusDesc() {
        return taskStatusDesc;
    }

    public void setTaskStatusDesc(String taskStatusDesc) {
        this.taskStatusDesc = taskStatusDesc;
    }

    public Byte getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Byte taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public Integer getIsDoneGroups() {
        return isDoneGroups;
    }

    public void setIsDoneGroups(Integer isDoneGroups) {
        this.isDoneGroups = isDoneGroups;
    }

    public Integer getNotDoneGroups() {
        return notDoneGroups;
    }

    public void setNotDoneGroups(Integer notDoneGroups) {
        this.notDoneGroups = notDoneGroups;
    }

    public Integer getTotalGroups() {
        return totalGroups;
    }

    public void setTotalGroups(Integer totalGroups) {
        this.totalGroups = totalGroups;
    }

    public Integer getIsDoneAds() {
        return isDoneAds;
    }

    public void setIsDoneAds(Integer isDoneAds) {
        this.isDoneAds = isDoneAds;
    }

    public Integer getNotDoneAds() {
        return notDoneAds;
    }

    public void setNotDoneAds(Integer notDoneAds) {
        this.notDoneAds = notDoneAds;
    }

    public Integer getTotalAds() {
        return totalAds;
    }

    public void setTotalAds(Integer totalAds) {
        this.totalAds = totalAds;
    }

}
