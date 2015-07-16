package com.baidu.dpop.ctp.adtag.vo;

import java.util.HashSet;
import java.util.Set;

import com.baidu.dpop.ctp.adtag.bo.GeneralTag;
import com.baidu.dpop.ctp.adtag.bo.TagGroup;

public class TaskType {

    private String name;
    private Integer value;
    private Set<String> groups;

    public TaskType(String name, Integer value, TagGroup...groups) {
        this.name = name;
        this.value = value;
        this.groups = new HashSet<String>();
        for (TagGroup group : groups) {
            this.groups.add(group.getName());
        }
    }

    public TaskType() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Set<String> getGroups() {
        return groups;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }

    public Boolean contains(GeneralTag gt) {
        if (gt == null) {
            return false;
        }
        return this.groups.contains(gt.getGroup());
    }

    public Boolean contains(TagGroup tg) {
        if (tg == null) {
            return false;
        }
        return this.groups.contains(tg.getName());
    }
}
