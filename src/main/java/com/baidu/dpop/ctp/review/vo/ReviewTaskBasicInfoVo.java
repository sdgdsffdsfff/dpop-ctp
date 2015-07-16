package com.baidu.dpop.ctp.review.vo;

/**
 * 审核任务的基本信息
 * 
 * @author cgd
 * @date 2015年3月28日 下午2:54:24
 */
public class ReviewTaskBasicInfoVo {

    // Basic Info
    private Integer reviewTaskId; // 任务ID
    private String reviewTaskName; // 任务名称
    private String addUser; // 添加人
    private String addTime; // 添加时间
    private Byte taskStatus; // 任务状态
    private String taskStatusDesc; // 任务状态描述
    private Integer taskType; // 0-内部， 1-外部，2-全体
    private String taskTypeDesc; // 内部/外部任务
    private String reviewTypeDesc; // 审核类型描述（盲审 or 明审）

    // review progress info
    private Integer taskCount; // 审核任务包含的任务数
    private Integer isReviewedGroups; // 已审核标注groups
    private Integer notReviewedGroups; // 未审核标注groups
    private Integer isReviewedAds; // 已审核标注创意数
    private Integer notReviewedAds; // 未审核创意数

    /** 初始化 **/
    public ReviewTaskBasicInfoVo() {
        // review progress info set default value.
        this.setTaskCount(0);
        this.setIsReviewedGroups(0);
        this.setNotReviewedGroups(0);
        this.setIsReviewedAds(0);
        this.setNotReviewedAds(0);
    }

    // --------------------------------------------------------
    public String getAddUser() {
        return addUser;
    }

    public Integer getReviewTaskId() {
        return reviewTaskId;
    }

    public void setReviewTaskId(Integer reviewTaskId) {
        this.reviewTaskId = reviewTaskId;
    }

    public String getReviewTaskName() {
        return reviewTaskName;
    }

    public void setReviewTaskName(String reviewTaskName) {
        this.reviewTaskName = reviewTaskName;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public Byte getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Byte taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatusDesc() {
        return taskStatusDesc;
    }

    public void setTaskStatusDesc(String taskStatusDesc) {
        this.taskStatusDesc = taskStatusDesc;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getTaskTypeDesc() {
        return taskTypeDesc;
    }

    public void setTaskTypeDesc(String taskTypeDesc) {
        this.taskTypeDesc = taskTypeDesc;
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public Integer getIsReviewedGroups() {
        return isReviewedGroups;
    }

    public void setIsReviewedGroups(Integer isReviewedGroups) {
        this.isReviewedGroups = isReviewedGroups;
    }

    public Integer getNotReviewedGroups() {
        return notReviewedGroups;
    }

    public void setNotReviewedGroups(Integer notReviewedGroups) {
        this.notReviewedGroups = notReviewedGroups;
    }

    public Integer getIsReviewedAds() {
        return isReviewedAds;
    }

    public void setIsReviewedAds(Integer isReviewedAds) {
        this.isReviewedAds = isReviewedAds;
    }

    public Integer getNotReviewedAds() {
        return notReviewedAds;
    }

    public void setNotReviewedAds(Integer notReviewedAds) {
        this.notReviewedAds = notReviewedAds;
    }

    public String getReviewTypeDesc() {
        return reviewTypeDesc;
    }

    public void setReviewTypeDesc(String reviewTypeDesc) {
        this.reviewTypeDesc = reviewTypeDesc;
    }

}
