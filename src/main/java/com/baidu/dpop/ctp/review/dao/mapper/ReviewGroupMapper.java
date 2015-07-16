/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.review.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.vo.QueryConditionVo;
import com.baidu.dpop.ctp.review.vo.ReviewCountVo;
import com.baidu.dpop.ctp.review.vo.ReviewInfoItem;
import com.baidu.dpop.ctp.review.vo.WrongAdDetailVo;

public interface ReviewGroupMapper extends GenericMapper<ReviewGroup, Long> {

    // SELECT--------------------------------------------------------

    /**
     * 根据指定的taskId获取一个group
     * 
     * @param taskId 需要获取的审核任务id
     * @param rand 随机参数
     * @return 获取到的group
     */
    public ReviewGroup selectRandomGroup(@Param("taskId") Integer taskId, @Param("rand") Integer rand);
    
    /**
     * 根据id获取某个审核group及其修改人的名称
     * 
     * @param groupId 需要获取的id
     * @return 获取到的ReviewGroup
     */
    public ReviewGroup selectWithUserNameById(Long groupId);

    /**
     * 获取所有还未开始的group数量
     * 
     * @param taskId 需要获取的审核任务id
     * @return 未开始的group数量
     */
    public int selectUnstartCount(Integer taskId);
    
    /**
     * 获取某一task中所有未完成Group的个数
     * 
     * @param taskId 需要获取的taskId
     * @return 此task中所有未完成的Group的个数
     */
    public Integer selectUnfinishedCount(Integer taskId);
    
    /**
     * 获取所有符合条件的group的数量
     * 
     * @param vo 筛选条件
     * @return 数量
     */
    public Integer selectGroupCount(ReviewCountVo vo);
    
    /**
     * 获取所有符合条件的ad数量
     * 
     * @param vo 筛选条件
     * @return 数量
     */
    public Integer selectAdCount(ReviewCountVo vo);
    
    /**
     * 根据审核任务id获取所有审核group
     * 
     * @param reviewTaskId 需要获取的审核任务id
     * @return 获取到的数据列表
     */
    public List<ReviewGroup> selectReviewGroupsByReviewTaskId(Long reviewTaskId);
    
    /**
     * 获取该用户未答的最早一道题目
     * 
     * @param vo 查询条件，包括用户id与task_id
     * @return 获取到的题目
     */
    public ReviewGroup selectStartedGroupByUser(@Param("taskId") Integer taskId, @Param("userId") Integer userId);
    
    /**
     * 获取历史审核group
     * 
     * @param vo 筛选条件
     * @return 历史审核group列表
     */
    public List<ReviewGroup> selectHistoryGroups(ReviewCountVo vo);
    
    /**
     * 获取未审核的group列表数据
     * 
     * @param vo 查询条件
     * @return 未审核列表的page InfoList
     * */
    public List<ReviewInfoItem> selectNotReviewedPageInfo(QueryConditionVo conditionVo);
    
    /**
     * 获取审核正确的group列表数据
     * 
     * @param vo 查询条件
     * @return 审核正确列表的page InfoList
     * */
    public List<ReviewInfoItem> selectReviewedRightPageInfo(QueryConditionVo conditionVo);
    
    /**
     * 获取审核有误的group列表数据
     * 
     * @param vo 查询条件
     * @return 审核正确列表的page InfoList
     * */
    public List<ReviewInfoItem> selectReviewedWrongPageInfo(QueryConditionVo conditionVo);
    
    /**
     * 获取指定审核任务下已审核完的创意总数
     * 
     * @param reviewTaskId 审核任务ID
     * */
    public Integer selectIsReviewedAdCount(Integer reviewTaskId);
    
    /**
     * 获取指定审核任务下审核结果（按resultType筛选）
     * 
     * @param reviewTaskId 审核任务ID
     * @param resultType 1:审核正确，2:审核有误，3:全部
     * */
    public List<WrongAdDetailVo> selectReviewedAdDetail(@Param("reviewTaskId") Integer reviewTaskId,
            @Param("resultType") Integer resultType);
    
    // INSERT--------------------------------------------------------

    /**
     * 批量插入数据
     * 
     * @param list 需要插入的列表
     * @return 实际插入的数量
     */
    public int batchInsert(List<ReviewGroup> list);

    // UPDATE--------------------------------------------------------

    /**
     * 回收超时的已分配标注group（时间在beginTime之前的）
     * 
     * @Param beginTime 超时基准时间
     * */
    public void recycleAssignGroups(@Param("beginTime") Date beginTime);

    // DELETE--------------------------------------------------------

    /**
     * 删除指定taskId下所有的审核ad
     * 
     * @param taskId 需要删除的taskId
     */
    public void deleteByTaskId(Integer taskId);

}