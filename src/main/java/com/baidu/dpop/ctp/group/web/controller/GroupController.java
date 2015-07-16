package com.baidu.dpop.ctp.group.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.dpop.ctp.adtag.bo.GeneralTag;
import com.baidu.dpop.ctp.adtag.service.AdTagService;
import com.baidu.dpop.ctp.adtag.service.TagTypeService;
import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.constant.GroupStatus;
import com.baidu.dpop.ctp.group.service.GroupService;
import com.baidu.dpop.ctp.group.vo.DistributeGroupResult;
import com.baidu.dpop.ctp.group.vo.SubmitInfoGetVo;
import com.baidu.dpop.ctp.group.vo.SubmitVo;
import com.baidu.dpop.ctp.industrytype.service.IndustryTypeService;
import com.baidu.dpop.ctp.invoke.bo.DownloadInfo;
import com.baidu.dpop.ctp.invoke.service.DownloadInfoService;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.task.vo.PresentedTask;
import com.baidu.dpop.ctp.task.vo.TagFollowInfoVo;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.service.UserService;
import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;

@Controller
@RequestMapping(value = "task/group")
public class GroupController extends JsonBaseController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @Autowired
    private GeneralTaskService generalTaskService;

    @Autowired
    private AdTagService adTagService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TagTypeService tagTypeService;

    @Autowired
    private DownloadInfoService downloadInfoService;

    @Autowired
    private IndustryTypeService industryTypeService;

    private static final Logger LOG = Logger.getLogger(GroupController.class);

    /**
     * 从指定的task中分配一个group。 系统会检查当前登陆的用户并给他分配一个group。 如果分配失败（例如抢占资源导致的冲突），则系统会重试，重试次数为5次
     * 
     * @param taskId 待分配的task_id
     * @return JsonResult 类型的结果，成功则包含了group信息
     */
    @RequestMapping(value = "/distributeNewGroup.do")
    @ResponseBody
    public JsonResult distributeNewGroup(Integer taskId) {

        Task task = null;
        Group g = null;
        User u = null;
        int tryTimes = 0;

        try {
            task = taskService.findById(taskId.longValue());
            u = userService.getCurrentLoginUser();
        } catch (Exception e) {
            LOG.error("分配任务失败！", e);
            return this.markErrorResult("分配任务失败！");
        }

        if (task == null) {
            LOG.error("Can't find task : " + taskId);
            return this.markErrorResult("任务id错误：不存在任务" + taskId);
        }

        do {
            try {
                g = groupService.distributeNewGroup(task);
                if (g == null) {
                    // 未分配已经没有了，使用已经分配给当前用户但他没有答完的题目
                    g = groupService.getStartedGroupByUser(task, u);
                }

                if (g == null) {
                    DistributeGroupResult err = new DistributeGroupResult();
                    err.setTask(task);
                    err.setList(new ArrayList<PresentedTask>());
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
            DistributeGroupResult result = groupService.getTasksByGroup(task, g, u);
            if (result != null) {
                return this.markSuccessResult(result, "分配审核任务成功！");
            }
        } catch (Exception e) {
            LOG.error("分配任务失败！", e);
        }

        return this.markErrorResult("分配任务失败！");
    }

    /**
     * 根据group的主键id获取任务明细
     * 
     * @param id 需要获取的group的主键id
     * @return 获取结果
     */
    @RequestMapping(value = "/getTasksByGroupId.do")
    @ResponseBody
    public JsonResult getTasksByGroup(Long id) {
        User u = userService.getCurrentLoginUser();
        try {
            Group g = groupService.findById(id);
            Task task = taskService.findById(g.getTaskId().longValue());
            List<Group> historyGroup = groupService.getGroupByUser(task, u);
            DistributeGroupResult result = generalTaskService.getTasksByGroup(g, u, historyGroup);
            result.setTask(task);
            result.setGroup(g);
            return this.markSuccessResult(result, "获取任务成功！");
        } catch (Exception e) {
            return this.markErrorResult("获取任务：" + id + "失败！原因：" + e.getLocalizedMessage());
        }
    }

    /**
     * 放弃当前正在执行的group。 这种情况出现在用户没有提交结果却要离开页面时。 用户只能放弃分配给自己的group。
     * 
     * @param groupId 需要放弃的group_id
     * @return JsonResult 类型的结果
     */
    @RequestMapping(value = "/giveUpGroup.do")
    @ResponseBody
    public JsonResult giveUpGroup(Long id) {
        User u = userService.getCurrentLoginUser();
        try {
            return groupService.giveUpGroup(id, u) ? this.markSuccessResult() : this.markErrorResult("group : " + id
                    + " 用户  : " + u.getId() + " 不能放弃！");
        } catch (Exception e) {
            LOG.error("giveUpGroup ERROR: ", e);
            return this.markErrorResult("放弃任务失败，请稍候再试");
        }
    }

    /**
     * 获取所有的行业类型
     * 
     * @return 三级Map结构表示的行业类型数据
     */
    @RequestMapping(value = "/getAllIndustryType.do")
    @ResponseBody
    public JsonResult getAllIndustryType() {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        try {
            Map<String, GeneralTag> map = new HashMap<String, GeneralTag>();
            for (GeneralTag tag : AdTagUtils.TAGLIST) {
                map.put(tag.getName(), tag);
            }

            mapResult.put("tagTypeInfo", map);
            mapResult.put("levelThree", this.industryTypeService.getLevelThreeIndustryType());
        } catch (Exception e) {
            LOG.error("error", e);
            return this.markErrorResult("Get industryType wrong");
        }
        return this.markSuccessResult(mapResult, "获取成功");
    }

    /**
     * 提交任务
     * 
     * @param map 提交参数：list - 提交的列表；groupId - 提交的grouId
     * @return JsonResult形式的结果
     */
    @RequestMapping(value = "/submitTasksByGroupId.do", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JsonResult submitGroup(@RequestBody SubmitVo vo) {
        try {
            groupService.submitTasks(vo.getList(), vo.getGroupId(), userService.getCurrentLoginUser());
            return this.markSuccessResult(null, "提交成功！");
        } catch (Exception e) {
            if (e instanceof BaseRuntimeException) {
                LOG.info("submit task wrong: " + e.getLocalizedMessage());
            } else {
                LOG.error("提交任务失败", e);
            }
            return this.markErrorResult(e.getLocalizedMessage());
        }
    }

    /**
     * 获取跟进中的标注任务详情
     * 
     * */
    @RequestMapping(value = "/getTagFollowInfo.do")
    @ResponseBody
    public JsonResult getTagFollowInfo(Integer taskId) {
        Assert.notNull(taskId);

        try {
            List<TagFollowInfoVo> data = this.groupService.getTagFollowInfoList(taskId);
            return this.markSuccessResult(data, "success");
        } catch (Exception e) {
            LOG.error("获取任务失败", e);
            return this.markErrorResult("getTagFollowInfo Error: " + e.getMessage());
        }
    }

    /**
     * 打开某个任务，测试用接口
     * 
     * @param groupId
     * @param taskId
     * @param dataType
     * @return
     */
    @RequestMapping(value = "/openGroupById.do")
    @ResponseBody
    public JsonResult openGroup(Long groupId, Integer taskId, Integer dataType) {
        try {
            Group g = new Group();
            g.setGroupId(groupId);
            g.setTaskId(taskId);
            g.setDataType(dataType.byteValue());
            g = groupService.getGroup(g);
            g.setStatus(GroupStatus.NOT_STARTED.getId());
            groupService.updateByIdSelective(g);
            return this.markSuccessResult();
        } catch (Exception e) {
            LOG.error("提交任务失败", e);
            return this.markErrorResult("提交任务失败！原因: " + e.getLocalizedMessage());
        }
    }

    /**
     * 变换行业，变换行业之后标签也会改变
     * 
     * @param adTradeId 需要变换的行业
     * @return 变换后所包含的标签信息
     */
    @RequestMapping(value = "/changeTrade.do")
    @ResponseBody
    public JsonResult changeTrade(Integer adTradeId, Integer taskType) {
        try {
            return this.markSuccessResult(tagTypeService.changeTradeType(adTradeId, taskType), "获取成功");
        } catch (Exception e) {
            LOG.error("变换行业失败", e);
            return this.markErrorResult("变换行业失败！");
        }
    }

    /**
     * 根据行业信息获取标签信息，取交集
     * 
     * @param map 参数列表，仅包含一个行业列表
     * @return 获取到的标签信息
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/getTagsByTradeIdList.do")
    @ResponseBody
    public JsonResult getTags(@RequestBody Map<String, Object> map) {
        try {
            List<Integer> tradeIds = (List<Integer>) map.get("tradeIds");
            Byte taskType = ((Integer) map.get("taskType")).byteValue();

            if (taskType == null) {
                return this.markErrorResult("必须先选择任务列表再选择行业！");
            }

            return this.markSuccessResult(tagTypeService.getByTradeIdList(tradeIds, taskType), "获取成功");
        } catch (Exception e) {
            LOG.error("变换行业失败", e);
            return this.markErrorResult("获取行业失败！");
        }
    }

    /**
     * 下载group标注信息
     * 
     * @param vo 获取参数
     * @return 下载信息id
     */
    @RequestMapping(value = "/downloadGroupInfo.do", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JsonResult download(@RequestBody SubmitInfoGetVo vo) {
        try {
            return this.markSuccessResult(groupService.getGroupDownloadInfo(vo), "获取成功");
        } catch (Exception e) {
            LOG.error("下载失败", e);
            return this.markErrorResult("下载失败！");
        }
    }

    /**
     * 根据下载信息id获取下载信息，如果完成则获取文件地址，否则获取完成百分比
     * 
     * @param id 下载信息id
     * @return 文件地址或下载信息
     */
    @RequestMapping(value = "/getDownloadInfo.do")
    @ResponseBody
    public JsonResult getDownloadInfo(Long id) {
        DownloadInfo info = downloadInfoService.findById(id);
        if (info.getPercentage() == 100) {
            return this.markSuccessResult("/nfs/" + info.getFileName(), "成功");
        } else {
            return this.markErrorResult("文件: " + info.getFileName() + " 还未下载完成, 进度: " + info.getPercentage());
        }
    }
}
