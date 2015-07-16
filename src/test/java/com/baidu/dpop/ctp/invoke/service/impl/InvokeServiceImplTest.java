package com.baidu.dpop.ctp.invoke.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.dpop.ctp.base.StaticUtilsMockUp;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.group.service.GroupService;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.nfs.service.FileArchiveService;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;
import com.baidu.dpop.frame.core.context.DpopPropertyUtils;

import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class InvokeServiceImplTest {

    @Tested
    private InvokeServiceImpl invokeServiceImpl;

    @Injectable
    private TaskService taskService;

    @Injectable
    private GeneralTaskService generalTaskService;

    @Injectable
    private GroupService groupService;

    @Injectable
    private FileArchiveService fileArchiveService;
//
//    private static final String NORMAL_TEST_FILE = "mater_info_normal";
//    private static final String ERROR_TEST_FILE = "mater_info_error6";
//
//    @Before
//    public void setUp() {
//        new MockUp<DpopPropertyUtils>() {
//
//            @Mock
//            public String getProperty(String key) {
//                return "1000";
//            }
//        };
//    }
//
//    /**
//     * 测试数据上传接口，数据共20条，为正确数据 对应case: 396-1.1
//     * 
//     * @throws IOException
//     */
//    @SuppressWarnings("unchecked")
//    @Ignore
//    @Test
//    public void testUploadFile() throws IOException {
//        new NonStrictExpectations() {
//            {
//                taskService.selectClosedTasks();
//                result = new ArrayList<Task>();
//
//                generalTaskService.insertGeneralTask((List<GeneralTask>) any, (Byte) any);
//                result = 20;
//
//                generalTaskService.selectTaskCount((List<GroupCountVo>) any, (Byte) any);
//                result = new ArrayList<Group>();
//
//                groupService.batchInsertGroup((List<Group>) any);
//
//                taskService.insertTasks((Map<Integer, Task>) any);
//            }
//        };
//
//        final File file = FileUtils.toFile(InvokeServiceImplTest.class.getResource(NORMAL_TEST_FILE));
//        final byte[] fileBytes = FileUtils.readFileToString(file, "utf-8").getBytes();
//        MultipartFile fileData = new MockMultipartFile("fileData", fileBytes);
//        Map<String, Object> result = invokeServiceImpl.uploadFile(fileData, GroupDataType.BEIDOU.getId());
//        Assert.assertFalse(result.isEmpty());
//        Assert.assertTrue(result.containsKey("INSERT"));
//        Assert.assertEquals(result.get("INSERT"), 20);
//        Assert.assertTrue(result.containsKey("ERROR_LIST"));
//        Assert.assertTrue(result.get("ERROR_LIST") instanceof ArrayList);
//        Assert.assertEquals(((ArrayList<?>) result.get("ERROR_LIST")).size(), 0);
//    }
//
//    /**
//     * 测试上传空文件，使用null作为参数传入 测试上传文件byte数组长度为0 对应case: 396-2.1
//     */
//    @SuppressWarnings("unchecked")
//    @Test(expected=Exception.class)
//    @Ignore
//    public void testUploadFileNull() {
//        StaticUtilsMockUp.getDpopPropertyUtilsMockUp();
//        new NonStrictExpectations() {
//            {
//                taskService.selectClosedTasks();
//                result = new ArrayList<Task>();
//
//                generalTaskService.insertGeneralTask((List<GeneralTask>) any, (Byte) any);
//                result = 20;
//
//                generalTaskService.selectTaskCount((List<GroupCountVo>) any, (Byte) any);
//                result = new ArrayList<Group>();
//
//                groupService.batchInsertGroup((List<Group>) any);
//
//                taskService.insertTasks((Map<Integer, Task>) any);
//            }
//        };
//        MultipartFile fileData = new MockMultipartFile("fileData", new byte[0]);
//        Map<String, Object> result = invokeServiceImpl.uploadFile(fileData, GroupDataType.BEIDOU.getId());
//        Assert.assertTrue(result.containsKey("INSERT"));
//        Assert.assertEquals(result.get("INSERT"), Integer.valueOf(0));
//    }
//
//    /**
//     * 测试上传错误文件，在文件中有6条错误记录，共20条记录
//     * 
//     * @throws IOException
//     */
//    @SuppressWarnings("unchecked")
//    @Test
//    @Ignore
//    public void testUploadFileWithErrors() throws IOException {
//        StaticUtilsMockUp.getDpopPropertyUtilsMockUp();
//        new NonStrictExpectations() {
//            {
//                taskService.selectClosedTasks();
//                result = new ArrayList<Task>();
//
//                generalTaskService.insertGeneralTask((List<GeneralTask>) any, (Byte) any);
//                result = 15;
//
//                generalTaskService.selectTaskCount((List<GroupCountVo>) any, (Byte) any);
//                result = new ArrayList<Group>();
//
//                groupService.batchInsertGroup((List<Group>) any);
//
//                taskService.insertTasks((Map<Integer, Task>) any);
//            }
//        };
//
//        final File file = FileUtils.toFile(InvokeServiceImplTest.class.getResource(ERROR_TEST_FILE));
//        final byte[] fileBytes = FileUtils.readFileToString(file, "utf-8").getBytes();
//        MultipartFile fileData = new MockMultipartFile("fileData", fileBytes);
//        Map<String, Object> result = invokeServiceImpl.uploadFile(fileData, GroupDataType.BEIDOU.getId());
//        Assert.assertFalse(result.isEmpty());
//        Assert.assertTrue(result.containsKey("INSERT"));
//        Assert.assertEquals(result.get("INSERT"), 0);
//        Assert.assertTrue(result.containsKey("ERROR_LIST"));
//        Assert.assertTrue(result.get("ERROR_LIST") instanceof ArrayList);
//        Assert.assertEquals(((ArrayList<?>) result.get("ERROR_LIST")).size(), 5);
//    }
//
    @Test
    public void testGetTagedFile() {
        new NonStrictExpectations() {
            {
                generalTaskService.getTagedFile((GeneralTaskQueryVo) any);
                result = "test.zip";
            }
        };

        Assert.assertEquals("test.zip", invokeServiceImpl.getTagedFile(new GeneralTaskQueryVo()));
    }

}
