package com.baidu.dpop.ctp.review.vo;

public class ReviewCountVo {
	
	private Long reviewTaskId;
	private Byte status;
	private Integer userId;
	
	public Long getReviewTaskId() {
		return reviewTaskId;
	}
	
	public void setReviewTaskId(Long reviewTaskId) {
		this.reviewTaskId = reviewTaskId;
	}
	
	public Byte getStatus() {
		return status;
	}
	
	public void setStatus(Byte status) {
		this.status = status;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
