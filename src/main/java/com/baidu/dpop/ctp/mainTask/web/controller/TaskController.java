package com.baidu.dpop.ctp.mainTask.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.dpop.ctp.common.constants.GetParamOrder;
import com.baidu.dpop.ctp.common.mybatis.page.PageInfo;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatus;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatusChangeType;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.mainTask.service.UBMCService;
import com.baidu.dpop.ctp.mainTask.vo.PagedTaskQueryVo;
import com.baidu.dpop.ctp.mainTask.vo.TaskQueryInfo;
import com.baidu.dpop.ctp.mainTask.vo.TaskStatusChangeVo;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.constant.UserRoleType;
import com.baidu.dpop.ctp.user.service.UserService;
import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;

@Controller
@RequestMapping(value = "task")
public class TaskController extends JsonBaseController {

    private static final Logger LOG = Logger.getLogger(TaskController.class);

    @Autowired
    TaskService taskService;

    @Autowired
    GeneralTaskService generalTaskService;

    @Autowired
    UserService userService;

    @Autowired
    UBMCService ubmcService;

    @Value("${dpop.ctp.task.page}")
    private Integer PAGE;

    @Value("${dpop.ctp.task.size}")
    private Integer SIZE;

    @Value("${dpop.ctp.task.order}")
    private String ORDER;

    /**
     * 获取分页task信息
     * 
     * @param pageNo 页码，从0开始，必须>=0
     * @param size 每页数目，必须大于0
     * @param order 排序依据，仅支持id与add_time
     * @param isDesc 是否逆序
     * @param navigatePages 导航页数量，默认为10页
     * @return JsonResult类型的结果内容, 其中包含了 count - 总共复合条件的条目数, data - 当前查询页的结果列表
     */
    @RequestMapping(value = "/getPagedTasks.do", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JsonResult find(@RequestBody PagedTaskQueryVo vo) {

        List<Task> map = null;
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
            taskQueryInfo.setOrderBy(vo.getOrderBy() == null ? ORDER : vo.getOrderBy());
            taskQueryInfo.setStatus(vo.getStatus());
            taskQueryInfo.setTaskName(vo.getTaskName());
            taskQueryInfo.setId(vo.getId());
            map =
                    taskService.getTaskListByTaskInfo(vo.getPage() == null ? PAGE : vo.getPage(),
                            vo.getSize() == null ? SIZE : vo.getSize(), taskQueryInfo);
        } catch (Exception e) {
            LOG.error("Some thing wrong during getPagedTasks.do: " + e.getLocalizedMessage());
            return this.markErrorResult(e.getLocalizedMessage());
        }

        JsonResult js = this.markSuccessResult();
        js.setData(new PageInfo<Task>(map));
        return js;
    }

    /**
     * 依据taskId下载某一任务的全集数据（所有类型数据库中的所有数据，包括未标注的）
     * 
     * @param taskId 需要下载的taskId
     * @return 下载文件的url地址
     */
    @RequestMapping(value = "/download.do")
    public String download(String taskId) {
        String fileName;
        List<Integer> ids = new ArrayList<Integer>();
        for (String temp : taskId.split(",")) {
            ids.add(Integer.valueOf(temp));
        }
        try {
            // 如果只是一个下载任务
            if (ids.size() == 1) {
                fileName = generalTaskService.getFullTaskInfoByTaskId(ids.get(0));
            }
            // 多个下载任务
            else {
                fileName = generalTaskService.getFullTaskInfoBytTaskIds(ids);
            }

            return "redirect:/nfs/" + fileName + "?filename=" + fileName;
        } catch (Exception e) {
            LOG.error("下载文件错误", e);
            return null; // 这里最好有个错误说明页面做地址
        }

    }

    /**
     * 根据taskId改变某任务的状态
     * 
     * @param taskId 需要该表状态的任务的taskId
     * @param status
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/changeTaskStatus.do")
    @ResponseBody
    public JsonResult changeTaskStatus(@RequestBody Map<String, Object> map) {
        List<Integer> tasks;
        Boolean status = false;
        try {
            tasks = (List<Integer>) map.get("tasks");
            status = (Boolean) map.get("status");
            TaskStatusChangeVo vo = new TaskStatusChangeVo();
            vo.setTaskIds(tasks);
            vo.setStatusChange(status ? TaskStatusChangeType.OPEN.getId() : TaskStatusChangeType.CLOSE.getId());
            return this.markSuccessResult(taskService.changeTaskState(vo), (status ? "开启" : "关闭") + "成功");
        } catch (Exception e) {
            LOG.error("关闭任务失败！", e);
            return this.markErrorResult((status ? "开启" : "关闭") + "失败, 原因: " + e.getLocalizedMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeTaskStatusForced.do", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JsonResult change(@RequestBody Map<String, Object> map) {

    	List<Integer> tasks;
    	Integer status = 0;
    	User u = userService.getCurrentLoginUser();
    	
    	if (!UserRoleType.isAdminRoleType(u.getRoleType())) {
    		return this.markErrorResult("非管理员不能操作");
    	}
    	
        try {
            tasks = (List<Integer>) map.get("tasks");
            status = Integer.valueOf(map.get("status").toString());
            TaskStatusChangeVo vo = new TaskStatusChangeVo();
            vo.setTaskIds(tasks);
            vo.setStatusChange(status);
            return this.markSuccessResult(taskService.changeTaskStateForced(vo), "成功");
        } catch (Exception e) {
            LOG.error("关闭任务失败！", e);
            return this.markErrorResult("失败");
        }
    }
    
	@RequestMapping(value = "/deleteTasksByIdList.do", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JsonResult delete(@RequestBody Map<String, Object> map) {

    	@SuppressWarnings("unchecked")
		List<String> taskIds = (List<String>) map.get("ids");
    	List<Integer> tasks = new ArrayList<Integer>();
    	User u = userService.getCurrentLoginUser();
    	
    	if (!UserRoleType.isAdminRoleType(u.getRoleType())) {
    		return this.markErrorResult("非管理员不能操作");
    	}
    	
        try {
           for (String id : taskIds) {
        	   tasks.add(Integer.valueOf(id));
           }
           taskService.deleteTasks(tasks);
           return this.markSuccessResult();
        } catch (Exception e) {
            LOG.error("删除任务失败！", e);
            return this.markErrorResult("失败");
        }
    }
}
