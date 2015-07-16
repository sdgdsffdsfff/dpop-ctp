package com.baidu.dpop.ctp.task.vo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.codehaus.jackson.annotate.JsonProperty;

import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;
import com.baidu.dpop.ctp.task.bo.NewDSPTask;

public class CreativityInfo implements Serializable {

    private static final long serialVersionUID = -7985862842405540662L;

    @JsonProperty("creativityId")
    private Long creativityId;

    @JsonProperty("textTitle")
    private String textTitle;

    @JsonProperty("textDesc")
    private String textDesc;

    @JsonProperty("adId")
    private Long adId;

    @JsonProperty("productType")
    private Integer productType;

    @JsonProperty("tradeId")
    private Integer tradeId;

    @JsonProperty("oldTradeId")
    private Integer oldTradeId;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("sizeDesc")
    private String sizeDesc;

    @JsonProperty("targetUrl")
    private String targetUrl;

    @JsonProperty("showUrl")
    private String showUrl;

    @JsonProperty("createTime")
    private String createTime;

    @JsonProperty("updateTime")
    private String updateTime;

    @JsonProperty("ownerId")
    private Long ownerId;

    @JsonProperty("tagList")
    private List<Long> tagList;

    @JsonProperty("urlList")
    private List<String> urlList;

    @JsonProperty("tagVersion")
    private Integer tagVersion;

    public Long getCreativityId() {
        return creativityId;
    }

    public void setCreativityId(Long creativityId) {
        this.creativityId = creativityId;
    }

    public String getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    public String getTextDesc() {
        return textDesc;
    }

    public void setTextDesc(String textDesc) {
        this.textDesc = textDesc;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getOldTradeId() {
        return oldTradeId;
    }

    public void setOldTradeId(Integer oldTradeId) {
        this.oldTradeId = oldTradeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSizeDesc() {
        return sizeDesc;
    }

    public void setSizeDesc(String sizeDesc) {
        this.sizeDesc = sizeDesc;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<Long> getTagList() {
        return tagList;
    }

    public void setTagList(List<Long> tagList) {
        this.tagList = tagList;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public Integer getTagVersion() {
        return tagVersion;
    }

    public void setTagVersion(Integer tagVersion) {
        this.tagVersion = tagVersion;
    }

    public NewDSPTask toNewDSPTask(Date date) {
        NewDSPTask result = new NewDSPTask();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat parse = new SimpleDateFormat("yyyyMMddHHmmss");
            result.setAdId(creativityId);
            result.setUserId(ownerId);
            result.setAdverId(adId);
            result.setWuliaoType(productType);
            result.setShowUrl(showUrl);
            result.setTargetUrl(targetUrl);
            if (Pattern.matches("\\d+\\*\\d+", sizeDesc)) {
                result.setWidth(Integer.valueOf(sizeDesc.split("\\*")[0]));
                result.setHeight(Integer.valueOf(sizeDesc.split("\\*")[1]));
            } else {
                result.setWidth(0);
                result.setHeight(0);
            }
            result.setTitle(textTitle == null ? "NULL" : textTitle);
            result.setDescription(textDesc == null ? "NULL" : textDesc);
            result.setAdTradeIdLevel2(oldTradeId);
            result.setAdTradeIdLevel3(tradeId);
            result.setAdTag(AdTagUtils.getAdTagFromTagList(tagList));
            result.setUrl(urlList == null ? "NULL" : urlList.get(0));
            result.setTagVersion(tagVersion);
            result.setTaskId(Integer.valueOf(format.format(date) + "04"));
            result.setTaskName("百度DSP日常标注_" + format.format(date));
            result.setCreateTime(parse.parse(createTime));
            result.setChatime(parse.parse(updateTime));
            result.setAddTime(date);
            result.setAddUser("System");
            result.setTaskType((byte) 3);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
