/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.review.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.mainTask.vo.TaskQueryInfo;
import com.baidu.dpop.ctp.review.bo.ReviewTask;

public interface ReviewTaskDao extends GenericMapperDao<ReviewTask, Long> {

	/**
	 * 获取最后插入的一条记录的id
	 * 
	 * @return 获取到的id
	 */
	public Long selectLastInsertId();

	/**
	 * 根据task的具体信息，返回匹配的任务列表
	 * 
	 * @param taskQueryInfo
	 * @return
	 */
	public List<ReviewTask> selectTaskByTaskInfo(
			@Param("taskQueryInfo") TaskQueryInfo taskQueryInfo);

	/**
	 * 根据任务信息查询任务
	 */
	public List<ReviewTask> getTaskListByTaskInfo(TaskQueryInfo taskQueryInfo);

}
