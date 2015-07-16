package com.baidu.dpop.ctp.task.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;





import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.task.vo.GetUrlVo;
import com.baidu.dpop.ctp.task.vo.PresentedTaskDetail;
import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;

@Controller
@RequestMapping(value = "generalTask")
public class GeneralTaskController extends JsonBaseController {

	@Autowired
	GeneralTaskService generalTaskService;

	private static final Logger LOG = Logger
			.getLogger(GeneralTaskController.class);

	/**
	 * 获取任务详细信息
	 * 
	 * @param id
	 * @param dataType
	 * @param region
	 * @return
	 */
	@RequestMapping(value = "/getTaskById.do")
	@ResponseBody
	public JsonResult getTaskById(Long id, Integer dataType, Integer region) {
		try {
			PresentedTaskDetail task = generalTaskService.getTaskById(id, region,
					dataType);
			return this.markSuccessResult(task, "获取任务成功！");
		} catch (Exception e) {
			LOG.error("获取任务失败", e);
		}
		return this.markErrorResult("获取任务失败！");
	}

	/**
	 * 获取某个ad的imgUrl
	 * 
	 * @param id
	 *            需要获取的ad的id(非ad_id)
	 * @param dataType
	 *            数据类型
	 * @return 获取到的imgUrl
	 */
	@RequestMapping(value = "/getImgUrl.do")
	@ResponseBody
	public JsonResult getImgUrl(Long id, Integer dataType) {
		try {
			return this.markSuccessResult(
					this.generalTaskService.getImgUrl(id, dataType), "获取url成功");
		} catch (Exception e) {
			LOG.error("获取Url失败, id: " + id, e);
		}
		return this.markErrorResult("获取Url失败, id: " + id);
	}

	/**
	 * 批量获取ad的imgUrl
	 * 
	 * @param vo
	 *            查询条件，包括了ad的id列表与数据类系
	 * @return 任务详细信息的列表，其中包含了imgUrl
	 */
	@RequestMapping(value = "/getImgUrlList.do", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResult getImgUrlList(@RequestBody GetUrlVo vo) {
		List<PresentedTaskDetail> list = null;
		Map<Long, PresentedTaskDetail> result = null;
		try {
			list = generalTaskService
					.getImgUrls(vo.getList(), vo.getDataType());
			result = new HashMap<Long, PresentedTaskDetail>();
			for (PresentedTaskDetail pt : list) {
			    result.put(pt.getId().longValue(), pt);
			}
		} catch (Exception e) {
			LOG.error("Get url wrong", e);
		}

		if (list != null) {
			return this.markSuccessResult(result, "获取成功");
		}

		return this.markErrorResult("获取失败");

	}
}
