package com.baidu.dpop.ctp.review.vo;

import java.util.List;

import com.baidu.dpop.ctp.review.bo.ReviewAdTask;

public class SubmitReviewTaskVo {
	
	private List<ReviewAdTask> basicTasks;
	private Long id;
	
	public List<ReviewAdTask> getBasicTasks() {
		return basicTasks;
	}
	
	public void setBasicTasks(List<ReviewAdTask> basicTasks) {
		this.basicTasks = basicTasks;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
