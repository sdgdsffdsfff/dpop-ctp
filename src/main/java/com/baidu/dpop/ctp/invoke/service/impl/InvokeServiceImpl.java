package com.baidu.dpop.ctp.invoke.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.baidu.dpop.ctp.account.service.AccountService;
import com.baidu.dpop.ctp.account.vo.AccountGetResultInfo;
import com.baidu.dpop.ctp.adtag.bo.TagTypeTreeNode;
import com.baidu.dpop.ctp.adtag.service.TagTypeService;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.constant.GroupStatus;
import com.baidu.dpop.ctp.group.service.GroupService;
import com.baidu.dpop.ctp.invoke.service.InvokeService;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.invoke.vo.TaskTestVo;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatus;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.nfs.service.FileArchiveService;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.task.utils.GeneralTaskUtils;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;

@Service
public class InvokeServiceImpl implements InvokeService {

	private static final Logger logger = Logger
			.getLogger(InvokeServiceImpl.class);

	@Value("${dpop.ctp.invoke.taskInsertNumber}")
	private long INSERTION;

	@Autowired
	private GeneralTaskService generalTaskService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private FileArchiveService fileArchiveService;
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private TagTypeService tagTypeService;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public int uploadFile(List<GeneralTask> data, Byte dataType,
			List<String> elist) {
		Assert.notNull(data);
		Assert.notNull(dataType);

		Set<Integer> closedTasks = new HashSet<Integer>();
		List<Task> closedTaskList = taskService.getClosedTasks();
		if (CollectionUtils.isNotEmpty(closedTaskList)) {
			for (Task k : closedTaskList) {
				closedTasks.add(k.getId());
			}
		}

		Map<Integer, TagTypeTreeNode> map = tagTypeService.getAll();

		try {
			Map<GroupCountVo, Group> groupMap = new HashMap<GroupCountVo, Group>(); // 本次上传所有用到的group
			Map<Integer, Task> taskMap = new HashMap<Integer, Task>();
			Date nowDate = new Date();
			List<GeneralTask> tasks = null;
			tasks = new ArrayList<GeneralTask>();

			Set<TaskTestVo> set = new HashSet<TaskTestVo>();
			Set<Integer> taskIds = new HashSet<Integer>();
			for (GeneralTask task : data) {
				tryLoadData(task, elist, tasks, closedTasks, dataType, set, map);
				taskIds.add(task.getTaskId().intValue()); 
			}
			
			// 上传是不会上传重复数据，但并不从task中删除重复数据，仅将其二级优先级置为0，防止计算总优先级时干扰
			// 不删除是因为与可能需要调整一级优先级
			int total = generalTaskService.insertGeneralTask(tasks, dataType);
			Set<Integer> userids = new HashSet<Integer>();

			// 这里只是获取task与group的信息
			for (GeneralTask gt : tasks) {
			    userids.add(gt.getUserId().intValue());
				addGeneralTask(gt, groupMap, taskMap, nowDate, dataType);
			}

			AccountGetResultInfo result = accountService.getUserInfoBatch(new ArrayList<Integer>(userids), false);
			if (result.getFlag().intValue() == 0) {
			    generalTaskService.updateCompanyInfo(result.getData(), new ArrayList<Integer>(taskIds), dataType);
			}
			
			Map<GroupCountVo, Group> hasGroups = groupService
					.getTestedGroup(new ArrayList<GroupCountVo>(groupMap
							.keySet()));
			List<Group> insertGroups = new ArrayList<Group>();
			for (Entry<GroupCountVo, Group> entry : groupMap.entrySet()) {
				Group temp = hasGroups.get(entry.getKey());
				if (temp != null) {
					temp.setPriority(GeneralTaskUtils.sumPriority(entry
							.getValue().getPriority(), temp.getPriority(), temp
							.getDataType()));
					insertGroups.add(temp);
				} else {
					entry.getValue().setAdNum(0);
					insertGroups.add(entry.getValue());
				}
			}
			insertGroups(insertGroups, dataType);
			taskService.insertTasks(taskMap);
			return total;
		} catch (Exception e) {
			logger.error("uploadFile Exception: " + e.getLocalizedMessage(), e);
			throw new RuntimeException("uploadFile Exception", e);
		}
	}

	private void insertGroups(List<Group> groups, Byte dataType) {
		List<Group> insert = new ArrayList<Group>();
		List<Group> update = new ArrayList<Group>();
		List<GroupCountVo> list = new ArrayList<GroupCountVo>();

		for (Group g : groups) {
			list.add(new GroupCountVo(g.getTaskId(), g.getGroupId(), g
					.getDataType()));
		}
		Map<GroupCountVo, Group> groupMap = generalTaskService.getTaskCount(
				list, dataType);

		for (Group g : groups) {
			Group temp = groupMap.get(new GroupCountVo(g.getTaskId(), g
					.getGroupId(), g.getDataType()));
			if (temp != null
					&& temp.getAdNum().intValue() != g.getAdNum().intValue()) {
				// 数量不同，说明有新增group
				g.setAdNum(temp.getAdNum());
				g.setStatus(GroupStatus.NOT_STARTED.getId());
			}

			if (g.getId() == null) {
				insert.add(g);
			} else {
				update.add(g);
			}
		}

		groupService.batchInsert(insert);
		groupService.batchUpdate(update);
	}

	private void addGeneralTask(GeneralTask task,
			Map<GroupCountVo, Group> groupMap, Map<Integer, Task> taskMap,
			Date nowDate, Byte dataType) {
		GroupCountVo vo = new GroupCountVo(task.getTaskId().intValue(), task
				.getGroupId().longValue(), dataType);
		if (!groupMap.containsKey(vo)) {
			Group g = new Group();
			g.setGroupId(task.getGroupId().longValue());
			g.setDataType(dataType);
			g.setPriority(GeneralTaskUtils.sumPriority(
					task.getGeneralPriority(), 0L, dataType));
			g.setStatus(GroupStatus.NOT_STARTED.getId());
			g.setTaskId(task.getTaskId().intValue());
			g.setTaskName(task.getTaskName());
			g.setModifyUserId(-1);
			groupMap.put(vo, g);
		} else {
			Group g = groupMap.get(vo);
			g.setPriority(GeneralTaskUtils.sumPriority(g.getPriority(),
					task.getGeneralPriority(), dataType));
		}

		if (!taskMap.containsKey(task.getTaskId().intValue())) {
			Task t = new Task();
			t.setModuserLevel(task.getModuserLevel().intValue());
			t.setAddTime(nowDate);
			t.setAddUser("System");
			t.setId(task.getTaskId().intValue());
			t.setModuserLevel(task.getModuserLevel().intValue());
			t.setStatus(TaskStatus.NOT_STARTED.getId());
			t.setTaskName(task.getTaskName());
			t.setTaskType(task.getTaskType());
			taskMap.put(task.getTaskId().intValue(), t);
		}
	}

	/**
	 * 尝试读取一行数据，将String转化为BO
	 * 
	 * @param line
	 *            需要转化的数据内容
	 * @param elist
	 *            错误信息列表，如果在转化中出现异常，将会在此列表中添加这一行数据的ad_id
	 * @param tasks
	 *            插入列表，由于每次插入是1000条批量插入，因此所有数据将被保存在列表中一并插入
	 * @return null if 转化失败 else bo
	 */
	private GeneralTask tryLoadData(GeneralTask task, List<String> elist,
			List<GeneralTask> tasks, Set<Integer> closedTasks, Byte dataType,
			Set<TaskTestVo> set, Map<Integer, TagTypeTreeNode> tree) {
		try {
			task.transformAdTag(tree);
			if (closedTasks.contains(task.getTaskId().intValue())) {
				elist.add("数据错误: 任务已关闭. task_id:" + task.getTaskId()
						+ ", ad_id:" + task.getAdId());
				return null;
			}
			TaskTestVo vo = new TaskTestVo(task.getTaskId().intValue(), task
					.getAdId().longValue());
			if (set.contains(vo)) {
				return null;
			} else {
				set.add(vo);
			}

			tasks.add(task);
			return task;
		} catch (Exception e) {
			elist.add(e.getLocalizedMessage());
			logger.error(e.getLocalizedMessage(), e);
			return null;
		}
	}

	@Override
	public String getTagedFile(GeneralTaskQueryVo vo) {
		Assert.notNull(vo);

		String fileName = generalTaskService.getTagedFile(vo);
		return fileName;
	}
}
