package com.baidu.dpop.ctp.adtag.dao.impl;

import java.util.Arrays;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.baidu.dpop.ctp.adtag.bo.TagType;
import com.baidu.dpop.ctp.adtag.dao.TagTypeDao;
import com.baidu.dpop.ctp.base.AbstractDAOTests;

@SuppressWarnings("restriction")
public class TagTypeDaoImplTest extends AbstractDAOTests {

    @Resource
    private TagTypeDao tagTypeDao;

    @Before
    public void setUp() {
        this.executeDatas("adtag/tagtype_dataset_init.sql");
    }

    @Test
    public void testSelectByTradeId() {
        Assert.assertEquals(9, tagTypeDao.selectByTradeId(820000).size());
    }

    @Test
    public void testSelectAll() {
        Assert.assertEquals(31, tagTypeDao.selectAll().size());
    }

    @Test
    public void testBatchInsert() {
        TagType type = new TagType();
        type.setTagType("nothing");
        type.setTradeId(1);
        tagTypeDao.batchInsertTagTypes(Arrays.asList(type));
        Assert.assertEquals(32, tagTypeDao.selectAll().size());
    }

}
