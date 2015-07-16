package com.baidu.dpop.ctp.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatusChangeType;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.mainTask.vo.TaskStatusChangeVo;

/**
 * 日常任务于每天下午21:00关闭，状态变更为【已关闭】
 * 
 * @author cgd
 * @date 2015年1月11日 下午9:35:05
 */
public class ShutdownScheduleTask {

    private static final Logger LOG = Logger.getLogger(ShutdownScheduleTask.class);

    @Autowired
    private TaskService taskService;

    public void execute() {
        try {
            LOG.info("ShutdownScheduleTask start at: " + new Date());
            // 当天的标注Task关闭
            String taskName = "日常标注_" + new SimpleDateFormat("yyyyMMdd").format(new Date());
            TaskStatusChangeVo vo = new TaskStatusChangeVo();
            vo.setStatusChange(TaskStatusChangeType.CLOSE.getId());
            List<Integer> taskIdList = taskService.getTaskIdsByName(taskName);
            if (CollectionUtils.isNotEmpty(taskIdList)) {
                vo.setTaskIds(taskIdList);
                this.taskService.changeTaskState(vo);
            }

        } catch (Exception e) {
        	if (e instanceof BaseRuntimeException) {
        		LOG.info("ShutdownScheduleTask wrong: " + e.getLocalizedMessage());
        	} else {
        		LOG.error("ShutdownScheduleTask Exception: " + e.getMessage(), e);
        	}
        } finally {
            LOG.info("ShutdownScheduleTask end at: " + new Date());
        }

    }

}
