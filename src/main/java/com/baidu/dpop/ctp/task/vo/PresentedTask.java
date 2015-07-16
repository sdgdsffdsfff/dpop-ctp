package com.baidu.dpop.ctp.task.vo;

import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.bo.TagInfo;
import com.baidu.dpop.ctp.task.bo.GeneralTask;

public class PresentedTask implements java.io.Serializable, Comparable<PresentedTask> {

    private static final long serialVersionUID = 2237615089277978231L;

    private Number id; // 主键id
    private Number adId; // ad_id
    private Number groupId; // group_id
    private Number dataType; // 数据类型
    private Number wuliaoType; // 物料类型
    private Byte taskType; // 任务类型
    private TagInfo tagInfo; // 标注信息
    private Number adTradeIdLevel2; // 二级行业id
    private String adTradeNameLevel2; // 二级行业名称
    private String targetUrl; // 落地页
    private String showUrl; // 展现页
    private String title; // 标题
    private String description1; // 描述一
    private String description2; // 描述二
    private Number mcId; // mc_id
    private Number mcVersionId; // mc_version_id

    public PresentedTask() {
    }

    public PresentedTask(GeneralTask task) {
        this.setId(task.getId());
        this.setAdId(task.getAdId());
        this.setGroupId(task.getGroupId());
        this.setDataType(task.getDataType());
        this.setWuliaoType(task.getGeneralWuliaoType());
        this.setTaskType(task.getTaskType());
        this.setAdTradeIdLevel2(task.getAdTradeIdLevel2());
        this.setAdTradeNameLevel2(task.getAdTradeNameLevel2());
        this.setTargetUrl(task.getTargetUrl());
        this.setShowUrl(task.getShowUrl());
        this.setTitle(task.getTitle());
        this.setDescription1(task.getDescription1());
        this.setDescription2(task.getDescription2());
        this.setMcId(task.getMcId());
        this.setMcVersionId(task.getMcVersionId());
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public Number getAdId() {
        return adId;
    }

    public void setAdId(Number adId) {
        this.adId = adId;
    }

    public Number getGroupId() {
        return groupId;
    }

    public void setGroupId(Number groupId) {
        this.groupId = groupId;
    }

    public Number getDataType() {
        return dataType;
    }

    public void setDataType(Number dataType) {
        this.dataType = dataType;
    }

    public Number getWuliaoType() {
        return wuliaoType;
    }

    public void setWuliaoType(Number wuliaoType) {
        this.wuliaoType = wuliaoType;
    }

    public Byte getTaskType() {
        return taskType;
    }

    public void setTaskType(Byte taskType) {
        this.taskType = taskType;
    }

    public TagInfo getTagInfo() {
        return tagInfo;
    }

    public void setTagInfo(TagInfo tagInfo) {
        this.tagInfo = tagInfo;
    }
    
    public void setTagInfo(AdTag adTag) {
        if (adTag != null) {
            TagInfo info = adTag.toTagInfo(getTaskType().intValue());
            this.setTagInfo(info);
        }
    }

    public Number getAdTradeIdLevel2() {
        return adTradeIdLevel2;
    }

    public void setAdTradeIdLevel2(Number adTradeIdLevel2) {
        this.adTradeIdLevel2 = adTradeIdLevel2;
    }

    public String getAdTradeNameLevel2() {
        return adTradeNameLevel2;
    }

    public void setAdTradeNameLevel2(String adTradeNameLevel2) {
        this.adTradeNameLevel2 = adTradeNameLevel2;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public Number getMcId() {
        return mcId;
    }

    public void setMcId(Number mcId) {
        this.mcId = mcId;
    }

    public Number getMcVersionId() {
        return mcVersionId;
    }

    public void setMcVersionId(Number mcVersionId) {
        this.mcVersionId = mcVersionId;
    }

    @Override
    public int compareTo(PresentedTask o) {
        if (getWuliaoType().intValue() != o.getWuliaoType().intValue()) {
            return getWuliaoType().intValue() - o.getWuliaoType().intValue();
        }

        if (getAdId().longValue() > o.getAdId().longValue()) {
            return 1;
        } else if (getAdId().longValue() == o.getAdId().longValue()) {
            return 0;
        } else {
            return -1;
        }
    }
}
