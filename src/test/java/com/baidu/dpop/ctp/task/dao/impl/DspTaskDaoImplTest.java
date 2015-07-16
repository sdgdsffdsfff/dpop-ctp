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
import com.baidu.dpop.ctp.task.bo.DSPTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.dao.DSPTaskDao;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;

/**
 * DspTaskDaoImplTest
 * 
 * @author cgd
 * @date 2015年3月19日 上午10:58:50
 */
@SuppressWarnings("restriction")
public class DspTaskDaoImplTest extends AbstractDAOTests {

    @Resource
    private DSPTaskDao dspTaskDao;

    @Before
    public void setUp() {
        this.executeDatas("task/dsp_task_dataset_init.sql");
    }

    @Test
    public void testBatchInsertTask() {
        List<DSPTask> data = Arrays.asList(DefaultBOUtils.getDSPTask(1L, 1L, 1L, 1));
        Integer ret = this.dspTaskDao.batchInsertTasks(data);
        Assert.assertTrue(ret == 1);

    }

    @Test
    public void testSelectDSPTaskByGroup() {
        List<DSPTask> ret = this.dspTaskDao.selectDSPTasksByGroup(1001, 101L);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));
    }

    @Test
    public void testSelectDSPTasksByQueryCondition() {
        GeneralTaskQueryVo vo = new GeneralTaskQueryVo();
        vo.setTaskId(1);
        List<DSPTask> ret = this.dspTaskDao.selectDSPTasksByQueryCondition(vo);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));
    }

    @Test
    public void testSelectDSPTasksByTaskId() {
        List<DSPTask> ret = this.dspTaskDao.selectDSPTasksByTaskId(1);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));
    }

    @Test
    public void testSelectTestedTasks() {
        List<GeneralTask> list = new ArrayList<GeneralTask>();
        List<TaskTestVo> ret = this.dspTaskDao.selectTestedTasks(list);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));

        DSPTask item = new DSPTask();
        item.setId(1L);
        list.add(item);
        ret = this.dspTaskDao.selectTestedTasks(list);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));

    }

    @Test
    public void testSelectTaskCount() {
        List<GroupCountVo> voList = new ArrayList<GroupCountVo>();
        List<Group> ret = this.dspTaskDao.selectTaskCount(voList);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));

        GroupCountVo item = new GroupCountVo();
        item.setDataType((byte) 0);
        item.setGroupId(1L);
        item.setTaskId(1L);
        voList.add(item);
        ret = this.dspTaskDao.selectTaskCount(voList);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));
    }

    @Test
    public void testSelectDownloadCount() {
        Integer ret = this.dspTaskDao.selectDownloadCount(1);
        Assert.assertTrue(ret == 0);
    }

    @Test
    public void testBatchSelect() {
        List<DSPTask> ret = this.dspTaskDao.batchSelect(Arrays.asList(1L));
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchSelectEmpty() {
        Assert.assertTrue(CollectionUtils.isEmpty(dspTaskDao.batchSelect(null)));
    }

    @Test
    public void testDeleteAdDetail() {
        Assert.assertNotNull(dspTaskDao.selectByPrimaryKey(1L));
        dspTaskDao.deleteAdDetail(1001);
        Assert.assertNull(dspTaskDao.selectByPrimaryKey(1L));
    }

}
