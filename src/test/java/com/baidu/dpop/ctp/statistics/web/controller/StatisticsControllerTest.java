package com.baidu.dpop.ctp.statistics.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.statistics.service.StatisticsService;
import com.baidu.dpop.ctp.statistics.vo.BasicStatisticsInfoVo;
import com.baidu.dpop.ctp.statistics.vo.QueryConditionVo;
import com.baidu.dpop.ctp.statistics.vo.TrendStatisticsItem;
import com.baidu.dpop.ctp.statistics.vo.UserStatisticsPageInfo;
import com.baidu.dpop.frame.core.base.web.JsonResult;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

/**
 * StatisticsController Test
 * 
 * @author cgd
 * @date 2015年3月23日 下午4:59:51
 */
public class StatisticsControllerTest {

    @Tested
    private StatisticsController statisticsController;

    @Injectable
    private StatisticsService statisticsService;

    @Test
    public void testgetBasicInfo() {
        new NonStrictExpectations() {
            {
                statisticsService.getBasicInfo(anyInt);
                BasicStatisticsInfoVo data = new BasicStatisticsInfoVo();
                result = data;
            }
        };
        JsonResult ret = this.statisticsController.getBasicInfo(1);
        Assert.assertTrue(ret.isHasSuccess());
        Assert.assertNotNull(ret.getData());

        new NonStrictExpectations() {
            {
                statisticsService.getBasicInfo(anyInt);
                result = null;
            }
        };
        ret = this.statisticsController.getBasicInfo(1);
        Assert.assertTrue(!ret.isHasSuccess());
        Assert.assertNull(ret.getData());

        new NonStrictExpectations() {
            {
                statisticsService.getBasicInfo(anyInt);
                result = new RuntimeException();
            }
        };
        ret = this.statisticsController.getBasicInfo(1);
        Assert.assertTrue(!ret.isHasSuccess());
        Assert.assertTrue(ret.getResultInfo().indexOf("ERROR") >= 0);
    }

    @Test
    public void testGetUserInfo() {
        new NonStrictExpectations() {
            {
                statisticsService.getUserInfo((QueryConditionVo) any);
                UserStatisticsPageInfo data = new UserStatisticsPageInfo();
                result = data;
            }
        };
        JsonResult ret = this.statisticsController.getUserInfo(1, 1, 1, "isDoneGroups", "asc");
        Assert.assertTrue(ret.isHasSuccess());
        Assert.assertNotNull(ret.getData());

        new NonStrictExpectations() {
            {
                statisticsService.getUserInfo((QueryConditionVo) any);
                result = null;
            }
        };
        ret = this.statisticsController.getUserInfo(1, 1, 1, "isDoneGroups", "asc");
        Assert.assertTrue(!ret.isHasSuccess());
        Assert.assertNull(ret.getData());

        new NonStrictExpectations() {
            {
                statisticsService.getUserInfo((QueryConditionVo) any);
                result = new RuntimeException();
            }
        };
        ret = this.statisticsController.getUserInfo(1, 1, 1, "isDoneGroups", "asc");
        Assert.assertTrue(!ret.isHasSuccess());
        Assert.assertTrue(ret.getResultInfo().indexOf("ERROR") >= 0);
    }

    @Test
    public void testGetTrendInfo() {
        new NonStrictExpectations() {
            {
                statisticsService.getTrendInfo(anyInt, anyString);
                List<TrendStatisticsItem> data = new ArrayList<TrendStatisticsItem>();
                TrendStatisticsItem item = new TrendStatisticsItem();
                data.add(item);
                result = data;
            }
        };
        JsonResult ret = this.statisticsController.getTrendInfo(1, "cgd");
        Assert.assertTrue(ret.isHasSuccess());
        Assert.assertNotNull(ret.getData());

        new NonStrictExpectations() {
            {
                statisticsService.getTrendInfo(anyInt, anyString);
                result = null;
            }
        };
        ret = this.statisticsController.getTrendInfo(1, "cgd");
        Assert.assertTrue(!ret.isHasSuccess());
        Assert.assertNull(ret.getData());

        new NonStrictExpectations() {
            {
                statisticsService.getTrendInfo(anyInt, anyString);
                result = new RuntimeException();
            }
        };
        ret = this.statisticsController.getTrendInfo(1, "cgd");
        Assert.assertTrue(!ret.isHasSuccess());
        Assert.assertTrue(ret.getResultInfo().indexOf("ERROR") >= 0);
    }

    @Test
    public void testGetDimentionInfo() {
//        new NonStrictExpectations() {
//            {
//                statisticsService.getDimensionInfo(anyInt);
//                DimensionStatisticsInfoVo data = new DimensionStatisticsInfoVo();
//                result = data;
//            }
//        };
//        JsonResult ret = this.statisticsController.getDimentionInfo(1);
//        Assert.assertTrue(ret.isHasSuccess());
//        Assert.assertNotNull(ret.getData());
//
//        new NonStrictExpectations() {
//            {
//                statisticsService.getDimensionInfo(anyInt);
//                result = null;
//            }
//        };
//        ret = this.statisticsController.getDimentionInfo(1);
//        Assert.assertTrue(!ret.isHasSuccess());
//        Assert.assertNull(ret.getData());
//
//        new NonStrictExpectations() {
//            {
//                statisticsService.getDimensionInfo(anyInt);
//                result = new RuntimeException();
//            }
//        };
//        ret = this.statisticsController.getDimentionInfo(1);
//        Assert.assertTrue(!ret.isHasSuccess());
//        Assert.assertTrue(ret.getResultInfo().indexOf("ERROR") >= 0);
    }

}
