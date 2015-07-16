package com.baidu.dpop.ctp.outerinvoke.vo;

import java.util.List;

public class CreativityTagResponse<T> implements java.io.Serializable {

	private static final long serialVersionUID = 5522545446909557633L;
	
	private Integer status; // 1-成功、2-部分成功、3-异常
	private String message; // 描述
	private List<T> datas; // 泛型包装，成功的创意ID列表
	private List<Long> notFoundList; // 未找到的创意ID列表（若部分创意ID无法找到，则status会被置为2）
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<T> getDatas() {
		return datas;
	}
	
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
	public List<Long> getNotFoundList() {
		return notFoundList;
	}
	
	public void setNotFoundList(List<Long> notFoundList) {
		this.notFoundList = notFoundList;
	}
}
