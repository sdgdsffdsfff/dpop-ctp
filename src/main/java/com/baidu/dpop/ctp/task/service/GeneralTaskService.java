/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.task.service;

import java.util.List;
import java.util.Map;

import com.baidu.dpop.ctp.account.bo.AccountInfo;
import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.vo.DistributeGroupResult;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;
import com.baidu.dpop.ctp.task.vo.PresentedTaskDetail;
import com.baidu.dpop.ctp.user.bo.User;

public interface GeneralTaskService {

    /**
     * 根据已经分配的Group分配任务
     * 
     * @param g 已经分配的Group
     * @param u 请求任务的用户
     * @param historyGroup 请求用户的历史分配信息
     * @return 分配结果
     * @see DistributeGroupResult
     */
    DistributeGroupResult getTasksByGroup(Group g, User u, List<Group> historyGroup);

    /**
     * 根据数据类获取某个任务的详细信息
     * 
     * @param id ad_id
     * @param region 地域
     * @param dataType 数据类型
     * @return 任务详细信息
     * @see PresentedTaskDetail
     */
    public PresentedTaskDetail getTaskById(Long id, Integer region, Integer dataType);

    /**
     * 批量获取任务
     * 
     * @param ids 需要获取的主键id
     * @param dataType 数据类型
     * @return list型的结果
     */
    public List<GeneralTask> batchGet(List<Long> ids, Number dataType);

    /**
     * 批量获取任务
     * 
     * @param ids 需要获取的主键id
     * @param dataType 数据类型
     */
    public Map<Long, GeneralTask> batchGetMapped(List<Long> ids, Number dataType);

    /**
     * 批量统计所有给定group_id的所有Group的adNum
     * 
     * @param groupIds 需要统计的group_id
     * @return Group实例列表，只有id与adNum两项有数值
     */
    public Map<GroupCountVo, Group> getTaskCount(List<GroupCountVo> groupIds, Byte dataType);

    /**
     * 在创建审核任务时进行下载
     * 
     * @param vo 创建审核任务的条件
     * @return 下载文件地址
     */
    public String downloadWhenCreateReviewTask(List<AdTag> tagList, Integer groupNum, Number taskType);

    /**
     * 获取条件下的有已标注数据
     * 
     * @param vo 获取条件
     * @return 查询数据对应的NFS文件名
     */
    public String getTagedFile(GeneralTaskQueryVo vo);

    /**
     * 获取任务的完整内容文件
     * 
     * @param taskId 需要获取的任务id
     * @return 获取到的文件nfs地址
     */
    public String getFullTaskInfoByTaskId(Integer taskId);

    /**
     * 批量获取任务的完整内容文件
     * 
     * @param taskIds 需要获取的任务id列表
     * 
     * @return 获取到的文件nfs地址
     */
    public String getFullTaskInfoBytTaskIds(List<Integer> taskIds);

    /**
     * 根据ad的主键Id获取其imgurl
     * 
     * @param id
     * @param dataType
     * @return
     */
    public List<String> getImgUrl(Long id, Integer dataType);

    /**
     * 获取给定列表中所有ad的imgUrl
     * 
     * @param list ad的id列表
     * @param dataType ad的数据库类型
     * @return 获取到的任务详细信息，包括了imgUrl
     */
    public List<PresentedTaskDetail> getImgUrls(List<Long> list, Integer dataType);

    /**
     * 根据数据类型插入不同的标注数据。taskId与adId完全相同的任务被认为是同一条记录
     * 
     * @param tasks 需要插入的任务列表
     * @param dataType 数据类型
     * @return 成功插入条目数
     */
    public int insertGeneralTask(List<GeneralTask> tasks, Byte dataType);

    /**
     * 根据任务id更新公司信息，包括company及website
     * 
     * @param list 更新信息列表
     * @param taskId 需要更新的task
     */
    public void updateCompanyInfo(List<AccountInfo> list, List<Integer> taskId, Number dataType);

    /**
     * 删除某一task下的所有数据
     * 
     * @param taskId 指定的task_id
     * */
    public void deleteExpiredAdDetail(Integer taskId);
}
