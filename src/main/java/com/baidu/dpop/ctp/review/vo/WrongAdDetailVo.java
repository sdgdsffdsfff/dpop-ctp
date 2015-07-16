package com.baidu.dpop.ctp.review.vo;

/**
 * 标注有误的创意明细
 * 
 * @author cgd
 * @date 2015年4月1日 下午3:57:37
 */
public class WrongAdDetailVo {

    private Long adId; // 创意ID
    private Byte dataType; // 数据类型
    private Byte wuliaoType; // 创意类型
    private Long reviewGroupId; // 审核组ID
    private Long tagTaskId; // 标注任务ID
    private Long reviewTaskId; // 审核任务ID
    private Integer tradeLevel3; // 标注行业ID
    private Integer reviewTradeLevel3; // 审核行业ID
    private Integer adTag; // 标注Tag信息
    private Integer reivewAdTag; // 审核Tag信息

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public Byte getDataType() {
        return dataType;
    }

    public void setDataType(Byte dataType) {
        this.dataType = dataType;
    }

    public Byte getWuliaoType() {
        return wuliaoType;
    }

    public void setWuliaoType(Byte wuliaoType) {
        this.wuliaoType = wuliaoType;
    }

    public Long getReviewGroupId() {
        return reviewGroupId;
    }

    public void setReviewGroupId(Long reviewGroupId) {
        this.reviewGroupId = reviewGroupId;
    }

    public Long getTagTaskId() {
        return tagTaskId;
    }

    public void setTagTaskId(Long tagTaskId) {
        this.tagTaskId = tagTaskId;
    }

    public Long getReviewTaskId() {
        return reviewTaskId;
    }

    public void setReviewTaskId(Long reviewTaskId) {
        this.reviewTaskId = reviewTaskId;
    }

    public Integer getTradeLevel3() {
        return tradeLevel3;
    }

    public void setTradeLevel3(Integer tradeLevel3) {
        this.tradeLevel3 = tradeLevel3;
    }

    public Integer getReviewTradeLevel3() {
        return reviewTradeLevel3;
    }

    public void setReviewTradeLevel3(Integer reviewTradeLevel3) {
        this.reviewTradeLevel3 = reviewTradeLevel3;
    }

    public Integer getAdTag() {
        return adTag;
    }

    public void setAdTag(Integer adTag) {
        this.adTag = adTag;
    }

    public Integer getReivewAdTag() {
        return reivewAdTag;
    }

    public void setReivewAdTag(Integer reivewAdTag) {
        this.reivewAdTag = reivewAdTag;
    }

}
