package com.baidu.dpop.ctp.beidouapi.vo;

import java.util.List;

/**
 * 智能创意数据封装
 * */
public class UnitPreResult {

    private String htmlSnippet;
    private List<String> targetUrls;

    public String getHtmlSnippet() {
        return htmlSnippet;
    }

    public void setHtmlSnippet(String htmlSnippet) {
        this.htmlSnippet = htmlSnippet;
    }

    public List<String> getTargetUrls() {
        return targetUrls;
    }

    public void setTargetUrls(List<String> targetUrls) {
        this.targetUrls = targetUrls;
    }
}
