/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.statistics.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.statistics.bo.StatisticsUserHis;
import com.baidu.dpop.ctp.statistics.dao.StatisticsUserHisDao;
import com.baidu.dpop.ctp.statistics.service.StatisticsUserHisService;
import com.baidu.dpop.ctp.statistics.vo.UserStatisticsItem;

@Service
public class StatisticsUserHisServiceImpl extends BaseService<StatisticsUserHis, Long> implements
        StatisticsUserHisService {

    @Autowired
    private StatisticsUserHisDao statisticsDao;

    @Override
    public GenericMapperDao<StatisticsUserHis, Long> getDao() {
        return statisticsDao;
    }

    @Override
    public void batchInsert(List<StatisticsUserHis> data) {
        Assert.notEmpty(data);
        this.statisticsDao.batchInsert(data);
    }

    @Override
    public List<UserStatisticsItem> getStatisticsInfo(Integer taskId) {
        Assert.notNull(taskId);

        List<UserStatisticsItem> ret = new ArrayList<UserStatisticsItem>();

        // 获取指定task的历史统计信息
        List<StatisticsUserHis> data = this.statisticsDao.getStatisticsInfo(taskId);
        if (CollectionUtils.isNotEmpty(data)) {
            for (int i = 0; i < data.size(); ++i) {
                StatisticsUserHis userHis = data.get(i);
                UserStatisticsItem item = new UserStatisticsItem();
                item.setRanking(i + 1);
                item.setIsDoneAds(userHis.getIsDoneAds());
                item.setIsDoneGroups(userHis.getIsDoneGroups());
                item.setUserName(userHis.getUserName());

                ret.add(item);
            }
        }

        return ret;
    }
}
