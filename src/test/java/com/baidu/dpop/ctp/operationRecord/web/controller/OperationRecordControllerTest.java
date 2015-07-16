package com.baidu.dpop.ctp.operationRecord.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.operationRecord.bo.OperationRecord;
import com.baidu.dpop.ctp.operationRecord.service.OperationRecordService;
import com.baidu.dpop.frame.core.base.web.JsonResult;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

/**
 * OperationRecordControllerTest
 * 
 * @author cgd
 * @date 2015年4月6日 下午4:34:22
 */
public class OperationRecordControllerTest {

    @Tested
    private OperationRecordController operationRecordController;
    @Injectable
    private OperationRecordService operationRecordService;

    @Test
    public void testgetAllOperationRecord() {
        new NonStrictExpectations() {
            {
                operationRecordService.getAll(anyInt, anyInt, anyInt, (String) any, (String) any);
                List<OperationRecord> list = new ArrayList<OperationRecord>();
                result = list;
            }
        };
        JsonResult ret = this.operationRecordController.getAllOperationRecord("updateTime", 1, 1, null, null);
        Assert.assertTrue(ret.getSuccess().equals("true"));

        new NonStrictExpectations() {
            {
                operationRecordService.getAll(anyInt, anyInt, anyInt, (String) any, (String) any);
                result = new RuntimeException();
            }
        };
        ret = this.operationRecordController.getAllOperationRecord("updateTime", 1, 1, null, null);
        Assert.assertTrue(ret.getSuccess().equals("false"));
    }

}
