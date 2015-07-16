package com.baidu.dpop.ctp.review.vo;

import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.baidu.dpop.ctp.adtag.bo.GeneralTag;
import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;
import com.baidu.dpop.ctp.adtag.utils.TaskTypeUtils;
import com.baidu.dpop.ctp.adtag.vo.TaskType;
import com.baidu.dpop.ctp.review.bo.ReviewAdTask;
import com.baidu.dpop.ctp.task.bo.DSPTask;
import com.baidu.dpop.ctp.task.bo.DownloadInfo;
import com.baidu.dpop.ctp.task.bo.GeneralTask;

public class ReviewDSPTask implements DownloadInfo {

    private DSPTask task;
    private ReviewAdTask rtask;

    public ReviewDSPTask(GeneralTask gt, ReviewAdTask rtask) {
        Assert.isTrue(gt instanceof DSPTask);
        this.setTask((DSPTask) gt);
        this.setRtask(rtask);
    }

    public DSPTask getTask() {
        return task;
    }

    public void setTask(DSPTask task) {
        this.task = task;
    }

    public ReviewAdTask getRtask() {
        return rtask;
    }

    public void setRtask(ReviewAdTask rtask) {
        this.rtask = rtask;
    }

    public static String genFullStringTitle(Number taskType) {
        StringBuilder sb = new StringBuilder();
        sb.append("创意ID,");
        sb.append("用户ID,");
        sb.append("DSP_ID,");
        sb.append("物料类型,");
        sb.append("落地页,");
        sb.append("创意宽度,");
        sb.append("创意高度,");
        sb.append("公司,");
        sb.append("公司网址,");
        sb.append("DSP名称,");
        sb.append("二级行业ID,");
        sb.append("二级行业,");
        sb.append("三级行业ID,");
        sb.append("三级行业,");
        sb.append("美观度(旧),");
        sb.append("低俗度(旧),");
        sb.append("高危度(旧),");
        sb.append("欺诈度(旧),");
        sb.append(AdTagUtils.getTagNamesReview(taskType) + ",");
        sb.append("备注,");
        sb.append("标注时间,");
        sb.append("chatime,");
        sb.append("标注人\n");
        return sb.toString();
    }

    @Override
    public String genFullString(Number taskType) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getAdId() + ",");
        sb.append(task.getUserId() + ",");
        sb.append(task.getDspId() + ",");
        sb.append(task.getUserId() + ",");
        sb.append(task.getWuliaoType() + ",");

        task.setLandingPage(task.getTargetUrl().replace("\"", "\"\""));
        sb.append(task.getTargetUrl().contains(",") ? ('"' + task.getTargetUrl() + '"') : task.getTargetUrl() + ",");

        sb.append(task.getWidth() + ",");
        sb.append(task.getHeight() + ",");

        task.setNickname(task.getCompany().replace("\"", "\"\""));
        sb.append(task.getCompany().contains(",") ? ('"' + task.getCompany() + '"') : task.getCompany() + ",");

        task.setWebsite(task.getWebsite().replace("\"", "\"\""));
        sb.append(task.getWebsite().contains(",") ? ('"' + task.getWebsite() + '"') : task.getWebsite() + ",");

        task.setDspname(task.getDspname().replace("\"", "\"\""));
        sb.append(task.getDspname().contains(",") ? ('"' + task.getDspname() + '"') : task.getDspname() + ",");

        sb.append(task.getAdTradeIdLevel2() + ",");
        sb.append(task.getAdTradeNameLevel2() + ",");
        sb.append(rtask.getAdTradeIdLevel3() + ",");
        sb.append(rtask.getAdTradeNameLevel3() + ",");
        sb.append(rtask.getAdTradeIdLevel3Review() + ",");
        sb.append(rtask.getAdTradeNameLevel3Review() + ",");

        String tagValue = rtask.getAdTag();
        String tagValueReview = rtask.getAdTagReview();
        
        int i = 0;
        TaskType type = TaskTypeUtils.getType(taskType);
        Assert.notNull(type);
        for (GeneralTag tag : AdTagUtils.TAGLIST) {
            if (type.contains(tag)) {
                sb.append(tagValueReview.charAt(i) + "|" + tagValue.charAt(i) + ",");
            }
            i++;
        }

        // comment
        String comment = rtask.getComment() == null ? "NULL" : rtask.getComment();
        if (StringUtils.isNotEmpty(comment)) {
            comment = comment.replace("\"", "\"\"").replace("\n", " ");
            sb.append(comment.contains(",") ? ('"' + comment + '"') : comment + ",");
        } else {
            sb.append("NULL,");
        }
        comment = rtask.getCommentReview() == null ? "NULL" : rtask.getCommentReview();
        if (StringUtils.isNotEmpty(comment)) {
            comment = comment.replace("\"", "\"\"").replace("\n", " ");
            sb.append(comment.contains(",") ? ('"' + comment + '"') : comment + ",");
        } else {
            sb.append("NULL,");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sb.append((rtask.getTagTime() == null ? "NULL" : sdf.format(rtask.getTagTime())) + ",");
        sb.append((rtask.getUpdateTime() == null ? "NULL" : sdf.format(rtask.getUpdateTime())) + ",");
        sb.append(sdf.format(task.getChatime()) + ",");
        sb.append((rtask.getTagUser() == null ? "NULL" : rtask.getTagUser()) + ",");
        sb.append((rtask.getUpdateUser() == null ? "NULL" : rtask.getUpdateUser()) + "\n");
        return sb.toString();
    }

}
