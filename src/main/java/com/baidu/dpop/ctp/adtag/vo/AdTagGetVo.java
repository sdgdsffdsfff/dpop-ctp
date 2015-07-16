package com.baidu.dpop.ctp.adtag.vo;

import java.util.List;

public class AdTagGetVo {
	
	private List<Long> list;
	private Integer dataType;
	private Long refId;
	
	public List<Long> getList() {
		return list;
	}
	
	public void setList(List<Long> list) {
		this.list = list;
	}
	
	public Integer getDataType() {
		return dataType;
	}
	
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	
	public Long getRefId() {
		return refId;
	}
	
	public void setRefId(Long refId) {
		this.refId = refId;
	}

}
