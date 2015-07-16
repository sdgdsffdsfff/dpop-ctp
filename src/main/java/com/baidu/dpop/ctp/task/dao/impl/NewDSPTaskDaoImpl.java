package com.baidu.dpop.ctp.task.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.baidu.dpop.ctp.account.bo.AccountInfo;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.invoke.vo.TaskTestVo;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.bo.NewDSPTask;
import com.baidu.dpop.ctp.task.dao.NewDSPTaskDao;
import com.baidu.dpop.ctp.task.dao.mapper.NewDSPTaskMapper;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;
import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;

@Repository
public class NewDSPTaskDaoImpl extends BaseDao<NewDSPTask, Long> implements
		NewDSPTaskDao {

	@Autowired
	private NewDSPTaskMapper taskMapper;

	@Override
	public GenericMapper<NewDSPTask, Long> getMapper() {
		// TODO Auto-generated method stub
		return taskMapper;
	}

	@Override
	public List<NewDSPTask> selectNewDSPTasksByGroup(Integer taskId,
			Long groupId) {
		Assert.notNull(taskId);
		Assert.notNull(groupId);
		return this.taskMapper.selectNewDSPTasksByGroup(taskId, groupId);
	}

	@Override
	public List<NewDSPTask> selectNewDSPTasksByTaskId(Integer taskId) {
		Assert.notNull(taskId);
		return this.taskMapper.selectNewDSPTasksByTaskId(taskId);
	}

	@Override
	public List<NewDSPTask> selectNewDSPTasksByQueryCondition(
			GeneralTaskQueryVo vo) {
		Assert.notNull(vo);
		return this.taskMapper.selectNewDSPTasksByQueryCondition(vo);
	}

	@Override
	public List<TaskTestVo> selectTestedTasks(List<GeneralTask> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<TaskTestVo>();
		}
		return this.taskMapper.selectTestedTasks(list);
	}

	@Override
	public List<NewDSPTask> batchSelect(List<Long> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<NewDSPTask>();
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
	public int batchInsertTasks(List<NewDSPTask> tasks) {
		if (CollectionUtils.isEmpty(tasks)) {
			return 0;
		}
		return this.taskMapper.batchInsertTasks(tasks);
	}
	
	@Override
    public void updateCompanyInfo(List<AccountInfo> list, List<Integer> taskIds) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        Assert.notNull(taskIds);
        this.taskMapper.updateCompanyInfo(list, taskIds);
    }

	@Override
	public void deleteAdDetail(Integer taskId) {
		Assert.notNull(taskId);
		this.taskMapper.deleteAdDetail(taskId);
	}

}
