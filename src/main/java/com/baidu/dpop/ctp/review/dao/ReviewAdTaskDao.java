/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.review.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.adtag.vo.AdTagAssignedVo;
import com.baidu.dpop.ctp.review.bo.ReviewAdTask;
import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.vo.ReviewAdTaskSubmitVo;

public interface ReviewAdTaskDao extends GenericMapperDao<ReviewAdTask, Long> {

 // SELECT--------------------------------------------------------

    /**
     * 根据审核组选取此组下的所有审核ad
     * 
     * @param group 需要被选取的审核组
     * @return 审核组中的所有ad
     */
    public List<ReviewAdTask> selectReviewTasksByReviewGroup(ReviewGroup group);

    /**
     * 根据审核任务id选取reviewTask
     * 
     * @param taskIdReview 审核任务id
     * @return 选取到的ReviewAdTask列表
     */
    public List<ReviewAdTask> selectReviewTasksByTaskId(Integer taskIdReview);

    /**
     * 根据ref_ad_id批量获取审核Ad
     * 
     * @param list 需要获取的列表
     * @return 获取到的列表
     */
    public List<ReviewAdTask> batchSelectByRefAdId(List<Long> list);

    /**
     * 获取审核有误的创意明细
     * 
     * @param reviewGroupIdList group列表
     * @return 审核有误的创意明细
     */
    public List<ReviewAdTask> selectReviewWrongDetail(@Param("reviewGroupIdList") List<Long> reviewGroupIdList);

    // INSERT--------------------------------------------------------

    /**
     * 批量插入新的审核ad
     * 
     * @param list 需要插入的列表
     * @return 实际插入个数
     */
    public int batchInsertTasks(List<ReviewAdTask> list);

    // UPDATE--------------------------------------------------------
    
    /**
     * 记录是否入库成功
     * 
     * @param vo 更新条件，主要包含task_id，dataType，更新的id列表
     */
    public void updateAssigned(AdTagAssignedVo vo);

    /**
     * 批量更新审核任务
     * 
     * @param list 提交的数据列表
     */
    public void batchUpdate(ReviewAdTaskSubmitVo vo);

    // DELETE--------------------------------------------------------

    /**
     * 删除指定taskId下所有的审核ad
     * 
     * @param taskId 需要删除的taskId
     */
    public void deleteByTaskId(Integer taskId);
}
