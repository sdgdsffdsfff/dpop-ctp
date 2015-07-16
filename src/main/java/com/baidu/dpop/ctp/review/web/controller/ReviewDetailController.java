package com.baidu.dpop.ctp.review.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.dpop.ctp.review.service.ReviewService;
import com.baidu.dpop.ctp.review.vo.QueryConditionVo;
import com.baidu.dpop.ctp.review.vo.ReviewPageInfoVo;
import com.baidu.dpop.ctp.review.vo.ReviewTaskBasicInfoVo;
import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;

/**
 * 审核详情Controller
 * 
 * @author cgd
 * @date 2015年3月29日 下午12:20:31
 */
@Controller
@RequestMapping(value = "review")
public class ReviewDetailController extends JsonBaseController {

    private static final Logger LOG = Logger.getLogger(ReviewDetailController.class);

    @Autowired
    private ReviewService reviewService;
    
    /**
     * 获取统计任务基本信息
     * */
    @RequestMapping(value = "/getBasicInfo.do")
    @ResponseBody
    public JsonResult getBasicInfo(Integer reviewTaskId) {
        Assert.notNull(reviewTaskId);

        try {
            ReviewTaskBasicInfoVo basicInfo = this.reviewService.getBasicInfo(reviewTaskId);
            if (basicInfo != null) {
                return this.markSuccessResult(basicInfo, "success");
            }
        } catch (Exception e) {
            LOG.error("ERROR in GetBasicInfo: ", e);
            return this.markErrorResult("ERROR in GetBasicInfo: " + e.getMessage());
        }
        return this.markErrorResult("Error: Can Not Found Data for taskId " + reviewTaskId);
    }

    /**
     * 获取审核详情列表Page信息
     * */
    @RequestMapping(value = "/getReviewPageInfo.do")
    @ResponseBody
    public JsonResult getReviewPageInfo(Integer reviewTaskId, Integer tabType, Integer size, Integer page) {
        Assert.notNull(reviewTaskId);
        Assert.notNull(tabType);
        QueryConditionVo vo = new QueryConditionVo();
        vo.init(reviewTaskId, tabType, size, page);

        try {
            ReviewPageInfoVo pageData = this.reviewService.getPageInfo(vo);
            return this.markSuccessResult(pageData, "success");
        } catch (Exception e) {
            LOG.error("ERROR in getReviewPageInfo: ", e);
            return this.markErrorResult("ERROR in getReviewPageInfo: " + e.getMessage());
        }
        
    }

}
