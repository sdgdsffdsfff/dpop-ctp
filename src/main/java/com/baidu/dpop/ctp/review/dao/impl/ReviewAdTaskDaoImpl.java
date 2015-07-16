/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.review.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.adtag.vo.AdTagAssignedVo;
import com.baidu.dpop.ctp.review.bo.ReviewAdTask;
import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.dao.ReviewAdTaskDao;
import com.baidu.dpop.ctp.review.dao.mapper.ReviewAdTaskMapper;
import com.baidu.dpop.ctp.review.vo.ReviewAdTaskSubmitVo;

@Repository
public class ReviewAdTaskDaoImpl extends BaseDao<ReviewAdTask, Long> implements ReviewAdTaskDao {

    @Autowired
    private ReviewAdTaskMapper taskMapper;

    @Override
    public GenericMapper<ReviewAdTask, Long> getMapper() {
        return this.taskMapper;
    }

    @Override
    public List<ReviewAdTask> selectReviewTasksByReviewGroup(ReviewGroup group) {
        Assert.notNull(group);
        return this.taskMapper.selectReviewTasksByReviewGroup(group);
    }

    @Override
    public List<ReviewAdTask> selectReviewTasksByTaskId(Integer taskIdReview) {
        Assert.notNull(taskIdReview);
        return this.taskMapper.selectReviewTasksByTaskId(taskIdReview);
    }

    @Override
    public List<ReviewAdTask> batchSelectByRefAdId(List<Long> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<ReviewAdTask>();
        }
        return this.taskMapper.batchSelectByRefAdId(list);
    }

    @Override
    public List<ReviewAdTask> selectReviewWrongDetail(List<Long> reviewGroupIdList) {
        if (CollectionUtils.isEmpty(reviewGroupIdList)) {
            return new ArrayList<ReviewAdTask>();
        }
        return this.taskMapper.selectReviewWrongDetail(reviewGroupIdList);
    }

    @Override
    public int batchInsertTasks(List<ReviewAdTask> list) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        return this.taskMapper.batchInsertTasks(list);
    }
    
    @Override
    public void updateAssigned(AdTagAssignedVo vo) {
        Assert.notNull(vo);
        this.taskMapper.updateAssigned(vo);
    }

    @Override
    public void batchUpdate(ReviewAdTaskSubmitVo vo) {
        Assert.notNull(vo);
        this.taskMapper.batchUpdate(vo);
    }

    @Override
    public void deleteByTaskId(Integer taskId) {
        Assert.notNull(taskId);
        this.taskMapper.deleteByTaskId(taskId);
    }
}
