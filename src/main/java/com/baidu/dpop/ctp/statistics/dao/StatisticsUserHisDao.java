/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.statistics.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.statistics.bo.StatisticsUserHis;

public interface StatisticsUserHisDao extends GenericMapperDao<StatisticsUserHis, Long> {

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
    public List<StatisticsUserHis> getStatisticsInfo(Integer taskId);

}
