/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.adtag.bo;

import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;
import com.baidu.dpop.ctp.adtag.utils.TaskTypeUtils;

/**
 * 标注信息类，包含了所有需要标注的内容
 * 
 * @author mading01
 * 
 */
public class AdTag extends AdTagBase {

    private static final long serialVersionUID = 3182396980205457354L;

    private String adTradeNameLevel3;

    public String getAdTradeNameLevel3() {
        return this.adTradeNameLevel3;
    }

    public void setAdTradeNameLevel3(String adTradeNameLevel3) {
        this.adTradeNameLevel3 = adTradeNameLevel3;
    }

    /**
     * 将AdTag实例转化为一个TagInfo实例返回
     * 
     * @return TagInfo实例，其中数据由此实例提供
     */
    public TagInfo toTagInfo(Integer taskType) {
        TagInfo info = new TagInfo();
        info.setId(this.getId());
        info.setRefId(this.getRefId());

        info.setAdTradeIdLevel3(this.getAdTradeIdLevel3());
        info.setComment(this.getComment());
        info.setSample(this.getAdTag());
        info.setTag(AdTagUtils.getTag(TaskTypeUtils.hideUnusedTag(this.getAdTag(), taskType)));

        return info;
    }

}