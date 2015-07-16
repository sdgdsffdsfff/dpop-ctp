/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.review.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.review.bo.ReviewTaskCondition;
import com.baidu.dpop.ctp.review.dao.ReviewTaskConditionDao;
import com.baidu.dpop.ctp.review.service.ReviewTaskConditionService;
import com.baidu.dpop.ctp.review.vo.BasicTaskQueryVo;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;

@Service
public class ReviewTaskConditionServiceImpl extends BaseService<ReviewTaskCondition, Long> implements
        ReviewTaskConditionService {

    @Autowired
    private ReviewTaskConditionDao taskConditionDao;

    @Autowired
    private GeneralTaskService generalTaskService;

    @Override
    public GenericMapperDao<ReviewTaskCondition, Long> getDao() {
        return taskConditionDao;
    }

    public int getGroupNumByQueryCondition(ReviewTaskCondition condition, List<Integer> taskList) {
        BasicTaskQueryVo vo = new BasicTaskQueryVo();
        if (CollectionUtils.isEmpty(taskList)) {
            return 0;
        }

        if (condition == null) {
            condition = new ReviewTaskCondition();
            condition.setDataType(GroupDataType.ALL.getId().intValue());
        }

        int result = 0;
        vo.setArgs(condition, taskList);
        result = taskConditionDao.selectGroupCountCreateReviewTask(vo).size();
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public Long insertReturnId(ReviewTaskCondition record) {
        taskConditionDao.insertSelective(record);
        return this.taskConditionDao.selectLastInsertId();
    }

    @Override
    public List<AdTag> getAdTagWhenCreateReviewTask(ReviewTaskCondition condition, List<Integer> taskList) {
        BasicTaskQueryVo vo = new BasicTaskQueryVo();
        if (condition == null) {
            condition = new ReviewTaskCondition();
            condition.setDataType(GroupDataType.ALL.getId().intValue());
        }

        vo.setArgs(condition, taskList);
        return taskConditionDao.selectAdTagWhenCreateReviewTask(vo);
    }

    @Override
    public String downloadWhenCreate(ReviewTaskCondition condition, Integer groupNum, List<Integer> taskList,
            Byte taskType) {
        BasicTaskQueryVo vo = new BasicTaskQueryVo();
        if (condition == null) {
            condition = new ReviewTaskCondition();
            condition.setDataType(GroupDataType.ALL.getId().intValue());
        }

        vo.setArgs(condition, taskList);
        List<AdTag> tagList = taskConditionDao.selectAdTagWhenCreateReviewTask(vo);
        return generalTaskService.downloadWhenCreateReviewTask(tagList, groupNum, taskType);
    }
}
