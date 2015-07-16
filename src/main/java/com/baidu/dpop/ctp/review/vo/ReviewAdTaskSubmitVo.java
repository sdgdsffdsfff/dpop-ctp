package com.baidu.dpop.ctp.review.vo;

import java.util.Date;
import java.util.List;

import com.baidu.dpop.ctp.review.bo.ReviewAdTask;

public class ReviewAdTaskSubmitVo {

	private List<ReviewAdTask> list;
	private Date updateTime;
	private String updateUser;
	
	public List<ReviewAdTask> getList() {
		return list;
	}
	
	public void setList(List<ReviewAdTask> list) {
		this.list = list;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getUpdateUser() {
		return updateUser;
	}
	
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}
