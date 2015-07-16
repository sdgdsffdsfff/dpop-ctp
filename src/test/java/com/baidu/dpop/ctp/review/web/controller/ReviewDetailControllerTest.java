package com.baidu.dpop.ctp.review.web.controller;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.review.service.ReviewService;
import com.baidu.dpop.ctp.review.vo.QueryConditionVo;
import com.baidu.dpop.ctp.review.vo.ReviewPageInfoVo;
import com.baidu.dpop.ctp.review.vo.ReviewTaskBasicInfoVo;
import com.baidu.dpop.frame.core.base.web.JsonResult;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

/**
 * ReviewDetailControllerTest
 * 
 * @author cgd
 * @date 2015年4月2日 下午3:34:54
 */
public class ReviewDetailControllerTest {

    @Tested
    private ReviewDetailController reviewDetailController;

    @Injectable
    private ReviewService reviewService;

    @Test
    public void testgetBasicInfo() {
        new NonStrictExpectations() {
            {
                reviewService.getBasicInfo(anyInt);
                result = new RuntimeException("e");
            }
        };
        JsonResult ret = this.reviewDetailController.getBasicInfo(1);
        Assert.assertTrue(ret.getSuccess().equals("false"));

        new NonStrictExpectations() {
            {
                reviewService.getBasicInfo(anyInt);
                result = null;
            }
        };
        ret = this.reviewDetailController.getBasicInfo(1);
        Assert.assertTrue(ret.getSuccess().equals("false"));

        new NonStrictExpectations() {
            {
                reviewService.getBasicInfo(anyInt);
                result = new ReviewTaskBasicInfoVo();
            }
        };
        ret = this.reviewDetailController.getBasicInfo(1);
        Assert.assertNotNull(ret.getResultInfo());

    }

    @Test
    public void testgetReviewPageInfo() {
        new NonStrictExpectations() {
            {
                reviewService.getPageInfo((QueryConditionVo) any);
                result = new RuntimeException("e");
            }
        };
        JsonResult ret = this.reviewDetailController.getReviewPageInfo(1, 1, 20, 1);
        Assert.assertTrue(ret.getSuccess().equals("false"));

        new NonStrictExpectations() {
            {
                reviewService.getPageInfo((QueryConditionVo) any);
                result = new ReviewPageInfoVo();
            }
        };
        ret = this.reviewDetailController.getReviewPageInfo(1, 1, 20, 1);
        Assert.assertNotNull(ret.getResultInfo());
    }

}
