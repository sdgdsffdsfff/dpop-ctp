package com.baidu.dpop.ctp.demo.web.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.dpop.ctp.adtag.bo.TagTypeTreeNode;
import com.baidu.dpop.ctp.adtag.service.TagTypeService;
import com.baidu.dpop.ctp.demo.dao.DemoTestBoDao;
import com.baidu.dpop.ctp.demo.service.DemoTestBoService;
import com.baidu.dpop.ctp.industrytype.bo.NewIndustryType;
import com.baidu.dpop.ctp.industrytype.dao.NewIndustryTypeDao;
import com.baidu.dpop.ctp.review.service.ReviewAdTaskService;
import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;

/**
 * DemoTest
 * 
 * @author cgd
 * @date 2014年12月8日 上午11:35:51
 */
@Controller
@RequestMapping(value = "demo")
public class ControllerDemo extends JsonBaseController {

    @Autowired
    private DemoTestBoService demoTestBoService;

    @Autowired
    private TagTypeService tagTypeService;

    @Autowired
    private ReviewAdTaskService reviewAdTaskService;

    @Autowired
    private NewIndustryTypeDao industryTypeDao;

    @Autowired
    private DemoTestBoDao dao;

    Logger LOG = Logger.getLogger(ControllerDemo.class);

    @RequestMapping(value = "/test.do", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult init(Integer taskId) {
        Map<Integer, TagTypeTreeNode> map = tagTypeService.getAll();
        return this.markSuccessResult(TagTypeTreeNode.getType(taskId, map), "");
    }

    @RequestMapping(value = "/tttt.do", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult tttt() {
        NewIndustryType type = industryTypeDao.selectByPrimaryKey(510000L);
        if (type == null) {
            type = new NewIndustryType();
            type.setFirstId(51);
            type.setFirstName("安全安保");
            type.setSecondId(0);
            type.setSecondName("");
            type.setThirdId(0);
            type.setThirdName("");
            type.setFullId(510000);
            type.setFullName("安全安保");
            type.setLevel2Id(0);
            type.setLevel2Name("");
            industryTypeDao.insert(type);
            return this.markSuccessResult();
        }
        return this.markErrorResult("");
    }

    @RequestMapping(value = "/beidou.do", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult updateBeidou() {
        try {
            dao.updateBeidou();
            return this.markSuccessResult();
        } catch (Exception e) {
            return this.markErrorResult("error");
        }
    }
    
    @RequestMapping(value = "/qiushi.do", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult updateQiushi() {
        try {
            dao.updateQiushi();
            return this.markSuccessResult();
        } catch (Exception e) {
            return this.markErrorResult("error");
        }
    }
    
    @RequestMapping(value = "/dsp.do", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult updateDSP() {
        try {
            dao.updateDSP();
            return this.markSuccessResult();
        } catch (Exception e) {
            return this.markErrorResult("error");
        }
    }
    
    @RequestMapping(value = "/newDsp.do", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult updateNewDSP() {
        try {
            dao.updateNewDSP();
            return this.markSuccessResult();
        } catch (Exception e) {
            return this.markErrorResult("error");
        }
    }
    
    @RequestMapping(value = "/adTag.do", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult updateAdTag() {
        try {
            dao.updateAdTag();
            return this.markSuccessResult();
        } catch (Exception e) {
            return this.markErrorResult("error");
        }
    }
    
    @RequestMapping(value = "/reviewAdTask.do", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult updateReviewAdTask() {
        try {
            dao.updateReviewAdTask();
            return this.markSuccessResult();
        } catch (Exception e) {
            return this.markErrorResult("error");
        }
    }
    
    @RequestMapping(value = "/reviewAdTask2.do", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult updateReviewAdTask2() {
        try {
            dao.updateReviewAdTask2();
            return this.markSuccessResult();
        } catch (Exception e) {
            return this.markErrorResult("error");
        }
    }
    
    @RequestMapping(value = "/task.do", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult updateTask() {
        try {
            dao.updateTask();
            return this.markSuccessResult();
        } catch (Exception e) {
            return this.markErrorResult("error");
        }
    }
}
