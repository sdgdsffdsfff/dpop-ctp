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
import com.baidu.dpop.ctp.task.bo.BeidouTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.dao.BeidouTaskDao;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;

/**
 * BeidouTaskDaoImpl Test
 * 
 * @author cgd
 * @date 2015年3月18日 下午6:17:42
 */
@SuppressWarnings("restriction")
public class BeidouTaskDaoImplTest extends AbstractDAOTests {

    @Resource
    private BeidouTaskDao beidouTaskDao;

    @Before
    public void setUp() {
        this.executeDatas("task/beidou_task_dataset_init.sql");
    }

    @Test
    public void testBatchInsertTask() {
        List<BeidouTask> data = Arrays.asList(DefaultBOUtils.getBeidouTask(1L, 1L, 1L, 1));
        Integer ret = this.beidouTaskDao.batchInsertTasks(data);
        Assert.assertTrue(ret == 1);

    }

    @Test
    public void testSelectBeidouTaskByGroup() {
        List<BeidouTask> ret = this.beidouTaskDao.selectBeidouTasksByGroup(1001, 101L);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));
    }

    @Test
    public void testSelectBeidouTasksByQueryCondition() {
        GeneralTaskQueryVo vo = new GeneralTaskQueryVo();
        vo.setTaskId(1);
        List<BeidouTask> ret = this.beidouTaskDao.selectBeidouTasksByQueryCondition(vo);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));
    }

    @Test
    public void testSelectBeidouTasksByTaskId() {
        List<BeidouTask> ret = this.beidouTaskDao.selectBeidouTasksByTaskId(1);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));
    }

    @Test
    public void testSelectTestedTasks() {
        List<GeneralTask> list = new ArrayList<GeneralTask>();
        List<TaskTestVo> ret = this.beidouTaskDao.selectTestedTasks(list);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));

        BeidouTask item = new BeidouTask();
        item.setId(1L);
        list.add(item);
        ret = this.beidouTaskDao.selectTestedTasks(list);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));

    }

    @Test
    public void testSelectTaskCount() {
        List<GroupCountVo> voList = new ArrayList<GroupCountVo>();
        List<Group> ret = this.beidouTaskDao.selectTaskCount(voList);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));

        GroupCountVo item = new GroupCountVo();
        item.setDataType((byte) 0);
        item.setGroupId(1L);
        item.setTaskId(1L);
        voList.add(item);
        ret = this.beidouTaskDao.selectTaskCount(voList);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));
    }

    @Test
    public void testSelectDownloadCount() {
        Integer ret = this.beidouTaskDao.selectDownloadCount(1);
        Assert.assertTrue(ret == 0);
    }

    @Test
    public void testBatchSelect() {
        List<BeidouTask> ret = this.beidouTaskDao.batchSelect(Arrays.asList(1L));
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchSelectEmpty() {
        Assert.assertTrue(CollectionUtils.isEmpty(beidouTaskDao.batchSelect(null)));
    }

    @Test
    public void testDeleteAdDetail() {
        Assert.assertNotNull(beidouTaskDao.selectByPrimaryKey(1L));
        beidouTaskDao.deleteAdDetail(1001);
        Assert.assertNull(beidouTaskDao.selectByPrimaryKey(1L));
    }

}
