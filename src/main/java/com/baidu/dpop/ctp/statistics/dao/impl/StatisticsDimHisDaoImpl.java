/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.statistics.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.statistics.bo.StatisticsDimHis;
import com.baidu.dpop.ctp.statistics.dao.StatisticsDimHisDao;
import com.baidu.dpop.ctp.statistics.dao.mapper.StatisticsDimHisMapper;

@Repository
public class StatisticsDimHisDaoImpl extends BaseDao<StatisticsDimHis, Long> implements StatisticsDimHisDao {

    @Autowired
    private StatisticsDimHisMapper statisticsMapper;

    @Override
    public GenericMapper<StatisticsDimHis, Long> getMapper() {
        return this.statisticsMapper;
    }

    @Override
    public void batchInsert(List<StatisticsDimHis> data) {
        Assert.notEmpty(data);

        this.statisticsMapper.batchInsert(data);
    }

    @Override
    public StatisticsDimHis getDimInfo(Integer taskId) {
        Assert.notNull(taskId);
        
        return this.statisticsMapper.getDimInfo(taskId);
    }

}
