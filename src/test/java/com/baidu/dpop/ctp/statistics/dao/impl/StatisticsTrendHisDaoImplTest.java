package com.baidu.dpop.ctp.statistics.dao.impl;

import javax.annotation.Resource;


import org.junit.Before;
import org.junit.Test;

import com.baidu.dpop.ctp.base.AbstractDAOTests;
import com.baidu.dpop.ctp.statistics.dao.StatisticsTrendHisDao;

/**   
 * StatisticsTrendHisDaoImplTest
 * @author cgd  
 * @date 2015年5月10日 下午4:17:59 
 */
@SuppressWarnings("restriction")
public class StatisticsTrendHisDaoImplTest extends AbstractDAOTests {
    
    @Resource
    private StatisticsTrendHisDao statisticsTrendHisDao;

    @Before
    public void setUp() {
        this.executeDatas("statistics/tb_statistics_trend_his_dataset_init.sql");
    }

    @Test
    public void testbatchInsert() {
//        List<StatisticsTrendHis> data = new ArrayList<StatisticsTrendHis>();
//        StatisticsTrendHis item = new StatisticsTrendHis();
//        item.setTaskId(1);
//        item.setUserName("cgd");
//        item.setTime6(0);
//        item.setTime7(0);
//        item.setTime8(0);
//        item.setTime9(0);
//        item.setTime10(0);
//        item.setTime11(0);
//        item.setTime12(0);
//        item.setTime13(0);
//        item.setTime14(0);
//        item.setTime15(0);
//        item.setTime16(0);
//        item.setTime17(0);
//        item.setTime18(0);
//        item.setTime19(0);
//        item.setTime20(0);
//        data.add(item);
//        
//        this.statisticsTrendHisDao.batchInsert(data);
//        Assert.assertTrue(true);
    }
    
    @Test
    public void testgetTrendInfo() {
//        StatisticsTrendHis data = this.statisticsTrendHisDao.getTrendInfo(1, "cgd");
//        Assert.assertNotNull(data);
//        Assert.assertTrue(data.getUserName().equals("cgd"));
    }
    

}
