package com.baidu.dpop.ctp.review.vo;

/**
 * 标注审核有误的明细vo
 * 
 * @author cgd
 * @date 2015年3月30日 下午7:57:15
 */
public class WrongTagDetailItem {
    
    private String tagInfo; // 标注项
    private String before; // 标注结果
    private String after; // 审核结果

    // ---------------------------------------------------------
    public String getTagInfo() {
        return tagInfo;
    }

    public void setTagInfo(String tagInfo) {
        this.tagInfo = tagInfo;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

}
