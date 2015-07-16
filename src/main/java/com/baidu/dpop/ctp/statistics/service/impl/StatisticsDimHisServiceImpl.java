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
import com.baidu.dpop.ctp.statistics.bo.StatisticsDimHis;
import com.baidu.dpop.ctp.statistics.dao.StatisticsDimHisDao;
import com.baidu.dpop.ctp.statistics.service.StatisticsDimHisService;

@Service
public class StatisticsDimHisServiceImpl extends BaseService<StatisticsDimHis, Long> implements StatisticsDimHisService {

    @Autowired
    private StatisticsDimHisDao statisticsDao;

    @Override
    public GenericMapperDao<StatisticsDimHis, Long> getDao() {
        return statisticsDao;
    }

    @Override
    public void batchInsert(List<StatisticsDimHis> data) {
        Assert.notEmpty(data);

        this.statisticsDao.batchInsert(data);
    }
}
