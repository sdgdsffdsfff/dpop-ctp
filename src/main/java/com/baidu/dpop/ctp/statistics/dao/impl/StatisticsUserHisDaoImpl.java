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
import com.baidu.dpop.ctp.statistics.bo.StatisticsUserHis;
import com.baidu.dpop.ctp.statistics.dao.StatisticsUserHisDao;
import com.baidu.dpop.ctp.statistics.dao.mapper.StatisticsUserHisMapper;

@Repository
public class StatisticsUserHisDaoImpl extends BaseDao<StatisticsUserHis, Long> implements StatisticsUserHisDao {

    @Autowired
    private StatisticsUserHisMapper statisticsMapper;

    @Override
    public GenericMapper<StatisticsUserHis, Long> getMapper() {
        return this.statisticsMapper;
    }

    @Override
    public void batchInsert(List<StatisticsUserHis> data) {
        Assert.notEmpty(data);

        this.statisticsMapper.batchInsert(data);
    }

    @Override
    public List<StatisticsUserHis> getStatisticsInfo(Integer taskId) {
        Assert.notNull(taskId);
        return this.statisticsMapper.getStatisticsInfo(taskId);
    }

}
