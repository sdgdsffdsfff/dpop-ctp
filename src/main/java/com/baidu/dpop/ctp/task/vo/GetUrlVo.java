package com.baidu.dpop.ctp.task.vo;

import java.util.List;

public class GetUrlVo {
	
	private List<Long> list;
	private Integer dataType;
	
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

}
