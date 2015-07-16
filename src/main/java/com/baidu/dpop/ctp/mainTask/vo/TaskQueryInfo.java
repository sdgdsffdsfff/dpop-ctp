package com.baidu.dpop.ctp.mainTask.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskQueryInfo implements Serializable {

	private static final long serialVersionUID = -2325596782471848901L;

	private Integer id; // 任务ID
	private String taskName; // 任务名称
	private String addUser; // 添加人
	private Date beginTime;
	private Date endTime;
	private String beginTimeString;
	private String endTimeString;
	private Byte status;
	private Integer moduserLevel;
	private String orderBy;
	private boolean order;

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isOrder() {
		return order;
	}

	public void setOrder(boolean order) {
		this.order = order;
	}

	public String getAddUser() {
		return addUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer taskId) {
		this.id = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
		if (null != beginTime) {
			this.beginTimeString = sdf.format(this.beginTime);
		}
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
		if (null != endTime) {
			this.endTimeString = sdf.format(this.endTime);
		}
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getModuserLevel() {
		return moduserLevel;
	}

	public void setModuserLevel(Integer moduserLevel) {
		this.moduserLevel = moduserLevel;
	}

	public String getBeginTimeString() {
		return this.beginTimeString;
	}

	public String getEndTimeString() {
		return this.endTimeString;
	}
}
