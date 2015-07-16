package com.baidu.dpop.ctp.task.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.base.DefaultBOUtils;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.mainTask.constant.BeidouMcType;
import com.baidu.dpop.ctp.task.bo.BeidouTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.task.vo.GetUrlVo;
import com.baidu.dpop.ctp.task.web.controller.GeneralTaskController;

public class TaskControllerTest {

	@Tested
	GeneralTaskController generalTaskController;

	@Injectable
	GeneralTaskService generalTaskService;

	// @Test
	// public void testGetTaskById() {
	// new NonStrictExpectations() {
	// {
	// generalTaskService.getTaskById((Long) any, (Integer) any,
	// withEqual(GroupDataType.BEIDOU.getId().intValue()));
	// result = new BeidouTask();
	//
	// generalTaskService.getTaskById((Long) any, (Integer) any,
	// withEqual(GroupDataType.QIUSHI.getId().intValue()));
	// result = new QiushiTask();
	//
	// generalTaskService.getTaskById((Long) any, (Integer) any,
	// withEqual(GroupDataType.DSP.getId().intValue()));
	// result = new DSPTask();
	// }
	// };
	//
	// Assert.assertNotNull(generalTaskController.getTaskById(1L,
	// GroupDataType.BEIDOU.getId().intValue(), 0).getData());
	// Assert.assertNotNull(generalTaskController.getTaskById(1L,
	// GroupDataType.QIUSHI.getId().intValue(), 0).getData());
	// Assert.assertNotNull(generalTaskController.getTaskById(1L,
	// GroupDataType.DSP.getId().intValue(), 0).getData());
	// Assert.assertFalse(generalTaskController.getTaskById(1L, 100,
	// 0).isHasSuccess());
	// }

	@Test
	public void testGetTaskByIdWrong() {
		new NonStrictExpectations() {
			{
				generalTaskService.getTaskById((Long) any, (Integer) any,
						(Integer) any);
				result = new Exception();
			}
		};
		Assert.assertFalse(generalTaskController.getTaskById(1L, 100, 0)
				.isHasSuccess());
	}

	@Test
	public void testGetImgUrl() {
		new NonStrictExpectations() {
			{
				generalTaskService.getImgUrl((Long) any, (Integer) any);
				result = Arrays.asList("www.baidu.com");
			}
		};
		Assert.assertEquals(Arrays.asList("www.baidu.com").toString(),
				generalTaskController.getImgUrl(1L, 0).getData().toString());

		new NonStrictExpectations() {
			{
				generalTaskService.getImgUrl((Long) any, (Integer) any);
				result = new Exception();
			}
		};
		Assert.assertEquals("获取Url失败, id: 1",
				generalTaskController.getImgUrl(1L, 0).getResultInfo());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetImgUrlList() {

		GetUrlVo vo = new GetUrlVo();
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 10; i++) {
			ids.add(i + 0L);
		}
		vo.setList(ids);
		vo.setDataType(GroupDataType.BEIDOU.getId().intValue());

		new NonStrictExpectations() {
			{
				generalTaskService.getImgUrls((List<Long>) any, (Integer) any);
				List<GeneralTask> list = new ArrayList<GeneralTask>();
				for (int i = 0; i < 10; i++) {
					BeidouTask b = DefaultBOUtils.getBeidouTask((long) i,
							(long) i, 1L, BeidouMcType.IMG.getId());
				}
				result = list;
			}
		};
		Assert.assertTrue(generalTaskController.getImgUrlList(vo)
				.isHasSuccess());

		new NonStrictExpectations() {
			{
				generalTaskService.getImgUrls((List<Long>) any, (Integer) any);
				result = new Exception();
			}
		};
		Assert.assertFalse(generalTaskController.getImgUrlList(vo)
				.isHasSuccess());
	}
}
