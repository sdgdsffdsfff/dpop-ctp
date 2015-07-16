/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.mainTask.service;

import java.util.List;
import java.util.Map;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.dpop.ctp.common.bo.BasicResult;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.vo.TaskQueryInfo;
import com.baidu.dpop.ctp.mainTask.vo.TaskStatusChangeVo;

public interface TaskService extends GenericMapperService<Task, Long> {

    /**
     * 根据任务信息查询任务列表
     * 
     * @param page 页码
     * @param size 页面数量
     * @param taskQueryInfo 查询条件
     * @return
     */
    public List<Task> getTaskListByTaskInfo(Integer page, Integer size, TaskQueryInfo taskQueryInfo);

    /**
     * 获取所有已关闭的任务id
     * 
     * @return 所有已关闭的任务, 只返回近30天的数据
     */
    public List<Task> getClosedTasks();

    /**
     * 获取所有正在进行的任务
     * 
     * @return 所有正在进行的任务列表
     */
    public List<Task> getUnfinishedTasks();

    /**
     * 检测哪些任务可以被设置为已完成状态
     * 
     * @return 需要被设置为完成状态的任务
     */
    public List<Task> getTasksToFinish();

    /**
     * 选取指定日期前创建并且已经关闭的任务
     * 
     * @param days 获取日期
     */
    public List<Task> getTasksToDelete(Integer days);

    /**
     * 根据任务名获取任务id
     * 
     * @param taskName
     * @return 任务id
     */
    public List<Integer> getTaskIdsByName(String taskName);

    /**
     * 插入一条task
     * 
     * @param task
     * @return 基本信息，使用getIsSuccess()获取是否成功，使用getInfo()获取内容
     */
    public BasicResult insertOneTask(Task task);

    /**
     * 添加任务
     * 
     * @param tasks 需要添加的任务，Map形式，键为任务id，值为任务实例
     */
    public void insertTasks(Map<Integer, Task> tasks);

    /**
     * 改变某一任务的状态
     * 
     * @param conditonVo 需要操作任务查询条件
     */
    public List<Task> changeTaskState(TaskStatusChangeVo vo);

    /**
     * 强制改变任务的状态
     * 
     * @param vo
     * @return
     */
    public List<Task> changeTaskStateForced(TaskStatusChangeVo vo);

    /**
     * 删除指定时间之前创建的所有任务
     * 
     * @param days 指定时间
     */
    public void deleteTasks(Integer days);

    /**
     * 根据id列表删除任务
     * 
     * @param tasks 需要删除的id列表
     */
    public void deleteTasks(List<Integer> tasks);
}
