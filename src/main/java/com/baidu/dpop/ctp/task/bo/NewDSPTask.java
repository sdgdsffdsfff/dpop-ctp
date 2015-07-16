/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.task.bo;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.bo.GeneralTag;
import com.baidu.dpop.ctp.adtag.bo.TagInfo;
import com.baidu.dpop.ctp.adtag.bo.TagTypeTreeNode;
import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;
import com.baidu.dpop.ctp.adtag.utils.TaskTypeUtils;
import com.baidu.dpop.ctp.adtag.vo.TaskType;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.mainTask.constant.GeneralMcType;
import com.baidu.dpop.ctp.mainTask.constant.ModuserLevel;

/**
 * 百度DSP BO类
 */
public class NewDSPTask extends NewDSPTaskBase implements GeneralTask {

    private static final long serialVersionUID = 6917144115921957186L;

    // 额外属性-------------------------------------------------------------

    private String adTradeNameLevel2; // 二级行业名称
    private String adTradeNameLevel3; // 三级行业名称
    private AdTag adTagNew; // 标注
    private Byte taskType; // 任务类型

    @Override
    public String getAdTradeNameLevel2() {
        return adTradeNameLevel2;
    }

    public void setAdTradeNameLevel2(String adTradeNameLevel2) {
        this.adTradeNameLevel2 = adTradeNameLevel2;
    }

    @Override
    public String getAdTradeNameLevel3() {
        return adTradeNameLevel3;
    }

    public void setAdTradeNameLevel3(String adTradeNameLevel3) {
        this.adTradeNameLevel3 = adTradeNameLevel3;
    }

    public AdTag getAdTagNew() {
        return adTagNew;
    }

    @Override
    public void setAdTagNew(AdTag adTag) {
        this.adTagNew = adTag;
    }

    public Byte getTaskType() {
        return taskType;
    }

    public void setTaskType(Byte taskType) {
        this.taskType = taskType;
    }

    // 接口需求-------------------------------------------------------------

    @Override
    public Number getGroupId() {
        return this.getUserId();
    }

    @Override
    public Number getModuserLevel() {
        return ModuserLevel.OUTSIDE.getId();
    }

    @Override
    public Number getMcId() {
        return null;
    }

    @Override
    public Number getMcVersionId() {
        return null;
    }

    @Override
    public String getDescription1() {
        return getDescription();
    }

    @Override
    public String getDescription2() {
        return null;
    }

    /**
     * 获取数据类型
     */
    @Override
    public Number getDataType() {
        return GroupDataType.NEWDSP.getId();
    }

    /**
     * 获取通用的物料类型
     */
    @Override
    public Number getGeneralWuliaoType() {
        if (getWuliaoType().intValue() == 1 && getUrl() != null && !getUrl().equals("NULL")) {
            return GeneralMcType.getGeneralMcType(5, GroupDataType.NEWDSP.getId());
        } else {
            return GeneralMcType.getGeneralMcType(getWuliaoType(), GroupDataType.NEWDSP.getId());
        }
    }

    /**
     * 获取优先级
     */
    @Override
    public Long getGeneralPriority() {
        return ((this.getChatime().getTime() / 60000L) << 32) + (this.getCreateTime().getTime() / 60000L);
    }

    /**
     * 根据自身含有的数据生成标注信息
     */
    @Override
    public TagInfo genTagInfo() {
        TagInfo info = new TagInfo();

        info.setAdTradeIdLevel3(getAdTradeIdLevel3());
        info.setComment("");
        info.setRefId(getId());
        info.setSample(getAdTag());
        info.setTag(AdTagUtils.getTag(TaskTypeUtils.hideUnusedTag(getAdTag(), taskType)));

        return info;
    }

    /**
     * 根据行业变换adTag，即将不含有的标签置为-
     */
    @Override
    public void transformAdTag(Map<Integer, TagTypeTreeNode> map) {
        Set<String> tags = TagTypeTreeNode.getType(getAdTradeIdLevel3(), map);
        setAdTag(AdTagUtils.transform(getAdTag(), tags));
    }

    // 上传需求-------------------------------------------------------------

    @Override
    public void setArgs(String line) {
        // 不使用此函数进行参数设置
    }

    // 下载需求-------------------------------------------------------------

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
        sb.append(AdTagUtils.getTagNames(taskType) + ",");
        sb.append("备注,");
        sb.append("标注时间,");
        sb.append("chatime,");
        sb.append("标注人\n");
        return sb.toString();
    }

    @Override
    public String genTagedString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append(getAdId() + "\t");
        sb.append(getAdverId() + "\t");
        sb.append(getUserId() + "\t");
        sb.append(getCompany().replace("\n", " ") + "\t");
        sb.append(getWebsite().replace("\n", " ") + "\t");
        sb.append(getWuliaoType() + "\t");
        sb.append(getTitle().replace("\n", " ") + "\t");
        sb.append(getDescription().replace("\n", " ") + "\t");
        sb.append(getShowUrl() + "\t");
        sb.append(getTargetUrl() + "\t");
        sb.append(getWidth() + "*" + getHeight() + "\t");
        sb.append(getAdTradeIdLevel2() + "\t");

        // tag 信息
        AdTag tagInfo = getAdTagNew();
        if (tagInfo == null) {
            return "";
        }

        sb.append(tagInfo.getAdTradeIdLevel3() + "\t");
        if (StringUtils.isNotEmpty(tagInfo.getAdTag())) {
            String tag = tagInfo.getAdTag();
            tag = tag.replace(AdTagUtils.NULL, '0');
            sb.append(tag.charAt(1) + "\t"); // vulgar
            sb.append(tag.substring(4) + "\t");
        } else {
            sb.append("null\t");
            sb.append("null\t");
        }

        // comment
        sb.append(tagInfo.getComment().replace("\n", " ") + "\t");
        sb.append(sdf.format(getCreateTime()) + "\t");
        sb.append(sdf.format(getChatime()) + "\t");
        // update Time
        sb.append(sdf.format(tagInfo.getUpdateTime()) + "\t");
        // update user
        sb.append(tagInfo.getUpdateUser() + "\t");

        sb.append(getTaskId() + "\t");
        sb.append(getTaskName() + "\n");

        return sb.toString();
    }

    @Override
    public String genFullString(Number taskType) {
        StringBuilder sb = new StringBuilder();
        sb.append(getAdId() + ",");
        sb.append(getAdverId() + ",");
        sb.append(getUserId() + ",");
        sb.append(getWuliaoType() + ",");

        setTitle(this.getTitle().replace("\"", "\"\"").replace("\n", " "));
        sb.append(getTitle().contains(",") ? ('"' + getTitle() + '"') : getTitle() + ",");

        setDescription(this.getDescription().replace("\"", "\"\"").replace("\n", " "));
        sb.append(getDescription().contains(",") ? ('"' + getDescription() + '"') : getDescription() + ",");

        sb.append(getShowUrl() + ",");
        sb.append(getTargetUrl() + ",");
        sb.append(getWidth() + ",");
        sb.append(getHeight() + ",");

        setCompany(this.getCompany().replace("\"", "\"\""));
        sb.append(getCompany().contains(",") ? ('"' + getCompany() + '"') : getCompany() + ",");

        setWebsite(getWebsite().replace("\"", "\"\""));
        sb.append(getWebsite().contains(",") ? ('"' + getWebsite() + '"') : getWebsite() + ",");

        AdTag tagInfo = this.getAdTagNew();

        sb.append(getAdTradeIdLevel2() + ",");
        sb.append(getAdTradeNameLevel2() + ",");
        sb.append(tagInfo == null ? getAdTradeIdLevel3() + "," : tagInfo.getAdTradeIdLevel3() + ",");
        sb.append(tagInfo == null ? getAdTradeNameLevel3() + "," : tagInfo.getAdTradeNameLevel3() + ",");

        String tagValue = tagInfo == null ? getAdTag() : tagInfo.getAdTag();

        int i = 0;
        TaskType type = TaskTypeUtils.getType(taskType);
        Assert.notNull(type);
        for (GeneralTag tag : AdTagUtils.TAGLIST) {
            if (type.contains(tag)) {
                sb.append(tagValue.charAt(i) + ",");
            }
            i++;
        }

        // comment
        String comment = tagInfo == null ? "NULL" : tagInfo.getComment();
        if (StringUtils.isNotEmpty(comment)) {
            comment = comment.replace("\"", "\"\"").replace("\n", " ");
            sb.append(comment.contains(",") ? ('"' + comment + '"') : comment + ",");
        } else {
            sb.append("NULL,");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sb.append((tagInfo == null ? "NULL" : sdf.format(tagInfo.getUpdateTime())) + ",");
        sb.append(sdf.format(getChatime()) + ",");
        sb.append((tagInfo == null ? "NULL" : tagInfo.getUpdateUser()) + "\n");
        return sb.toString();
    }

}