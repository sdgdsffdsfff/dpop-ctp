package com.baidu.dpop.ctp.task.vo;

/**
 * 跟进中的标注任务情况
 * 
 * @author cgd
 * @date 2015年4月21日 下午2:25:00
 */
public class TagFollowInfoVo {

    private String userName; // 跟进人名称
    private Long groupId; // Group ID
    private Integer taskId; // 任务ID
    private String assignTime; // 分配时间

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(String assignTime) {
        this.assignTime = assignTime;
    }

}
