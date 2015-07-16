package com.baidu.dpop.ctp.adtag.vo;

import java.util.List;

public class TagTypeAddVo {

    private List<Integer> tradeIds;
    private String tagType;

    public List<Integer> getTradeIds() {
        return tradeIds;
    }

    public void setTradeIds(List<Integer> tradeIds) {
        this.tradeIds = tradeIds;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

}
