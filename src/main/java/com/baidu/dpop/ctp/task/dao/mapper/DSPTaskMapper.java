/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.task.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.invoke.vo.TaskTestVo;
import com.baidu.dpop.ctp.task.bo.DSPTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;

public interface DSPTaskMapper extends GenericMapper<DSPTask, Long> {

    // SELECT--------------------------------------------------------

    /**
     * 根据group_id获取所有的DSPtask
     * 
     * @param groupId
     *            需要获取的group_id;
     * @return 获取到的任务列表
     */
    public List<DSPTask> selectDSPTasksByGroup(@Param("taskId") Integer taskId,
            @Param("groupId") Long groupId);

    /**
     * 通过task_id获取DSP任务
     * 
     * @param taskId
     *            需要获取的task_id
     * @return 获取到的DSP任务
     */
    public List<DSPTask> selectDSPTasksByTaskId(Integer taskId);

    /**
     * 通过查询条件获取DSP任务
     * 
     * @param vo
     *            查询条件
     * @return 获取到的DSP任务
     */
    public List<DSPTask> selectDSPTasksByQueryCondition(GeneralTaskQueryVo vo);

    /**
     * 获取数据库中的重复数据
     * 
     * @param list
     *            需要获取的list
     * @return ad_id与task_id的组合
     */
    public List<TaskTestVo> selectTestedTasks(List<GeneralTask> list);

    /**
     * 批量获取DSP数据
     * 
     * @param list
     *            需要获取的ad的id列表
     * @return 获取到的所有DSP任务
     */
    public List<DSPTask> batchSelect(List<Long> list);

    /**
     * 获取某个Group中所有标注项的数目
     * 
     * @param groupId
     *            需要获取的GroupId
     */
    public List<Group> selectTaskCount(List<GroupCountVo> groupIds);

    /**
     * 获取某一任务下所有任务的数量。 因为下载全集数据时如果一次性获取某任务的所有数据会导致堆溢出，因此需要统计总数并分页获取
     * 
     * @param taskId
     *            需要获取的taskId
     * @return 此task下所有ad的数量
     */
    public Integer selectDownloadCount(Integer taskId);

    // INSERT--------------------------------------------------------

    /**
     * 批量插入数据
     * 
     * @param tasks
     *            列表形式的数据内容
     * @return 成功插入的条目数
     */
    public int batchInsertTasks(@Param("tasks") List<DSPTask> tasks);

    // UPDATE--------------------------------------------------------

    // DELETE--------------------------------------------------------

    /**
     * 删除某一task下的所有数据
     * 
     * @param taskId
     *            指定的task_id
     * */
    public void deleteAdDetail(Integer taskId);
}