package com.baidu.dpop.ctp.adtag.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.dpop.ctp.adtag.bo.TagType;
import com.baidu.dpop.ctp.adtag.service.TagTypeService;
import com.baidu.dpop.ctp.adtag.vo.TagTypeAddVo;
import com.baidu.dpop.ctp.industrytype.service.IndustryTypeService;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.constant.UserRoleType;
import com.baidu.dpop.ctp.user.service.UserService;
import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;
import com.ctc.wstx.util.StringUtil;

@Controller
@RequestMapping(value = "tagType")
public class TagTypeController extends JsonBaseController {

    @Autowired
    private TagTypeService tagTypeService;

    @Autowired
    private IndustryTypeService industryTypeService;

    @Autowired
    private UserService userService;

    private static final Logger LOG = Logger.getLogger(TagTypeController.class);

    @RequestMapping(value = "/addTagType.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult uploadFile(@RequestParam("token") String token, @RequestParam("upLoadFile") MultipartFile file) {
        BufferedReader reader = null;
        try {
            InputStreamReader in = new InputStreamReader(file.getInputStream(), "UTF-8");
            reader = new BufferedReader(in);
            String lineData;
            List<String> insert = new ArrayList<String>();
            while ((lineData = reader.readLine()) != null) {
                insert.add(lineData);
            }
            tagTypeService.batchInsertTagTypes(insert);
            return this.markSuccessResult();
        } catch (Exception e) {
            LOG.error("error in insert tag type", e);
            return this.markErrorResult("error in insert tag type");
        }

    }

    @RequestMapping(value = "/addTradeType.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult uploadTradeType(@RequestParam("token") String token,
            @RequestParam("upLoadFile") MultipartFile file) {
        BufferedReader reader = null;
        try {
            InputStreamReader in = new InputStreamReader(file.getInputStream(), "UTF-8");
            reader = new BufferedReader(in);
            String lineData;
            List<String> insert = new ArrayList<String>();
            while ((lineData = reader.readLine()) != null) {
                insert.add(lineData);
            }
            industryTypeService.batchUpdateNewIndustryType(insert);
            return this.markSuccessResult();
        } catch (Exception e) {
            LOG.error("error in insert tag type", e);
            return this.markErrorResult("error in insert tag type");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/delete.do", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JsonResult deleteTradeType(@RequestBody Map<String, Object> map) {
        try {
            User u = userService.getCurrentLoginUser();
            if (UserRoleType.isAdminRoleType(u.getRoleType())) {
                List<Long> list = (List<Long>) map.get("list");
                tagTypeService.deleteByIds(list);
                return this.markSuccessResult();
            } else {
                return this.markErrorResult("非管理员不能操作！");
            }

        } catch (Exception e) {
            LOG.error("error in insert tag type", e);
            return this.markErrorResult("error in insert tag type");
        }
    }

    @RequestMapping(value = "/getAll.do")
    @ResponseBody
    public JsonResult getAll() {
        try {
            return this.markSuccessResult(tagTypeService.getAllList(), "获取成功");
        } catch (Exception e) {
            LOG.error("error in insert tag type", e);
            return this.markErrorResult("error in insert tag type");
        }
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JsonResult add(@RequestBody TagTypeAddVo vo) {
        try {
            List<Integer> list = vo.getTradeIds();

            if (CollectionUtils.isEmpty(list) || vo.getTagType() == null
                    || StringUtil.isAllWhitespace(vo.getTagType())) {
                return this.markErrorResult("参数错误！");
            }

            if (list.size() == 1 && list.get(0) == 0) {
                list = industryTypeService.getAllFirstTradeType();
            }

            List<TagType> insert = new ArrayList<TagType>();
            for (Integer tradeId : list) {
                TagType type = new TagType();
                type.setTagType(vo.getTagType());
                type.setTradeId(tradeId);
                insert.add(type);
            }
            tagTypeService.batchInsertTagTypesByBo(insert);
            return this.markSuccessResult();
        } catch (Exception e) {
            LOG.error("error in insert tag type", e);
            return this.markErrorResult("error in insert tag type");
        }
    }
}
