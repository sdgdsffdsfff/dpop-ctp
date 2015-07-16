package com.baidu.dpop.ctp.invoke.web.controller;

import java.io.File;
import java.util.Date;

import junit.framework.Assert;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.dpop.ctp.base.AbstractJMockitTests;
import com.baidu.dpop.ctp.invoke.bo.DataLoadInfo;
import com.baidu.dpop.ctp.invoke.service.DataLoadInfoService;
import com.baidu.dpop.ctp.invoke.service.InvokeService;
import com.baidu.dpop.ctp.nfs.utils.NfsUtils;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;
import com.baidu.dpop.frame.core.base.web.JsonResult;

/**
 * InvokeControllerTest
 * 
 * @author cgd
 * @date 2014年12月19日 下午1:19:43
 */
@RunWith(JMockit.class)
public class InvokeControllerTest extends AbstractJMockitTests {

    @Tested
    private InvokeController invokeController;

    @Injectable
    private InvokeService invokeService;
    @Injectable
    private DataLoadInfoService dataLoadInfoService;
    @Injectable
    private GeneralTaskService taskService;
    @Injectable
    private String uploadToken = "token";
    @Injectable
    private Integer INSERTION = 1;

    @Test
    public void testgetLoadDataProcess() {
        new NonStrictExpectations() {
            {
                dataLoadInfoService.getDataLoadInfoByMd5(anyString);
                result = new RuntimeException();
            }
        };
        JsonResult ret = this.invokeController
                .getLoadDataProcess("test", "md5");
        Assert.assertTrue(ret.getSuccess().equals("false"));

        new NonStrictExpectations() {
            {
                dataLoadInfoService.getDataLoadInfoByMd5(anyString);
                DataLoadInfo info = new DataLoadInfo();
                info.setAddTime(new Date());
                info.setDataType((byte) 1);
                info.setFileName("file_name");
                info.setFileSize(1L);
                info.setStatus((byte) 1);
                info.setScanRecord(10);
                info.setInsertRecord(10);
                result = info;
            }
        };
        ret = this.invokeController.getLoadDataProcess("test", "md5");
        Assert.assertTrue(ret.getSuccess().equals("true"));
    }

    @Test
    public void testloadDataByThread() {
        JsonResult ret = this.invokeController.loadDataByThread(null, null,
                null);
        Assert.assertTrue(ret.getSuccess().equals("false"));

        new MockUp<NfsUtils>() {
            @Mock
            public File getWriteFile(String fileRelativePath) {
                return null;
            }
        };
        byte[] data = new byte[100];
        data[0] = 's';
        data[1] = 'h';
        MultipartFile file = new MockMultipartFile("file", data);
        ret = this.invokeController.loadDataByThread("token", file, (byte) 0);
        Assert.assertTrue(ret.getSuccess().equals("false"));
    }

    @Test
    public void testgetTagedFile() {
        JsonResult ret = this.invokeController.getTagedFile((byte) 1, "1,2",
                "07:00", "08:00", null);
        Assert.assertTrue(ret.getSuccess().equals("false"));

        ret = this.invokeController.getTagedFile(null, "1,2", "07:00", "08:00",
                null);
        Assert.assertTrue(ret.getSuccess().equals("false"));

        ret = this.invokeController.getTagedFile((byte) 1, "1,2", "07:00",
                "08:00", "worong_token");
        Assert.assertTrue(ret.getSuccess().equals("false"));

        new NonStrictExpectations() {
            {
                invokeService.getTagedFile((GeneralTaskQueryVo) any);
                result = new RuntimeException();
            }
        };
        ret = this.invokeController.getTagedFile((byte) 1, "1,2", "07:00",
                "08:00", "token");
        Assert.assertTrue(ret.getSuccess().equals("false"));

        new NonStrictExpectations() {
            {
                invokeService.getTagedFile((GeneralTaskQueryVo) any);
                result = "file_name";
            }
        };
        ret = this.invokeController.getTagedFile((byte) 1, "1,2", "07:00",
                "08:00", "token");
        Assert.assertTrue(ret.getSuccess().equals("true"));

        ret = this.invokeController.getTagedFile((byte) 1, "", "07:00",
                "08:00", "token");
        Assert.assertTrue(ret.getSuccess().equals("true"));
    }

}
