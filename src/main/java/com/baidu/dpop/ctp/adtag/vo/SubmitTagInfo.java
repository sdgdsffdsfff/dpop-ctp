package com.baidu.dpop.ctp.adtag.vo;

import java.util.Map;

import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;
import com.baidu.dpop.ctp.adtag.utils.TaskTypeUtils;

public class SubmitTagInfo implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5869632054589127837L;
	
	private Long id; // TagInfo本身的id
	private Long refId; // 与此TagInfo关联的ad的主键id
	private Integer adTradeIdLevel3; // 三级行业信息，需要标注
	private String comment; // 备注
	private String sample;
	private Map<String, String> tag; // 具体标注项
	
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
	
	public Map<String, String> getTag() {
		return tag;
	}
	
	public void setTag(Map<String, String> tag) {
		this.tag = tag;
	}
	
	public AdTag toAdTag(Integer taskType) {
		AdTag tag = new AdTag();

		tag.setId(this.getId());
		tag.setRefId(this.getRefId());

		tag.setAdTag(TaskTypeUtils.transformAdTag(sample, AdTagUtils.getTagFromMap(getTag())));
		tag.setComment(this.getComment());
		tag.setAdTradeIdLevel3(this.getAdTradeIdLevel3());

		return tag;
	}
}
