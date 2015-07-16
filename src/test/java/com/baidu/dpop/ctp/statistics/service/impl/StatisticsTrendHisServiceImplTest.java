package com.baidu.dpop.ctp.statistics.service.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;

import com.baidu.dpop.ctp.statistics.bo.StatisticsTrendHis;
import com.baidu.dpop.ctp.statistics.dao.StatisticsTrendHisDao;

/**
 * StatisticsTrendHisServiceImplTest
 * 
 * @author cgd
 * @date 2015年5月12日 下午5:33:37
 */
public class StatisticsTrendHisServiceImplTest {

    @Tested
    private StatisticsTrendHisServiceImpl statisticsTrendHisService;
    @Injectable
    private StatisticsTrendHisDao statisticsDao;

    @SuppressWarnings("unchecked")
    @Test
    public void testbatchInsert() {
        List<StatisticsTrendHis> data = Arrays.asList(new StatisticsTrendHis());

        this.statisticsTrendHisService.batchInsert(data);
        new Verifications() {
            {
                statisticsDao.batchInsert((List<StatisticsTrendHis>) any);
            }
        };
    }
    
    @Test
    public void testgetTrendInfo() {
        this.statisticsTrendHisService.getTrendInfo(1, "cgd");
        
        new Verifications() {
            {
                statisticsDao.getTrendInfo(anyInt, anyString);
            }
        };
    }

}
