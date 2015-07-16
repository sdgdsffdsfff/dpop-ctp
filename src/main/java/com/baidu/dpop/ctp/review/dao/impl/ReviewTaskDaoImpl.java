/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.review.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.mainTask.vo.TaskQueryInfo;
import com.baidu.dpop.ctp.review.bo.ReviewTask;
import com.baidu.dpop.ctp.review.dao.ReviewTaskDao;
import com.baidu.dpop.ctp.review.dao.mapper.ReviewTaskMapper;

@Repository
public class ReviewTaskDaoImpl extends BaseDao<ReviewTask, Long> implements
		ReviewTaskDao {

	@Autowired
	private ReviewTaskMapper mainTaskMapper;

	@Override
	public GenericMapper<ReviewTask, Long> getMapper() {
		return this.mainTaskMapper;
	}

	@Override
	public Long selectLastInsertId() {
		return this.mainTaskMapper.selectLastInsertId();
	}

	@Override
	public List<ReviewTask> selectTaskByTaskInfo(TaskQueryInfo taskQueryInfo) {
		return this.mainTaskMapper.selectTaskByTaskInfo(taskQueryInfo);
	}

	@Override
	public List<ReviewTask> getTaskListByTaskInfo(TaskQueryInfo taskQueryInfo) {
		return this.mainTaskMapper.selectTaskByTaskInfo(taskQueryInfo);
	}

}
