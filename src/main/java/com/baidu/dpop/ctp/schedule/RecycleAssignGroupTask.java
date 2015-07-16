package com.baidu.dpop.ctp.schedule;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.baidu.dpop.ctp.group.service.GroupService;
import com.baidu.dpop.ctp.review.service.ReviewGroupService;

/**   
 * 回收已分配的标注&审核Group
 * @author cgd  
 * @date 2015年4月8日 下午2:36:29 
 */
public class RecycleAssignGroupTask {
    
    private static final Logger LOG = Logger.getLogger(RecycleAssignGroupTask.class);
    
    @Autowired
    private GroupService groupService;
    @Autowired
    private ReviewGroupService reviewGroupService;
    
    @Value("${dpop.ctp.recycle.assign.interval.time}")
    private Integer intervalTime;
    private static final Integer DEFAULT_INTERVAL_TIME = 15;

    public void execute() {
        try {
            LOG.info("RecycleAssignGroupTask start at: " + new Date());
            Calendar beginTimeCal = Calendar.getInstance();
            if(intervalTime == null || intervalTime < 0) {
                intervalTime = DEFAULT_INTERVAL_TIME;
            }
            beginTimeCal.add(Calendar.MINUTE, intervalTime * -1);
            
            // 回收30分钟前分配但未完成标注的Groups
            this.groupService.recycleAssignGroups(beginTimeCal.getTime());
            
            // 回收30分钟前分配但未完成的审核Groups
            this.reviewGroupService.recycleAssignGroups(beginTimeCal.getTime());

        } catch (Exception e) {
            LOG.error("RecycleAssignGroupTask Exception: " + e.getMessage(), e);
        } finally {
            LOG.info("RecycleAssignGroupTask end at: " + new Date());
        }

    }
    
    public static void main(String[] args) {
        Calendar beginTimeCal = Calendar.getInstance();
        System.out.println(beginTimeCal.getTime().toString());
        
        beginTimeCal.add(Calendar.MINUTE, -30);
        System.out.println(beginTimeCal.getTime().toString());
    }

}
