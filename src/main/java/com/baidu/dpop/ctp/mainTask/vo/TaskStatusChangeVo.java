package com.baidu.dpop.ctp.mainTask.vo;

import java.util.List;

public class TaskStatusChangeVo {
	
	Integer statusChange;
	List<Integer> taskIds;
	
	public Integer getStatusChange() {
		return statusChange;
	}
	
	public void setStatusChange(Integer statusChange) {
		this.statusChange = statusChange;
	}
	
	public List<Integer> getTaskIds() {
		return taskIds;
	}
	
	public void setTaskIds(List<Integer> taskIds) {
		this.taskIds = taskIds;
	}
}
