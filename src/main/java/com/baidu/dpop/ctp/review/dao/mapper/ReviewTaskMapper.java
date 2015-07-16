/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.review.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.mainTask.vo.TaskQueryInfo;
import com.baidu.dpop.ctp.review.bo.ReviewTask;

public interface ReviewTaskMapper extends GenericMapper<ReviewTask, Long> {

	/**
	 * 获取上次插入的数据的id
	 * 
	 * @return 上次执行插入的数据的Id
	 */
	public Long selectLastInsertId();

	/**
	 * 根据task的具体信息，返回匹配的任务列表
	 * 
	 * @param taskQueryInfo
	 *            查询条件
	 * @return 得到的列表
	 */
	public List<ReviewTask> selectTaskByTaskInfo(
			@Param("taskQueryInfo") TaskQueryInfo taskQueryInfo);

}