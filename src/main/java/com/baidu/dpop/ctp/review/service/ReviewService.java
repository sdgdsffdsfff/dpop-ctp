package com.baidu.dpop.ctp.review.service;

import com.baidu.dpop.ctp.review.vo.QueryConditionVo;
import com.baidu.dpop.ctp.review.vo.ReviewPageInfoVo;
import com.baidu.dpop.ctp.review.vo.ReviewTagStatisticsVo;
import com.baidu.dpop.ctp.review.vo.ReviewTaskAccuracyVo;
import com.baidu.dpop.ctp.review.vo.ReviewTaskBasicInfoVo;

/**
 * 审核相关service
 * 
 * @author cgd
 * @date 2015年3月29日 下午12:26:58
 */
public interface ReviewService {

    /**
     * 获取审核详情的基本信息
     * 
     * @param taskId 任务ID
     * */
    public ReviewTaskBasicInfoVo getBasicInfo(Integer taskId);

    /**
     * 获取审核列表Page信息
     * */
    public ReviewPageInfoVo getPageInfo(QueryConditionVo vo);

    /**
     * 获取统计页审核任务的准确率信息
     * 
     * @param reviewTaskId 审核任务ID
     * */
    public ReviewTaskAccuracyVo getReviewTaskAccuracy(Integer reviewTaskId);

    /**
     * 获取统计页标签review统计信息
     * 
     * @param reviewTaskId 审核任务ID
     * */
    public ReviewTagStatisticsVo getReviewTagAccuracy(Integer reviewTaskId);

}
