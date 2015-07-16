package com.baidu.dpop.ctp.mainTask.constant;

public enum QiushiMcType {

	INVALID(0, "非法类型"), TXT(1, "文字"), IMG(2, "图片"), VIDEO(3, "视频"), RICHMEDIA(
			4, "富媒体"), SMALLIMGTXT(5, "小图文"), BIGIMGTXT(6, "大图文"), GENIMGTEXT(100, "图文，只做判断用");

	private Integer id;
	private String desc;

	private QiushiMcType(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public static QiushiMcType get(Number id) {
		if (null == id) {
			return null;
		}
		for (QiushiMcType temp : QiushiMcType.values()) {
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

	public static boolean needUrl(Number id) {
		if (id.intValue() == IMG.getId().intValue()) {
			return true;
		}

		if (id.intValue() == SMALLIMGTXT.getId().intValue()) {
			return true;
		}
		
		if (id.intValue() == BIGIMGTXT.getId().intValue()) {
			return true;
		}
		
		if (id.intValue() == VIDEO.getId().intValue()) {
			return true;
		}

		return false;
	}
}
