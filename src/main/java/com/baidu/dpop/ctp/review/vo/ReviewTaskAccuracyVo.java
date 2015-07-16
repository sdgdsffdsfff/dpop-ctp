package com.baidu.dpop.ctp.review.vo;

import org.springframework.util.Assert;

/**
 * 审核任务准确率VO
 * 
 * @author cgd
 * @date 2015年3月31日 下午4:36:34
 */
public class ReviewTaskAccuracyVo {

    private Double taskAccuracy; // 任务准确率
    private Double textAdAccuracy; // 文字类（包含图文）准确率
    private Double otherAdAccuracy; // 非文字类准确率
    private Double tradeAccuracy; // 所属行业准确率
    private Double beautyAccuracy; // 美观度准确率
    private Double vulgarAccuracy; // 低俗度准确率
    private Double cheatAccuracy; // 欺诈度准确率
    private Double highDangerAccuracy; // 高危度准确率

    /**
     * 设置default值
     * */
    public void setDefaultValue(Double value) {
        Assert.notNull(value);

        this.taskAccuracy = value;
        this.textAdAccuracy = value;
        this.otherAdAccuracy = value;
        this.tradeAccuracy = value;
        this.beautyAccuracy = value;
        this.vulgarAccuracy = value;
        this.cheatAccuracy = value;
        this.highDangerAccuracy = value;
    }

    public Double getTaskAccuracy() {
        return taskAccuracy;
    }

    public void setTaskAccuracy(Double taskAccuracy) {
        this.taskAccuracy = taskAccuracy;
    }

    public Double getTextAdAccuracy() {
        return textAdAccuracy;
    }

    public void setTextAdAccuracy(Double textAdAccuracy) {
        this.textAdAccuracy = textAdAccuracy;
    }

    public Double getOtherAdAccuracy() {
        return otherAdAccuracy;
    }

    public void setOtherAdAccuracy(Double otherAdAccuracy) {
        this.otherAdAccuracy = otherAdAccuracy;
    }

    public Double getTradeAccuracy() {
        return tradeAccuracy;
    }

    public void setTradeAccuracy(Double tradeAccuracy) {
        this.tradeAccuracy = tradeAccuracy;
    }

    public Double getBeautyAccuracy() {
        return beautyAccuracy;
    }

    public void setBeautyAccuracy(Double beautyAccuracy) {
        this.beautyAccuracy = beautyAccuracy;
    }

    public Double getVulgarAccuracy() {
        return vulgarAccuracy;
    }

    public void setVulgarAccuracy(Double vulgarAccuracy) {
        this.vulgarAccuracy = vulgarAccuracy;
    }

    public Double getCheatAccuracy() {
        return cheatAccuracy;
    }

    public void setCheatAccuracy(Double cheatAccuracy) {
        this.cheatAccuracy = cheatAccuracy;
    }

    public Double getHighDangerAccuracy() {
        return highDangerAccuracy;
    }

    public void setHighDangerAccuracy(Double highDangerAccuracy) {
        this.highDangerAccuracy = highDangerAccuracy;
    }

}
