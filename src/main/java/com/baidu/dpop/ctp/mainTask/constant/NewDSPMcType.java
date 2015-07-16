package com.baidu.dpop.ctp.mainTask.constant;

public enum NewDSPMcType {
	
	TXT(1, "文本|图文"), IMG(2, "图片"), FLASH(3, "flash"), HTML(7, "网页"), VIDEO(8, "大视频");

	private Integer id;
	private String desc;

	private NewDSPMcType(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public static NewDSPMcType get(Number id) {
		if (null == id) {
			return null;
		}
		for (NewDSPMcType temp : NewDSPMcType.values()) {
			if (temp.getId().equals(id.intValue())) {
				return temp;
			}
		}
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
