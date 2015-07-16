package com.baidu.dpop.ctp.review.web.controller;

import org.junit.Assert;
import org.junit.Test;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import com.baidu.dpop.ctp.review.service.ReviewService;
import com.baidu.dpop.ctp.review.vo.ReviewTaskBasicInfoVo;
import com.baidu.dpop.frame.core.base.web.JsonResult;

/**   
 * ReviewStatisticsControllerTest
 * @author cgd  
 * @date 2015年4月2日 下午4:09:01 
 */
public class ReviewStatisticsControllerTest {
    @Tested
    private ReviewStatisticsController reviewStatisticsController;

    @Injectable
    private ReviewService reviewService;
    
    @Test
    public void testgetReviewTaskAccuracy() {
        new NonStrictExpectations() {
            {
                reviewService.getReviewTaskAccuracy(anyInt);
                result = new RuntimeException("e");
            }
        };
        JsonResult ret = this.reviewStatisticsController.getReviewTaskAccuracy(1);
        Assert.assertTrue(ret.getSuccess().equals("false"));

        new NonStrictExpectations() {
            {
                reviewService.getReviewTaskAccuracy(anyInt);
                result = null;
            }
        };
        ret = this.reviewStatisticsController.getReviewTaskAccuracy(1);
        Assert.assertTrue(ret.getSuccess().equals("false"));

        new NonStrictExpectations() {
            {
                reviewService.getReviewTaskAccuracy(anyInt);
                result = new ReviewTaskBasicInfoVo();
            }
        };
        ret = this.reviewStatisticsController.getReviewTaskAccuracy(1);
        Assert.assertNotNull(ret.getResultInfo());
    }
    
    @Test
    public void testgetReviewTagAccuracy() {
        new NonStrictExpectations() {
            {
                reviewService.getReviewTagAccuracy(anyInt);
                result = new RuntimeException("e");
            }
        };
        JsonResult ret = this.reviewStatisticsController.getReviewTagAccuracy(1);
        Assert.assertTrue(ret.getSuccess().equals("false"));

        new NonStrictExpectations() {
            {
                reviewService.getReviewTagAccuracy(anyInt);
                result = null;
            }
        };
        ret = this.reviewStatisticsController.getReviewTagAccuracy(1);
        Assert.assertTrue(ret.getSuccess().equals("false"));

        new NonStrictExpectations() {
            {
                reviewService.getReviewTagAccuracy(anyInt);
                result = new ReviewTaskBasicInfoVo();
            }
        };
        ret = this.reviewStatisticsController.getReviewTagAccuracy(1);
        Assert.assertNotNull(ret.getResultInfo());
    }

}
