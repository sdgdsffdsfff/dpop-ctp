package com.baidu.dpop.ctp.task.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.adtag.service.AdTagService;
import com.baidu.dpop.ctp.base.DefaultBOUtils;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.group.vo.DistributeGroupResult;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.mainTask.service.UBMCService;
import com.baidu.dpop.ctp.nfs.utils.NfsUtils;
import com.baidu.dpop.ctp.review.service.ReviewAdTaskService;
import com.baidu.dpop.ctp.task.bo.QiushiTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.dao.QiushiTaskDao;

public class QiushiTaskServiceImplTest {

    @Tested
    QiushiTaskServiceImpl qiushiTaskServiceImpl;

    @Injectable
    QiushiTaskDao qiushiTaskDao;

    @Injectable
    UBMCService ubmcService;

    @Injectable
    ReviewAdTaskService reviewAdTaskService;

    @Injectable
    AdTagService adTagService;
    
    @Injectable
    TaskService taskService;


    @Test
    public void testGetQiushiTasksByGroup() {

        new NonStrictExpectations() {
            {
                qiushiTaskDao.selectQiushiTasksByGroup((Integer) any, (Long) any);
                result = DefaultBOUtils.getQiushiTasks(10, 1L);
            }
        };

        DistributeGroupResult map =
                qiushiTaskServiceImpl.getQiushiTasksByGroup(DefaultBOUtils.getGroup(1L, 1L,
                        GroupDataType.QIUSHI.getId()));
        Assert.assertNotNull(map);
        Assert.assertEquals(DefaultBOUtils.DEFAULT_COMPANY, map.getCompanyName());

        new NonStrictExpectations() {
            {
                qiushiTaskDao.selectQiushiTasksByGroup((Integer) any, (Long) any);
                result = null;
            }
        };
        Assert.assertNull(qiushiTaskServiceImpl.getQiushiTasksByGroup(DefaultBOUtils.getGroup(1L, 1L,
                GroupDataType.QIUSHI.getId())));
    }

    @Test
    public void testGetQiushiTaskById() {
        qiushiTaskServiceImpl.getQiushiTaskById(1L, 0);

        new Verifications() {
            {
                qiushiTaskDao.selectByPrimaryKey((Long) any);
                times = 1;
            }
        };
    }

    @Test
    public void testGetFullTaskInfoByTaskId() {
        new MockUp<NfsUtils>() {
            @Mock
            public File getWriteFile(String fileName) {
                return new File(fileName);
            }
        };

        new NonStrictExpectations() {
            {
                qiushiTaskDao.selectQiushiTasksByTaskId((Integer) any);
                result = DefaultBOUtils.getQiushiTasks(DefaultBOUtils.DEFAULT_ADNUM, 1L);

                qiushiTaskDao.selectDownloadCount(anyInt);
                result = DefaultBOUtils.DEFAULT_ADNUM;
                
                taskService.findById((Long) any);
                result = DefaultBOUtils.getTask(1, "task1");
            }
        };

        List<String> fileNames = new ArrayList<String>();
        qiushiTaskServiceImpl.getFullTaskInfoByTaskId(1, fileNames, "111");
        Assert.assertEquals(1, fileNames.size());
        String fileName = "qiushi[taskId_1][111].csv";
        Assert.assertEquals(fileName, fileNames.get(0));
        File f = new File(fileName);
        Assert.assertTrue(f.exists());
        f.delete();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSelectTestedTasks() {

        qiushiTaskServiceImpl.getTestedTasks(new ArrayList<GeneralTask>());

        new Verifications() {
            {
                qiushiTaskDao.selectTestedTasks((List<GeneralTask>) any);
                times = 1;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchGet() {

        qiushiTaskServiceImpl.batchGet(Arrays.asList(1L, 2L));

        new Verifications() {
            {
                qiushiTaskDao.batchSelect((List<Long>) any);
                times = 1;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetTaskCount() {

        qiushiTaskServiceImpl.getTaskCount(new ArrayList<GroupCountVo>());

        new Verifications() {
            {
                qiushiTaskDao.selectTaskCount((List<GroupCountVo>) any);
                times = 1;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchInserTasks() {
        new NonStrictExpectations() {
            {
                qiushiTaskDao.batchInsertTasks((List<QiushiTask>) any);
                result = 0;
            }
        };
        Assert.assertEquals(0, qiushiTaskServiceImpl.batchInsertTasks(new ArrayList<QiushiTask>()));
    }

    @Test
    public void testDeleteAdDetail() {
        qiushiTaskServiceImpl.deleteAdDetail(1);

        new Verifications() {
            {
                qiushiTaskDao.deleteAdDetail((Integer) any);
                times = 1;
            }
        };
    }
}
