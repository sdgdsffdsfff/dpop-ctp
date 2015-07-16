package com.baidu.dpop.ctp.adtag.vo;

import java.util.Date;
import java.util.List;

import com.baidu.dpop.ctp.adtag.bo.AdTag;

public class AdTagSubmitVo {
	
	private List<AdTag> list;
	private Date updateTime;
	private String updateUser;
	
	public List<AdTag> getList() {
		return list;
	}
	
	public void setList(List<AdTag> list) {
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
