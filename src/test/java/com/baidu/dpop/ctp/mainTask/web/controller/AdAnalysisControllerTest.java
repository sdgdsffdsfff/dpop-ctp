package com.baidu.dpop.ctp.mainTask.web.controller;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.mainTask.service.UBMCService;
import com.baidu.dpop.ctp.mainTask.vo.FlashIdeaResponse;
import com.baidu.dpop.frame.core.base.web.JsonResult;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

/**
 * AdAnalysisController Test
 * 
 * @author cgd
 * @date 2015年5月5日 下午3:45:36
 */
public class AdAnalysisControllerTest {

    @Tested
    private AdAnalysisController adAnalysisController;
    @Injectable
    private UBMCService uBMCService;

    @Test
    public void testgetFlashAdAnalysisType() {
        // new NonStrictExpectations() {
        // {
        // uBMCService.getFlashIdeasInfo(anyInt, anyInt);
        // result = new RuntimeException();
        // }
        // };
        //
        // JsonResult ret =
        // this.adAnalysisController.getFlashAdAnalysisType(null, 1);
        // Assert.assertTrue(ret.getSuccess().equals("false"));
        //
        // new NonStrictExpectations() {
        // {
        // uBMCService.getFlashIdeasInfo(anyInt, anyInt);
        // result = new RuntimeException();
        // }
        // };
        //
        // ret = this.adAnalysisController.getFlashAdAnalysisType(1, 1);
        // Assert.assertTrue(ret.getSuccess().equals("false"));
        //
        // new NonStrictExpectations() {
        // {
        // uBMCService.getFlashIdeasInfo(anyInt, anyInt);
        // result = null;
        // }
        // };
        //
        // ret = this.adAnalysisController.getFlashAdAnalysisType(1, 1);
        // Assert.assertTrue(ret.getData().equals("FLASH 请求失败"));
        //
        // new NonStrictExpectations() {
        // {
        // uBMCService.getFlashIdeasInfo(anyInt, anyInt);
        // FlashIdeaResponse response = new FlashIdeaResponse();
        // response.setHasBinary((byte) 1);
        // result = response;
        // }
        // };
        // ret = this.adAnalysisController.getFlashAdAnalysisType(1, 1);
        // Assert.assertTrue(ret.getData().equals("FLASH Binary"));
        //
        // new NonStrictExpectations() {
        // {
        // uBMCService.getFlashIdeasInfo(anyInt, anyInt);
        // FlashIdeaResponse response = new FlashIdeaResponse();
        // response.setHasBinary((byte) 0);
        // response.setOutMcUrl(Arrays.asList("test"));
        // result = response;
        // }
        // };
        // ret = this.adAnalysisController.getFlashAdAnalysisType(1, 1);
        // Assert.assertTrue(ret.getData().equals("FLASH 外链"));
        //
        // new NonStrictExpectations() {
        // {
        // uBMCService.getFlashIdeasInfo(anyInt, anyInt);
        // FlashIdeaResponse response = new FlashIdeaResponse();
        // response.setHasBinary((byte) 0);
        // response.setImageList(Arrays.asList("url"));
        // result = response;
        // }
        // };
        // ret = this.adAnalysisController.getFlashAdAnalysisType(1, 1);
        // Assert.assertTrue(ret.getData().equals("FLASH 多图"));
        //
        // new NonStrictExpectations() {
        // {
        // uBMCService.getFlashIdeasInfo(anyInt, anyInt);
        // FlashIdeaResponse response = new FlashIdeaResponse();
        // response.setHasBinary((byte) 0);
        // result = response;
        // }
        // };
        // ret = this.adAnalysisController.getFlashAdAnalysisType(1, 1);
        // Assert.assertTrue(ret.getData().equals("FLASH 无法解析"));
        //
        // Assert.assertTrue(true);
    }

}
