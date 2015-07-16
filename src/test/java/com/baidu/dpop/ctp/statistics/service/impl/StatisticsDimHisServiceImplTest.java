package com.baidu.dpop.ctp.statistics.service.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.baidu.dpop.ctp.statistics.bo.StatisticsDimHis;
import com.baidu.dpop.ctp.statistics.dao.StatisticsDimHisDao;

import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;

/**
 * StatisticsDimHisServiceImplTest
 * 
 * @author cgd
 * @date 2015年5月12日 下午5:39:24
 */
public class StatisticsDimHisServiceImplTest {

    @Tested
    private StatisticsDimHisServiceImpl statisticsDimHisService;
    @Injectable
    private StatisticsDimHisDao statisticsDao;

    @Test
    @SuppressWarnings("unchecked")
    public void testbatchInsert() {
        List<StatisticsDimHis> data = Arrays.asList(new StatisticsDimHis());

        this.statisticsDimHisService.batchInsert(data);
        new Verifications() {
            {
                statisticsDao.batchInsert((List<StatisticsDimHis>) any);
            }
        };
    }

    @Test
    public void testgetDimInfo() {

    }

}
