/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.review.service;

import java.util.Date;
import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.dpop.ctp.review.bo.ReviewAdTask;
import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.bo.ReviewTask;
import com.baidu.dpop.ctp.review.vo.DistributeReviewGroupResult;
import com.baidu.dpop.ctp.user.bo.User;

public interface ReviewAdTaskService extends GenericMapperService<ReviewAdTask, Long> {

    /**
     * 获取分配的审核任务
     * 
     * @param groupIdReview 审核groupId
     * @param dataType 数据类型
     * @param taskType 任务类型，明审/盲审
     * @return 分配结果
     * @see DistributeReviewGroupResult
     */
    public DistributeReviewGroupResult getReviewTasksByReviewGroup(ReviewGroup group, ReviewTask task);

    /**
     * 根据审核任务id选取reviewTask
     * 
     * @param taskIdReview 审核任务id
     * @return 选取到的ReviewAdTask列表
     */
    public List<ReviewAdTask> getReviewTasksByTaskId(Integer taskIdReview);

    /**
     * 根据ref_ad_id批量获取审核任务
     * 
     * @param list 批量获取的ref_ad_id列表
     * @return 获取到的审核ad列表
     */
    public List<ReviewAdTask> batchGetByRefAdId(List<Long> list);

    /**
     * 获取审核错误信息
     * 
     * @param reviewGroupIdList 需要获取的group列表
     * @return 错误信息
     */
    public List<ReviewAdTask> getReviewWrongDetail(List<Long> reviewGroupIdList);

    /**
     * 下载给定taskList中所有task的审核数据
     * 
     * @param list 需要下载的列表
     * @return 生成的文件地址
     */
    public String download(List<Integer> list);

    /**
     * 批量插入新数据
     * 
     * @param list 待插入的列表
     * @return 实际插入的数量
     */
    public int batchInsertTasks(List<ReviewAdTask> list);

    /**
     * 在向上游入库成功后调用 更新数据库，将已入库的标志位置为1
     * 
     * @param taskId 需要提交的taskId
     * @param dataType 需要提交的数据类型
     * @param ids adId列表
     */
    public void assignedAdTags(Integer taskId, Integer dataType, List<Long> ids);

    /**
     * 提交审核任务
     * 
     * @param list 需要提交的列表
     */
    public void submitReviewTasks(List<ReviewAdTask> list, User updateUser, Date updateTime);

    /**
     * 删除指定taskId下所有的审核ad
     * 
     * @param taskId 需要删除的taskId
     */
    public void deleteByTaskId(Integer taskId);

}
