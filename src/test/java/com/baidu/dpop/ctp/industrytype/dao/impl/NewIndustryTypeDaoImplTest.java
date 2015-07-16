package com.baidu.dpop.ctp.industrytype.dao.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.dpop.ctp.base.AbstractDAOTests;
import com.baidu.dpop.ctp.industrytype.bo.NewIndustryType;
import com.baidu.dpop.ctp.industrytype.dao.NewIndustryTypeDao;

@SuppressWarnings("restriction")
public class NewIndustryTypeDaoImplTest extends AbstractDAOTests {

    @Resource
    private NewIndustryTypeDao newIndustryTyoeDao;

    @Before
    public void setUp() {
        this.executeDatas("industryType/tradetype_dataset_init.sql");
    }

    @Test
    public void testSelectLevel3Data() {
        Assert.assertEquals(30, newIndustryTyoeDao.selectLevel3Data().size());
    }

    @Test
    public void testBatchSelect() {
        Assert.assertEquals(3, newIndustryTyoeDao.batchSelect(Arrays.asList(510201, 510202, 510203)).size());
        List<NewIndustryType> result = newIndustryTyoeDao.batchSelect(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
    
    @Test
    public void batchUpdateLevel2IdAndNameNull() {
        newIndustryTyoeDao.batchUpdateLevel2IdAndName(null);
        Assert.assertEquals(0, newIndustryTyoeDao.selectByPrimaryKey(510101L).getLevel2Id().intValue());
    }

}
