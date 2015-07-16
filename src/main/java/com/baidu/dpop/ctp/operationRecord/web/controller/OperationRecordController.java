package com.baidu.dpop.ctp.operationRecord.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.dpop.ctp.common.mybatis.page.PageInfo;
import com.baidu.dpop.ctp.operationRecord.bo.OperationRecord;
import com.baidu.dpop.ctp.operationRecord.service.OperationRecordService;
import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;

@Controller
@RequestMapping(value = "operationRecord")
public class OperationRecordController extends JsonBaseController {

    @Autowired
    OperationRecordService operationRecordService;

    @Value("${dpop.ctp.task.page}")
    private Integer deaultPage;

    @Value("${dpop.ctp.task.size}")
    private Integer defaultSize;

    private static Logger LOG = Logger.getLogger(OperationRecordController.class);

    /**
     * 获取所有操作记录
     * 
     * @return 所有的操作记录，JsonResult类型的结果
     */
    @RequestMapping(value = "getAllOperationRecord.do")
    @ResponseBody
    public JsonResult getAllOperationRecord(String type, Integer page, Integer size, String order, String orderBy) {
        try {
            Integer typeInteger = 1;
            if (type.equals("user")) {
                typeInteger = 1;
            } else if (type.equals("review")) {
                typeInteger = 2;
            }

            if (orderBy == null) {
                orderBy = "updateTime";
            }

            if (order == null) {
                order = "desc";
            }

            List<OperationRecord> list =
                    operationRecordService.getAll(typeInteger, page == null ? deaultPage : page,
                            size == null ? defaultSize : size, order, orderBy);
            return this.markSuccessResult(new PageInfo<OperationRecord>(list), "获取操作记录成功！");
        } catch (Exception e) {
            LOG.error("获取操作记录失败", e);
            return this.markErrorResult("获取操作记录失败：" + e.getLocalizedMessage());
        }
    }
}
