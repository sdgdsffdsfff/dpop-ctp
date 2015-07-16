package com.baidu.dpop.ctp.review.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.dpop.ctp.base.AbstractDAOTests;
import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.dao.ReviewGroupDao;
import com.baidu.dpop.ctp.review.vo.QueryConditionVo;
import com.baidu.dpop.ctp.review.vo.ReviewInfoItem;
import com.baidu.dpop.ctp.review.vo.WrongAdDetailVo;

/**
 * ReviewGroupDaoImplTest
 * 
 * @author cgd
 * @date 2015年3月31日 上午10:26:35
 */
@SuppressWarnings("restriction")
public class ReviewGroupDaoImplTest extends AbstractDAOTests {

    @Resource
    private ReviewGroupDao reviewGroupDao;

    @Before
    public void setUp() {
        this.executeDatas("review/review_group_dataset_init.sql");
    }

    @Test
    public void testGetInfoByReviewTaskId() {
        List<ReviewGroup> ret = this.reviewGroupDao.selectReviewGroupsByReviewTaskId(2222L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() > 0);
    }

    @Test
    public void testGetNotReviewedPageInfo() {
        QueryConditionVo vo = new QueryConditionVo();
        vo.setReviewTaskId(2222);
        List<ReviewInfoItem> ret = this.reviewGroupDao.selectNotReviewedPageInfo(vo);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() > 0);
    }

    @Test
    public void testGetReviewedRightPageInfo() {
        QueryConditionVo vo = new QueryConditionVo();
        vo.setReviewTaskId(2222);
        List<ReviewInfoItem> ret = this.reviewGroupDao.selectReviewedRightPageInfo(vo);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() > 0);
    }

    @Test
    public void testGetReviewedWrongPageInfo() {
        QueryConditionVo vo = new QueryConditionVo();
        vo.setReviewTaskId(2223);
        List<ReviewInfoItem> ret = this.reviewGroupDao.selectReviewedWrongPageInfo(vo);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() > 0);
    }
    
    @Test
    public void testrecycleAssignGroups() {
        this.reviewGroupDao.recycleAssignGroups(new Date());
        Assert.assertTrue(true);
    }

}
