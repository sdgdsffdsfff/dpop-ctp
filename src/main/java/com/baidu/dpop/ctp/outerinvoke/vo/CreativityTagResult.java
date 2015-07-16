package com.baidu.dpop.ctp.outerinvoke.vo;

import org.springframework.util.Assert;

public class CreativityTagResult implements java.io.Serializable {
	
	private static final long serialVersionUID = 7343056986709371914L;
	
	private Long creativityId;
	private Long tradeId;
	private Long oldTradeId;
	private Long adTag1;
	private Long adTag2;
	private Long adTag3;
	private Integer tagVersion;
	private Long submitTime;
	
	public Long getCreativityId() {
		return creativityId;
	}
	
	public void setCreativityId(Long creativityId) {
		this.creativityId = creativityId;
	}
	
	public Long getTradeId() {
		return tradeId;
	}
	
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}
	
	public Long getOldTradeId() {
		return oldTradeId;
	}
	
	public void setOldTradeId(Long oldTradeId) {
		this.oldTradeId = oldTradeId;
	}
	
	public Long getAdTag1() {
		return adTag1;
	}
	
	public void setAdTag1(Long adTag1) {
		this.adTag1 = adTag1;
	}
	
	public Long getAdTag2() {
		return adTag2;
	}
	
	public void setAdTag2(Long adTag2) {
		this.adTag2 = adTag2;
	}
	
	public Long getAdTag3() {
		return adTag3;
	}
	
	public void setAdTag3(Long adTag3) {
		this.adTag3 = adTag3;
	}
	
	public Integer getTagVersion() {
		return tagVersion;
	}
	
	public void setTagVersion(Integer tagVersion) {
		this.tagVersion = tagVersion;
	}
	
	public Long getSubmitTime() {
		return submitTime;
	}
	
	public void setSubmitTime(Long submitTime) {
		this.submitTime = submitTime;
	}
	
	public void setTags(long[] tags) {
		Assert.state(tags.length == 3);
		setAdTag1(tags[0]);
		setAdTag2(tags[1]);
		setAdTag3(tags[2]);
	}
}
