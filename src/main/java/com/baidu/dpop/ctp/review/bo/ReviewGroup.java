
/*
* Copyright 2014 baidu dpop
* All right reserved.
*
*/
package com.baidu.dpop.ctp.review.bo;

import com.baidu.dpop.ctp.group.bo.Group;

/**
* 
*/	
public class ReviewGroup extends ReviewGroupBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6980301670994844147L;
	
	private String modifyUserName;
	private String modifyUserNameReview;

	public void setArgs(Group g) {
		this.setTagGroupId(g.getId());
		this.setAdNum(g.getAdNum());
		this.setDataType(g.getDataType());
		this.setGroupId(g.getGroupId());
		this.setTaskId(g.getTaskId());
		this.setModifyUserId(g.getModifyUserId());
		this.setTaskName(g.getTaskName());
	}
	
	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	public String getModifyUserNameReview() {
		return modifyUserNameReview;
	}

	public void setModifyUserNameReview(String modifyUserNameReview) {
		this.modifyUserNameReview = modifyUserNameReview;
	}
  
}