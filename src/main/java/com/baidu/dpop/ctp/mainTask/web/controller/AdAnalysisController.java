package com.baidu.dpop.ctp.mainTask.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.dpop.ctp.mainTask.service.UBMCService;
import com.baidu.dpop.ctp.mainTask.vo.FlashIdeaResponse;
import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;

/**
 * 创意分析相关 Controller
 * 
 * @author cgd
 * @date 2015年5月5日 上午11:28:31
 */
@Controller
@RequestMapping(value = "analysis")
public class AdAnalysisController extends JsonBaseController {

    private static final Logger LOG = Logger
            .getLogger(AdAnalysisController.class);

    @Autowired
    private UBMCService uBMCService;

    /**
     * 获取Flash创意对应的解析类型
     */
    @RequestMapping(value = "/getFlashAdAnalysisType.do", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getFlashAdAnalysisType(Integer mcId, Integer mcVersionId) {
        FlashIdeaResponse response = FlashIdeaResponse.getFaildFlash();
        // 输入参数有误
        if (mcId == null || mcVersionId == null) {
            return this.markErrorResult(response.getFlashType());
        }

        try {
            response = this.uBMCService.getFlashIdeasInfo(mcId, mcVersionId);
            return this.markSuccessResult(response.getFlashType(), "success");
        } catch (Exception e) {
            LOG.error("Exception in getFalshAdTypeInfo", e);
            return this.markErrorResult(response.getFlashType());
        }
    }
}
