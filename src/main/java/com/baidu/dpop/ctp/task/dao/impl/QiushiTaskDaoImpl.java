/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.task.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.invoke.vo.TaskTestVo;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.bo.QiushiTask;
import com.baidu.dpop.ctp.task.dao.QiushiTaskDao;
import com.baidu.dpop.ctp.task.dao.mapper.QiushiTaskMapper;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;

@Repository
public class QiushiTaskDaoImpl extends BaseDao<QiushiTask, Long> implements
        QiushiTaskDao {

    @Autowired
    private QiushiTaskMapper taskMapper;

    @Override
    public GenericMapper<QiushiTask, Long> getMapper() {
        return this.taskMapper;
    }

    @Override
    public List<QiushiTask> selectQiushiTasksByGroup(Integer taskId,
            Long groupId) {
        Assert.notNull(taskId);
        Assert.notNull(groupId);
        return this.taskMapper.selectQiushiTasksByGroup(taskId, groupId);
    }

    @Override
    public List<QiushiTask> selectQiushiTasksByTaskId(Integer taskId) {
        Assert.notNull(taskId);
        return this.taskMapper.selectQiushiTasksByTaskId(taskId);
    }

    @Override
    public List<QiushiTask> selectQiushiTasksByQueryCondition(
            GeneralTaskQueryVo vo) {
        Assert.notNull(vo);
        return this.taskMapper.selectQiushiTasksByQueryCondition(vo);
    }

    @Override
    public List<TaskTestVo> selectTestedTasks(List<GeneralTask> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<TaskTestVo>();
        }
        return this.taskMapper.selectTestedTasks(list);
    }

    @Override
    public List<QiushiTask> batchSelect(List<Long> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<QiushiTask>();
        }
        return this.taskMapper.batchSelect(list);
    }

    @Override
    public List<Group> selectTaskCount(List<GroupCountVo> groupIds) {
        if (CollectionUtils.isEmpty(groupIds)) {
            return new ArrayList<Group>();
        }
        return this.taskMapper.selectTaskCount(groupIds);
    }

    @Override
    public Integer selectDownloadCount(Integer taskId) {
        Assert.notNull(taskId);
        return this.taskMapper.selectDownloadCount(taskId);
    }

    @Override
    public int batchInsertTasks(List<QiushiTask> tasks) {
        if (CollectionUtils.isEmpty(tasks)) {
            return 0;
        }
        return taskMapper.batchInsertTasks(tasks);
    }

    @Override
    public void deleteAdDetail(Integer taskId) {
        Assert.notNull(taskId);
        this.taskMapper.deleteAdDetail(taskId);
    }
}
