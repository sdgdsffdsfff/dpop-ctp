package com.baidu.dpop.ctp.review.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.dpop.ctp.common.constants.GetParamOrder;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.common.mybatis.page.PageInfo;
import com.baidu.dpop.ctp.common.utils.CollectionDisplayUtil;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatus;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.mainTask.vo.PagedTaskQueryVo;
import com.baidu.dpop.ctp.mainTask.vo.TaskQueryInfo;
import com.baidu.dpop.ctp.review.bo.ReviewTask;
import com.baidu.dpop.ctp.review.constants.ReviewTaskStatus;
import com.baidu.dpop.ctp.review.service.ReviewAdTaskService;
import com.baidu.dpop.ctp.review.service.ReviewTaskConditionService;
import com.baidu.dpop.ctp.review.service.ReviewTaskService;
import com.baidu.dpop.ctp.review.vo.CreateReviewTaskVo;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.user.service.UserService;
import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;

@Controller
@RequestMapping(value = "review/task")
public class ReviewMainTaskController extends JsonBaseController {

    private static final Logger LOG = Logger.getLogger(ReviewMainTaskController.class);

    @Value("${dpop.ctp.task.page}")
    private Integer defaultPage;

    @Value("${dpop.ctp.task.size}")
    private Integer defaultSize;

    @Value("${dpop.ctp.task.order}")
    private String defaultOrder;

    @Autowired
    private ReviewAdTaskService reviewAdTaskService;

    @Autowired
    private ReviewTaskConditionService reviewTaskConditionService;

    @Autowired
    private TaskService mainTaskService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewTaskService reviewTaskService;

    @Autowired
    private GeneralTaskService generalTaskService;

    @RequestMapping(value = "/getTasks.do")
    @ResponseBody
    public JsonResult getTasks() {
        try {
            return this.markSuccessResult(mainTaskService.getClosedTasks(), "获取成功！");
        } catch (Exception e) {
            return this.markErrorResult("获取任务失败！原因:" + e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/getGroupNum.do", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JsonResult getGroupNumByQueryCondition(@RequestBody CreateReviewTaskVo vo) {

        try {
            List<Integer> taskList = vo.getTaskList();
            if (CollectionUtils.isEmpty(taskList)) {
                return this.markErrorResult("任务列表不能为空！");
            }

            int result = reviewTaskConditionService.getGroupNumByQueryCondition(vo.getReviewTaskCondition(), taskList);
            return result == 0 ? this.markErrorResult("没有符合条件的任务") : this.markSuccessResult(result, "获取数量成功");
        } catch (Exception e) {
            return this.markErrorResult("获取数量失败！");
        }
    }

    /**
     * 创建审核任务
     * 
     * @param vo 创建条件
     * @return 创建成功的task信息
     */
    @RequestMapping(value = "/createNewReviewTask.do", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JsonResult createNewReviewTask(@RequestBody CreateReviewTaskVo vo) {

        List<Integer> taskList = null;

        try {
            // 创建新的任务，设置各项基本内容
            ReviewTask rTask = new ReviewTask();
            Date d = new Date();
            rTask.setAddTime(d);
            rTask.setAddUser(userService.getCurrentLoginUser().getUserName());
            rTask.setBlind(vo.getIsBlind());
            rTask.setGroupNum(vo.getGroupNum());
            rTask.setModuserLevel(vo.getModuserLevel());
            rTask.setStatus(ReviewTaskStatus.NOT_STARTED.getId());
            rTask.setTaskType(vo.getTaskType());
            rTask.setTaskName(vo.getTaskName() + "-" + new SimpleDateFormat("yyyyMMddhhmm").format(d));

            taskList = vo.getTaskList();
            if (CollectionUtils.isEmpty(taskList)) {
                return this.markErrorResult("任务列表不能为空！");
            }

            rTask.setTaskList(CollectionDisplayUtil.listToString(taskList, "", "", ","));
            ReviewTask t = reviewTaskService.createNewReviewTask(rTask, vo.getReviewTaskCondition(), taskList);

            return this.markSuccessResult(t, "创建成功！");
        } catch (Exception e) {
            if (!(e instanceof BaseRuntimeException)) {
                LOG.error("生成审核任务失败！", e);
                return this.markErrorResult("生成审核任务失败: " + e.getLocalizedMessage());
            }
            return this.markErrorResult("生成审核任务失败！");
        }
    }

    @RequestMapping(value = "/getPagedTasks.do", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JsonResult find(@RequestBody PagedTaskQueryVo vo) {

        List<ReviewTask> result = null;
        try {
            boolean isDesc = true;
            if (vo.getOrder() != null) {
                GetParamOrder desc = GetParamOrder.get(vo.getOrder());
                if (desc == null) {
                    LOG.error("The order is wrong: " + vo.getOrder());
                    return this.markErrorResult("The order can only be 'desc' or 'asc'.");
                }
                isDesc = desc.equals(GetParamOrder.DESC);
            }

            if (vo.getStatus() != null && TaskStatus.get(vo.getStatus()) == null) {
                LOG.error("The status is wrong: " + vo.getStatus());
                return this.markErrorResult("The status is wrong.");
            }

            TaskQueryInfo taskQueryInfo = new TaskQueryInfo();
            taskQueryInfo.setAddUser(vo.getAddUser());
            taskQueryInfo.setBeginTime(vo.getBeginTime() == null ? null : new Date(vo.getBeginTime()));
            taskQueryInfo.setEndTime(vo.getEndTime() == null ? null : new Date(vo.getEndTime()));
            taskQueryInfo.setOrder(isDesc);
            taskQueryInfo.setOrderBy(vo.getOrderBy() == null ? defaultOrder : vo.getOrderBy());
            taskQueryInfo.setStatus(vo.getStatus());
            taskQueryInfo.setTaskName(vo.getTaskName());
            taskQueryInfo.setId(vo.getId());
            result =
                    reviewTaskService.getTaskListByTaskInfo(vo.getPage() == null ? defaultPage : vo.getPage(),
                            vo.getSize() == null ? defaultSize : vo.getSize(), taskQueryInfo);
        } catch (Exception e) {
            LOG.error("Some thing wrong during getPagedTasks.do: " + e.getLocalizedMessage());
            return this.markErrorResult(e.getLocalizedMessage());
        }

        JsonResult js = this.markSuccessResult();
        js.setData(new PageInfo<ReviewTask>(result));
        return js;
    }

    @RequestMapping(value = "/download.do")
    public String download(String taskId) {
        String fileName;
        List<Integer> ids = new ArrayList<Integer>();
        for (String temp : taskId.split(",")) {
            ids.add(Integer.valueOf(temp));
        }
        try {
            fileName = reviewAdTaskService.download(ids);
            return "redirect:/nfs/" + fileName + "?filename=" + fileName;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            LOG.error("下载文件错误", e);
            return null; // 这里最好有个错误说明页面做地址
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/delete.do", method = RequestMethod.POST, consumes = "application/json")
    public JsonResult delete(@RequestBody Map<String, Object> map) {

        try {
            List<Integer> taskIds = (List<Integer>) map.get("taskIds");
            if (CollectionUtils.isEmpty(taskIds)) {
                return this.markSuccessResult();
            }
            this.reviewTaskService.deleteByTaskId(taskIds);
            return this.markSuccessResult();
        } catch (Exception e) {
            return this.markErrorResult("删除失败！");
        }
    }

    @RequestMapping(value = "/downloadWhenCreateReview.do", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JsonResult downloadWhenCreateReviewTask(@RequestBody CreateReviewTaskVo vo) {
        try {
            List<Integer> taskList = vo.getTaskList();
            if (CollectionUtils.isEmpty(taskList)) {
                return this.markErrorResult("任务列表不能为空！");
            }

            String result =
                    reviewTaskConditionService.downloadWhenCreate(vo.getReviewTaskCondition(), vo.getGroupNum(),
                            taskList, vo.getTaskType());
            return this.markSuccessResult(result, "下载成功");
        } catch (Exception e) {
            return this.markErrorResult("获取数量失败！");
        }
    }
}
