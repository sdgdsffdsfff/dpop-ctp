/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.group.dao.mapper;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.vo.GroupDownloadInfo;
import com.baidu.dpop.ctp.group.vo.SubmitInfoGetVo;
import com.baidu.dpop.ctp.group.vo.TrendInfoGetVo;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.statistics.vo.TrendStatisticsItem;
import com.baidu.dpop.ctp.statistics.vo.UserStatisticsItem;
import com.baidu.dpop.ctp.task.vo.TagFollowInfoVo;

public interface GroupMapper extends GenericMapper<Group, Long> {

    // SELECT--------------------------------------------------------

    /**
     * 根据task_id, group_id, data_type选取一个group
     * 
     * @param group 包含了选取参数
     * @return 选择结果
     */
    public Group selectGroup(Group group);

    /**
     * 选择一个随机的group，依据优先级排列，选取优先级最高的前200个group中的第rand个
     * 
     * @param taskId task_id
     * @param rand 随机参数
     * @return 选取到的group
     */
    public Group selectRandomGroup(@Param("taskId") Integer taskId, @Param("rand") Integer rand);

    /**
     * 获取某一task中还没有开始的group的数量
     * 
     * @param taskId 需要获取的task_id
     * @return 获取到的个数
     */
    public Integer selectUnstartCount(Integer taskId);

    /**
     * 获取某一task中还没有完成的group的数量
     * 
     * @param taskId 需要获取的task_id
     * @return 获取到的个数
     */
    public Integer selectUnfinishedCount(Integer taskId);

    /**
     * 根据task_id,group_id,data_type的三元组选取group
     * 
     * @param list 三元组列表
     * @return 选取到的结果
     * @see GroupCountVo
     */
    public List<Group> selectTestedGroup(List<GroupCountVo> list);

    /**
     * 根据某group的主键id选取此group所属的task的状态
     * 
     * @param id 需要获取的group的主键Id
     * @return g所属的task的状态
     */
    public Byte selectTaskStatusById(Long id);

    /**
     * 获取该用户在某一task中未答的最早一道题目
     * 
     * @param taskId 需要查询的taskId
     * @param userId 需要查询的用户id
     * @return 查询到的group
     */
    public Group selectStartedGroupByUser(@Param("taskId") Integer taskId, @Param("userId") Integer userId);

    /**
     * 获取某一用户名下的隶属于某一task的所有group
     * 
     * @param taskId 需要查询的taskId
     * @param userId 需要查询的用户id
     * @return 查询到的group
     */
    public List<Group> selectHistoryGroups(@Param("taskId") Integer taskId, @Param("userId") Integer userId);

    /**
     * 获取指定查询条件下的group信息
     * 
     * @param taskId group所属的task的id
     * @param status group的状态
     * @param isNotFinished 是否已完成
     * @return 依据条件查询到的group列表
     */
    public List<Group> selectGroupListByCondition(@Param("taskId") Integer taskId, @Param("status") Integer status,
            @Param("isNotFinished") Integer isNotFinished);

    /**
     * 获取标注趋势信息
     * 
     * @param vo 查询参数
     * @return 查询结果
     * @see TrendInfoGetVo
     */
    public TrendStatisticsItem selectTrendInfo(TrendInfoGetVo vo);

    /**
     * 获取指定task下统计信息中人员标注情况
     * 
     * @param taskId 需要获取的任务id
     * @return 获取到的结果
     */
    public List<UserStatisticsItem> selectUserStatisticsInfo(Integer taskId);

    /**
     * 获取指定任务的用户跟进中的标注任务情况
     * 
     * @param taskId 标注任务ID
     * */
    public List<TagFollowInfoVo> selectTagFollowInfoList(@Param("taskId") Integer taskId);

    /**
     * 获取group的提交时间信息
     * 
     * @param vo 参数列表
     * @return 获取到的数据
     */
    public Set<GroupDownloadInfo> selectGroupDownloadInfo(SubmitInfoGetVo vo);

    /**
     * 批量选取Group
     * 
     * @param list id列表
     * @return 选取到的Group列表
     */
    public List<Group> batchSelect(List<Long> list);

    // INSERT--------------------------------------------------------

    /**
     * 批量插入group
     * 
     * @param groups 需要插入的group列表
     */
    public void batchInsert(List<Group> groups);

    // UPDATE--------------------------------------------------------

    /**
     * 回收超时的已分配标注group（时间在beginTime之前的）
     * 
     * @Param beginTime 超时基准时间
     * */
    public void recycleAssignGroups(@Param("beginTime") Date beginTime);

    /**
     * 批量更新group的优先级与ad数量
     * 
     * @param list 更新列表
     */
    public void batchUpdate(List<Group> list);

    // DELETE--------------------------------------------------------

    /**
     * 删除某一task下的所有group
     * 
     * @param taskId 需要删除的taskId
     */
    public void deleteGroupByTaskId(Integer taskId);
}