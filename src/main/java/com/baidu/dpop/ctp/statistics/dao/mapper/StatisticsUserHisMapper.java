/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.statistics.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.statistics.bo.StatisticsUserHis;

public interface StatisticsUserHisMapper extends GenericMapper<StatisticsUserHis, Long> {

    /**
     * 数据批量插入接口
     * 
     * @param data 待插入数据list
     * */
    public void batchInsert(@Param("data") List<StatisticsUserHis> data);

    /**
     * 查询指定任务ID对应的历史维度统计数据
     * 
     * @param taskId 任务ID
     * */
    public List<StatisticsUserHis> getStatisticsInfo(@Param("taskId") Integer taskId);

}