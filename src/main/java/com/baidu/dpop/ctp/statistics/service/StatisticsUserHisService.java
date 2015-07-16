/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.statistics.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.dpop.ctp.statistics.bo.StatisticsUserHis;
import com.baidu.dpop.ctp.statistics.vo.UserStatisticsItem;

public interface StatisticsUserHisService extends GenericMapperService<StatisticsUserHis, Long> {

    /**
     * 数据批量插入接口
     * 
     * @param data 待插入数据list
     * */
    public void batchInsert(List<StatisticsUserHis> data);

    /**
     * 查询指定任务ID对应的历史维度统计数据
     * 
     * @param taskId 任务ID
     * */
    public List<UserStatisticsItem> getStatisticsInfo(Integer taskId);

}
