package com.baidu.dpop.ctp.statistics.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;

import com.baidu.dpop.ctp.statistics.bo.StatisticsUserHis;
import com.baidu.dpop.ctp.statistics.dao.StatisticsUserHisDao;
import com.baidu.dpop.ctp.statistics.vo.UserStatisticsItem;

/**
 * StatisticsUserHisServiceImplTest
 * 
 * @author cgd
 * @date 2015年5月12日 下午5:28:39
 */
public class StatisticsUserHisServiceImplTest {
    @Tested
    private StatisticsUserHisServiceImpl statisticsUserHisService;
    @Injectable
    private StatisticsUserHisDao statisticsDao;
    
    @SuppressWarnings("unchecked")
    @Test
    public void testbatchInsert() {
        List<StatisticsUserHis> data = Arrays.asList(new StatisticsUserHis());

        this.statisticsUserHisService.batchInsert(data);
        new Verifications() {
            {
                statisticsDao.batchInsert((List<StatisticsUserHis>) any);
            }
        };
    }
    
    @Test
    public void testgetStatisticsInfo() {
        new NonStrictExpectations() {
            {
                statisticsDao.getStatisticsInfo(anyInt);
                List<StatisticsUserHis> data = new ArrayList<StatisticsUserHis>();
                StatisticsUserHis item = new StatisticsUserHis();
                item.setUserName("cgd");
                item.setIsDoneAds(1);
                item.setIsDoneGroups(1);
                data.add(item);
                
                result = data;
            }
        };
        
        List<UserStatisticsItem> ret = this.statisticsUserHisService.getStatisticsInfo(1);
        Assert.assertTrue(ret.size() == 1);
        Assert.assertTrue(ret.get(0).getIsDoneAds() == 1);
    }
}
