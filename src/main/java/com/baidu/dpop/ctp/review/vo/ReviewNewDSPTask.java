package com.baidu.dpop.ctp.review.vo;

import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.baidu.dpop.ctp.adtag.bo.GeneralTag;
import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;
import com.baidu.dpop.ctp.adtag.utils.TaskTypeUtils;
import com.baidu.dpop.ctp.adtag.vo.TaskType;
import com.baidu.dpop.ctp.review.bo.ReviewAdTask;
import com.baidu.dpop.ctp.task.bo.DownloadInfo;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.bo.NewDSPTask;

public class ReviewNewDSPTask implements DownloadInfo {
    
    private NewDSPTask task;
    private ReviewAdTask rtask;
    
    public ReviewNewDSPTask(GeneralTask gt, ReviewAdTask rtask) {
        Assert.isTrue(gt instanceof NewDSPTask);
        this.setTask((NewDSPTask) gt);
        this.setRtask(rtask);
    }
    
    public NewDSPTask getTask() {
        return task;
    }
    
    public void setTask(NewDSPTask task) {
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
        sb.append("广告ID,");
        sb.append("用户ID,");
        sb.append("物料类型,");
        sb.append("标题,");
        sb.append("描述,");
        sb.append("展现页,");
        sb.append("落地页,");
        sb.append("创意宽度,");
        sb.append("创意高度,");
        sb.append("公司,");
        sb.append("公司网址,");
        sb.append("二级行业ID,");
        sb.append("二级行业,");
        sb.append("三级行业ID,");
        sb.append("三级行业,");
        sb.append("审核三级行业ID,");
        sb.append("审核三级行业,");
        sb.append(AdTagUtils.getTagNamesReview(taskType) + ",");
        sb.append("备注,");
        sb.append("审核备注,");
        sb.append("标注时间,");
        sb.append("审核时间,");
        sb.append("chatime,");
        sb.append("标注人,");
        sb.append("审核人\n");
        return sb.toString();
    }

    /**
     * 获取全量下载数据的数据行，复合csv文件格式
     * 
     * @return 全量下载数据行
     */
    public String genFullString(Number taskType) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getAdId() + ",");
        sb.append(task.getAdverId() + ",");
        sb.append(task.getUserId() + ",");
        sb.append(task.getWuliaoType() + ",");

        task.setTitle(task.getTitle().replace("\"", "\"\"").replace("\n", " "));
        sb.append((task.getTitle().contains(",") ? ('"' + task.getTitle() + '"') : task.getTitle()) + ",");

        task.setDescription(task.getDescription().replace("\"", "\"\"").replace("\n", " "));
        sb.append((task.getDescription().contains(",") ? ('"' + task.getDescription() + '"') : task.getDescription()) + ",");

        task.setShowUrl(task.getShowUrl().replace("\"", "\"\"").replace("\n", " "));
        sb.append(task.getShowUrl().contains(",") ? ('"' + task.getShowUrl() + '"') : task.getShowUrl() + ",");

        task.setTargetUrl(task.getTargetUrl().replace("\"", "\"\"").replace("\n", " "));
        sb.append(task.getTargetUrl().contains(",") ? ('"' + task.getTargetUrl() + '"') : task.getTargetUrl() + ",");

        sb.append(task.getWidth() + ",");
        sb.append(task.getHeight() + ",");

        task.setCompany(task.getCompany().replace("\"", "\"\"").replace("\n", " "));
        sb.append(task.getCompany().contains(",") ? ('"' + task.getCompany() + '"') : task.getCompany() + ",");

        task.setWebsite(task.getWebsite().replace("\"", "\"\"").replace("\n", " "));
        sb.append(task.getWebsite().contains(",") ? ('"' + task.getWebsite() + '"') : task.getWebsite() + ",");

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
