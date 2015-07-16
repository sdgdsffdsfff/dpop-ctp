package com.baidu.dpop.ctp.task.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;

import com.baidu.dpop.ctp.adtag.service.AdTagService;
import com.baidu.dpop.ctp.base.DefaultBOUtils;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.group.vo.DistributeGroupResult;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.mainTask.service.UBMCService;
import com.baidu.dpop.ctp.nfs.utils.NfsUtils;
import com.baidu.dpop.ctp.review.service.ReviewAdTaskService;
import com.baidu.dpop.ctp.task.bo.BeidouTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.dao.BeidouTaskDao;

public class BeidouTaskServiceImplTest {

    @Tested
    BeidouTaskServiceImpl beidouTaskServiceImpl;

    @Injectable
    BeidouTaskDao beidouTaskDao;

    @Injectable
    UBMCService ubmcService;

    @Injectable
    ReviewAdTaskService reviewAdTaskService;

    @Injectable
    AdTagService adTagService;
    
    @Injectable
    TaskService taskService;

    @Test
    public void testGetBeidouTasksByGroup() {

        new NonStrictExpectations() {
            {
                beidouTaskDao.selectBeidouTasksByGroup((Integer) any, (Long) any);
                result = DefaultBOUtils.getBeidouTasks(10, 1L);
            }
        };

        DistributeGroupResult map =
                beidouTaskServiceImpl.getBeidouTasksByGroup(DefaultBOUtils.getGroup(1L, 1L,
                        GroupDataType.BEIDOU.getId()));
        Assert.assertNotNull(map);
        Assert.assertEquals(DefaultBOUtils.DEFAULT_COMPANY, map.getCompanyName());

        new NonStrictExpectations() {
            {
                beidouTaskDao.selectBeidouTasksByGroup((Integer) any, (Long) any);
                result = null;
            }
        };
        Assert.assertNull(beidouTaskServiceImpl.getBeidouTasksByGroup(DefaultBOUtils.getGroup(1L, 1L,
                GroupDataType.QIUSHI.getId())));
    }

    @Test
    public void testGetBeidouTaskById() {
        beidouTaskServiceImpl.getBeidouTaskById(1L, 0);

        new Verifications() {
            {
                beidouTaskDao.selectByPrimaryKey((Long) any);
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
                beidouTaskDao.selectBeidouTasksByTaskId((Integer) any);
                result = DefaultBOUtils.getBeidouTasks(DefaultBOUtils.DEFAULT_ADNUM, 1L);

                beidouTaskDao.selectDownloadCount(anyInt);
                result = DefaultBOUtils.DEFAULT_ADNUM;
                
                taskService.findById((Long) any);
                result = DefaultBOUtils.getTask(1, "task1");
            }
        };

        List<String> fileNames = new ArrayList<String>();
        beidouTaskServiceImpl.getFullTaskInfoByTaskId(1, fileNames, "111");
        Assert.assertEquals(1, fileNames.size());
        String fileName = "beidou[taskId_1][111].csv";
        Assert.assertEquals(fileName, fileNames.get(0));
        File f = new File(fileName);
        Assert.assertTrue(f.exists());
        f.delete();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSelectTestedTasks() {

        beidouTaskServiceImpl.getTestedTasks(new ArrayList<GeneralTask>());

        new Verifications() {
            {
                beidouTaskDao.selectTestedTasks((List<GeneralTask>) any);
                times = 1;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchGet() {

        beidouTaskServiceImpl.batchGet(Arrays.asList(1L, 2L));

        new Verifications() {
            {
                beidouTaskDao.batchSelect((List<Long>) any);
                times = 1;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetTaskCount() {

        beidouTaskServiceImpl.getTaskCount(new ArrayList<GroupCountVo>());

        new Verifications() {
            {
                beidouTaskDao.selectTaskCount((List<GroupCountVo>) any);
                times = 1;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchInserTasks() {
        new NonStrictExpectations() {
            {
                beidouTaskDao.batchInsertTasks((List<BeidouTask>) any);
                result = 0;
            }
        };
        Assert.assertEquals(0, beidouTaskServiceImpl.batchInsertTasks(new ArrayList<BeidouTask>()));
    }

    @Test
    public void testDeleteAdDetail() {
        beidouTaskServiceImpl.deleteAdDetail(1);

        new Verifications() {
            {
                beidouTaskDao.deleteAdDetail((Integer) any);
                times = 1;
            }
        };
    }

}
