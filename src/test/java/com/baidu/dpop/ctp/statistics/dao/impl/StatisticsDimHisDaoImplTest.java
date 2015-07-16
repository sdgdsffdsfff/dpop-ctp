package com.baidu.dpop.ctp.statistics.dao.impl;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.baidu.dpop.ctp.base.AbstractDAOTests;
import com.baidu.dpop.ctp.statistics.dao.StatisticsDimHisDao;

/**   
 * StatisticsDimHisDaoImplTest
 * @author cgd  
 * @date 2015年5月8日 下午5:31:32 
 */
@SuppressWarnings("restriction")
public class StatisticsDimHisDaoImplTest extends AbstractDAOTests {
    
    @Resource
    private StatisticsDimHisDao statisticsDimHisDao;

    @Before
    public void setUp() {
        this.executeDatas("statistics/tb_statistics_dim_his_dataset_init.sql");
    }

    @Test
    public void testbatchInsert() {
//        List<StatisticsDimHis> data = new ArrayList<StatisticsDimHis>();
//        StatisticsDimHis item = new StatisticsDimHis();
//        item.setTaskId(1);
//        item.setBlackVulgarNum(0);
//        item.setGrayVulgarNum(0);
//        item.setHighBeautyNum(0);
//        item.setIsCheatNum(0);
//        item.setIsHighDangerNum(0);
//        item.setLowBeautyNum(0);
//        item.setNormalBeautyNum(0);
//        item.setNotCheatNum(0);
//        item.setNotHighDangerNum(0);
//        item.setWhiteVulgarNum(0);
//        data.add(item);
//        
//        this.statisticsDimHisDao.batchInsert(data);
//        Assert.assertTrue(true);
    }
    
    @Test
    public void testgetDimInfo() {
//        StatisticsDimHis ret =  this.statisticsDimHisDao.getDimInfo(1);
//        Assert.assertNotNull(ret);
//        Assert.assertTrue(ret.getTaskId() == 1);
    }

}
