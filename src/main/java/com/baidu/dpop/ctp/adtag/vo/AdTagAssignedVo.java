package com.baidu.dpop.ctp.adtag.vo;

import java.util.List;

public class AdTagAssignedVo {
	
	private Integer taskId;
	private Integer dataType;
	private List<Long> ids;
	
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
	
	public List<Long> getIds() {
		return ids;
	}
	
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
}
