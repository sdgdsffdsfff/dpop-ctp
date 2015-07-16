/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.mainTask.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.dao.TaskDao;
import com.baidu.dpop.ctp.mainTask.dao.mapper.TaskMapper;
import com.baidu.dpop.ctp.mainTask.vo.TaskQueryInfo;

@Repository
public class TaskDaoImpl extends BaseDao<Task, Long> implements TaskDao {

    @Autowired
    private TaskMapper mainTaskMapper;

    @Override
    public GenericMapper<Task, Long> getMapper() {
        return this.mainTaskMapper;
    }

    @Override
    public List<Task> selectTasksByTaskInfo(TaskQueryInfo taskQueryInfo) {
        Assert.notNull(taskQueryInfo);
        return this.mainTaskMapper.selectTasksByTaskInfo(taskQueryInfo);
    }

    @Override
    public List<Task> selectClosedTasks() {
        return this.mainTaskMapper.selectClosedTasks();
    }

    @Override
    public List<Task> selectUnfinishedTasks() {
        return this.mainTaskMapper.selectUnfinishedTasks();
    }

    @Override
    public List<Task> selectTasksToDelete(Integer days) {
        Assert.notNull(days);
        return this.mainTaskMapper.selectTasksToDelete(days);
    }

    @Override
    public List<Integer> selectTaskIdsByName(String taskName) {
        Assert.notNull(taskName);
        return this.mainTaskMapper.selectTaskIdsByName(taskName);
    }

    @Override
    public List<Task> batchSelectMainTasks(List<Integer> tasks) {
        if (CollectionUtils.isEmpty(tasks)) {
            return new ArrayList<Task>();
        }
        return this.mainTaskMapper.batchSelectMainTasks(tasks);
    }

    @Override
    public void insertDuplicate(Task task) {
        this.mainTaskMapper.insertDuplicate(task);
    }

    @Override
    public void changeTaskStatus(List<Task> list) {
        Assert.state(!CollectionUtils.isEmpty(list));
        this.mainTaskMapper.changeTaskStatus(list);
    }

}
