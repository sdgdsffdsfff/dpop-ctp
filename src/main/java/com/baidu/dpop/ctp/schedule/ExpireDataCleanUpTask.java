package com.baidu.dpop.ctp.schedule;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.baidu.dpop.ctp.mainTask.service.TaskService;

/**
 * 清除超过设定期限的标注明细数据任务
 * 
 * @author cgd
 * @date 2015年5月6日 上午10:59:15
 */
public class ExpireDataCleanUpTask {
    private static final Logger LOG = Logger.getLogger(ExpireDataCleanUpTask.class);

    @Autowired
    private TaskService mainTaskService;

    @Value("${dpop.ctp.clean.up.expire.time}")
    private Integer expireTime;

    public void execute() {
        try {
            LOG.info("ExpireDataCleanUpTask start at: " + new Date());

            // 获取存储超时对应的待删除数据的日期
            mainTaskService.deleteTasks(expireTime);

        } catch (Exception e) {
            LOG.error("ExpireDataCleanUpTask Exception: " + e.getMessage(), e);
        } finally {
            LOG.info("ExpireDataCleanUpTask end at: " + new Date());
        }

    }

}
