package com.baidu.dpop.ctp.review.vo;

import java.util.List;

import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.bo.ReviewTask;

public class DistributeReviewGroupResult {
	
	private ReviewTask task;
	private ReviewGroup group;
	
	private Integer isReview;		//是否审核，0-不是，1-是
	private Integer historyAdNum;	//已审核任务数量
	private List<ReviewGroup> historyGroup;	//已审核任务列表
	private Integer groupNumAll;	//所有审核group数量
	private Integer groupNumDone;	//已完成审核group数量
	private Integer adNumAll;		//所有审核ad数量
	private Integer adNumDone;		//已完成审核ad数量
	private Long accountId;			//账户id	
	private String companyName;		//公司名
	private String companyUrl;		//公司网址
	private List<PresentedReviewTask> list;			//具体数据
	
	public ReviewTask getTask() {
		return task;
	}
	
	public void setTask(ReviewTask task) {
		this.task = task;
	}
	
	public ReviewGroup getGroup() {
		return group;
	}
	
	public void setGroup(ReviewGroup group) {
		this.group = group;
	}
	
	public Integer getIsReview() {
		return isReview;
	}
	
	public void setIsReview(Integer isReview) {
		this.isReview = isReview;
	}
	
	public Integer getHistoryAdNum() {
		return historyAdNum;
	}
	
	public void setHistoryAdNum(Integer historyAdNum) {
		this.historyAdNum = historyAdNum;
	}
	
	public List<ReviewGroup> getHistoryGroup() {
		return historyGroup;
	}
	
	public void setHistoryGroup(List<ReviewGroup> historyGroup) {
		this.historyGroup = historyGroup;
	}
	
	public Integer getGroupNumAll() {
		return groupNumAll;
	}
	
	public void setGroupNumAll(Integer groupNumAll) {
		this.groupNumAll = groupNumAll;
	}
	
	public Integer getGroupNumDone() {
		return groupNumDone;
	}
	
	public void setGroupNumDone(Integer groupNumDone) {
		this.groupNumDone = groupNumDone;
	}
	
	public Integer getAdNumAll() {
		return adNumAll;
	}
	
	public void setAdNumAll(Integer adNumAll) {
		this.adNumAll = adNumAll;
	}
	
	public Integer getAdNumDone() {
		return adNumDone;
	}
	
	public void setAdNumDone(Integer adNumDone) {
		this.adNumDone = adNumDone;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCompanyUrl() {
		return companyUrl;
	}
	
	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}
	
	public List<PresentedReviewTask> getList() {
		return list;
	}
	
	public void setList(List<PresentedReviewTask> list) {
		this.list = list;
	}
}
