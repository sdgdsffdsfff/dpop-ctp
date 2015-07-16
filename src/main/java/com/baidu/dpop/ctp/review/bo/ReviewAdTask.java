
/*
* Copyright 2014 baidu dpop
* All right reserved.
*
*/
package com.baidu.dpop.ctp.review.bo;

import java.util.Date;

import com.baidu.dpop.ctp.adtag.bo.AdTag;

/**
* 
*/	
public class ReviewAdTask extends ReviewAdTaskBase {

	private static final long serialVersionUID = 1L;
	
	public static final String updateUser = "System";
	
	private String adTradeNameLevel3;
	private String adTradeNameLevel3Review;

	
	public String getAdTradeNameLevel3() {
		return adTradeNameLevel3;
	}

	public void setAdTradeNameLevel3(String adTradeNameLevel3) {
		this.adTradeNameLevel3 = adTradeNameLevel3;
	}

	public String getAdTradeNameLevel3Review() {
		return adTradeNameLevel3Review;
	}

	public void setAdTradeNameLevel3Review(String adTradeNameLevel3Review) {
		this.adTradeNameLevel3Review = adTradeNameLevel3Review;
	}
	
	public AdTag toAdTag() {
		AdTag tag = new AdTag();
		tag.setAdTag(this.getAdTagReview());
		tag.setAdTradeIdLevel3(this.getAdTradeIdLevel3Review());
		tag.setComment(this.getCommentReview());
		tag.setDataType(this.getDataType().intValue());
		tag.setGeneralWuliaoType(this.getWuliaoType());
		tag.setRefId(this.getRefAdId());
		tag.setUpdateTime(this.getUpdateTime());
		tag.setUpdateUser(this.getUpdateUser());
		return tag;
	}

	public ReviewAdTask() {}
	
	public ReviewAdTask(AdTag tag, Date addTime, String addUser) {
		this.setRefAdId(tag.getRefId());
		this.setGroupId(tag.getRefGroupId());
		this.setTaskId(tag.getTaskId().longValue());
		this.setDataType(tag.getDataType().byteValue());
		this.setWuliaoType(tag.getGeneralWuliaoType());
		this.setAddTime(addTime);
		this.setAddUser(addUser);
		this.setUpdateTime(addTime);
		this.setUpdateUser(updateUser);
		this.setAdTag(tag.getAdTag());
		this.setTagTime(tag.getUpdateTime());
		this.setTagUser(tag.getUpdateUser());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tag.getAdTag().length(); i++) {
		    char c = tag.getAdTag().charAt(i);
			sb.append((c == '-' || c == '7') ? c : '0');
		}
		this.setAdTagReview(sb.toString());
		this.setAdTradeIdLevel3(tag.getAdTradeIdLevel3());
		this.setComment(tag.getComment());
	}
	
	public Boolean isReviewRight() {
	    if (!this.getAdTag().equals(this.getAdTagReview())) {
	        return false;
	    }
	    
	    if (!this.getAdTradeIdLevel3().equals(this.getAdTradeIdLevel3Review())) {
	        return false;
	    }
	    
	    return true;
	}
}