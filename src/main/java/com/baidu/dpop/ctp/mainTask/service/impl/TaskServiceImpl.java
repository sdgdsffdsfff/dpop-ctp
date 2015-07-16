/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.mainTask.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.common.bo.BasicResult;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.common.mybatis.page.PageHelper;
import com.baidu.dpop.ctp.group.service.GroupService;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.constant.ModuserLevel;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatus;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatusChangeType;
import com.baidu.dpop.ctp.mainTask.dao.TaskDao;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.mainTask.vo.TaskQueryInfo;
import com.baidu.dpop.ctp.mainTask.vo.TaskStatusChangeVo;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.constant.UserRoleType;
import com.baidu.dpop.ctp.user.service.UserService;

@Service
public class TaskServiceImpl extends BaseService<Task, Long> implements TaskService {

    @Autowired
    private TaskDao mainTaskDao;

    @Override
    public GenericMapperDao<Task, Long> getDao() {
        return mainTaskDao;
    }

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @Autowired
    private GeneralTaskService generalTaskService;

    @Override
    public List<Task> getTaskListByTaskInfo(Integer page, Integer size, TaskQueryInfo taskQueryInfo) {
        User u = userService.getCurrentLoginUser();
        if (null == u) {
            throw new IllegalArgumentException("getUserByName fail, user is null");
        }

        if (null == taskQueryInfo) {
            throw new IllegalArgumentException("taskQueryInfo is null");
        }

        if (u.getRoleType().equals(UserRoleType.OUTSIDE_AUDIT_USER.getId())
                || u.getRoleType().equals(UserRoleType.OUTSIDE_TAG_USER.getId())) {
            taskQueryInfo.setModuserLevel(ModuserLevel.OUTSIDE.getId());
        } else {
            taskQueryInfo.setModuserLevel(ModuserLevel.INSIDE.getId());
        }
        PageHelper.startPage(page, size);
        return mainTaskDao.selectTasksByTaskInfo(taskQueryInfo);
    }

    @Override
    public List<Task> getClosedTasks() {
        return this.mainTaskDao.selectClosedTasks();
    }

    @Override
    public List<Task> getUnfinishedTasks() {
        return this.mainTaskDao.selectUnfinishedTasks();
    }

    @Override
    public List<Task> getTasksToFinish() {
        List<Task> taskList = this.getUnfinishedTasks();
        List<Task> result = new ArrayList<Task>();
        for (Task task : taskList) {
            if (groupService.getUnfinishedCount(task.getId()) == 0) {
                task.setStatus(TaskStatus.FINISHED.getId());
                result.add(task);
            }
        }
        return result;
    }
    
    @Override
    public List<Task> getTasksToDelete(Integer days) {
        return this.mainTaskDao.selectTasksToDelete(days);
    }

    @Override
    public List<Integer> getTaskIdsByName(String taskName) {
        return this.mainTaskDao.selectTaskIdsByName(taskName);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public BasicResult insertOneTask(Task task) {
        if (task == null) {
            return new BasicResult(false, "Task is null");
        }

        if (TaskStatus.get(task.getStatus()) == null) {
            return new BasicResult(false, "Invalid status: " + task.getStatus());
        }

        if (task.getTaskName() == null) {
            return new BasicResult(false, "Task name is null");
        }

        task.setAddTime(new Date());
        try {
            mainTaskDao.insert(task);
        } catch (Exception e) {
            return new BasicResult(false, "DAO Exception: " + e.getLocalizedMessage());
        }
        return new BasicResult(true, "Success");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void insertTasks(Map<Integer, Task> tasks) {
        for (Integer key : tasks.keySet()) {
            this.mainTaskDao.insertDuplicate(tasks.get(key));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<Task> changeTaskState(TaskStatusChangeVo vo) {
        List<Task> list = mainTaskDao.batchSelectMainTasks(vo.getTaskIds());
        if (TaskStatusChangeType.isCANTOPEN(vo.getStatusChange())) {
            for (Task t : list) {
                if (!TaskStatus.isCLOSED(t.getStatus())) {
                    // 只有状态为关闭，即10,11,12的任务才能执行此操作
                    throw new BaseRuntimeException("task:" + t.getStatus() + "status:" + t.getStatus()
                            + " wrong, can't lock");
                }
                t.setStatus(TaskStatus.closedToCantOpen(t.getStatus()));

            }
        } else if (TaskStatusChangeType.isCANOPEN(vo.getStatusChange())) {
            for (Task t : list) {
                if (!TaskStatus.isCANTOPEN(t.getStatus())) {
                    // 只有状态大于100的任务才能执行此操作
                    throw new BaseRuntimeException("task:" + t.getStatus() + "status:" + t.getStatus()
                            + " wrong, can't unlock");
                }
                t.setStatus(TaskStatus.cantOpenToClosed(t.getStatus()));
            }
        } else if (TaskStatusChangeType.isOPEN(vo.getStatusChange())) {
            for (Task t : list) {
                if (!TaskStatus.isCLOSED(t.getStatus())) {
                    throw new BaseRuntimeException("task:" + t.getStatus() + "status:" + t.getStatus()
                            + " wrong, can't open");
                }
                t.setStatus(TaskStatus.closedToNotClosed(t.getStatus()));
            }
        } else if (TaskStatusChangeType.isCLOSE(vo.getStatusChange())) {
            for (Task t : list) {
                if (!TaskStatus.isNOTCLOSED(t.getStatus())) {
                    throw new BaseRuntimeException("task:" + t.getStatus() + "status:" + t.getStatus()
                            + " wrong, can't close");
                }
                t.setStatus(TaskStatus.notClosedToClosed(t.getStatus()));
            }
        }

        this.mainTaskDao.changeTaskStatus(list);
        return this.mainTaskDao.batchSelectMainTasks(vo.getTaskIds());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<Task> changeTaskStateForced(TaskStatusChangeVo vo) {
        List<Task> list = mainTaskDao.batchSelectMainTasks(vo.getTaskIds());
        for (Task t : list) {
            t.setStatus(vo.getStatusChange().byteValue());
        }
        mainTaskDao.changeTaskStatus(list);
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void deleteTasks(Integer days) {
        List<Task> list = this.getTasksToDelete(days);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Task task : list) {
                this.deleteById(task.getId().longValue());
                generalTaskService.deleteExpiredAdDetail(task.getId());
                groupService.deleteGroupByTaskId(task.getId());
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void deleteTasks(List<Integer> tasks) {
        if (CollectionUtils.isNotEmpty(tasks)) {
            for (Integer taskId : tasks) {
                this.deleteById(taskId.longValue());
                generalTaskService.deleteExpiredAdDetail(taskId);
                groupService.deleteGroupByTaskId(taskId);
            }
        }
    }

}
