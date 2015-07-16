package com.baidu.dpop.ctp.review.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.dpop.ctp.review.service.ReviewService;
import com.baidu.dpop.ctp.review.vo.ReviewTagStatisticsVo;
import com.baidu.dpop.ctp.review.vo.ReviewTaskAccuracyVo;
import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;

/**
 * 审核详情统计页Controller
 * 
 * @author cgd
 * @date 2015年3月31日 下午4:46:26
 */
@Controller
@RequestMapping(value = "review")
public class ReviewStatisticsController extends JsonBaseController {

    private static final Logger LOG = Logger.getLogger(ReviewStatisticsController.class);

    @Autowired
    private ReviewService reviewService;

    /**
     * 获取任务准确率统计信息
     * */
    @RequestMapping(value = "/getReviewTaskAccuracy.do")
    @ResponseBody
    public JsonResult getReviewTaskAccuracy(Integer reviewTaskId) {
        Assert.notNull(reviewTaskId);
//        try {
//            ReviewTaskAccuracyVo vo = this.reviewService.getReviewTaskAccuracy(reviewTaskId);
//            if (vo != null) {
//                return this.markSuccessResult(vo, "success");
//            }
//        } catch (Exception e) {
//            LOG.error("getReviewTaskAccuracy Error: ", e);
//            return this.markErrorResult("getReviewTaskAccuracy Error: " + e.getMessage());
//        }

        return this.markErrorResult("getReviewTaskAccuracy: Get Data NULL.");
    }

    /**
     * 获取标签审核统计信息
     * */
    @RequestMapping(value = "/getReviewTagAccuracy.do")
    @ResponseBody
    public JsonResult getReviewTagAccuracy(Integer reviewTaskId) {
        Assert.notNull(reviewTaskId);
        try {
            ReviewTagStatisticsVo vo = this.reviewService.getReviewTagAccuracy(reviewTaskId);
            if (vo != null) {
                return this.markSuccessResult(vo, "success");
            }
        } catch (Exception e) {
            LOG.error("getReviewTagAccuracy Error: ", e);
            return this.markErrorResult("getReviewTagAccuracy Error: " + e.getMessage());
        }

        return this.markErrorResult("getReviewTagAccuracy: Get Data NULL.");
    }

}
