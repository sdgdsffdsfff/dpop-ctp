package com.baidu.dpop.ctp.common.bo;

public class BasicResult {
	
	private boolean isSuccess;
	private String info;
	
	public BasicResult(){};
	
	public BasicResult(boolean isSuccess, String info) {
		this.isSuccess = isSuccess;
		this.info = info;
	}
	
	public boolean isSuccess() {
		return isSuccess;
	}
	
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}

}
