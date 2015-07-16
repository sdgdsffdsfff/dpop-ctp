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
import com.baidu.dpop.ctp.statistics.bo.StatisticsTrendHis;
import com.baidu.dpop.ctp.statistics.dao.StatisticsTrendHisDao;
import com.baidu.dpop.ctp.statistics.dao.mapper.StatisticsTrendHisMapper;

@Repository
public class StatisticsTrendHisDaoImpl extends BaseDao<StatisticsTrendHis, Long> implements StatisticsTrendHisDao {

    @Autowired
    private StatisticsTrendHisMapper statisticsMapper;

    @Override
    public GenericMapper<StatisticsTrendHis, Long> getMapper() {
        return this.statisticsMapper;
    }

    @Override
    public void batchInsert(List<StatisticsTrendHis> data) {
        Assert.notEmpty(data);

        this.statisticsMapper.batchInsert(data);
    }

    @Override
    public StatisticsTrendHis getTrendInfo(Integer taskId, String userName) {
        Assert.notNull(taskId);
        return this.statisticsMapper.getTrendInfo(taskId, userName);
    }

}
