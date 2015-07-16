package com.baidu.dpop.ctp.review.vo;

import java.util.List;
import java.util.Map;

public class AccuracyResultVo {

    private List<ReviewTaskAccuracyItem> general;
    private Map<String, ReviewTaskAccuracyItem> tags;
    private List<String> defaultValue;

    public List<ReviewTaskAccuracyItem> getGeneral() {
        return general;
    }

    public void setGeneral(List<ReviewTaskAccuracyItem> general) {
        this.general = general;
    }

    public Map<String, ReviewTaskAccuracyItem> getTags() {
        return tags;
    }

    public void setTags(Map<String, ReviewTaskAccuracyItem> tags) {
        this.tags = tags;
    }

    public List<String> getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(List<String> defaultValue) {
        this.defaultValue = defaultValue;
    }

}
