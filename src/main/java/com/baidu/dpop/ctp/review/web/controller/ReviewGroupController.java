package com.baidu.dpop.ctp.review.web.controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.group.constant.GroupStatus;
import com.baidu.dpop.ctp.group.vo.SubmitVo;
import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.bo.ReviewTask;
import com.baidu.dpop.ctp.review.service.ReviewAdTaskService;
import com.baidu.dpop.ctp.review.service.ReviewGroupService;
import com.baidu.dpop.ctp.review.service.ReviewTaskConditionService;
import com.baidu.dpop.ctp.review.service.ReviewTaskService;
import com.baidu.dpop.ctp.review.vo.DistributeReviewGroupResult;
import com.baidu.dpop.ctp.review.vo.PresentedReviewTask;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.service.UserService;
import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;

@Controller
@RequestMapping(value = "review/task/group")
public class ReviewGroupController extends JsonBaseController {

	private static final Logger LOG = Logger
			.getLogger(ReviewGroupController.class);

	@Autowired
	private ReviewTaskService reviewTaskService;

	@Autowired
	private ReviewTaskConditionService reviewTaskConditionService;

	@Autowired
	private ReviewAdTaskService adTaskService;

	@Autowired
	private UserService userService;

	@Autowired
	private ReviewGroupService reviewGroupService;

	@RequestMapping(value = "/distributeNewGroup.do")
	@ResponseBody
	public JsonResult getTasks(Integer taskId) {

		ReviewTask task = null;
		ReviewGroup rg = null;
		User u = null;
		int tryTimes = 0;

		try {
			task = reviewTaskService.findById(taskId.longValue());
			u = userService.getCurrentLoginUser();
		} catch (Exception e) {
			LOG.error("分配任务失败！", e);
			return this.markErrorResult("");
		}

		do {
			try {
				rg = reviewGroupService.distributeNewReviewGroup(task);
				if (rg == null) {
					rg = reviewGroupService.getStartedGroupByUser(task, u);
				}

				if (rg == null) {
					DistributeReviewGroupResult err = new DistributeReviewGroupResult();
					err.setTask(task);
					err.setList(new ArrayList<PresentedReviewTask>());
					return this.markSuccessResult(err, "已经没有任务可以分配了！");
				}

				break;
			} catch (Exception e) {
				LOG.error("分配任务失败！", e);
				tryTimes++;
			}
		} while (tryTimes < 5);

		if (tryTimes == 5) {
			return this.markErrorResult("分配任务失败:尝试次数超限。请稍后再试。");
		}

		try {
			DistributeReviewGroupResult result = reviewTaskService
					.getTasksByGroup(task, rg, u);
			if (result != null) {
				return this.markSuccessResult(result, "分配审核任务成功！");
			}
		} catch (Exception e) {
			LOG.error("分配任务失败！", e);
		}

		return this.markErrorResult("分配任务失败！");
	}

	@RequestMapping(value = "/getTasksByGroupId.do")
	@ResponseBody
	public JsonResult getTasksByGroupId(Long id) {
		try {
			ReviewGroup rg = reviewGroupService.getWithUserNameById(id);
			if (rg == null) {
				return this.markErrorResult("reviewGroup : " + id + "不存在！");
			}
			
			ReviewTask task = reviewTaskService.findById(rg.getTaskIdReview()
					.longValue());
			User u = userService.getCurrentLoginUser();
			DistributeReviewGroupResult result = reviewTaskService
					.getTasksByGroup(task, rg, u);
			return this.markSuccessResult(result, "分配审核任务成功！");
		} catch (Exception e) {
			LOG.error("分配任务失败！", e);
			return this.markErrorResult("获取任务失败！");
		}
	}

	@RequestMapping(value = "/submitTasksByGroupId.do", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResult submitTasksByGroupId(@RequestBody SubmitVo vo) {
		try {
			reviewGroupService.submitReviewTasks(vo.getList(), vo.getGroupId(),
					userService.getCurrentLoginUser());
			return this.markSuccessResult();
		} catch (Exception e) {
			if (e instanceof BaseRuntimeException) {
				LOG.info("submit task wrong: " + e.getLocalizedMessage());
			} else {
				LOG.error("提交任务失败", e);
			}
			return this.markErrorResult(e.getLocalizedMessage());
		}
	}

	@RequestMapping(value = "/giveUpGroup.do")
	@ResponseBody
	public JsonResult giveUpGroup(Long id) {
		User u = userService.getCurrentLoginUser();
		ReviewGroup g = reviewGroupService.findById(id);
		if (!g.getStatus().equals(GroupStatus.STARTED.getId())) {
			return this.markSuccessResult("任务已完成，无须放弃", null);
		} else if (!u.getId().equals(g.getModifyUserIdReview())) {
			return this.markErrorResult("不能放弃任务：此任务并非属于此用户");
		}
		try {
			reviewGroupService.giveUpGroup(g);
		} catch (Exception e) {
			LOG.error("放弃任务失败", e);
			return this.markErrorResult("放弃任务失败，请稍候再试");
		}
		return this.markSuccessResult(null, "放弃任务成功");
	}
}
