package com.baidu.dpop.ctp.review.vo;

import com.baidu.dpop.ctp.adtag.bo.TagInfo;
import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;
import com.baidu.dpop.ctp.adtag.utils.TaskTypeUtils;
import com.baidu.dpop.ctp.review.bo.ReviewAdTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.vo.PresentedTask;

public class PresentedReviewTask extends PresentedTask {

    private static final long serialVersionUID = -7681201921503625683L;

    private TagInfo tagedTagInfo;

    public TagInfo getTagedTagInfo() {
        return tagedTagInfo;
    }

    public void setTagedTagInfo(TagInfo tagedTagInfo) {
        this.tagedTagInfo = tagedTagInfo;
    }

    public PresentedReviewTask() {
    }

    public PresentedReviewTask(GeneralTask task, ReviewAdTask reviewTask, boolean notBlind, Number taskType) {
        super(task);
        TagInfo tag = new TagInfo();
        tag.setSample(reviewTask.getAdTagReview());
        tag.setAdTradeIdLevel3(reviewTask.getAdTradeIdLevel3Review());
        tag.setComment(reviewTask.getCommentReview());
        tag.setId(reviewTask.getId());
        tag.setRefId(task.getId().longValue());
        tag.setTag(AdTagUtils.getTag(TaskTypeUtils.hideUnusedTag(reviewTask.getAdTag(), taskType)));
        super.setTagInfo(tag);
        TagInfo taged = null;
        if (!reviewTask.getUpdateUser().equals(ReviewAdTask.updateUser) || notBlind) {
            taged = new TagInfo();
            taged.setAdTradeIdLevel3(reviewTask.getAdTradeIdLevel3());
            taged.setRefId(task.getId().longValue());
            taged.setComment(reviewTask.getComment());
            taged.setSample(reviewTask.getAdTag());
            taged.setId(null);
            taged.setRefId(null);
            taged.setTag(AdTagUtils.getTag(TaskTypeUtils.hideUnusedTag(reviewTask.getAdTag(), taskType)));
            this.setTagedTagInfo(taged);
        } else {
            tag.setAdTradeIdLevel3(reviewTask.getAdTradeIdLevel3());
        }
    }
}
