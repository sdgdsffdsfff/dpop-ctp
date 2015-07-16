package com.baidu.dpop.ctp.task.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.baidu.dpop.ctp.account.bo.AccountInfo;
import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.service.AdTagService;
import com.baidu.dpop.ctp.base.DefaultBOUtils;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.common.utils.UBMCUtils;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.group.vo.DistributeGroupResult;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.invoke.vo.TaskTestVo;
import com.baidu.dpop.ctp.mainTask.constant.GeneralMcType;
import com.baidu.dpop.ctp.mainTask.service.UBMCService;
import com.baidu.dpop.ctp.nfs.service.FileArchiveService;
import com.baidu.dpop.ctp.nfs.utils.NfsUtils;
import com.baidu.dpop.ctp.outerinvoke.service.OuterInvokeService;
import com.baidu.dpop.ctp.review.service.ReviewAdTaskService;
import com.baidu.dpop.ctp.task.bo.BeidouTask;
import com.baidu.dpop.ctp.task.bo.DSPTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.bo.NewDSPTask;
import com.baidu.dpop.ctp.task.bo.QiushiTask;
import com.baidu.dpop.ctp.task.service.BeidouTaskService;
import com.baidu.dpop.ctp.task.service.DSPTaskService;
import com.baidu.dpop.ctp.task.service.NewDSPTaskService;
import com.baidu.dpop.ctp.task.service.QiushiTaskService;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;
import com.baidu.dpop.ctp.task.vo.PresentedTaskDetail;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.constant.UserRoleType;

import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;

public class GeneralTaskServiceImplTest {

    @Tested
    GeneralTaskServiceImpl generalTaskServiceImpl;

    @Injectable
    BeidouTaskService beidouTaskService;

    @Injectable
    QiushiTaskService qiushiTaskService;

    @Injectable
    DSPTaskService dspTaskService;

    @Injectable
    NewDSPTaskService newDspTaskService;

    @Injectable
    FileArchiveService fileArchiveService;

    @Injectable
    AdTagService adTagService;

    @Injectable
    ReviewAdTaskService reviewAdTaskService;

    @Injectable
    OuterInvokeService outerInvokeService;

    @Injectable
    UBMCService ubmcService;

    private List<TaskTestVo> getDefaultTaskTestVo() {
        List<TaskTestVo> list = new ArrayList<TaskTestVo>();
        TaskTestVo vo = new TaskTestVo();
        vo.setAdId(0L);
        vo.setTaskId(1021L);
        list.add(vo);
        return list;
    }

    @Test
    public void testGetTasksByGroup() {
        new NonStrictExpectations() {
            {
                beidouTaskService.getBeidouTasksByGroup((Group) any);
                result = DefaultBOUtils.getDefaultResult(GroupDataType.BEIDOU.getId());

                qiushiTaskService.getQiushiTasksByGroup((Group) any);
                result = DefaultBOUtils.getDefaultResult(GroupDataType.QIUSHI.getId());

                dspTaskService.getDSPTasksByGroup((Group) any);
                result = DefaultBOUtils.getDefaultResult(GroupDataType.DSP.getId());

                newDspTaskService.getNewDSPTasksByGroup((Group) any);
                result = DefaultBOUtils.getDefaultResult(GroupDataType.NEWDSP.getId());

                adTagService.getByGroup((Long) any);
                result = new ArrayList<AdTag>();
            }
        };

        Group g = null;
        List<Group> historyGroup = null;
        User u = DefaultBOUtils.getUser(1, "user1", UserRoleType.INNER_ADMIN_USER.getId());

        g = DefaultBOUtils.getGroup(1L, 1L, GroupDataType.BEIDOU.getId());
        historyGroup = new ArrayList<Group>();
        for (int i = 0; i < 5; i++) {
            historyGroup.add(DefaultBOUtils.getGroup(i + 100L, i + 100L, GroupDataType.BEIDOU.getId()));
        }
        DistributeGroupResult map = generalTaskServiceImpl.getTasksByGroup(g, u, historyGroup);
        Assert.assertEquals(String.valueOf(DefaultBOUtils.DEFAULT_ADNUM * 5), map.getHistoryAdNum().toString());

        g = DefaultBOUtils.getGroup(1L, 1L, GroupDataType.QIUSHI.getId());
        historyGroup = new ArrayList<Group>();
        for (int i = 0; i < 5; i++) {
            historyGroup.add(DefaultBOUtils.getGroup(i + 100L, i + 100L, GroupDataType.QIUSHI.getId()));
        }
        map = generalTaskServiceImpl.getTasksByGroup(g, u, historyGroup);
        Assert.assertEquals(String.valueOf(DefaultBOUtils.DEFAULT_ADNUM * 5), map.getHistoryAdNum().toString());

        g = DefaultBOUtils.getGroup(1L, 1L, GroupDataType.DSP.getId());
        historyGroup = new ArrayList<Group>();
        for (int i = 0; i < 5; i++) {
            historyGroup.add(DefaultBOUtils.getGroup(i + 100L, i + 100L, GroupDataType.DSP.getId()));
        }
        map = generalTaskServiceImpl.getTasksByGroup(g, u, historyGroup);
        Assert.assertEquals(String.valueOf(DefaultBOUtils.DEFAULT_ADNUM * 5), map.getHistoryAdNum().toString());

        g = DefaultBOUtils.getGroup(1L, 1L, GroupDataType.NEWDSP.getId());
        historyGroup = new ArrayList<Group>();
        for (int i = 0; i < 5; i++) {
            historyGroup.add(DefaultBOUtils.getGroup(i + 100L, i + 100L, GroupDataType.NEWDSP.getId()));
        }
        map = generalTaskServiceImpl.getTasksByGroup(g, u, historyGroup);
        Assert.assertEquals(String.valueOf(DefaultBOUtils.DEFAULT_ADNUM * 5), map.getHistoryAdNum().toString());

        new NonStrictExpectations() {
            {
                beidouTaskService.getBeidouTasksByGroup((Group) any);
                result = DefaultBOUtils.getDefaultResult(GroupDataType.BEIDOU.getId());

                adTagService.getByGroup((Long) any);
                result = DefaultBOUtils.getAdTags(1, GroupDataType.BEIDOU.getId());
            }
        };
        g = DefaultBOUtils.getGroup(1L, 1L, GroupDataType.BEIDOU.getId());
        historyGroup = new ArrayList<Group>();
        for (int i = 0; i < 5; i++) {
            historyGroup.add(DefaultBOUtils.getGroup(i + 100L, i + 100L, GroupDataType.BEIDOU.getId()));
        }
        map = generalTaskServiceImpl.getTasksByGroup(g, u, historyGroup);
        Assert.assertEquals(String.valueOf(DefaultBOUtils.DEFAULT_ADNUM * 5), map.getHistoryAdNum().toString());
    }

    @Test(expected = BaseRuntimeException.class)
    public void testGetTasksByGroupBeidouWrong() {
        new NonStrictExpectations() {
            {
                beidouTaskService.getBeidouTasksByGroup((Group) any);
                result = null;
            }
        };

        Group g = DefaultBOUtils.getGroup(1L, 1L, GroupDataType.BEIDOU.getId());
        User u = DefaultBOUtils.getUser(1, "user1", UserRoleType.INNER_ADMIN_USER.getId());

        g.setDataType(GroupDataType.BEIDOU.getId());
        generalTaskServiceImpl.getTasksByGroup(g, u, new ArrayList<Group>());
    }

    @Test(expected = BaseRuntimeException.class)
    public void testGetTasksByGroupQiushiWrong() {
        new NonStrictExpectations() {
            {
                qiushiTaskService.getQiushiTasksByGroup((Group) any);
                result = null;
            }
        };

        Group g = DefaultBOUtils.getGroup(1L, 1L, GroupDataType.QIUSHI.getId());
        User u = DefaultBOUtils.getUser(1, "user1", UserRoleType.INNER_ADMIN_USER.getId());

        g.setDataType(GroupDataType.QIUSHI.getId());
        generalTaskServiceImpl.getTasksByGroup(g, u, new ArrayList<Group>());
    }

    @Test(expected = BaseRuntimeException.class)
    public void testGetTasksByGroupDSPWrong() {
        new NonStrictExpectations() {
            {
                dspTaskService.getDSPTasksByGroup((Group) any);
                result = null;
            }
        };

        Group g = DefaultBOUtils.getGroup(1L, 1L, GroupDataType.DSP.getId());
        User u = DefaultBOUtils.getUser(1, "user1", UserRoleType.INNER_ADMIN_USER.getId());
        ;

        g.setDataType(GroupDataType.DSP.getId());
        generalTaskServiceImpl.getTasksByGroup(g, u, new ArrayList<Group>());
    }

    @Test(expected = BaseRuntimeException.class)
    public void testGetTasksByGroupNewDSPWrong() {
        new NonStrictExpectations() {
            {
                newDspTaskService.getNewDSPTasksByGroup((Group) any);
                result = null;
            }
        };

        Group g = DefaultBOUtils.getGroup(1L, 1L, GroupDataType.NEWDSP.getId());
        User u = DefaultBOUtils.getUser(1, "user1", UserRoleType.INNER_ADMIN_USER.getId());
        ;

        g.setDataType(GroupDataType.NEWDSP.getId());
        generalTaskServiceImpl.getTasksByGroup(g, u, new ArrayList<Group>());
    }

    @Test(expected = BaseRuntimeException.class)
    public void testGetTasksByGroupWrong() {
        Group g = DefaultBOUtils.getGroup(1L, 1L, GroupDataType.ALL.getId());
        User u = DefaultBOUtils.getUser(1, "user1", UserRoleType.INNER_ADMIN_USER.getId());
        ;

        g.setDataType(GroupDataType.NEWDSP.getId());
        generalTaskServiceImpl.getTasksByGroup(g, u, new ArrayList<Group>());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetTaskById() {
        new NonStrictExpectations() {
            {
                beidouTaskService.getBeidouTaskById((Long) any, (Integer) any);
                result = DefaultBOUtils.getBeidouTask(1L, 1L, 1L, 1);

                qiushiTaskService.getQiushiTaskById((Long) any, (Integer) any);
                result = DefaultBOUtils.getQiushiTask(1L, 1L, 1L, 1);

                dspTaskService.getDSPTaskById((Long) any, (Integer) any);
                result = DefaultBOUtils.getDSPTask(1L, 1L, 1L, 1);

                newDspTaskService.getNewDSPTaskById((Long) any, (Integer) any);
                result = DefaultBOUtils.getNewDSPTask(1L, 1L, 1L, 1);

                adTagService.getByRefId((Long) any, (Integer) any);
                result = DefaultBOUtils.getAdTags(1, 0).get(0);

                outerInvokeService.getCreativityInfo((List<PresentedTaskDetail>) any);
            }
        };

        new MockUp<UBMCUtils>() {
            @Mock
            public void setUBMCImgUrl(PresentedTaskDetail pt, UBMCService ubmcService, Number dataType) {
            }
        };

        PresentedTaskDetail pt = generalTaskServiceImpl.getTaskById(1L, 0, GroupDataType.BEIDOU.getId().intValue());
        Assert.assertEquals(GroupDataType.BEIDOU.getId(), pt.getDataType());

        pt = generalTaskServiceImpl.getTaskById(1L, 0, GroupDataType.QIUSHI.getId().intValue());
        Assert.assertEquals(GroupDataType.QIUSHI.getId(), pt.getDataType());

        pt = generalTaskServiceImpl.getTaskById(1L, 0, GroupDataType.DSP.getId().intValue());
        Assert.assertEquals(GroupDataType.DSP.getId(), pt.getDataType());

        pt = generalTaskServiceImpl.getTaskById(1L, 0, GroupDataType.NEWDSP.getId().intValue());
        Assert.assertEquals(GroupDataType.NEWDSP.getId(), pt.getDataType());

        Assert.assertNull(generalTaskServiceImpl.getTaskById(1L, 0, GroupDataType.ALL.getId().intValue()));

    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchGet() {
        new NonStrictExpectations() {
            {
                beidouTaskService.batchGet((List<Long>) any);
                result = DefaultBOUtils.getBeidouTasks(10, 1L);

                qiushiTaskService.batchGet((List<Long>) any);
                result = DefaultBOUtils.getQiushiTasks(5, 1L);

                dspTaskService.batchGet((List<Long>) any);
                result = DefaultBOUtils.getDSPTasks(15, 1L);

                newDspTaskService.batchGet((List<Long>) any);
                result = DefaultBOUtils.getNewDSPTasks(20, 1L);

            }
        };

        Assert.assertEquals(10, generalTaskServiceImpl.batchGet(Arrays.asList(1L), GroupDataType.BEIDOU.getId()).size());
        Assert.assertEquals(5, generalTaskServiceImpl.batchGet(Arrays.asList(1L), GroupDataType.QIUSHI.getId()).size());
        Assert.assertEquals(15, generalTaskServiceImpl.batchGet(Arrays.asList(1L), GroupDataType.DSP.getId()).size());
        Assert.assertEquals(20, generalTaskServiceImpl.batchGet(Arrays.asList(1L), GroupDataType.NEWDSP.getId()).size());
        Assert.assertEquals(0, generalTaskServiceImpl.batchGet(Arrays.asList(1L), GroupDataType.ALL.getId()).size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchGetMapped() {
        new NonStrictExpectations() {
            {
                beidouTaskService.batchGet((List<Long>) any);
                result = DefaultBOUtils.getBeidouTasks(10, 1L);

                qiushiTaskService.batchGet((List<Long>) any);
                result = DefaultBOUtils.getQiushiTasks(5, 1L);

                dspTaskService.batchGet((List<Long>) any);
                result = DefaultBOUtils.getDSPTasks(15, 1L);

                newDspTaskService.batchGet((List<Long>) any);
                result = DefaultBOUtils.getNewDSPTasks(20, 1L);

            }
        };

        Assert.assertEquals(10, generalTaskServiceImpl.batchGetMapped(Arrays.asList(1L), GroupDataType.BEIDOU.getId())
                .size());
        Assert.assertEquals(5, generalTaskServiceImpl.batchGetMapped(Arrays.asList(1L), GroupDataType.QIUSHI.getId())
                .size());
        Assert.assertEquals(15, generalTaskServiceImpl.batchGetMapped(Arrays.asList(1L), GroupDataType.DSP.getId())
                .size());
        Assert.assertEquals(20, generalTaskServiceImpl.batchGetMapped(Arrays.asList(1L), GroupDataType.NEWDSP.getId())
                .size());
        Assert.assertEquals(0, generalTaskServiceImpl.batchGetMapped(Arrays.asList(1L), GroupDataType.ALL.getId())
                .size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetTaskCount() {
        new NonStrictExpectations() {
            {
                beidouTaskService.getTaskCount((List<GroupCountVo>) any);
                result = Arrays.asList(DefaultBOUtils.getGroup(1L, 1L, GroupDataType.BEIDOU.getId()));

                qiushiTaskService.getTaskCount((List<GroupCountVo>) any);
                result = Arrays.asList(DefaultBOUtils.getGroup(1L, 1L, GroupDataType.QIUSHI.getId()));

                dspTaskService.getTaskCount((List<GroupCountVo>) any);
                result = Arrays.asList(DefaultBOUtils.getGroup(1L, 1L, GroupDataType.DSP.getId()));

                newDspTaskService.getTaskCount((List<GroupCountVo>) any);
                result = Arrays.asList(DefaultBOUtils.getGroup(1L, 1L, GroupDataType.NEWDSP.getId()));
            }
        };

        Assert.assertEquals(1,
                generalTaskServiceImpl.getTaskCount(Arrays.asList(new GroupCountVo()), GroupDataType.BEIDOU.getId())
                        .size());

        Assert.assertEquals(1,
                generalTaskServiceImpl.getTaskCount(Arrays.asList(new GroupCountVo()), GroupDataType.QIUSHI.getId())
                        .size());

        Assert.assertEquals(1,
                generalTaskServiceImpl.getTaskCount(Arrays.asList(new GroupCountVo()), GroupDataType.DSP.getId())
                        .size());

        Assert.assertEquals(1,
                generalTaskServiceImpl.getTaskCount(Arrays.asList(new GroupCountVo()), GroupDataType.NEWDSP.getId())
                        .size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetTaskCountEmpty() {
        new NonStrictExpectations() {
            {
                beidouTaskService.getTaskCount((List<GroupCountVo>) any);
                result = null;

                qiushiTaskService.getTaskCount((List<GroupCountVo>) any);
                result = null;

                dspTaskService.getTaskCount((List<GroupCountVo>) any);
                result = null;

                newDspTaskService.getTaskCount((List<GroupCountVo>) any);
                result = null;
            }
        };

        Assert.assertEquals(0,
                generalTaskServiceImpl.getTaskCount(Arrays.asList(new GroupCountVo()), GroupDataType.BEIDOU.getId())
                        .size());

        Assert.assertEquals(0,
                generalTaskServiceImpl.getTaskCount(Arrays.asList(new GroupCountVo()), GroupDataType.QIUSHI.getId())
                        .size());

        Assert.assertEquals(0,
                generalTaskServiceImpl.getTaskCount(Arrays.asList(new GroupCountVo()), GroupDataType.DSP.getId())
                        .size());

        Assert.assertEquals(0,
                generalTaskServiceImpl.getTaskCount(Arrays.asList(new GroupCountVo()), GroupDataType.NEWDSP.getId())
                        .size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testDownloadWhenCreateReviewTask() {

        new NonStrictExpectations() {
            {
                beidouTaskService.batchGet((List<Long>) any);
                result = DefaultBOUtils.getBeidouTasks(10, 1L);
            }
        };

        new MockUp<NfsUtils>() {

            @Mock
            public File getWriteFile(String path) {
                return new File("test.test");
            }
        };

        generalTaskServiceImpl.downloadWhenCreateReviewTask(DefaultBOUtils.getAdTags(10, GroupDataType.BEIDOU.getId()),
                1, 3);
        Assert.assertTrue(new File("test.test").delete());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetTagedFile() {

        new NonStrictExpectations() {
            {
                adTagService.getMapedTagedtags((GeneralTaskQueryVo) any);
                result = DefaultBOUtils.getAdTags(DefaultBOUtils.getAdTags(10, GroupDataType.BEIDOU.getId()));

                beidouTaskService.batchGet((List<Long>) any);
                result = DefaultBOUtils.getBeidouTasks(10, 1L);

                reviewAdTaskService.batchGetByRefAdId((List<Long>) any);
                result = DefaultBOUtils.getReviewAdTasks(10, 1L);
            }
        };

        new MockUp<NfsUtils>() {

            @Mock
            public File getWriteFile(String path) {
                return new File("test.test");
            }
        };

        GeneralTaskQueryVo vo = new GeneralTaskQueryVo();
        vo.setDataType(GroupDataType.BEIDOU.getId());
        generalTaskServiceImpl.getTagedFile(vo);
        Assert.assertTrue(new File("test.test").delete());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetFullTaskInfoByTaskId() {
        new NonStrictExpectations() {
            {
                beidouTaskService.getFullTaskInfoByTaskId((Integer) any, (List<String>) any, (String) any);

                qiushiTaskService.getFullTaskInfoByTaskId((Integer) any, (List<String>) any, (String) any);

                dspTaskService.getFullTaskInfoByTaskId((Integer) any, (List<String>) any, (String) any);

                newDspTaskService.getFullTaskInfoByTaskId((Integer) any, (List<String>) any, (String) any);

                fileArchiveService.getPackageFilePath((List<String>) any, (String) any);
                result = "test";
            }
        };
        Assert.assertEquals("test", generalTaskServiceImpl.getFullTaskInfoByTaskId(1));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetFullTaskInfoByTaskIds() {
        new NonStrictExpectations() {
            {
                generalTaskServiceImpl.getFullTaskInfoByTaskId((Integer) any);
                result = "test";

                fileArchiveService.getPackageFilePath((List<String>) any, (String) any);
                result = "test";
            }
        };
        Assert.assertEquals("test", generalTaskServiceImpl.getFullTaskInfoBytTaskIds(new ArrayList<Integer>()));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testInsertGeneralTask() {
        new NonStrictExpectations() {
            {
                beidouTaskService.getTestedTasks((List<GeneralTask>) any);
                result = getDefaultTaskTestVo();

                beidouTaskService.batchInsertTasks((List<BeidouTask>) any);
                result = 10;

                qiushiTaskService.getTestedTasks((List<GeneralTask>) any);
                result = getDefaultTaskTestVo();

                qiushiTaskService.batchInsertTasks((List<QiushiTask>) any);
                result = 10;

                dspTaskService.getTestedTasks((List<GeneralTask>) any);
                result = getDefaultTaskTestVo();

                dspTaskService.batchInsertTasks((List<DSPTask>) any);
                result = 10;

                newDspTaskService.getTestedTasks((List<GeneralTask>) any);
                result = getDefaultTaskTestVo();

                newDspTaskService.batchInsertTasks((List<NewDSPTask>) any);
                result = 10;
            }
        };

        Assert.assertEquals(0,
                generalTaskServiceImpl.insertGeneralTask(new ArrayList<GeneralTask>(), GroupDataType.BEIDOU.getId()));
        Assert.assertEquals(0,
                generalTaskServiceImpl.insertGeneralTask(new ArrayList<GeneralTask>(), GroupDataType.QIUSHI.getId()));
        Assert.assertEquals(0,
                generalTaskServiceImpl.insertGeneralTask(new ArrayList<GeneralTask>(), GroupDataType.DSP.getId()));
        Assert.assertEquals(0,
                generalTaskServiceImpl.insertGeneralTask(new ArrayList<GeneralTask>(), GroupDataType.NEWDSP.getId()));
    }

    @Test
    public void testgGetImgUrl() {

        new MockUp<UBMCUtils>() {

            @Mock
            public void setUBMCImgUrl(PresentedTaskDetail pt, UBMCService ubmcService, Number dataType) {
                pt.setImgUrl("test");
            }
        };

        new NonStrictExpectations() {
            {
                beidouTaskService.findById((Long) any);
                result = DefaultBOUtils.getBeidouTask(1L, 1L, 1L, 1);

                qiushiTaskService.findById((Long) any);
                result = DefaultBOUtils.getQiushiTask(1L, 1L, 1L, 1);

                dspTaskService.findById((Long) any);
                result = DefaultBOUtils.getDSPTask(1L, 1L, 1L, 1);
            }
        };

        List<String> result = generalTaskServiceImpl.getImgUrl(1L, GroupDataType.BEIDOU.getId().intValue());
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("test", result.get(0));

        result = generalTaskServiceImpl.getImgUrl(1L, GroupDataType.QIUSHI.getId().intValue());
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("test", result.get(0));

        result = generalTaskServiceImpl.getImgUrl(1L, GroupDataType.DSP.getId().intValue());
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("test", result.get(0));

        Assert.assertNull(generalTaskServiceImpl.getImgUrl(1L, GroupDataType.NEWDSP.getId().intValue()));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetImgUrls() {
        new MockUp<UBMCUtils>() {

            @Mock
            public void setUBMCImgUrl(List<PresentedTaskDetail> list, UBMCService service, Number dataType) {
                for (PresentedTaskDetail pt : list) {
                    pt.setImgUrl("test");
                }
            }
        };

        new MockUp<GeneralMcType>() {

            @Mock
            public Boolean needUrl(Number type) {
                return true;
            }
        };

        new NonStrictExpectations() {
            {
                beidouTaskService.batchGet((List<Long>) any);
                result = DefaultBOUtils.getBeidouTasks(1, 1L);

                qiushiTaskService.batchGet((List<Long>) any);
                result = DefaultBOUtils.getQiushiTasks(1, 1L);

                dspTaskService.batchGet((List<Long>) any);
                result = DefaultBOUtils.getDSPTasks(1, 1L);

                newDspTaskService.batchGet((List<Long>) any);
                result = DefaultBOUtils.getNewDSPTasks(1, 1L);
            }
        };

        List<PresentedTaskDetail> list =
                generalTaskServiceImpl.getImgUrls(Arrays.asList(1L), GroupDataType.BEIDOU.getId().intValue());
        Assert.assertEquals(1, list.size());
        Assert.assertEquals("test", list.get(0).getImgUrl().get(0));

        list = generalTaskServiceImpl.getImgUrls(Arrays.asList(1L), GroupDataType.QIUSHI.getId().intValue());
        Assert.assertEquals(1, list.size());
        Assert.assertEquals("test", list.get(0).getImgUrl().get(0));

        list = generalTaskServiceImpl.getImgUrls(Arrays.asList(1L), GroupDataType.DSP.getId().intValue());
        Assert.assertEquals(1, list.size());
        Assert.assertEquals("test", list.get(0).getImgUrl().get(0));

        list = generalTaskServiceImpl.getImgUrls(Arrays.asList(1L), GroupDataType.NEWDSP.getId().intValue());
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(DefaultBOUtils.DEFAULT_URL, list.get(0).getImgUrl().get(0));

    }

    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateCompanyInfo() {
        generalTaskServiceImpl.updateCompanyInfo(Arrays.asList(new AccountInfo()), Arrays.asList(1),
                GroupDataType.NEWDSP.getId());
        new Verifications() {
            {
                newDspTaskService.updateCompanyInfo((List<AccountInfo>) any, (List<Integer>) any);
                times = 1;
            }
        };
    }

    @Test
    public void testDeleteExpiredAdDetail() {
        generalTaskServiceImpl.deleteExpiredAdDetail(1);
        new Verifications() {
            {
                beidouTaskService.deleteAdDetail((Integer) any);
                times = 1;

                qiushiTaskService.deleteAdDetail((Integer) any);
                times = 1;

                dspTaskService.deleteAdDetail((Integer) any);
                times = 1;

                newDspTaskService.deleteAdDetail((Integer) any);
                times = 1;
            }
        };
    }

}
