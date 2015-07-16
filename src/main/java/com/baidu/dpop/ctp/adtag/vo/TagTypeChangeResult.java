package com.baidu.dpop.ctp.adtag.vo;

import java.util.List;

import com.baidu.dpop.ctp.adtag.bo.TagGroup;

public class TagTypeChangeResult {

    private List<TagGroup> list;
    private String sample;

    public List<TagGroup> getList() {
        return list;
    }

    public void setList(List<TagGroup> list) {
        this.list = list;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

}
