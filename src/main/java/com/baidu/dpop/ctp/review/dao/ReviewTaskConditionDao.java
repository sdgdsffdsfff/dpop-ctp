
/*
* Copyright 2014 baidu dpop
* All right reserved.
*
*/

package com.baidu.dpop.ctp.review.dao;

import java.util.List;
import java.util.Set;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.review.bo.ReviewTaskCondition;
import com.baidu.dpop.ctp.review.vo.BasicTaskQueryVo;

public interface ReviewTaskConditionDao extends GenericMapperDao<ReviewTaskCondition, Long> {
	
	/**
	 * 获取符合条件的标注group数量
	 * 
	 * @param vo
	 *            查询条件
	 * @return 包含符合条件ad的group的主键id组成的集合
	 */
	Set<Long> selectGroupCountCreateReviewTask(BasicTaskQueryVo vo);
	
	/**
	 * 获取所有符合条件的AdTag
	 * 
	 * @param vo
	 *            查询条件
	 * @return 获取到的列表
	 */
	List<AdTag> selectAdTagWhenCreateReviewTask(BasicTaskQueryVo vo);
	
	/**
	 * 选择最后插入记录的id
	 * @return 最后插入记录的id
	 */
	public Long selectLastInsertId();
}
