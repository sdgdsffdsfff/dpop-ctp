
/*
* Copyright 2014 baidu dpop
* All right reserved.
*
*/
package com.baidu.dpop.ctp.review.bo;

/**
* 
*/	
public class ReviewTask extends ReviewTaskBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5396709599749757315L;
	
	private ReviewTaskCondition reviewTaskCondition;
	
	public ReviewTaskCondition getReviewTaskCondition() {
		return reviewTaskCondition;
	}
	
	public void setReviewTaskCondition(ReviewTaskCondition reviewTaskCondition) {
		this.reviewTaskCondition = reviewTaskCondition;
	}
  
}