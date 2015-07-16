package com.baidu.dpop.ctp.adtag.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.dao.AdTagDao;
import com.baidu.dpop.ctp.adtag.vo.AdTagGetVo;
import com.baidu.dpop.ctp.base.AbstractDAOTests;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;

@SuppressWarnings("restriction")
public class AdTagDaoImplTest extends AbstractDAOTests {

    @Resource
    private AdTagDao adTagDao;

    @Before
    public void setUp() {
        this.executeDatas("adtag/adtag_dataset_init.sql");
        this.executeDatas("industryType/tradetype_dataset_init.sql");
    }

    @Test
    public void testSelectByGroup() {
        List<AdTag> result = adTagDao.selectByGroup(2L);
        Assert.assertEquals(4, result.size());
    }

    @Test
    public void testBatchInsert() {
        List<AdTag> list = new ArrayList<AdTag>();
        for (int i = 100; i < 105; i++) {
            AdTag tag = new AdTag();
            tag.setRefId(i + 0L);
            tag.setRefGroupId(5L);
            tag.setDataType(GroupDataType.BEIDOU.getId().intValue());
            tag.setTaskId(1);

            tag.setAdTag("0000----------");
            tag.setComment("");
            tag.setUpdateTime(new Date());
            tag.setUpdateUser("user1");
            list.add(tag);
        }

        Assert.assertEquals(5, adTagDao.batchInsert(list));

        list.clear();
        adTagDao.batchInsert(list);
        Assert.assertEquals(0, adTagDao.batchInsert(list));
    }

    // @Test
    // public void testBatchUpdate() {
    // List<AdTag> list = new ArrayList<AdTag>();
    // for (int i = 1; i < 5; i++) {
    // AdTag tag = new AdTag();
    // tag.setId(0L + i);
    // tag.setRefId(i + 0L);
    // tag.setRefGroupId(5L);
    // tag.setDataType(GroupDataType.BEIDOU.getId().intValue());
    // tag.setTaskId(1);
    //
    // tag.setAdTag("000-----------");
    // tag.setAdTradeIdLevel3(510101);
    // tag.setComment("");
    // tag.setUpdateTime(new Date());
    // tag.setUpdateUser("user1");
    // list.add(tag);
    // }
    //
    // AdTagSubmitVo vo = new AdTagSubmitVo();
    // vo.setList(list);
    // vo.setUpdateTime(new Date());
    // vo.setUpdateUser("user1");
    // adTagDao.batchUpdate(vo);
    // }

    @Test
    public void testBatchSelect() {
        List<Long> ids = new ArrayList<Long>();
        ids.add(1L);
        ids.add(2L);
        ids.add(100L);
        AdTagGetVo vo = new AdTagGetVo();
        vo.setDataType(GroupDataType.BEIDOU.getId().intValue());
        vo.setList(ids);
        Assert.assertEquals(2, adTagDao.batchSelect(vo).size());

        ids.clear();
        Assert.assertEquals(0, adTagDao.batchSelect(vo).size());
    }

    @Test
    public void testSelectTestedAdTagByRefId() {
        Assert.assertEquals(4, adTagDao.selectTestedAdTagByRefId(Arrays.asList(1L, 2L, 3L, 4L, 100L), (byte) 0).size());
        Set<Long> result = adTagDao.selectTestedAdTagByRefId(null, (byte) 0);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testSelectByRefId() {
        AdTagGetVo vo = new AdTagGetVo();
        vo.setRefId(1L);
        vo.setDataType(GroupDataType.BEIDOU.getId().intValue());
        Assert.assertEquals(Long.valueOf(1), adTagDao.selectByRefId(vo).getRefId());
    }

    @Test
    public void testgetAdCount() {
        List<Integer> taskIdList = Arrays.asList(1);
        GeneralTaskQueryVo vo = new GeneralTaskQueryVo();
        vo.setTaskIdList(taskIdList);
        vo.setDataType((byte) 0);
        Integer ret = this.adTagDao.selectTagedCount(vo);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testSelectTagedtags() {
        GeneralTaskQueryVo vo = new GeneralTaskQueryVo();
        vo.setDataType(GroupDataType.BEIDOU.getId());
        vo.setEndTimeString("2014-11-20 11:00:00");
        vo.setStartTimeString("2014-11-19 11:00:00");
        vo.setTaskIdList(Arrays.asList(1));
        Assert.assertEquals(8, adTagDao.selectTagedtags(vo).size());
    }
    
    @Test
    public void testSelectAdTag() {
        int page = 1;
        int size = 1;
        
        List<String> list = adTagDao.selectAdTag(1, page, size);
        Assert.assertEquals(1, list.size());
        Assert.assertEquals("2222----------", list.get(0));
    }

    @Test
    public void testDeleteAdDetail() {
        adTagDao.deleteAdDetail(1);
        Assert.assertEquals(0, adTagDao.selectByGroup(2L).size());
    }
}
