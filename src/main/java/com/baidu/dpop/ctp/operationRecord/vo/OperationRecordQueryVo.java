package com.baidu.dpop.ctp.operationRecord.vo;

public class OperationRecordQueryVo {
	
	private String orderBy;
	private Boolean order;
	private Integer type;
	
	public String getOrderBy() {
		return orderBy;
	}
	
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public Boolean getOrder() {
		return order;
	}
	
	public void setOrder(Boolean order) {
		this.order = order;
	}
	
	public Integer getType() {
	    return type;
	}
	
	public void setType(Integer type) {
	    this.type = type;
	}
	
}
