/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.statistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.statistics.bo.StatisticsTrendHis;
import com.baidu.dpop.ctp.statistics.dao.StatisticsTrendHisDao;
import com.baidu.dpop.ctp.statistics.service.StatisticsTrendHisService;

@Service
public class StatisticsTrendHisServiceImpl extends BaseService<StatisticsTrendHis, Long> implements
        StatisticsTrendHisService {

    @Autowired
    private StatisticsTrendHisDao statisticsDao;

    @Override
    public GenericMapperDao<StatisticsTrendHis, Long> getDao() {
        return statisticsDao;
    }

    @Override
    public void batchInsert(List<StatisticsTrendHis> data) {
        Assert.notEmpty(data);
        this.statisticsDao.batchInsert(data);
    }

    @Override
    public StatisticsTrendHis getTrendInfo(Integer taskId, String userName) {
        Assert.notNull(taskId);
        return this.statisticsDao.getTrendInfo(taskId, userName);
    }
}
