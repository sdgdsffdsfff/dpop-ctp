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
import com.baidu.dpop.ctp.task.bo.QiushiTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.dao.QiushiTaskDao;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;

/**
 * QiushiTaskDaoImpl Test
 * 
 * @author cgd
 * @date 2015年3月19日 上午10:58:50
 */
@SuppressWarnings("restriction")
public class QiushiTaskDaoImplTest extends AbstractDAOTests {

    @Resource
    private QiushiTaskDao qiushiTaskDao;

    @Before
    public void setUp() {
        this.executeDatas("task/qiushi_task_dataset_init.sql");
    }

    @Test
    public void testBatchInsertTask() {
        List<QiushiTask> data = Arrays.asList(DefaultBOUtils.getQiushiTask(1L, 1L, 1L, 1));
        Integer ret = this.qiushiTaskDao.batchInsertTasks(data);
        Assert.assertTrue(ret == 1);

    }

    @Test
    public void testSelectQiushiTaskByGroup() {
        List<QiushiTask> ret = this.qiushiTaskDao.selectQiushiTasksByGroup(1001, 101L);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));
    }

    @Test
    public void testSelectQiushiTasksByQueryCondition() {
        GeneralTaskQueryVo vo = new GeneralTaskQueryVo();
        vo.setTaskId(1);
        List<QiushiTask> ret = this.qiushiTaskDao.selectQiushiTasksByQueryCondition(vo);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));
    }

    @Test
    public void testSelectQiushiTasksByTaskId() {
        List<QiushiTask> ret = this.qiushiTaskDao.selectQiushiTasksByTaskId(1);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));
    }

    @Test
    public void testSelectTestedTasks() {
        List<GeneralTask> list = new ArrayList<GeneralTask>();
        List<TaskTestVo> ret = this.qiushiTaskDao.selectTestedTasks(list);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));

        QiushiTask item = new QiushiTask();
        item.setId(1L);
        list.add(item);
        ret = this.qiushiTaskDao.selectTestedTasks(list);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));

    }

    @Test
    public void testSelectTaskCount() {
        List<GroupCountVo> voList = new ArrayList<GroupCountVo>();
        List<Group> ret = this.qiushiTaskDao.selectTaskCount(voList);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));

        GroupCountVo item = new GroupCountVo();
        item.setDataType((byte) 0);
        item.setGroupId(1L);
        item.setTaskId(1L);
        voList.add(item);
        ret = this.qiushiTaskDao.selectTaskCount(voList);
        Assert.assertTrue(CollectionUtils.isEmpty(ret));
    }

    @Test
    public void testSelectDownloadCount() {
        Integer ret = this.qiushiTaskDao.selectDownloadCount(1);
        Assert.assertTrue(ret == 0);
    }

    @Test
    public void testBatchSelect() {
        List<QiushiTask> ret = this.qiushiTaskDao.batchSelect(Arrays.asList(1L));
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchSelectEmpty() {
        Assert.assertTrue(CollectionUtils.isEmpty(qiushiTaskDao.batchSelect(null)));
    }

    @Test
    public void testDeleteAdDetail() {
        Assert.assertNotNull(qiushiTaskDao.selectByPrimaryKey(1L));
        qiushiTaskDao.deleteAdDetail(1001);
        Assert.assertNull(qiushiTaskDao.selectByPrimaryKey(1L));
    }
}
