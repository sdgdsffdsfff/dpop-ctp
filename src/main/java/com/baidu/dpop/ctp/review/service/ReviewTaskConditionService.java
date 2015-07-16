/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.review.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.review.bo.ReviewTaskCondition;

public interface ReviewTaskConditionService extends
		GenericMapperService<ReviewTaskCondition, Long> {

	/**
	 * 在创建审核任务时获取包含符合条件ad的group的数量
	 * 
	 * @param condition
	 *            查询条件
	 * @param taskList
	 *            任务列表
	 * @return 获取到的group的数量
	 */
	public int getGroupNumByQueryCondition(ReviewTaskCondition condition,
			List<Integer> taskList);

	/**
	 * 获取所有符合条件的AdTag
	 * 
	 * @param condition
	 *            查询条件
	 * @param taskList
	 *            任务列表
	 * @return 获取到的所有adTag
	 */
	List<AdTag> getAdTagWhenCreateReviewTask(ReviewTaskCondition condition,
			List<Integer> taskList);

	/**
	 * 插入一个ReviewTaskCondition并返回其id
	 * 
	 * @param record
	 *            需要插入的记录
	 * @return 新插入记录的id
	 */
	public Long insertReturnId(ReviewTaskCondition record);

	/**
	 * 在创建审核任务时生成文件并下载
	 * 
	 * @param condition
	 *            生成审核任务的选择条件
	 * @param taskList
	 *            选取的任务列表
	 * @return 生成的文件地址
	 */
	public String downloadWhenCreate(ReviewTaskCondition condition, Integer groupNum,
			List<Integer> taskList, Byte taskType);
}
