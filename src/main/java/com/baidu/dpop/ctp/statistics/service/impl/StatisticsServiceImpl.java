package com.baidu.dpop.ctp.statistics.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baidu.dpop.ctp.adtag.service.AdTagService;
import com.baidu.dpop.ctp.adtag.utils.TagCountUtils;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.service.GroupService;
import com.baidu.dpop.ctp.group.vo.TrendInfoGetVo;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatus;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.statistics.bo.StatisticsTrendHis;
import com.baidu.dpop.ctp.statistics.service.StatisticsDimHisService;
import com.baidu.dpop.ctp.statistics.service.StatisticsService;
import com.baidu.dpop.ctp.statistics.service.StatisticsTrendHisService;
import com.baidu.dpop.ctp.statistics.service.StatisticsUserHisService;
import com.baidu.dpop.ctp.statistics.vo.BasicStatisticsInfoVo;
import com.baidu.dpop.ctp.statistics.vo.ListInfo;
import com.baidu.dpop.ctp.statistics.vo.QueryConditionVo;
import com.baidu.dpop.ctp.statistics.vo.TagInfo;
import com.baidu.dpop.ctp.statistics.vo.TrendStatisticsItem;
import com.baidu.dpop.ctp.statistics.vo.UserStatisticsItem;
import com.baidu.dpop.ctp.statistics.vo.UserStatisticsPageInfo;

//import com.baidu.dpop.ctp.task.vo.DimensionInfoItem;

/**
 * 创意标注统计相关service
 * 
 * @author cgd
 * @date 2015年3月17日 下午2:58:37
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private StatisticsDimHisService statisticsDimHisService;

    @Autowired
    private StatisticsTrendHisService statisticsTrendHisService;

    @Autowired
    private StatisticsUserHisService statisticsUserHisService;

    @Autowired
    private AdTagService adTagService;

    // 趋势时间间隔
    private static final Integer DEFAULT_TREND_TIME_RANGE = 15;

    @Override
    public BasicStatisticsInfoVo getBasicInfo(Integer taskId) {
        Assert.notNull(taskId);
        Task taskInfo = this.taskService.findById(Long.valueOf(taskId));
        if (taskInfo == null) {
            throw new RuntimeException("无法获取对应task信息  taskID:" + taskId);
        }

        // 任务基本信息
        BasicStatisticsInfoVo ret = new BasicStatisticsInfoVo();
        ret.setTaskId(taskId);
        ret.setTaskName(taskInfo.getTaskName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ret.setAddTime(sdf.format(taskInfo.getAddTime()));
        ret.setTaskStatus(taskInfo.getStatus());
        ret.setTaskStatusDesc(TaskStatus.get(taskInfo.getStatus()).getDesc());
        ret.setAddUser(taskInfo.getAddUser());

        // 已完成推广组进度信息
        List<Group> finishedGroupList =
                this.groupService.getGroupListByCondition(taskId, TaskStatus.FINISHED.getId().intValue(), null);
        if (CollectionUtils.isNotEmpty(finishedGroupList)) {
            ret.setIsDoneGroups(finishedGroupList.size());
            Integer isDoneAds = 0;
            for (Group item : finishedGroupList) {
                isDoneAds += item.getAdNum();
            }
            ret.setIsDoneAds(isDoneAds);
        } else {
            ret.setIsDoneAds(0);
            ret.setIsDoneGroups(0);
        }

        // 未完成推广组信息
        List<Group> notFinishedGroupList = this.groupService.getGroupListByCondition(taskId, null, 1);
        if (CollectionUtils.isNotEmpty(notFinishedGroupList)) {
            ret.setNotDoneGroups(notFinishedGroupList.size());
            Integer notDoneAds = 0;
            for (Group item : notFinishedGroupList) {
                notDoneAds += item.getAdNum();
            }
            ret.setNotDoneAds(notDoneAds);
        } else {
            ret.setNotDoneAds(0);
            ret.setNotDoneGroups(0);
        }

        return ret;
    }

    @Override
    public UserStatisticsPageInfo getUserInfo(QueryConditionVo conditionVo) {
        Assert.notNull(conditionVo);
        Assert.notNull(conditionVo.getTaskId());

        UserStatisticsPageInfo ret = new UserStatisticsPageInfo();
        // 获取task下统计信息中人员标注情况
        List<UserStatisticsItem> data = null;

        // 超期则去历史记录表中获取统计信息
        if (isExpireDate(conditionVo.getTaskId())) {
            data = this.statisticsUserHisService.getStatisticsInfo(conditionVo.getTaskId());
        } else {
            data = this.groupService.getUserStatisticsInfo(conditionVo.getTaskId());
        }

        // 设置排名
        if (CollectionUtils.isNotEmpty(data)) {
            for (int i = 0; i < data.size(); ++i) {
                data.get(i).setRanking(i + 1);
            }
        }
        ret.setPageInfo(conditionVo, data);

        return ret;
    }

    @Override
    public List<TrendStatisticsItem> getTrendInfo(Integer taskId, String userName) {
        Assert.notNull(taskId);

        // 保存返回结果
        List<TrendStatisticsItem> ret = null;

        // 超期则去历史记录表中获取统计信息
        if (isExpireDate(taskId)) {
            ret = this.getHisTrendInfo(taskId, userName);
        } else {
            ret = this.getNowTrendInfo(taskId, userName);
        }

        return ret;
    }

    private List<TrendStatisticsItem> getNowTrendInfo(Integer taskId, String userName) {
        Assert.notNull(taskId);

        // 保存返回结果
        List<TrendStatisticsItem> ret = new ArrayList<TrendStatisticsItem>();

        Task taskInfo = this.taskService.findById(Long.valueOf(taskId));
        if (taskInfo == null) {
            return ret;
        }

        // ------------ 初始化查询条件 -----------
        TrendInfoGetVo vo = new TrendInfoGetVo();
        vo.setTaskId(taskId);
        if (StringUtils.isNotEmpty(userName)) {
            vo.setUserName(userName);
        }

        // 查询起止时间
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Calendar nowTime = Calendar.getInstance();
        Calendar beginTime = Calendar.getInstance();
        beginTime.setTime(taskInfo.getAddTime());

        // 任务统计时间点比较逻辑
        Calendar compareTime = Calendar.getInstance();
        compareTime.setTime(taskInfo.getAddTime());
        compareTime.set(Calendar.HOUR_OF_DAY, 19);
        compareTime.set(Calendar.MINUTE, 0);
        compareTime.set(Calendar.SECOND, 0);

        // 如果任务导入时间在当日19:00以前，则算当日任务，否则为第二天的任务
        if (beginTime.compareTo(compareTime) < 0) {
            beginTime.set(Calendar.HOUR_OF_DAY, 6);
            beginTime.set(Calendar.MINUTE, 0);
            beginTime.set(Calendar.SECOND, 0);
        } else {
            beginTime.add(Calendar.DAY_OF_MONTH, 1);
            beginTime.set(Calendar.HOUR_OF_DAY, 6);
            beginTime.set(Calendar.MINUTE, 0);
            beginTime.set(Calendar.SECOND, 0);
        }

        // 查询时间 06:00 - 21:00 标注趋势数据
        for (int i = 0; i < DEFAULT_TREND_TIME_RANGE; ++i) {
            TrendStatisticsItem trendItem = new TrendStatisticsItem();
            trendItem.setTime(sdf.format(beginTime.getTime()));
            trendItem.setIsDoneAds(0);

            // 查询beginTime在当前时间之前则查询DB获取数据（之后的肯定没数据）
            if (beginTime.compareTo(nowTime) <= 0) {
                // 查询DB获取beginTime~endTime时间段标注数据
                vo.setStartTime(beginTime.getTime());
                Calendar endTime = Calendar.getInstance();
                endTime.setTime(beginTime.getTime());
                endTime.add(Calendar.HOUR_OF_DAY, 1);
                vo.setEndTime(endTime.getTime());

                TrendStatisticsItem data = null;
                data = this.groupService.getTrendInfo(vo);
                if (data != null && data.getIsDoneAds() != null) {
                    trendItem.setIsDoneAds(data.getIsDoneAds());
                }
            }

            ret.add(trendItem);
            // 计算下一小时的数据
            beginTime.add(Calendar.HOUR_OF_DAY, 1);

        }

        return ret;
    }

    public List<TrendStatisticsItem> getHisTrendInfo(Integer taskId, String userName) {
        Assert.notNull(taskId);

        // 保存返回结果
        List<TrendStatisticsItem> ret = new ArrayList<TrendStatisticsItem>();

        StatisticsTrendHis data = this.statisticsTrendHisService.getTrendInfo(taskId, userName);
        if (data != null) {
            TrendStatisticsItem item = new TrendStatisticsItem();
            item.setTime("06:00");
            item.setIsDoneAds(data.getTime6());
            ret.add(item);
            item = new TrendStatisticsItem();
            item.setTime("07:00");
            item.setIsDoneAds(data.getTime7());
            ret.add(item);
            item = new TrendStatisticsItem();
            item.setTime("08:00");
            item.setIsDoneAds(data.getTime8());
            ret.add(item);
            item = new TrendStatisticsItem();
            item.setTime("09:00");
            item.setIsDoneAds(data.getTime9());
            ret.add(item);
            item = new TrendStatisticsItem();
            item.setTime("10:00");
            item.setIsDoneAds(data.getTime10());
            ret.add(item);
            item = new TrendStatisticsItem();
            item.setTime("11:00");
            item.setIsDoneAds(data.getTime11());
            ret.add(item);
            item = new TrendStatisticsItem();
            item.setTime("12:00");
            item.setIsDoneAds(data.getTime12());
            ret.add(item);
            item = new TrendStatisticsItem();
            item.setTime("13:00");
            item.setIsDoneAds(data.getTime13());
            ret.add(item);
            item = new TrendStatisticsItem();
            item.setTime("14:00");
            item.setIsDoneAds(data.getTime14());
            ret.add(item);
            item = new TrendStatisticsItem();
            item.setTime("15:00");
            item.setIsDoneAds(data.getTime15());
            ret.add(item);
            item = new TrendStatisticsItem();
            item.setTime("16:00");
            item.setIsDoneAds(data.getTime16());
            ret.add(item);
            item = new TrendStatisticsItem();
            item.setTime("17:00");
            item.setIsDoneAds(data.getTime17());
            ret.add(item);
            item = new TrendStatisticsItem();
            item.setTime("18:00");
            item.setIsDoneAds(data.getTime18());
            ret.add(item);
            item = new TrendStatisticsItem();
            item.setTime("19:00");
            item.setIsDoneAds(data.getTime19());
            ret.add(item);
            item = new TrendStatisticsItem();
            item.setTime("20:00");
            item.setIsDoneAds(data.getTime20());
            ret.add(item);
        }

        return ret;
    }

    @Override
    public List<ListInfo<ListInfo<TagInfo>>> getDimensionInfo(Integer taskId) {
        Task task = taskService.findById(taskId.longValue());
        if (task == null || TaskStatus.isDELETED(task.getStatus())) {
            return null;
        } else {
            return getNowDimensionInfo(taskId);
        }
    }

    private List<ListInfo<ListInfo<TagInfo>>> getNowDimensionInfo(Integer taskId) {
        Integer page = 0;
        Integer size = 100000;

        List<String> tags = adTagService.getAdTag(taskId, page, size);
        Map<String, Map<String, Integer>> tempMap = null;

        while (!CollectionUtils.isEmpty(tags)) {
            tempMap = TagCountUtils.getTagDimensionInfo(tags, tempMap);
            page++;
            tags = adTagService.getAdTag(taskId, page * size, size);
        }

        return TagCountUtils.countTagDimensionInfo(tempMap);
    }

    /**
     * 判断给定的日期是否超期
     * */
    private boolean isExpireDate(Integer taskId) {
        Assert.notNull(taskId);

        Task taskInfo = this.taskService.findById(Long.valueOf(taskId));
        if (taskInfo == null) {
            throw new RuntimeException("Task not found by taskID: " + taskId);
        }

        Date addTime = taskInfo.getAddTime();
        Calendar expireCal = Calendar.getInstance();
        expireCal.add(Calendar.DAY_OF_MONTH, -100);
        expireCal.set(Calendar.HOUR_OF_DAY, 0);
        expireCal.set(Calendar.MINUTE, 0);
        expireCal.set(Calendar.SECOND, 0);

        // 超过期限
        if (addTime.compareTo(expireCal.getTime()) < 0) {
            return true;
        }
        return false;
    }

}
