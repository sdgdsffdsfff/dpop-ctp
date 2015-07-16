package com.baidu.dpop.ctp.adtag.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.adtag.bo.TagType;
import com.baidu.dpop.ctp.adtag.bo.TagTypeTreeNode;
import com.baidu.dpop.ctp.adtag.dao.TagTypeDao;
import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;
import com.baidu.dpop.ctp.base.DefaultBOUtils;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;

public class TagTypeServiceImplTest {

    @Tested
    TagTypeServiceImpl tagTypeServiceImpl;

    @Injectable
    TagTypeDao tagTypeDao;

    @Test
    public void testGetByTradeId() {
        tagTypeServiceImpl.getByTradeId(51);
        new Verifications() {
            {
                tagTypeDao.selectByTradeId((Integer) any);
            }
        };
        
        tagTypeServiceImpl.getByTradeId(5101);
        new Verifications() {
            {
                tagTypeDao.selectByTradeId((Integer) any);
            }
        };
        
        tagTypeServiceImpl.getByTradeId(510101);
        new Verifications() {
            {
                tagTypeDao.selectByTradeId((Integer) any);
            }
        };
    }

    @Test
    public void testGetAll() {
        new NonStrictExpectations() {
            {
                tagTypeDao.selectAll();
                List<TagType> list = new ArrayList<TagType>();
                TagType type = new TagType();
                type.setId(1L);
                type.setTagType("beauty");
                type.setTradeId(510000);
                list.add(type);

                type = new TagType();
                type.setId(1L);
                type.setTagType("beauty");
                type.setTradeId(510100);
                list.add(type);

                type = new TagType();
                type.setId(1L);
                type.setTagType("beauty");
                type.setTradeId(510101);
                list.add(type);
                result = list;
            }
        };

        Map<Integer, TagTypeTreeNode> map = tagTypeServiceImpl.getAll();
        TagTypeTreeNode node = map.get(510000);
        Assert.assertNotNull(node);

        node = node.getChild(510100);
        Assert.assertNotNull(node);

        node = node.getChild(510101);
        Assert.assertNotNull(node);
        Assert.assertNotNull(node);
    }
    
    @Test
    public void testGetByIdList() {
        new NonStrictExpectations() {
            {
                tagTypeServiceImpl.getByTradeId(withEqual(510101));
                result = new HashSet<String>(Arrays.asList("cheat", "falseInfo", "flash"));
                
                tagTypeServiceImpl.getByTradeId(withEqual(510102));
                result = new HashSet<String>(Arrays.asList("falseEffect", "falseInfo"));
            }
        };
        
        List<String> list = tagTypeServiceImpl.getByTradeIdList(Arrays.asList(510101, 510102), (byte) 1);
        System.out.println(list);
        Assert.assertEquals(4, list.size());
        Assert.assertEquals("beauty", list.get(0));
        Assert.assertEquals("vulgar", list.get(1));
        Assert.assertEquals("danger", list.get(2));
        Assert.assertEquals("falseInfo", list.get(3));
        
        list = tagTypeServiceImpl.getByTradeIdList(null, (byte) 3);
        Assert.assertEquals(3, list.size());
    }
    
    @Test
    public void testChangeTradeType() {
        new NonStrictExpectations() {
            {
                tagTypeDao.selectByTradeId((Integer) any);
                result = DefaultBOUtils.getAllTagType();
            }
        };

        Assert.assertEquals(AdTagUtils.TAGGROUPLIST.size(), tagTypeServiceImpl
                .changeTradeType(510101, 3).getList().size());

    }
}
