/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.review.dao.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.review.bo.ReviewTaskCondition;
import com.baidu.dpop.ctp.review.dao.ReviewTaskConditionDao;
import com.baidu.dpop.ctp.review.dao.mapper.ReviewTaskConditionMapper;
import com.baidu.dpop.ctp.review.vo.BasicTaskQueryVo;

@Repository
public class ReviewTaskConditionDaoImpl extends
		BaseDao<ReviewTaskCondition, Long> implements ReviewTaskConditionDao {

	@Autowired
	private ReviewTaskConditionMapper taskConditionMapper;

	@Override
	public GenericMapper<ReviewTaskCondition, Long> getMapper() {
		return this.taskConditionMapper;
	}

	@Override
	public Set<Long> selectGroupCountCreateReviewTask(BasicTaskQueryVo vo) {
		return this.taskConditionMapper.selectGroupCountCreateReviewTask(vo);
	}

	@Override
	public Long selectLastInsertId() {
		return this.taskConditionMapper.selectLastInsertId();
	}

	@Override
	public List<AdTag> selectAdTagWhenCreateReviewTask(BasicTaskQueryVo vo) {
		return this.taskConditionMapper.selectAdTagWhenCreateReviewTask(vo);
	}
}
