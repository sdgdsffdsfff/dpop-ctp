package com.baidu.dpop.ctp.mainTask.constant;

public enum BeidouMcType {

	TXT(1, "文字"), IMG(2, "图片"), FLASH(3, "flash"), IMGTXT(5, "图文"), HTML(9,
			"网页");

	private Integer id;
	private String desc;

	private BeidouMcType(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public static BeidouMcType get(Number id) {
		if (null == id) {
			return null;
		}
		for (BeidouMcType temp : BeidouMcType.values()) {
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

		if (id.intValue() == IMGTXT.getId().intValue()) {
			return true;
		}
		
		if (id.intValue() == FLASH.getId().intValue()) {
			return true;
		}

		return false;
	}

	public static boolean needFlash(Number id) {
		if (id.intValue() == FLASH.getId().intValue()) {
			return true;
		}
		return false;
	}
	
	public static boolean needSmartUrl(Number id) {
		if (id.intValue() == HTML.getId().intValue()) {
			return true;
		}
		return false;
	}
}
