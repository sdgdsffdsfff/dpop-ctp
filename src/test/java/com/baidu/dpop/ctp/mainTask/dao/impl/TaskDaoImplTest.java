package com.baidu.dpop.ctp.mainTask.dao.impl;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.dpop.ctp.base.AbstractDAOTests;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.constant.ModuserLevel;
import com.baidu.dpop.ctp.mainTask.dao.TaskDao;
import com.baidu.dpop.ctp.mainTask.vo.TaskQueryInfo;

@SuppressWarnings("restriction")
public class TaskDaoImplTest extends AbstractDAOTests {

    @Resource
    private TaskDao taskDao;

    @Before
    public void setUp() {
        this.executeDatas("mainTask/task_dataset_init.sql");
    }

    @Test(expected = Exception.class)
    public void testchangeTaskState() {
        List<Task> list = new ArrayList<Task>();
        Task item = new Task();
        item.setId(1);
        item.setAddTime(new Date());
        item.setAddUser("cgd");
        item.setModuserLevel(1);
        item.setStatus((byte) 0);
        item.setTaskName("task_name");
        list.add(item);

        this.taskDao.changeTaskStatus(list);
    }

    @Test
    public void testGetTaskListByTaskInfo() {
        TaskQueryInfo taskQueryInfo = new TaskQueryInfo();
        taskQueryInfo.setAddUser("System");
        taskQueryInfo.setModuserLevel(ModuserLevel.OUTSIDE.getId());
        taskQueryInfo.setOrderBy("id");
        List<Task> list = taskDao.selectTasksByTaskInfo(taskQueryInfo);
        Assert.assertNotNull(list);
        int id = 0;
        for (Task task : list) {
            Assert.assertTrue(task.getId() >= id);
            id = task.getId();
        }
    }

    @Test(expected = Exception.class)
    public void testinsertDuplicate() {
        Task item = new Task();
        item.setId(1);
        item.setAddTime(new Date());
        item.setAddUser("cgd");
        item.setModuserLevel(1);
        item.setStatus((byte) 0);
        item.setTaskName("task_name");

        this.taskDao.insertDuplicate(item);
    }

    @Test(expected = Exception.class)
    public void testselectClosedTasks() {
        this.taskDao.selectClosedTasks();
    }

    @Test
    public void testselectTaskByIds() {
        List<Task> ret = this.taskDao.batchSelectMainTasks(Arrays.asList(100));
        Assert.assertTrue(ret.size() > 0);
    }

    @Test
    public void testselectTaskIdsByName() {
        List<Integer> ret = this.taskDao.selectTaskIdsByName("task_100");
        Assert.assertTrue(ret.size() > 0);
    }

}
