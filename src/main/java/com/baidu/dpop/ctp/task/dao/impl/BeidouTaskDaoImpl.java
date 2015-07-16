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
import com.baidu.dpop.ctp.task.bo.BeidouTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.dao.BeidouTaskDao;
import com.baidu.dpop.ctp.task.dao.mapper.BeidouTaskMapper;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;

@Repository
public class BeidouTaskDaoImpl extends BaseDao<BeidouTask, Long> implements
        BeidouTaskDao {

    @Autowired
    private BeidouTaskMapper taskMapper;

    @Override
    public GenericMapper<BeidouTask, Long> getMapper() {
        return this.taskMapper;
    }

    @Override
    public List<BeidouTask> selectBeidouTasksByGroup(Integer taskId,
            Long groupId) {
        Assert.notNull(taskId);
        Assert.notNull(groupId);
        return this.taskMapper.selectBeidouTasksByGroup(taskId, groupId);
    }

    @Override
    public List<BeidouTask> selectBeidouTasksByTaskId(Integer taskId) {
        Assert.notNull(taskId);
        return this.taskMapper.selectBeidouTasksByTaskId(taskId);
    }

    @Override
    public List<BeidouTask> selectBeidouTasksByQueryCondition(
            GeneralTaskQueryVo vo) {
        Assert.notNull(vo);
        return this.taskMapper.selectBeidouTasksByQueryCondition(vo);
    }

    @Override
    public List<TaskTestVo> selectTestedTasks(List<GeneralTask> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<TaskTestVo>();
        }
        return this.taskMapper.selectTestedTasks(list);
    }

    @Override
    public List<BeidouTask> batchSelect(List<Long> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<BeidouTask>();
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
    public int batchInsertTasks(List<BeidouTask> tasks) {
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
