/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.mainTask.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.vo.TaskQueryInfo;

public interface TaskMapper extends GenericMapper<Task, Long> {

    // SELECT--------------------------------------------------------

    /**
     * 根据task的具体信息，返回匹配的任务列表
     * 
     * @param taskQueryInfo 查询条件
     * @return 符合条件的列表
     */
    public List<Task> selectTasksByTaskInfo(TaskQueryInfo taskQueryInfo);

    /**
     * 获取所有已关闭的任务id
     * 
     * @return 所有已关闭的任务id, 只返回近30天的数据
     */
    public List<Task> selectClosedTasks();

    /**
     * 获取所有进行中的任务
     * 
     * @return 进行中的任务列表
     */
    public List<Task> selectUnfinishedTasks();

    /**
     * 选取指定日期前创建并且已经关闭的任务
     * 
     * @param days 获取日期
     */
    public List<Task> selectTasksToDelete(Integer days);

    /**
     * 根据任务名获取任务id
     * 
     * @param taskName 任务名称
     * @return 任务id列表
     */
    public List<Integer> selectTaskIdsByName(@Param("taskName") String taskName);

    /**
     * 根据提供的id数组选择任务
     * 
     * @param tasks 选择的id数组
     * @return 被选择的task
     */
    public List<Task> batchSelectMainTasks(List<Integer> tasks);

    // INSERT--------------------------------------------------------

    /**
     * 插入一条task，如果已经存在，则更新之（只修改status）
     * 
     * @param task 需要插入的task
     */
    public void insertDuplicate(Task task);

    // UPDATE--------------------------------------------------------

    /**
     * 批量改变任务的状态
     * 
     * @param list 需要改变的任务列表
     */
    public void changeTaskStatus(List<Task> list);

    // DELETE--------------------------------------------------------

}