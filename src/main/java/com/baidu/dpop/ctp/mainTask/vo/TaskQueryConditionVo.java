package com.baidu.dpop.ctp.mainTask.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * TaskQueryCondition Vo
 * 
 * @author cgd
 * @date 2015年1月13日 下午8:28:36
 */
public class TaskQueryConditionVo implements Serializable {

	private static final long serialVersionUID = 5865498534716897071L;

	private String taskName; // 任务名
	private String addUser; // 添加人
	private Date addTime; // 添加日期
	private Boolean status; // 需要改变的状态
	private Integer taskId;

	public Integer TaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}
