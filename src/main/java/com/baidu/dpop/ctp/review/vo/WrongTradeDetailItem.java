package com.baidu.dpop.ctp.review.vo;

/**
 * 行业审核有误的明细vo
 * 
 * @author cgd
 * @date 2015年4月1日 下午2:09:33
 */
public class WrongTradeDetailItem {

    private String beforeTrade1; // 标注的一级行业信息
    private String beforeTrade2; // 标注的二级行业信息
    private String beforeTrade3; // 标注的三级行业信息

    private String afterTrade1; // 审核的一级行业信息
    private String afterTrade2; // 审核的二级行业信息
    private String afterTrade3; // 审核的三级行业信息

    public String getBeforeTrade1() {
        return beforeTrade1;
    }

    public void setBeforeTrade1(String beforeTrade1) {
        this.beforeTrade1 = beforeTrade1;
    }

    public String getBeforeTrade2() {
        return beforeTrade2;
    }

    public void setBeforeTrade2(String beforeTrade2) {
        this.beforeTrade2 = beforeTrade2;
    }

    public String getBeforeTrade3() {
        return beforeTrade3;
    }

    public void setBeforeTrade3(String beforeTrade3) {
        this.beforeTrade3 = beforeTrade3;
    }

    public String getAfterTrade1() {
        return afterTrade1;
    }

    public void setAfterTrade1(String afterTrade1) {
        this.afterTrade1 = afterTrade1;
    }

    public String getAfterTrade2() {
        return afterTrade2;
    }

    public void setAfterTrade2(String afterTrade2) {
        this.afterTrade2 = afterTrade2;
    }

    public String getAfterTrade3() {
        return afterTrade3;
    }

    public void setAfterTrade3(String afterTrade3) {
        this.afterTrade3 = afterTrade3;
    }

}
