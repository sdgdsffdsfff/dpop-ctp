package com.baidu.dpop.ctp.review.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.dao.ReviewGroupDao;
import com.baidu.dpop.ctp.review.vo.QueryConditionVo;
import com.baidu.dpop.ctp.review.vo.ReviewInfoItem;
import com.baidu.dpop.ctp.review.vo.ReviewPageInfoVo;
import com.baidu.dpop.ctp.review.vo.WrongAdDetailVo;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

/**
 * ReviewGroupServiceImplTest
 * 
 * @author cgd
 * @date 2015年3月31日 上午10:56:09
 */
public class ReviewGroupServiceImplTest {

    @Tested
    private ReviewGroupServiceImpl reviewGroupService;

    @Injectable
    private ReviewGroupDao reviewGroupDao;

    @Test
    public void testGetInfoByReviewTaskId() {
        new NonStrictExpectations() {
            {
                reviewGroupDao.selectReviewGroupsByReviewTaskId(anyLong);
                result = new ArrayList<ReviewGroup>();
            }
        };

        List<ReviewGroup> ret = reviewGroupService.getReviewGroupsByReviewTaskId(1L);
        Assert.assertTrue(ret.size() == 0);
    }

    @Test
    public void testGetNotReviewedPageInfo() {
        new NonStrictExpectations() {
            {
                reviewGroupDao.selectNotReviewedPageInfo((QueryConditionVo) any);
                result = new ArrayList<ReviewInfoItem>();
            }
        };
        QueryConditionVo vo = new QueryConditionVo();
        vo.setReviewTaskId(1);
        vo.setPageNo(1);
        vo.setPageSize(20);
        ReviewPageInfoVo ret = reviewGroupService.getNotReviewedPageInfo(vo);
        Assert.assertTrue(ret.getList().size() == 0);
    }

    @Test
    public void testGetReviewedRightPageInfo() {
        new NonStrictExpectations() {
            {
                reviewGroupDao.selectReviewedRightPageInfo((QueryConditionVo) any);
                result = new ArrayList<ReviewInfoItem>();
            }
        };
        QueryConditionVo vo = new QueryConditionVo();
        vo.setReviewTaskId(1);
        vo.setPageNo(1);
        vo.setPageSize(20);
        ReviewPageInfoVo ret = reviewGroupService.getReviewedRightPageInfo(vo);
        Assert.assertTrue(ret.getList().size() == 0);
    }

    @Test
    public void testGetReviewedWrongPageInfo() {
        new NonStrictExpectations() {
            {
                reviewGroupDao.selectReviewedWrongPageInfo((QueryConditionVo) any);
                result = new ArrayList<ReviewInfoItem>();
            }
        };
        QueryConditionVo vo = new QueryConditionVo();
        vo.setReviewTaskId(1);
        vo.setPageNo(1);
        vo.setPageSize(20);
        ReviewPageInfoVo ret = reviewGroupService.getReviewedWrongPageInfo(vo);
        Assert.assertTrue(ret.getList().size() == 0);
    }

    @Test
    public void testgetIsReviewedAdCount() {
        new NonStrictExpectations() {
            {
                reviewGroupDao.selectIsReviewedAdCount(anyInt);
                result = 10;
            }
        };
        Integer ret = this.reviewGroupService.getIsReviewedAdCount(1);
        Assert.assertTrue(ret == 10);
    }

    @Test
    public void testgetReviewedAdDetail() {
        new NonStrictExpectations() {
            {
                reviewGroupDao.selectReviewedAdDetail(anyInt, anyInt);
                result = new ArrayList<WrongAdDetailVo>();
            }
        };
        List<WrongAdDetailVo> ret = this.reviewGroupService.getReviewedAdDetail(1, 1);
        Assert.assertTrue(ret.size() == 0);
    }
    
    @Test
    public void testrecycleAssignGroups() {
        new NonStrictExpectations() {
            {
                reviewGroupDao.recycleAssignGroups((Date)any);
            }
        };
        this.reviewGroupService.recycleAssignGroups(new Date());
        Assert.assertTrue(true);
    }

}
