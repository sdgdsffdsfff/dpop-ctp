package com.baidu.dpop.ctp.task.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.dpop.ctp.base.AbstractDAOTests;
import com.baidu.dpop.ctp.base.DefaultBOUtils;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.invoke.vo.TaskTestVo;
import com.baidu.dpop.ctp.task.bo.NewDSPTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.dao.NewDSPTaskDao;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;

@SuppressWarnings("restriction")
public class NewDSPTaskDaoImplTest extends AbstractDAOTests {

    @Resource
    private NewDSPTaskDao newDspTaskDao;

    @Before
    public void setUp() {
        this.executeDatas("task/newdsp_task_dataset_init.sql");
    }

    @Test
    public void testBatchInsertTask() {
        List<NewDSPTask> data = Arrays.asList(DefaultBOUtils.getNewDSPTask(1L, 1L, 1L, 1));
        Integer ret = this.newDspTaskDao.batchInsertTasks(data);
        Assert.assertTrue(ret == 1);

    }

    @Test
    public void testSelectNewDSPTaskByGroup() {
        List<NewDSPTask> ret = this.newDspTaskDao.selectNewDSPTasksByGroup(1001, 101L);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));
    }

    @Test
    public void testSelectNewDSPTasksByQueryCondition() {
        GeneralTaskQueryVo vo = new GeneralTaskQueryVo();
        vo.setTaskId(1);
        List<NewDSPTask> ret = this.newDspTaskDao.selectNewDSPTasksByQueryCondition(vo);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));
    }

    @Test
    public void testSelectNewDSPTasksByTaskId() {
        List<NewDSPTask> ret = this.newDspTaskDao.selectNewDSPTasksByTaskId(1);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));
    }

    @Test
    public void testSelectTestedTasks() {
        List<GeneralTask> list = new ArrayList<GeneralTask>();
        List<TaskTestVo> ret = this.newDspTaskDao.selectTestedTasks(list);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));

        NewDSPTask item = new NewDSPTask();
        item.setId(1L);
        list.add(item);
        ret = this.newDspTaskDao.selectTestedTasks(list);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));

    }

    @Test
    public void testSelectTaskCount() {
        List<GroupCountVo> voList = new ArrayList<GroupCountVo>();
        List<Group> ret = this.newDspTaskDao.selectTaskCount(voList);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));

        GroupCountVo item = new GroupCountVo();
        item.setDataType((byte) 0);
        item.setGroupId(1L);
        item.setTaskId(1L);
        voList.add(item);
        ret = this.newDspTaskDao.selectTaskCount(voList);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));
    }

    @Test
    public void testSelectDownloadCount() {
        Integer ret = this.newDspTaskDao.selectDownloadCount(1);
        Assert.assertTrue(ret == 0);
    }

    @Test
    public void testBatchSelect() {
        List<NewDSPTask> ret = this.newDspTaskDao.batchSelect(Arrays.asList(1L));
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchSelectEmpty() {
        Assert.assertTrue(CollectionUtils.isEmpty(newDspTaskDao.batchSelect(null)));
    }

    @Test
    public void testDeleteAdDetail() {
        Assert.assertNotNull(newDspTaskDao.selectByPrimaryKey(1L));
        newDspTaskDao.deleteAdDetail(1001);
        Assert.assertNull(newDspTaskDao.selectByPrimaryKey(1L));
    }

}
