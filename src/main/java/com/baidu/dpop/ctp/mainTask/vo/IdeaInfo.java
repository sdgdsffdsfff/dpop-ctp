package com.baidu.dpop.ctp.mainTask.vo;

import java.io.Serializable;

public class IdeaInfo implements Serializable {

	/**
	 * 创意的存储结构
	 */
	private static final long serialVersionUID = 3218790478292030848L;

	private Long adId;
	private Integer mcId;
	private Integer mcVersionId;
	private Integer mcType;
	private String title;
	private String description1;
	private String description2;
	private String url;

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public Integer getMcId() {
		return mcId;
	}

	public void setMcId(Integer mcId) {
		this.mcId = mcId;
	}

	public Integer getMcVersionId() {
		return mcVersionId;
	}

	public void setMcVersionId(Integer mcVersionId) {
		this.mcVersionId = mcVersionId;
	}

	public Integer getMcType() {
		return mcType;
	}

	public void setMcType(Integer mcType) {
		this.mcType = mcType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
