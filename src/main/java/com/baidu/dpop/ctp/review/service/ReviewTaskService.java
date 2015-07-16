/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.review.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.dpop.ctp.mainTask.vo.TaskQueryInfo;
import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.bo.ReviewTask;
import com.baidu.dpop.ctp.review.bo.ReviewTaskCondition;
import com.baidu.dpop.ctp.review.vo.DistributeReviewGroupResult;
import com.baidu.dpop.ctp.user.bo.User;

public interface ReviewTaskService extends
		GenericMapperService<ReviewTask, Long> {

	public ReviewTask createNewReviewTask(ReviewTask task,
			ReviewTaskCondition condition, List<Integer> taskList);
	
	public Long insertReturnId(ReviewTask task);

	/**
	 * 根据任务信息查询任务列表
	 * 
	 * @param page
	 *            页码
	 * @param size
	 *            页面数量
	 * @param taskQueryInfo
	 *            查询条件
	 * @return
	 */
	public List<ReviewTask> getTaskListByTaskInfo(Integer page, Integer size,
			TaskQueryInfo taskQueryInfo);
	
	public DistributeReviewGroupResult getTasksByGroup(ReviewTask task, ReviewGroup group, User user);
	
	public void deleteByTaskId(List<Integer> ids);
}
