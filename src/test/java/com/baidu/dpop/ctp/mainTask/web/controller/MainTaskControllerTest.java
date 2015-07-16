package com.baidu.dpop.ctp.mainTask.web.controller;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.common.mybatis.page.Page;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatus;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.mainTask.vo.PagedTaskQueryVo;
import com.baidu.dpop.ctp.mainTask.vo.TaskQueryInfo;
import com.baidu.dpop.frame.core.base.web.JsonResult;
import com.baidu.dpop.frame.core.context.DpopPropertyUtils;

import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.NonStrictExpectations;
import mockit.Tested;

public class MainTaskControllerTest {

	@Tested
	TaskController taskController;

	@Injectable
	TaskService taskService;

	@Test
	public void testFind() {

		new MockUp<DpopPropertyUtils>() {
			@Mock
			public String getProperty(String any) {
				return "10";
			}
		};

		new NonStrictExpectations() {
			{
				taskService.getTaskListByTaskInfo((Integer) any, (Integer) any,
						(TaskQueryInfo) any);
				List<Task> page = new Page<Task>(1, 10);
				for (int i = 0; i < 10; i++) {
					page.add(new Task());
				}
				result = page;
			}
		};

		JsonResult jso = taskController.find(new PagedTaskQueryVo(null, null, null, null, null,
				null, null, null, null, null, null));
		Assert.assertEquals("true", jso.getSuccess());

		jso = taskController.find(new PagedTaskQueryVo(1, 10, "addTime", "desc", 5, 1, "task_", "1",
				new Date().getTime(), new Date().getTime(),
				TaskStatus.FINISHED.getId()));
		Assert.assertEquals("true", jso.getSuccess());

	}

	@Test
	public void testFindWrong() {

		new MockUp<DpopPropertyUtils>() {
			@Mock
			public String getProperty(String any) {
				return "10";
			}
		};

		new NonStrictExpectations() {
			{
				taskService.getTaskListByTaskInfo((Integer) any, (Integer) any,
						(TaskQueryInfo) any);
				List<Task> page = new Page<Task>(1, 10);
				for (int i = 0; i < 10; i++) {
					page.add(new Task());
				}
				result = page;
			}
		};

		JsonResult jso = taskController.find(new PagedTaskQueryVo(1, 10, "addTime", "abc", null,
				null, null, null, null, null, null));
		Assert.assertEquals("false", jso.getSuccess());

		jso = taskController.find(new PagedTaskQueryVo(1, 10, "addTime", "desc", null, null, null,
				null, null, null, (byte) 20));
		Assert.assertEquals("false", jso.getSuccess());
		
		new NonStrictExpectations() {
			{
				taskService.getTaskListByTaskInfo((Integer) any, (Integer) any,
						(TaskQueryInfo) any);
				result = new Exception("test");
			}
		};
		jso = taskController.find(new PagedTaskQueryVo(1, 10, "addTime", "desc", null, null, null,
				null, null, null, (byte) 20));
		Assert.assertEquals("false", jso.getSuccess());
	}

}
