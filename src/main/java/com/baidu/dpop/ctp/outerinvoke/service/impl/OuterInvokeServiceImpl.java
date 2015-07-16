package com.baidu.dpop.ctp.outerinvoke.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.ctp.adtag.service.AdTagService;
import com.baidu.dpop.ctp.adtag.vo.SubmitTagInfo;
import com.baidu.dpop.ctp.common.rpc.service.NewDSPRpcService;
import com.baidu.dpop.ctp.common.utils.HttpClientUtils;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.industrytype.service.IndustryTypeService;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.outerinvoke.service.OuterInvokeService;
import com.baidu.dpop.ctp.outerinvoke.vo.CreativityTagResponse;
import com.baidu.dpop.ctp.review.service.ReviewAdTaskService;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.task.vo.CreativityInfo;
import com.baidu.dpop.ctp.task.vo.PresentedTaskDetail;

import javax.annotation.Resource;

@SuppressWarnings("restriction")
@Service
public class OuterInvokeServiceImpl implements OuterInvokeService {

    // private ExecutorService pool = Executors.newFixedThreadPool(10);
    //
    // private static final Logger LOG = Logger.getLogger(OuterInvokeServiceImpl.class);

    @Autowired
    private HttpClientUtils httpClientUtils;

    @Autowired
    private AdTagService adTagService;

    @Autowired
    private ReviewAdTaskService reviewAdTaskService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private GeneralTaskService generalTaskService;

    @Autowired
    private IndustryTypeService industryTypeService;

    @Resource
    private NewDSPRpcService newDSPRpcService;

    @Override
    public void getCreativityInfo(List<PresentedTaskDetail> list) {

        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        if (!GroupDataType.isNewDsp(list.get(0).getDataType())) {
            return;
        }

        List<Long> ids = new ArrayList<Long>(list.size());
        for (PresentedTaskDetail task : list) {
            ids.add(task.getAdId().longValue());
        }

        CreativityTagResponse<CreativityInfo> r = newDSPRpcService.getCreativityInfo(ids);
        List<CreativityInfo> cr = new ArrayList<CreativityInfo>();
        if (r.getStatus() != 3) {
            cr = r.getDatas();
        }

        Map<Long, CreativityInfo> map = new HashMap<Long, CreativityInfo>(cr.size());

        for (CreativityInfo info : cr) {
            map.put(info.getCreativityId(), info);
        }
        for (PresentedTaskDetail pd : list) {
            CreativityInfo info = map.get(pd.getAdId());
            if (info != null) {
                pd.setImgUrl(info.getUrlList());
            }
        }
    }

    @Override
    public void doUnBlockedAssignTask(final Boolean isReview, final List<Long> ids, final List<SubmitTagInfo> list,
            final Integer dataType, final Integer taskId, final Date date) {

        // TODO 如果不是百度dsp则直接返回
        if (!GroupDataType.isNewDsp(dataType)) {
            return;
        }

        // pool.execute(new Runnable() {
        // @Override
        // public void run() {
        //
        // Task t = taskService.findById(taskId.longValue());
        // if (t == null) {
        // LOG.error("Error during assign new dsp : taskId " + taskId + " is null.");
        // return;
        // }
        //
        // List<CreativityTagResult> assign = new ArrayList<CreativityTagResult>();
        // Map<Long, GeneralTask> map = generalTaskService.batchGetMapped(ids, dataType);
        //
        // Set<Integer> tradeTypes = new HashSet<Integer>();
        // for (SubmitTagInfo info : list) {
        // tradeTypes.add(info.getAdTradeIdLevel3());
        // }
        //
        // Map<Integer, NewIndustryType> tradeMap =
        // industryTypeService.batchGetMapped(new ArrayList<Integer>(tradeTypes));
        // for (SubmitTagInfo info : list) {
        // AdTag tag = info.toAdTag(t.getTaskType().intValue());
        // GeneralTask task = map.get(tag.getRefId());
        //
        // CreativityTagResult result = new CreativityTagResult();
        // result.setCreativityId(task.getAdId().longValue());
        // result.setOldTradeId(tradeMap.get(info.getAdTradeIdLevel3()).getLevel2Id().longValue());
        // result.setTagVersion(task.getTagVersion());
        // result.setTags(AdTagUtils.tagtoLongList(tag.getAdTag()));
        // result.setSubmitTime(date.getTime());
        // result.setTradeId(info.getAdTradeIdLevel3().longValue());
        // assign.add(result);
        // System.out.println(result.getTradeId() + " : " + result.getOldTradeId());
        // }
        //
        // for (int tryTimes = 1; tryTimes <= 3; tryTimes++) {
        // try {
        // CreativityTagResponse<Long> result = newDSPRpcService.updateCreativityTag(assign);
        // if (result.getStatus() != 3) {
        // if (!isReview) {
        // adTagService.assignedAdTags(taskId, dataType, result.getDatas());
        // } else {
        // reviewAdTaskService.assignedAdTags(taskId, dataType, result.getDatas());
        // }
        // break;
        // }
        //
        // if (tryTimes == 3) {
        // throw new BaseRuntimeException("assign wrong!");
        // }
        // } catch (Exception e) {
        // LOG.error(e.getLocalizedMessage(), e);
        // }
        // }
        // }
        // });
    }

}
