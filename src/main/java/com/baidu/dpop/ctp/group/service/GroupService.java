/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.group.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.dpop.ctp.adtag.vo.SubmitTagInfo;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.vo.DistributeGroupResult;
import com.baidu.dpop.ctp.group.vo.SubmitInfoGetVo;
import com.baidu.dpop.ctp.group.vo.TrendInfoGetVo;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.statistics.vo.TrendStatisticsItem;
import com.baidu.dpop.ctp.statistics.vo.UserStatisticsItem;
import com.baidu.dpop.ctp.task.vo.TagFollowInfoVo;
import com.baidu.dpop.ctp.user.bo.User;

public interface GroupService extends GenericMapperService<Group, Long> {

    /**
     * 根据task_id, group_id, data_type选取一个group
     * 
     * @param group 包含了选取参数
     * @return 选择结果
     */
    public Group getGroup(Group group);

    /**
     * 自动分配一个group，系统将选取优先级前200的随机一个group分配
     * 
     * @param task 需要分配的task
     * @return group bo
     */
    public Group distributeNewGroup(Task task);

    /**
     * 根据分配的group获取分配信息
     * 
     * @param task 被分配的task
     * @param group 被分配的group
     * @param user 分配操作请求者
     * @return 分配结果
     */
    public DistributeGroupResult getTasksByGroup(Task task, Group group, User user);
    
    /**
     * 获取某一task下还没有完成的group的数量
     * 
     * @param taskId 需要获取的taskId
     * @return 获取到的数量
     */
    public Integer getUnfinishedCount(Integer taskId);

    /**
     * 获取已经插入的所有group
     * 
     * @param list 获取条件列表，包含task_id,group_id,data_type
     * @return map形式，以GroupCountVo作key
     * @see GroupCountVo
     */
    public Map<GroupCountVo, Group> getTestedGroup(List<GroupCountVo> list);

    /**
     * 获取某一用户名下的所有已开始的Group
     * 
     * @param u 需要获取的关联用户
     * @return 获取到的Group列表，其中的所有Group状态一定是已完成的
     */
    public Group getStartedGroupByUser(Task task, User user);

    /**
     * 获取某一用户名下的所有已完成的Group
     * 
     * @param u 需要获取的关联用户
     * @return 获取到的Group列表，其中的所有Group状态一定是已完成的
     */
    public List<Group> getGroupByUser(Task task, User u);

    /**
     * 获取指定查询条件下的group信息
     * 
     * @param taskId group所属的task的id
     * @param status group的状态
     * @param isNotFinished 是否已完成
     * @return 依据条件查询到的group列表
     */
    public List<Group> getGroupListByCondition(Integer taskId, Integer status, Integer isNotFinished);

    /**
     * 获取标注趋势信息
     * 
     * @param vo 查询参数
     * @return 查询结果
     * @see TrendInfoGetVo
     */
    public TrendStatisticsItem getTrendInfo(TrendInfoGetVo vo);

    /**
     * 获取指定task下统计信息中人员标注情况
     * 
     * @param taskId 需要获取的任务id
     * @return 获取到的结果
     */
    public List<UserStatisticsItem> getUserStatisticsInfo(Integer taskId);

    /**
     * 获取指定任务的用户跟进中的标注任务情况
     * 
     * @param taskId 标注任务ID
     * */
    public List<TagFollowInfoVo> getTagFollowInfoList(Integer taskId);
    
    /**
     * 获取group的提交时间信息
     * 
     * @param vo 参数列表
     * @return 生成的相应的下载记录的id
     */
    public Long getGroupDownloadInfo(SubmitInfoGetVo vo);

    /**
     * 批量选取Group
     * 
     * @param list id列表
     * @return 选取到的Group列表
     */
    public List<Group> batchGet(List<Long> list);

    /**
     * 批量插入Group
     * 
     * @param groups 批量插入的列表
     */
    public void batchInsert(List<Group> groups);

    /**
     * 提交任务
     * 
     * @param list 提交的ad信息列表
     * @param groupId 需要提交的group的主键id
     * @param user 提交人
     */
    public void submitTasks(List<SubmitTagInfo> list, Long groupId, User user);

    /**
     * 根据id放弃某一group <br>
     * 放弃成功后，此group的修改人，修改时间将被刷掉。 如果没有查到相应的group，则什么也不做。
     * 
     * @param id 需要放弃的group的主键id
     * @param user 操作者
     * @return 是否放弃成功
     */
    public Boolean giveUpGroup(Long id, User user);

    /**
     * 回收超时的已分配标注group（时间在beginTime之前的）
     * 
     * @Param beginTime 超时基准时间
     * */
    public void recycleAssignGroups(Date beginTime);

    /**
     * 批量更新group的优先级与ad数量
     * 
     * @param list 更新列表
     */
    public void batchUpdate(List<Group> list);

    /**
     * 删除某一task下的所有group
     * 
     * @param taskId 需要删除的taskId
     */
    public void deleteGroupByTaskId(Integer taskId);
}
