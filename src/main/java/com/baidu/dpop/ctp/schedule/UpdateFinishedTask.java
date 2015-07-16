package com.baidu.dpop.ctp.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatus;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.mainTask.vo.TaskStatusChangeVo;

public class UpdateFinishedTask {

    private static final Logger LOG = Logger.getLogger(ShutdownScheduleTask.class);

    @Autowired
    private TaskService taskService;

    public void execute() {
        try {
            LOG.info("Update finished tasks start at: " + new Date());
            List<Task> list = taskService.getTasksToFinish();

            if (CollectionUtils.isEmpty(list)) {
                return;
            }

            List<Integer> ids = new ArrayList<Integer>();
            for (Task t : list) {
                ids.add(t.getId());
            }

            TaskStatusChangeVo vo = new TaskStatusChangeVo();
            vo.setStatusChange(TaskStatus.FINISHED.getId().intValue());
            vo.setTaskIds(ids);
            taskService.changeTaskStateForced(vo);
        } catch (Exception e) {
            if (e instanceof BaseRuntimeException) {
                LOG.info("Update finished tasks wrong: " + e.getLocalizedMessage());
            } else {
                LOG.error("Update finished tasks Exception: " + e.getMessage(), e);
            }
        } finally {
            LOG.info("Update finished tasks end at: " + new Date());
        }

    }

}
