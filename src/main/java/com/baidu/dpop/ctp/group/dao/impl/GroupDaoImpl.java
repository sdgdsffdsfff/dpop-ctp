/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.group.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.dao.GroupDao;
import com.baidu.dpop.ctp.group.dao.mapper.GroupMapper;
import com.baidu.dpop.ctp.group.vo.GroupDownloadInfo;
import com.baidu.dpop.ctp.group.vo.SubmitInfoGetVo;
import com.baidu.dpop.ctp.group.vo.TrendInfoGetVo;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.statistics.vo.TrendStatisticsItem;
import com.baidu.dpop.ctp.statistics.vo.UserStatisticsItem;
import com.baidu.dpop.ctp.task.vo.TagFollowInfoVo;

@Repository
public class GroupDaoImpl extends BaseDao<Group, Long> implements GroupDao {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public GenericMapper<Group, Long> getMapper() {
        return this.groupMapper;
    }

    @Override
    public Group selectGroup(Group group) {
        Assert.notNull(group.getGroupId());
        Assert.notNull(group.getTaskId());
        Assert.notNull(group.getDataType());
        return this.groupMapper.selectGroup(group);
    }

    @Override
    public Group selectRandomGroup(Integer taskId, Integer rand) {
        Assert.notNull(taskId);
        Assert.notNull(rand);
        return this.groupMapper.selectRandomGroup(taskId, rand);
    }

    @Override
    public Integer selectUnstartCount(Integer taskId) {
        Assert.notNull(taskId);
        return this.groupMapper.selectUnstartCount(taskId);
    }

    @Override
    public Integer selectUnfinishedCount(Integer taskId) {
        Assert.notNull(taskId);
        return this.groupMapper.selectUnfinishedCount(taskId);
    }

    @Override
    public List<Group> selectTestedGroup(List<GroupCountVo> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<Group>();
        }
        return this.groupMapper.selectTestedGroup(list);
    }

    @Override
    public Byte selectTaskStatusById(Long id) {
        Assert.notNull(id);
        return this.groupMapper.selectTaskStatusById(id);
    }

    @Override
    public Group selectStartedGroupByUser(Integer taskId, Integer userId) {
        Assert.notNull(taskId);
        Assert.notNull(userId);
        return this.groupMapper.selectStartedGroupByUser(taskId, userId);
    }

    @Override
    public List<Group> selectHistoryGroups(Integer taskId, Integer userId) {
        Assert.notNull(taskId);
        Assert.notNull(userId);
        return this.groupMapper.selectHistoryGroups(taskId, userId);
    }

    @Override
    public List<Group> selectGroupListByCondition(Integer taskId, Integer status, Integer isNotFinished) {
        if (taskId == null && status == null && isNotFinished == null) {
            return new ArrayList<Group>();
        }
        return this.groupMapper.selectGroupListByCondition(taskId, status, isNotFinished);
    }

    @Override
    public TrendStatisticsItem selectTrendInfo(TrendInfoGetVo vo) {
        Assert.notNull(vo);
        return this.groupMapper.selectTrendInfo(vo);
    }

    @Override
    public List<UserStatisticsItem> selectUserStatisticsInfo(Integer taskId) {
        Assert.notNull(taskId);
        return this.groupMapper.selectUserStatisticsInfo(taskId);
    }

    @Override
    public List<TagFollowInfoVo> selectTagFollowInfoList(Integer taskId) {
        Assert.notNull(taskId);
        return this.groupMapper.selectTagFollowInfoList(taskId);
    }
    
    @Override
    public Set<GroupDownloadInfo> selectGroupDownloadInfo(SubmitInfoGetVo vo) {
        Assert.notNull(vo);
        return this.groupMapper.selectGroupDownloadInfo(vo);
    }

    @Override
    public List<Group> batchSelect(List<Long> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<Group>();
        }
        return this.groupMapper.batchSelect(list);
    }

    @Override
    public void batchInsert(List<Group> groups) {
        if (CollectionUtils.isEmpty(groups)) {
            return;
        }
        this.groupMapper.batchInsert(groups);
    }

    @Override
    public void recycleAssignGroups(Date beginTime) {
        Assert.notNull(beginTime);
        this.groupMapper.recycleAssignGroups(beginTime);
    }

    @Override
    public void batchUpdate(List<Group> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        this.groupMapper.batchUpdate(list);
    }

    @Override
    public void deleteGroupByTaskId(Integer taskId) {
        Assert.notNull(taskId);
        this.groupMapper.deleteGroupByTaskId(taskId);
    }

}
