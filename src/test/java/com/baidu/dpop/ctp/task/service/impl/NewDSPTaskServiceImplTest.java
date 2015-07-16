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

import com.baidu.dpop.ctp.account.bo.AccountInfo;
import com.baidu.dpop.ctp.adtag.service.AdTagService;
import com.baidu.dpop.ctp.base.DefaultBOUtils;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.group.vo.DistributeGroupResult;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.mainTask.service.UBMCService;
import com.baidu.dpop.ctp.nfs.utils.NfsUtils;
import com.baidu.dpop.ctp.review.service.ReviewAdTaskService;
import com.baidu.dpop.ctp.task.bo.NewDSPTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.dao.NewDSPTaskDao;

public class NewDSPTaskServiceImplTest {

    @Tested
    NewDSPTaskServiceImpl newDspTaskServiceImpl;

    @Injectable
    NewDSPTaskDao newDspTaskDao;

    @Injectable
    UBMCService ubmcService;

    @Injectable
    ReviewAdTaskService reviewAdTaskService;

    @Injectable
    AdTagService adTagService;
    
    @Injectable
    TaskService taskService;


    @Test
    public void testGetNewDSPTasksByGroup() {

        new NonStrictExpectations() {
            {
                newDspTaskDao.selectNewDSPTasksByGroup((Integer) any, (Long) any);
                result = DefaultBOUtils.getNewDSPTasks(10, 1L);
            }
        };

        DistributeGroupResult map =
                newDspTaskServiceImpl.getNewDSPTasksByGroup(DefaultBOUtils.getGroup(1L, 1L,
                        GroupDataType.QIUSHI.getId()));
        Assert.assertNotNull(map);
        Assert.assertEquals(DefaultBOUtils.DEFAULT_COMPANY, map.getCompanyName());

        new NonStrictExpectations() {
            {
                newDspTaskDao.selectNewDSPTasksByGroup((Integer) any, (Long) any);
                result = null;
            }
        };
        Assert.assertNull(newDspTaskServiceImpl.getNewDSPTasksByGroup(DefaultBOUtils.getGroup(1L, 1L,
                GroupDataType.QIUSHI.getId())));
    }

    @Test
    public void testGetNewDSPTaskById() {
        newDspTaskServiceImpl.getNewDSPTaskById(1L, 0);

        new Verifications() {
            {
                newDspTaskDao.selectByPrimaryKey((Long) any);
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
                newDspTaskDao.selectNewDSPTasksByTaskId((Integer) any);
                result = DefaultBOUtils.getNewDSPTasks(DefaultBOUtils.DEFAULT_ADNUM, 1L);

                newDspTaskDao.selectDownloadCount(anyInt);
                result = DefaultBOUtils.DEFAULT_ADNUM;
                
                taskService.findById((Long) any);
                result = DefaultBOUtils.getTask(1, "task1");
            }
        };

        List<String> fileNames = new ArrayList<String>();
        newDspTaskServiceImpl.getFullTaskInfoByTaskId(1, fileNames, "111");
        Assert.assertEquals(1, fileNames.size());
        String fileName = "newDSP[taskId_1][111].csv";
        Assert.assertEquals(fileName, fileNames.get(0));
        File f = new File(fileName);
        Assert.assertTrue(f.exists());
        f.delete();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSelectTestedTasks() {

        newDspTaskServiceImpl.getTestedTasks(new ArrayList<GeneralTask>());

        new Verifications() {
            {
                newDspTaskDao.selectTestedTasks((List<GeneralTask>) any);
                times = 1;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchGet() {

        newDspTaskServiceImpl.batchGet(Arrays.asList(1L, 2L));

        new Verifications() {
            {
                newDspTaskDao.batchSelect((List<Long>) any);
                times = 1;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetTaskCount() {

        newDspTaskServiceImpl.getTaskCount(new ArrayList<GroupCountVo>());

        new Verifications() {
            {
                newDspTaskDao.selectTaskCount((List<GroupCountVo>) any);
                times = 1;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchInserTasks() {
        new NonStrictExpectations() {
            {
                newDspTaskDao.batchInsertTasks((List<NewDSPTask>) any);
                result = 0;
            }
        };
        Assert.assertEquals(0, newDspTaskServiceImpl.batchInsertTasks(new ArrayList<NewDSPTask>()));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateCompanyInfo() {
        newDspTaskServiceImpl.updateCompanyInfo(Arrays.asList(new AccountInfo()), Arrays.asList(1));

        new Verifications() {
            {
                newDspTaskDao.updateCompanyInfo((List<AccountInfo>) any, (List<Integer>) any);
                times = 1;
            }
        };
    }

    @Test
    public void testDeleteAdDetail() {
        newDspTaskServiceImpl.deleteAdDetail(1);

        new Verifications() {
            {
                newDspTaskDao.deleteAdDetail((Integer) any);
                times = 1;
            }
        };
    }

}
