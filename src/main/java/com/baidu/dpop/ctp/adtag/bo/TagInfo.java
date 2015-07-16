package com.baidu.dpop.ctp.adtag.bo;

import java.util.List;

import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;
import com.baidu.dpop.ctp.adtag.utils.TaskTypeUtils;

public class TagInfo implements java.io.Serializable {

    private static final long serialVersionUID = 7116353993345469891L;

    private Long id; // TagInfo本身的id
    private Long refId; // 与此TagInfo关联的ad的主键id
    private Integer adTradeIdLevel3; // 三级行业信息，需要标注
    private String comment; // 备注
    private String sample;
    private List<TagGroup> tag; // 具体标注项

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public Integer getAdTradeIdLevel3() {
        return adTradeIdLevel3;
    }

    public void setAdTradeIdLevel3(Integer adTradeIdLevel3) {
        this.adTradeIdLevel3 = adTradeIdLevel3;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public List<TagGroup> getTag() {
        return tag;
    }

    public void setTag(List<TagGroup> tag) {
        this.tag = tag;
    }

    public AdTag toAdTag() {
        AdTag tag = new AdTag();

        tag.setId(this.getId());
        tag.setRefId(this.getRefId());

        tag.setAdTag(TaskTypeUtils.transformAdTag(getSample(), AdTagUtils.getTagString(Tag.fromList(this.getTag()))));
        tag.setComment(this.getComment());
        tag.setAdTradeIdLevel3(this.getAdTradeIdLevel3());

        return tag;
    }

}
