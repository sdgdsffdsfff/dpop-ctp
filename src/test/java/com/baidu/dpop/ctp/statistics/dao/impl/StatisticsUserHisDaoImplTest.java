package com.baidu.dpop.ctp.statistics.dao.impl;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.baidu.dpop.ctp.base.AbstractDAOTests;
import com.baidu.dpop.ctp.statistics.dao.StatisticsUserHisDao;

/**
 * StatisticsUserHisDaoImplTest
 * 
 * @author cgd
 * @date 2015年5月10日 下午4:17:59
 */
@SuppressWarnings("restriction")
public class StatisticsUserHisDaoImplTest extends AbstractDAOTests {

    @Resource
    private StatisticsUserHisDao statisticsUserHisDao;

    @Before
    public void setUp() {
        this.executeDatas("statistics/tb_statistics_user_his_dataset_init.sql");
    }

    @Test
    public void testbatchInsert() {
        // List<StatisticsUserHis> data = new ArrayList<StatisticsUserHis>();
        // StatisticsUserHis item = new StatisticsUserHis();
        // item.setTaskId(1);
        // item.setUserName("cgd");
        // item.setIsDoneAds(0);
        // item.setIsDoneGroups(0);
        // data.add(item);
        //
        // this.statisticsUserHisDao.batchInsert(data);
        // Assert.assertTrue(true);
    }

    @Test
    public void testgetStatisticsInfo() {
        // List<StatisticsUserHis> data = this.statisticsUserHisDao.getStatisticsInfo(1);
        // Assert.assertNotNull(data);
        // Assert.assertTrue(data.size() > 0);
    }

}
