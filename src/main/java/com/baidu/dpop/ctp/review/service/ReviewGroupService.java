/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.review.service;

import java.util.Date;
import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.vo.SubmitTagInfo;
import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.bo.ReviewTask;
import com.baidu.dpop.ctp.review.vo.QueryConditionVo;
import com.baidu.dpop.ctp.review.vo.ReviewCountVo;
import com.baidu.dpop.ctp.review.vo.ReviewPageInfoVo;
import com.baidu.dpop.ctp.review.vo.WrongAdDetailVo;
import com.baidu.dpop.ctp.user.bo.User;

public interface ReviewGroupService extends GenericMapperService<ReviewGroup, Long> {

    /**
     * 分配一个新的审核任务题目
     * 
     * @param task 需要分配的审核任务
     * @param user 当前登录用户
     * @return 分配到的组
     */
    public ReviewGroup distributeNewReviewGroup(ReviewTask task);

    /**
     * 根据id获取某个审核group及其修改人的名称
     * 
     * @param groupId 需要获取的id
     * @return 获取到的ReviewGroup
     */
    public ReviewGroup getWithUserNameById(Long groupId);

    /**
     * 获取所有符合条件的group的数量
     * 
     * @param vo 筛选条件
     * @return 查询结果
     */
    public Integer getGroupCount(ReviewCountVo vo);

    /**
     * 获取所有符合条件的ad数量
     * 
     * @param vo 筛选条件
     * @return 数量
     */
    public Integer getAdCount(ReviewCountVo vo);

    /**
     * 获取指定审核任务下的Group信息
     * 
     * @param reviewTaskId 审核任务ID
     * */
    public List<ReviewGroup> getReviewGroupsByReviewTaskId(Long reviewTaskId);

    /**
     * 获取某个用户正在标注的group
     * 
     * @param task 需要获取的task
     * @param user 需要获取的用户
     * @return 此用户正在标注的任务
     */
    public ReviewGroup getStartedGroupByUser(ReviewTask task, User user);

    /**
     * 获取历史审核group
     * 
     * @param vo 筛选条件
     * @return 历史审核group列表
     */
    public List<ReviewGroup> getHistoryGroups(ReviewCountVo vo);

    /**
     * 获取未审核的group列表数据
     * 
     * @param vo 查询条件
     * @return 未审核列表的page InfoList
     * */
    public ReviewPageInfoVo getNotReviewedPageInfo(QueryConditionVo vo);

    /**
     * 获取审核正确的group列表数据
     * 
     * @param vo 查询条件
     * @return 审核正确列表的page InfoList
     * */
    public ReviewPageInfoVo getReviewedRightPageInfo(QueryConditionVo vo);

    /**
     * 获取审核有误的group列表数据
     * 
     * @param vo 查询条件
     * @return 审核正确列表的page InfoList
     * */
    public ReviewPageInfoVo getReviewedWrongPageInfo(QueryConditionVo vo);

    /**
     * 获取指定审核任务下已审核完的创意总数
     * 
     * @param reviewTaskId 审核任务ID
     * */
    public Integer getIsReviewedAdCount(Integer reviewTaskId);

    /**
     * 获取指定审核任务下审核结果（按resultType筛选）
     * 
     * @param reviewTaskId 审核任务ID
     * @param resultType 1:审核正确，2:审核有误，3:全部
     * */
    public List<WrongAdDetailVo> getReviewedAdDetail(Integer reviewTaskId, Integer resultType);

    /**
     * 批量插入ReviewGroup
     * 
     * @param list 需要插入的列表
     * @param taskId 审核任务id
     * @param taskName 审核任务名称
     * @param groupNum 设定的group数量
     * @return 实际插入的数量
     */
    public int batchInsert(List<ReviewGroup> list, Integer taskId, String taskName, Integer groupNum);

    /**
     * 根据选择出的ad列表及ReviewTask创建审核任务，这里负责生成所有的ReviewGroup
     * 
     * @param adList 根据条件选择的ad列表
     * @param task 生成的审核任务
     * @return 创建的ReviewGroup数量
     */
    public int createReviewTask(List<AdTag> adList, ReviewTask task);

    /**
     * 提交审核任务
     * 
     * @param vo 提交的参数，包括列表和reviewGroupId
     */
    public void submitReviewTasks(List<SubmitTagInfo> list, Long groupId, User u);

    /**
     * 放弃某个group
     * 
     * @param group 需要放弃的group
     */
    public void giveUpGroup(ReviewGroup group);

    /**
     * 回收超时的已分配标注group（时间在beginTime之前的）
     * 
     * @Param beginTime 超时基准时间
     */
    public void recycleAssignGroups(Date beginTime);

    /**
     * 删除指定taskId下所有的审核ad
     * 
     * @param taskId 需要删除的taskId
     */
    public void deleteByTaskId(Integer taskId);

}
