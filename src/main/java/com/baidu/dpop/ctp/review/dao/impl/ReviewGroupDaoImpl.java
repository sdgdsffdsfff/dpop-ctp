/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.review.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.dao.ReviewGroupDao;
import com.baidu.dpop.ctp.review.dao.mapper.ReviewGroupMapper;
import com.baidu.dpop.ctp.review.vo.QueryConditionVo;
import com.baidu.dpop.ctp.review.vo.ReviewCountVo;
import com.baidu.dpop.ctp.review.vo.ReviewInfoItem;
import com.baidu.dpop.ctp.review.vo.WrongAdDetailVo;

@Repository
public class ReviewGroupDaoImpl extends BaseDao<ReviewGroup, Long> implements ReviewGroupDao {

    @Autowired
    private ReviewGroupMapper groupMapper;

    @Override
    public GenericMapper<ReviewGroup, Long> getMapper() {
        return this.groupMapper;
    }

    @Override
    public ReviewGroup selectRandomGroup(Integer taskId, Integer rand) {
        Assert.notNull(taskId);
        Assert.notNull(rand);
        return this.groupMapper.selectRandomGroup(taskId, rand);
    }

    @Override
    public ReviewGroup selectWithUserNameById(Long groupId) {
        Assert.notNull(groupId);
        return this.groupMapper.selectWithUserNameById(groupId);
    }

    @Override
    public int selectUnstartCount(Integer taskId) {
        Assert.notNull(taskId);
        return this.groupMapper.selectUnstartCount(taskId);
    }

    @Override
    public Integer selectUnfinishedCount(Integer taskId) {
        Assert.notNull(taskId);
        return this.groupMapper.selectUnfinishedCount(taskId);
    }

    @Override
    public Integer selectGroupCount(ReviewCountVo vo) {
        Assert.notNull(vo);
        return this.groupMapper.selectGroupCount(vo);
    }

    @Override
    public Integer selectAdCount(ReviewCountVo vo) {
        Assert.notNull(vo);
        return this.groupMapper.selectAdCount(vo);
    }

    @Override
    public List<ReviewGroup> selectReviewGroupsByReviewTaskId(Long reviewTaskId) {
        Assert.notNull(reviewTaskId);
        return this.groupMapper.selectReviewGroupsByReviewTaskId(reviewTaskId);
    }

    @Override
    public ReviewGroup selectStartedGroupByUser(Integer taskId, Integer userId) {
        Assert.notNull(taskId);
        Assert.notNull(userId);
        return this.groupMapper.selectStartedGroupByUser(taskId, userId);
    }

    @Override
    public List<ReviewGroup> selectHistoryGroups(ReviewCountVo vo) {
        Assert.notNull(vo);
        return this.groupMapper.selectHistoryGroups(vo);
    }

    @Override
    public List<ReviewInfoItem> selectNotReviewedPageInfo(QueryConditionVo conditionVo) {
        Assert.notNull(conditionVo);
        return this.groupMapper.selectNotReviewedPageInfo(conditionVo);
    }

    @Override
    public List<ReviewInfoItem> selectReviewedRightPageInfo(QueryConditionVo conditionVo) {
        Assert.notNull(conditionVo);
        return this.groupMapper.selectReviewedRightPageInfo(conditionVo);
    }

    @Override
    public List<ReviewInfoItem> selectReviewedWrongPageInfo(QueryConditionVo conditionVo) {
        Assert.notNull(conditionVo);
        return this.groupMapper.selectReviewedWrongPageInfo(conditionVo);
    }

    @Override
    public Integer selectIsReviewedAdCount(Integer reviewTaskId) {
        Assert.notNull(reviewTaskId);
        return this.groupMapper.selectIsReviewedAdCount(reviewTaskId);
    }

    @Override
    public List<WrongAdDetailVo> selectReviewedAdDetail(Integer reviewTaskId, Integer resultType) {
        Assert.notNull(reviewTaskId);
        Assert.notNull(resultType);
        return this.groupMapper.selectReviewedAdDetail(reviewTaskId, resultType);
    }

    @Override
    public int batchInsert(List<ReviewGroup> list) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        return this.groupMapper.batchInsert(list);
    }

    @Override
    public void recycleAssignGroups(Date beginTime) {
        Assert.notNull(beginTime);
        this.groupMapper.recycleAssignGroups(beginTime);
    }

    @Override
    public void deleteByTaskId(Integer taskId) {
        Assert.notNull(taskId);
        this.groupMapper.deleteByTaskId(taskId);
    }

}
