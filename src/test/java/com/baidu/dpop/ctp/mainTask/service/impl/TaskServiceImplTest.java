package com.baidu.dpop.ctp.mainTask.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;

import com.baidu.dpop.ctp.base.DefaultBOUtils;
import com.baidu.dpop.ctp.common.bo.BasicResult;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.group.service.GroupService;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatus;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatusChangeType;
import com.baidu.dpop.ctp.mainTask.dao.TaskDao;
import com.baidu.dpop.ctp.mainTask.vo.TaskQueryInfo;
import com.baidu.dpop.ctp.mainTask.vo.TaskStatusChangeVo;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.user.constant.UserRoleType;
import com.baidu.dpop.ctp.user.service.UserService;

/**
 * TaskServiceImpl
 * 
 * @author cgd
 * @date 2015年1月13日 下午8:52:17
 */
public class TaskServiceImplTest {

    @Tested
    private TaskServiceImpl taskServiceImpl;

    @Injectable
    private TaskDao mainTaskDao;

    @Injectable
    private GroupService groupService;

    @Injectable
    private GeneralTaskService generalTaskService;

    @Injectable
    private UserService userService;

    @Test
    public void testInserTask() {
        new NonStrictExpectations() {
            {
                mainTaskDao.insert((Task) any);
                result = 1;
            }
        };
        Task task = new Task();
        task.setStatus(TaskStatus.NOT_STARTED.getId());
        task.setTaskName("task_1");
        BasicResult b = taskServiceImpl.insertOneTask(task);
        Assert.assertTrue(b.isSuccess());
    }

    @Test
    public void testInserTaskNull() {
        new NonStrictExpectations() {
            {
                mainTaskDao.insert((Task) any);
                result = 1;
            }
        };
        BasicResult b = taskServiceImpl.insertOneTask(null);
        Assert.assertFalse(b.isSuccess());

        Task task = new Task();
        b = taskServiceImpl.insertOneTask(task);
        Assert.assertFalse(b.isSuccess());

        task.setStatus(TaskStatus.NOT_STARTED.getId());
        b = taskServiceImpl.insertOneTask(task);
        Assert.assertFalse(b.isSuccess());
    }

    @Test
    public void testInserTaskException() {
        new NonStrictExpectations() {
            {
                mainTaskDao.insert((Task) any);
                result = new Exception("test");
            }
        };
        Task task = new Task();
        task.setStatus(TaskStatus.NOT_STARTED.getId());
        task.setTaskName("task_1");
        BasicResult b = taskServiceImpl.insertOneTask(task);
        Assert.assertFalse(b.isSuccess());
        Assert.assertEquals("DAO Exception: test", b.getInfo());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testChangeTaskState() {
        new NonStrictExpectations() {
            {
                mainTaskDao.changeTaskStatus((List<Task>) any);
            }
        };
        TaskStatusChangeVo vo = new TaskStatusChangeVo();
        vo.setTaskIds(Arrays.asList(1));
        vo.setStatusChange(-10);
        this.taskServiceImpl.changeTaskState(vo);
    }

    @SuppressWarnings("unchecked")
    @Test(expected = BaseRuntimeException.class)
    public void testChangeTaskStateWrong1() {
        new NonStrictExpectations() {
            {
                mainTaskDao.batchSelectMainTasks((List<Integer>) any);
                Task task = DefaultBOUtils.getTask(1, "task1");
                task.setStatus(TaskStatus.STARTED.getId());
                result = Arrays.asList(task);

                mainTaskDao.changeTaskStatus((List<Task>) any);
            }
        };
        TaskStatusChangeVo vo = new TaskStatusChangeVo();
        vo.setStatusChange(TaskStatusChangeType.CANTOPEN.getId());
        vo.setTaskIds(Arrays.asList(1));
        taskServiceImpl.changeTaskState(vo);
    }

    @SuppressWarnings("unchecked")
    @Test(expected = BaseRuntimeException.class)
    public void testChangeTaskStateWrong2() {
        new NonStrictExpectations() {
            {
                mainTaskDao.batchSelectMainTasks((List<Integer>) any);
                Task task = DefaultBOUtils.getTask(1, "task1");
                task.setStatus(TaskStatus.STARTED.getId());
                result = Arrays.asList(task);

                mainTaskDao.changeTaskStatus((List<Task>) any);
            }
        };
        TaskStatusChangeVo vo = new TaskStatusChangeVo();
        vo.setStatusChange(TaskStatusChangeType.CANTOPEN.getId());
        vo.setTaskIds(Arrays.asList(1));
        taskServiceImpl.changeTaskState(vo);
    }

    @SuppressWarnings("unchecked")
    @Test(expected = BaseRuntimeException.class)
    public void testChangeTaskStateWrong3() {
        new NonStrictExpectations() {
            {
                mainTaskDao.batchSelectMainTasks((List<Integer>) any);
                Task task = DefaultBOUtils.getTask(1, "task1");
                task.setStatus(TaskStatus.STARTED.getId());
                result = Arrays.asList(task);

                mainTaskDao.changeTaskStatus((List<Task>) any);
            }
        };
        TaskStatusChangeVo vo = new TaskStatusChangeVo();
        vo.setStatusChange(TaskStatusChangeType.OPEN.getId());
        vo.setTaskIds(Arrays.asList(1));
        taskServiceImpl.changeTaskState(vo);
    }

    @SuppressWarnings("unchecked")
    @Test(expected = BaseRuntimeException.class)
    public void testChangeTaskStateWrong4() {
        new NonStrictExpectations() {
            {
                mainTaskDao.batchSelectMainTasks((List<Integer>) any);
                Task task = DefaultBOUtils.getTask(1, "task1");
                task.setStatus(TaskStatus.CLOSED_1.getId());
                result = Arrays.asList(task);

                mainTaskDao.changeTaskStatus((List<Task>) any);
            }
        };
        TaskStatusChangeVo vo = new TaskStatusChangeVo();
        vo.setStatusChange(TaskStatusChangeType.CLOSE.getId());
        vo.setTaskIds(Arrays.asList(1));
        taskServiceImpl.changeTaskState(vo);
    }

    @Test
    public void testGetTaskListByTaskInfo() {

        new NonStrictExpectations() {
            {
                userService.getCurrentLoginUser();
                result = DefaultBOUtils.getUser(1, "user", UserRoleType.INNER_ADMIN_USER.getId());

                mainTaskDao.selectTasksByTaskInfo((TaskQueryInfo) any);
                List<Task> list = new ArrayList<Task>();
                for (int i = 0; i < 10; i++) {
                    list.add(new Task());
                }
                result = list;
            }
        };
        TaskQueryInfo taskQueryInfo = new TaskQueryInfo();
        taskQueryInfo.setOrderBy("addTime");
        List<Task> list = taskServiceImpl.getTaskListByTaskInfo(1, 10, taskQueryInfo);
        Assert.assertNotNull(list);
        Assert.assertEquals(10, list.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTaskListByTaskInfoNull() {

        new NonStrictExpectations() {
            {
                userService.getCurrentLoginUser();
                result = DefaultBOUtils.getUser(1, "user1", UserRoleType.INNER_AUDIT_USER.getId());
            }
        };
        taskServiceImpl.getTaskListByTaskInfo(1, 10, null);

        new NonStrictExpectations() {
            {
                userService.getCurrentLoginUser();
                result = DefaultBOUtils.getUser(1, "user1", UserRoleType.OUTSIDE_AUDIT_USER.getId());
            }
        };
        taskServiceImpl.getTaskListByTaskInfo(1, 10, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTaskListByTaskInfoUserNull() {

        new NonStrictExpectations() {
            {
                userService.getCurrentLoginUser();
                result = null;
            }
        };

        taskServiceImpl.getTaskListByTaskInfo(1, 10, new TaskQueryInfo());
    }

    @Test
    public void testInsertTasks() {
        Map<Integer, Task> map = new HashMap<Integer, Task>();
        map.put(1, new Task());
        map.put(2, new Task());
        taskServiceImpl.insertTasks(map);
        new Verifications() {
            {
                mainTaskDao.insertDuplicate((Task) any);
                times = 2;
            }
        };
    }

    @Test
    public void testSelectClosedTasks() {
        taskServiceImpl.getClosedTasks();
        new Verifications() {
            {
                mainTaskDao.selectClosedTasks();
                times = 1;
            }
        };
    }

    @Test
    public void testSelectTaskIdsByName() {

        taskServiceImpl.getTaskIdsByName("task1");

        new Verifications() {
            {
                mainTaskDao.selectTaskIdsByName((String) any);
                times = 1;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testChangeTaskStateForced() {
        taskServiceImpl.changeTaskStateForced(new TaskStatusChangeVo());
        new Verifications() {
            {
                mainTaskDao.batchSelectMainTasks((List<Integer>) any);
                times = 1;

                mainTaskDao.changeTaskStatus((List<Task>) any);
                times = 1;
            }
        };
    }

    @Test
    public void testGetTasksToDelete() {
        taskServiceImpl.getTasksToDelete(10);
        new Verifications() {
            {
                mainTaskDao.selectTasksToDelete((Integer) any);
                times = 1;
            }
        };
    }

    @Test
    public void testDeleteTasks() {

        new NonStrictExpectations() {
            {
                mainTaskDao.selectTasksToDelete((Integer) any);
                result = Arrays.asList(DefaultBOUtils.getTask(1, "task1"), DefaultBOUtils.getTask(2, "task2"));
            }
        };

        taskServiceImpl.deleteTasks(10);

        new Verifications() {
            {
                generalTaskService.deleteExpiredAdDetail((Integer) any);
                times = 2;

                groupService.deleteGroupByTaskId((Integer) any);
                times = 2;
            }
        };
    }

    @Test
    public void testDeleteTasks2() {

        taskServiceImpl.deleteTasks(Arrays.asList(1, 2));

        new Verifications() {
            {
                generalTaskService.deleteExpiredAdDetail((Integer) any);
                times = 2;

                groupService.deleteGroupByTaskId((Integer) any);
                times = 2;
            }
        };
    }

}
