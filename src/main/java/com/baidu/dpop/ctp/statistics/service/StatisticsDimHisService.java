/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.statistics.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.dpop.ctp.statistics.bo.StatisticsDimHis;

public interface StatisticsDimHisService extends GenericMapperService<StatisticsDimHis, Long> {

    /**
     * 数据批量插入接口
     * 
     * @param data 待插入数据list
     * */
    public void batchInsert(List<StatisticsDimHis> data);

}
