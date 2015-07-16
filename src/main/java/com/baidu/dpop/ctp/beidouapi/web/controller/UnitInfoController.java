package com.baidu.dpop.ctp.beidouapi.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.dpop.ctp.beidouapi.service.impl.UnitInfoServiceWrapper;

/**
 * beidou 创意信息获取Controller
 * 
 * @author cgd
 * @date 2015年3月19日 下午2:12:09
 */
@Controller
@RequestMapping(value = "unitinfo")
public class UnitInfoController {

    private static final Logger LOG = Logger.getLogger(UnitInfoController.class);

    @RequestMapping(value = "/getPagedTasks.do", method = RequestMethod.GET, 
            produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getHtmlSnippet(Long userId, Long unitId) {
        try {
            if (userId == null || unitId == null) {
                return "ERROR: Wrong Params.";
            }
            String htmlData = UnitInfoServiceWrapper.getSmartUnitHtmlSnippet(userId, unitId, 0);
            if (htmlData == null) {
                return "ERROR: Not data.";
            }
            return htmlData;
        } catch (Exception e) {
            LOG.error("GetHtmlSnippet Error:", e);
            return "Error: " + e.getMessage();
        }

    }

}
