package com.baidu.dpop.ctp.statistics.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.dpop.ctp.statistics.service.StatisticsService;
import com.baidu.dpop.ctp.statistics.vo.BasicStatisticsInfoVo;
import com.baidu.dpop.ctp.statistics.vo.ListInfo;
import com.baidu.dpop.ctp.statistics.vo.QueryConditionVo;
import com.baidu.dpop.ctp.statistics.vo.TagInfo;
import com.baidu.dpop.ctp.statistics.vo.TrendStatisticsItem;
import com.baidu.dpop.ctp.statistics.vo.UserStatisticsPageInfo;
import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;

/**
 * 标注统计Controller
 * 
 * @author cgd
 * @date 2015年3月23日 上午10:54:59
 */
@Controller
@RequestMapping(value = "statistics")
public class StatisticsController extends JsonBaseController {

    private static final Logger LOG = Logger.getLogger(StatisticsController.class);

    @Autowired
    private StatisticsService statisticsService;

    /**
     * 获取统计任务基本信息
     * */
    @RequestMapping(value = "/getBasicInfo.do")
    @ResponseBody
    public JsonResult getBasicInfo(Integer taskId) {
        Assert.notNull(taskId);

        try {
            BasicStatisticsInfoVo data = this.statisticsService.getBasicInfo(taskId);
            if (data != null) {
                return this.markSuccessResult(data, "success");
            }
        } catch (Exception e) {
            LOG.error("ERROR in GetBasicInfo: ", e);
            return this.markErrorResult("ERROR in GetBasicInfo: " + e.getMessage());
        }
        return this.markErrorResult("Error: Can Not Found Data for taskId " + taskId);
    }

    /**
     * 获取人员排名信息
     * */
    @RequestMapping(value = "/getUserInfo.do")
    @ResponseBody
    public JsonResult getUserInfo(Integer taskId, Integer size, Integer page, String orderBy, String order) {
        Assert.notNull(taskId);

        // 查询条件初始化
        QueryConditionVo vo = new QueryConditionVo();
        vo.init(taskId, size, page, orderBy, order);

        try {
            UserStatisticsPageInfo data = this.statisticsService.getUserInfo(vo);
            if (data != null) {
                return this.markSuccessResult(data, "success");
            }
        } catch (Exception e) {
            LOG.error("ERROR in GetUserInfo: ", e);
            return this.markErrorResult("ERROR in GetUserInfo: " + e.getMessage());
        }
        return this.markErrorResult("Can Not Found Page Data for taskId: " + taskId);
    }

    /**
     * 趋势图接口
     * */
    @RequestMapping(value = "/getTrendInfo.do")
    @ResponseBody
    public JsonResult getTrendInfo(Integer taskId, String username) {
        Assert.notNull(taskId);

        try {
            List<TrendStatisticsItem> data = this.statisticsService.getTrendInfo(taskId, username);
            if (data != null) {
                return this.markSuccessResult(data, "success");
            }
        } catch (Exception e) {
            LOG.error("ERROR in GetTrendInfo: ", e);
            return this.markErrorResult("ERROR in GetTrendInfo: " + e.getMessage());
        }
        return this.markErrorResult("Can Not Found Data for taskId: " + taskId);
    }

    /**
     * 各维度数据统计
     * */
    @RequestMapping(value = "/getDimentionInfo.do")
    @ResponseBody
    public JsonResult getDimentionInfo(Integer taskId) {
        Assert.notNull(taskId);

        try {
            List<ListInfo<ListInfo<TagInfo>>> data = this.statisticsService.getDimensionInfo(taskId);
            if (data != null) {
                return this.markSuccessResult(data, "success");
            }
        } catch (Exception e) {
            LOG.error("ERROR in GetDimentionInfo: ", e);
            return this.markErrorResult("ERROR in GetDimentionInfo: " + e.getMessage());
        }
        return this.markErrorResult("Can Not Found Data for taskId: " + taskId);
    }

}
