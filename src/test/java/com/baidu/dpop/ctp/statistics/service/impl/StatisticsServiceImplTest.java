package com.baidu.dpop.ctp.statistics.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.group.service.GroupService;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.statistics.service.StatisticsDimHisService;
import com.baidu.dpop.ctp.statistics.service.StatisticsTrendHisService;
import com.baidu.dpop.ctp.statistics.service.StatisticsUserHisService;
import com.baidu.dpop.ctp.statistics.vo.QueryConditionVo;
import com.baidu.dpop.ctp.statistics.vo.UserStatisticsItem;
import com.baidu.dpop.ctp.statistics.vo.UserStatisticsPageInfo;
import com.baidu.dpop.ctp.task.service.BeidouTaskService;
import com.baidu.dpop.ctp.task.service.DSPTaskService;
import com.baidu.dpop.ctp.task.service.QiushiTaskService;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

/**
 * StatisticsServiceImplTest
 * 
 * @author cgd
 * @date 2015年3月19日 下午4:16:49
 */
public class StatisticsServiceImplTest {

    @Tested
    private StatisticsServiceImpl statisticsService;

    @Injectable
    private TaskService taskService;
    @Injectable
    private GroupService groupService;
    @Injectable
    private BeidouTaskService beidouTaskService;
    @Injectable
    private DSPTaskService dSPTaskService;
    @Injectable
    private QiushiTaskService qiushiTaskService;

    @Injectable
    private StatisticsDimHisService statisticsDimHisService;
    @Injectable
    private StatisticsTrendHisService statisticsTrendHisService;
    @Injectable
    private StatisticsUserHisService statisticsUserHisService;

    @Test(expected = RuntimeException.class)
    public void testgetBasicInfoException() {
        new NonStrictExpectations() {
            {
                taskService.findById(anyLong);
                result = null;
            }
        };
        this.statisticsService.getBasicInfo(1);
    }

//    @Test
//    @SuppressWarnings("unchecked")
//    public void testgetBasicInfo() {
//        new NonStrictExpectations() {
//            {
//                taskService.findById(anyLong);
//                Task taskInfo = new Task();
//                taskInfo.setAddTime(new Date());
//                taskInfo.setAddUser("cgd");
//                taskInfo.setStatus((byte) 0);
//                taskInfo.setTaskName("task");
//                result = taskInfo;
//
//                groupService.getGroupListByCondition((Map<String, Object>) any);
//                List<Group> finishedGroupList = new ArrayList<Group>();
//                Group item = new Group();
//                item.setAdNum(3);
//                finishedGroupList.add(item);
//                result = finishedGroupList;
//
//            }
//        };
//        BasicStatisticsInfoVo ret = this.statisticsService.getBasicInfo(1);
//        Assert.assertNotNull(ret);
//        Assert.assertTrue(ret.getAddUser().equals("cgd"));
//        Assert.assertTrue(ret.getIsDoneAds() > 0);
//    }

    @Test
    public void testGetUserInfo() {
        new NonStrictExpectations() {
            {
                taskService.findById(anyLong);
                Task taskInfo = new Task();
                taskInfo.setAddTime(new Date());
                result = taskInfo;

                groupService.getUserStatisticsInfo(anyInt);
                List<UserStatisticsItem> data = new ArrayList<UserStatisticsItem>();
                UserStatisticsItem item = new UserStatisticsItem();
                item.setIsDoneAds(1);
                item.setIsDoneGroups(1);
                item.setUserName("cgd");
                data.add(item);
                result = data;
            }
        };

        QueryConditionVo vo = new QueryConditionVo();
        vo.init(1, 1, 1, null, null);
        UserStatisticsPageInfo ret = this.statisticsService.getUserInfo(vo);
        Assert.assertNotNull(ret);
        Assert.assertNotNull(ret.getList());

        new NonStrictExpectations() {
            {
                taskService.findById(anyLong);
                Task taskInfo = new Task();
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, 2014);
                taskInfo.setAddTime(cal.getTime());
                result = taskInfo;

                statisticsUserHisService.getStatisticsInfo(anyInt);
                List<UserStatisticsItem> data = new ArrayList<UserStatisticsItem>();
                UserStatisticsItem item = new UserStatisticsItem();
                item.setIsDoneAds(1);
                item.setIsDoneGroups(1);
                item.setUserName("cgd");
                data.add(item);
                result = data;
            }
        };
        vo = new QueryConditionVo();
        vo.init(1, 1, 1, null, null);
        ret = this.statisticsService.getUserInfo(vo);
        Assert.assertNotNull(ret);
        Assert.assertNotNull(ret.getList());
    }

//    @Test
//    @SuppressWarnings("unchecked")
//    public void testGetTrendInfo() {
//        new NonStrictExpectations() {
//            {
//                taskService.findById(anyLong);
//                Task taskInfo = new Task();
//                Calendar cal = Calendar.getInstance();
//                cal.set(Calendar.YEAR, 2014);
//                taskInfo.setAddTime(cal.getTime());
//                result = taskInfo;
//
//                statisticsTrendHisService.getTrendInfo(anyInt, anyString);
//                StatisticsTrendHis data = new StatisticsTrendHis();
//                data.setTime6(1);
//                data.setTime7(1);
//                data.setTime8(1);
//                data.setTime9(1);
//                data.setTime10(1);
//                data.setTime11(1);
//                data.setTime12(1);
//                data.setTime13(1);
//                data.setTime14(1);
//                data.setTime15(1);
//                data.setTime16(1);
//                data.setTime17(1);
//                data.setTime18(1);
//                data.setTime19(1);
//                data.setTime20(1);
//                result = data;
//            }
//        };
//        List<TrendStatisticsItem> ret = this.statisticsService.getTrendInfo(1,
//                "cgd");
//        Assert.assertNotNull(ret);
//        Assert.assertTrue(ret.size() > 0);
//
//        new NonStrictExpectations() {
//            {
//                taskService.findById(anyLong);
//                Task task = new Task();
//                task.setAddTime(new Date());
//                result = task;
//
//                groupService.getTrendInfo((Map<String, Object>) any);
//                TrendStatisticsItem item = new TrendStatisticsItem();
//                item.setIsDoneAds(4);
//                result = item;
//            }
//        };
//    }

    // @Test
    // public void testGetDimensionInfo() {
    // new NonStrictExpectations() {
    // {
    // taskService.findById(anyLong);
    // Task task = new Task();
    // task.setAddTime(new Date());
    // result = task;
    //
    // beidouTaskService.getFinishedTaskDimInfo(anyInt);
    // List<DimensionInfoItem> beidouData = new ArrayList<DimensionInfoItem>();
    // DimensionInfoItem item = new DimensionInfoItem();
    // beidouData.add(item);
    // result = beidouData;
    //
    // dSPTaskService.getFinishedTaskDimInfo(anyInt);
    // List<DimensionInfoItem> dspData = new ArrayList<DimensionInfoItem>();
    // dspData.add(item);
    // result = dspData;
    //
    // qiushiTaskService.getFinishedTaskDimInfo(anyInt);
    // List<DimensionInfoItem> qiushiData = new ArrayList<DimensionInfoItem>();
    // qiushiData.add(item);
    // result = qiushiData;
    // }
    // };
    // new MockUp<AdTagUtils>() {
    // @Mock
    // public void countStaticDimInfo(DimensionStatisticsInfoVo vo,
    // List<DimensionInfoItem> data, Byte dataType) {
    // vo.setBlackVulgarNum(1);
    // vo.setGrayVulagrNum(2);
    // }
    // };
    // DimensionStatisticsInfoVo ret =
    // this.statisticsService.getDimensionInfo(1);
    // Assert.assertNotNull(ret);
    // Assert.assertTrue(ret.getBlackVulgarNum() == 1);
    // Assert.assertTrue(ret.getGrayVulagrNum() == 2);
    //
    // new NonStrictExpectations() {
    // {
    // taskService.findById(anyLong);
    // Task taskInfo = new Task();
    // Calendar cal = Calendar.getInstance();
    // cal.set(Calendar.YEAR, 2014);
    // taskInfo.setAddTime(cal.getTime());
    // result = taskInfo;
    //
    // statisticsDimHisService.getDimInfo(anyInt);
    // DimensionStatisticsInfoVo vo = new DimensionStatisticsInfoVo();
    // vo.setBlackVulgarNum(2);
    // result = vo;
    // }
    // };
    // ret = this.statisticsService.getDimensionInfo(1);
    // Assert.assertNotNull(ret);
    // Assert.assertTrue(ret.getBlackVulgarNum() == 2);
    // }

}
