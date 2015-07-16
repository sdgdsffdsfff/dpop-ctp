package com.baidu.dpop.ctp.review.vo;

import java.util.List;
import java.util.Map;

import com.baidu.dpop.ctp.common.utils.CollectionDisplayUtil;
import com.baidu.dpop.ctp.review.bo.ReviewTaskCondition;

public class BasicTaskQueryVo {

	private Integer dataType;
	private Long groupId;
	private Long maxReviewTasks;
	private List<Integer> tasks; // String类型的列表数据，使用逗号分隔
	private List<Integer> wuliaoType;
	private Map<Integer, List<String>> tagInfos;
	private List<Integer> adTradeLevel1;
	private List<Integer> adTradeLevel2;
	private List<Integer> adTradeLevel3;
	
	public BasicTaskQueryVo() {
		this.maxReviewTasks = 300000L;
	}

	public void setArgs(ReviewTaskCondition condition, List<Integer> taskList) {
		this.setTasks(taskList);
		this.setDataType(condition.getDataType());
		this.setWuliaoType(CollectionDisplayUtil.getIntegerListFromString(
				condition.getWuliaoType(), ","));
		this.setAdTradeLevel1(CollectionDisplayUtil.getIntegerListFromString(
				condition.getAdTradeLevel(1), ","));
		this.setAdTradeLevel2(CollectionDisplayUtil.getIntegerListFromString(
				condition.getAdTradeLevel(2), ","));
		this.setAdTradeLevel3(CollectionDisplayUtil.getIntegerListFromString(
				condition.getAdTradeLevel(3), ","));
		this.tagInfos = condition.getTagInfos();
	}

	public Long getMaxReviewTasks() {
		return maxReviewTasks;
	}

	public void setMaxReviewTasks(Long maxReviewTasks) {
		this.maxReviewTasks = maxReviewTasks;
	}
	
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	public Integer getDataType() {
		return dataType;
	}

	public List<Integer> getTasks() {
		return tasks;
	}

	public void setTasks(List<Integer> tasks) {
		this.tasks = tasks;
	}

	public List<Integer> getWuliaoType() {
		return wuliaoType;
	}

	public void setWuliaoType(List<Integer> wuliaoType) {
		this.wuliaoType = wuliaoType;
	}

	public List<Integer> getAdTradeLevel1() {
		return adTradeLevel1;
	}

	public void setAdTradeLevel1(List<Integer> adTradeLevel1) {
		this.adTradeLevel1 = adTradeLevel1;
	}

	public List<Integer> getAdTradeLevel2() {
		return adTradeLevel2;
	}

	public void setAdTradeLevel2(List<Integer> adTradeLevel2) {
		this.adTradeLevel2 = adTradeLevel2;
	}

	public List<Integer> getAdTradeLevel3() {
		return adTradeLevel3;
	}

	public void setAdTradeLevel3(List<Integer> adTradeLevel3) {
		this.adTradeLevel3 = adTradeLevel3;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public boolean getHasAdType() {
		if (adTradeLevel1 != null && adTradeLevel1.size() > 0) {
			return true;
		}

		if (adTradeLevel2 != null && adTradeLevel2.size() > 0) {
			return true;
		}
		
		if (adTradeLevel3 != null && adTradeLevel3.size() > 0) {
			return true;
		}
		return false;
	}
	
	public Map<Integer, List<String>> getTagInfos() {
		return tagInfos;
	}
}
