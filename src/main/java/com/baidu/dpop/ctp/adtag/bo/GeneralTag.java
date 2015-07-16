package com.baidu.dpop.ctp.adtag.bo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class GeneralTag {

    private String name; // 名称

    @JsonProperty("name_ch")
    private String nameCh; // 中文名称

    private Integer index; // 在标签体系中是第几位
    private Integer tagIndex; // 标签共3个long型，这里标识了此标签在其中的哪一个
    private Integer mask; // 在标签中如何获取
    private String group; // 标签组名称
    private List<TagItem> tags; // 含有什么标签
    private Integer priority; // 再标签组内的优先级
    private String defaultValue; // 默认值

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCh() {
        return nameCh;
    }

    public void setNameCh(String nameCh) {
        this.nameCh = nameCh;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<TagItem> getTags() {
        return tags;
    }

    public void setTags(List<TagItem> tags) {
        this.tags = tags;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getTagIndex() {
        return tagIndex;
    }

    public void setTagIndex(Integer tagIndex) {
        this.tagIndex = tagIndex;
    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }
    
    public Integer getPriority() {
        return priority;
    }
    
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public GeneralTag(String name, String nameCh, Integer priority, Integer index,
            Integer tagIndex, Integer mask, String group, TagItemList itemList) {
        this.name = name;
        this.nameCh = nameCh;
        this.priority = priority;
        this.index = index;
        this.tagIndex = tagIndex;
        this.mask = mask;
        this.group = group;
        this.tags = itemList.getItemList();
        this.defaultValue = tags.get(itemList.getDefaultValue()).getValue();
    }

    public String getValue(List<Long> tagList) {
        if (mask != null && tagIndex != null) {
            long v = tagList.get(getTagIndex());
            Long m = 0x0000000000000007L << ((mask - 1) * 3);
            return String.valueOf((v & m) >>> ((mask - 1) * 3));
        } else {
            return "0";
        }
    }
}
