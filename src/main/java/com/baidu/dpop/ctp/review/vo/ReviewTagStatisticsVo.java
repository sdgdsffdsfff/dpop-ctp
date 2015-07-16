package com.baidu.dpop.ctp.review.vo;

import org.springframework.util.Assert;

/**
 * 标签统计信息Vo
 * 
 * @author cgd
 * @date 2015年3月31日 下午4:41:52
 */
public class ReviewTagStatisticsVo {

    // 标注美观度统计信息（高中低）
    private Integer tagHighBeautyNum;
    private Integer tagNormalBeautyNum;
    private Integer tagLowBeautyNum;

    // 审核美观度统计信息
    private Integer reviewHighBeautyNum;
    private Integer reviewNormalBeautyNum;
    private Integer reviewLowBeautyNum;

    // 标注低俗度（黑灰白）
    private Integer tagBlackVulgarNum;
    private Integer tagGrayVulagrNum;
    private Integer tagWhiteVulgarNum;

    // 审核低俗度（黑灰白）
    private Integer reviewBlackVulgarNum;
    private Integer reviewGrayVulagrNum;
    private Integer reviewWhiteVulgarNum;

    // 标注欺诈度（是否）
    private Integer tagIsCheatNum;
    private Integer tagNotCheatNum;

    // 审核欺诈度（是否）
    private Integer reviewIsCheatNum;
    private Integer reviewNotCheatNum;

    // 标注高危度（是否）
    private Integer tagIsHighDangerNum;
    private Integer tagNotHighDangerNum;

    // 审核高危度（是否）
    private Integer reviewIsHighDangerNum;
    private Integer reviewNotHighDangerNum;

    /**
     * 设置默认值
     * */
    public void setDefaultValue(Integer value) {
        Assert.notNull(value);

        this.tagHighBeautyNum = value;
        this.tagNormalBeautyNum = value;
        this.tagLowBeautyNum = value;
        this.reviewHighBeautyNum = value;
        this.reviewNormalBeautyNum = value;
        this.reviewLowBeautyNum = value;

        this.tagBlackVulgarNum = value;
        this.tagGrayVulagrNum = value;
        this.tagWhiteVulgarNum = value;
        this.reviewBlackVulgarNum = value;
        this.reviewGrayVulagrNum = value;
        this.reviewWhiteVulgarNum = value;

        this.tagIsCheatNum = value;
        this.tagNotCheatNum = value;
        this.reviewIsCheatNum = value;
        this.reviewNotCheatNum = value;

        this.tagIsHighDangerNum = value;
        this.tagNotHighDangerNum = value;
        this.reviewIsHighDangerNum = value;
        this.reviewNotHighDangerNum = value;
    }

    public Integer getTagHighBeautyNum() {
        return tagHighBeautyNum;
    }

    public void setTagHighBeautyNum(Integer tagHighBeautyNum) {
        this.tagHighBeautyNum = tagHighBeautyNum;
    }

    public Integer getTagNormalBeautyNum() {
        return tagNormalBeautyNum;
    }

    public void setTagNormalBeautyNum(Integer tagNormalBeautyNum) {
        this.tagNormalBeautyNum = tagNormalBeautyNum;
    }

    public Integer getTagLowBeautyNum() {
        return tagLowBeautyNum;
    }

    public void setTagLowBeautyNum(Integer tagLowBeautyNum) {
        this.tagLowBeautyNum = tagLowBeautyNum;
    }

    public Integer getReviewHighBeautyNum() {
        return reviewHighBeautyNum;
    }

    public void setReviewHighBeautyNum(Integer reviewHighBeautyNum) {
        this.reviewHighBeautyNum = reviewHighBeautyNum;
    }

    public Integer getReviewNormalBeautyNum() {
        return reviewNormalBeautyNum;
    }

    public void setReviewNormalBeautyNum(Integer reviewNormalBeautyNum) {
        this.reviewNormalBeautyNum = reviewNormalBeautyNum;
    }

    public Integer getReviewLowBeautyNum() {
        return reviewLowBeautyNum;
    }

    public void setReviewLowBeautyNum(Integer reviewLowBeautyNum) {
        this.reviewLowBeautyNum = reviewLowBeautyNum;
    }

    public Integer getTagBlackVulgarNum() {
        return tagBlackVulgarNum;
    }

    public void setTagBlackVulgarNum(Integer tagBlackVulgarNum) {
        this.tagBlackVulgarNum = tagBlackVulgarNum;
    }

    public Integer getTagGrayVulagrNum() {
        return tagGrayVulagrNum;
    }

    public void setTagGrayVulagrNum(Integer tagGrayVulagrNum) {
        this.tagGrayVulagrNum = tagGrayVulagrNum;
    }

    public Integer getTagWhiteVulgarNum() {
        return tagWhiteVulgarNum;
    }

    public void setTagWhiteVulgarNum(Integer tagWhiteVulgarNum) {
        this.tagWhiteVulgarNum = tagWhiteVulgarNum;
    }

    public Integer getReviewBlackVulgarNum() {
        return reviewBlackVulgarNum;
    }

    public void setReviewBlackVulgarNum(Integer reviewBlackVulgarNum) {
        this.reviewBlackVulgarNum = reviewBlackVulgarNum;
    }

    public Integer getReviewGrayVulagrNum() {
        return reviewGrayVulagrNum;
    }

    public void setReviewGrayVulagrNum(Integer reviewGrayVulagrNum) {
        this.reviewGrayVulagrNum = reviewGrayVulagrNum;
    }

    public Integer getReviewWhiteVulgarNum() {
        return reviewWhiteVulgarNum;
    }

    public void setReviewWhiteVulgarNum(Integer reviewWhiteVulgarNum) {
        this.reviewWhiteVulgarNum = reviewWhiteVulgarNum;
    }

    public Integer getTagIsCheatNum() {
        return tagIsCheatNum;
    }

    public void setTagIsCheatNum(Integer tagIsCheatNum) {
        this.tagIsCheatNum = tagIsCheatNum;
    }

    public Integer getTagNotCheatNum() {
        return tagNotCheatNum;
    }

    public void setTagNotCheatNum(Integer tagNotCheatNum) {
        this.tagNotCheatNum = tagNotCheatNum;
    }

    public Integer getReviewIsCheatNum() {
        return reviewIsCheatNum;
    }

    public void setReviewIsCheatNum(Integer reviewIsCheatNum) {
        this.reviewIsCheatNum = reviewIsCheatNum;
    }

    public Integer getReviewNotCheatNum() {
        return reviewNotCheatNum;
    }

    public void setReviewNotCheatNum(Integer reviewNotCheatNum) {
        this.reviewNotCheatNum = reviewNotCheatNum;
    }

    public Integer getTagIsHighDangerNum() {
        return tagIsHighDangerNum;
    }

    public void setTagIsHighDangerNum(Integer tagIsHighDangerNum) {
        this.tagIsHighDangerNum = tagIsHighDangerNum;
    }

    public Integer getTagNotHighDangerNum() {
        return tagNotHighDangerNum;
    }

    public void setTagNotHighDangerNum(Integer tagNotHighDangerNum) {
        this.tagNotHighDangerNum = tagNotHighDangerNum;
    }

    public Integer getReviewIsHighDangerNum() {
        return reviewIsHighDangerNum;
    }

    public void setReviewIsHighDangerNum(Integer reviewIsHighDangerNum) {
        this.reviewIsHighDangerNum = reviewIsHighDangerNum;
    }

    public Integer getReviewNotHighDangerNum() {
        return reviewNotHighDangerNum;
    }

    public void setReviewNotHighDangerNum(Integer reviewNotHighDangerNum) {
        this.reviewNotHighDangerNum = reviewNotHighDangerNum;
    }

}
