package com.baidu.dpop.ctp.invoke.vo;

import java.util.List;

public class GroupSelectVo {
	
	private Integer taskId;
	private Integer dataType;
	private List<Long> groupIds;
	
	public Integer getTaskId() {
		return taskId;
	}
	
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	public Integer getDataType() {
		return dataType;
	}
	
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	
	public List<Long> getGroupIds() {
		return groupIds;
	}
	
	public void setGroupIds(List<Long> groupIds) {
		this.groupIds = groupIds;
	}
}
