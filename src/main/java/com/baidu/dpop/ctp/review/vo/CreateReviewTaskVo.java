package com.baidu.dpop.ctp.review.vo;

import java.io.Serializable;
import java.util.List;

import com.baidu.dpop.ctp.review.bo.ReviewTaskCondition;

public class CreateReviewTaskVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3646647314971031487L;
	
	private String taskName;
	private Boolean isBlind;
	private Integer groupNum;
	private Integer moduserLevel;
	private List<Integer> taskList;
	private Integer conditionType;
	private Byte taskType;
	private ReviewTaskCondition reviewTaskCondition;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Boolean getIsBlind() {
		return isBlind;
	}

	public void setIsBlind(Boolean isBlind) {
		this.isBlind = isBlind;
	}

	public Integer getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(Integer groupNum) {
		this.groupNum = groupNum;
	}

	public Integer getModuserLevel() {
		return moduserLevel;
	}

	public void setModuserLevel(Integer moduserLevel) {
		this.moduserLevel = moduserLevel;
	}

	public List<Integer> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Integer> taskList) {
		this.taskList = taskList;
	}

	public Integer getConditionType() {
		return conditionType;
	}

	public void setConditionType(Integer conditionType) {
		this.conditionType = conditionType;
	}
	
	public Byte getTaskType() {
	    return taskType;
	}
	
	public void setTaskType(Byte taskType) {
	    this.taskType = taskType;
	}

	public ReviewTaskCondition getReviewTaskCondition() {
		return reviewTaskCondition;
	}

	public void setReviewTaskCondition(ReviewTaskCondition reviewTaskCondition) {
		this.reviewTaskCondition = reviewTaskCondition;
	}
}
