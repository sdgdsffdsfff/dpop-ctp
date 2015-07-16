package com.baidu.dpop.ctp.outerinvoke.service;

import java.util.Date;
import java.util.List;

import com.baidu.dpop.ctp.adtag.vo.SubmitTagInfo;
import com.baidu.dpop.ctp.task.vo.PresentedTaskDetail;

public interface OuterInvokeService {

    /**
     * 百度dsp实时获取信息
     * 
     * @param list 需要获取的信息列表，结果直接修改这个列表填充
     */
    public void getCreativityInfo(List<PresentedTaskDetail> list);

    /**
     * 百度dsp实时入库，非阻塞
     * 
     * @param isReview 是否是审核
     * @param ids 提交的id列表
     * @param list 提交的信息列表
     * @param dataType 数据类型
     * @param taskId 任务id
     * @param date 提交时间
     */
    public void doUnBlockedAssignTask(final Boolean isReview, final List<Long> ids, final List<SubmitTagInfo> list,
            final Integer dataType, final Integer taskId, final Date date);
}
