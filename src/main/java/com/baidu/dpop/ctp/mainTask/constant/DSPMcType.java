package com.baidu.dpop.ctp.mainTask.constant;

public enum DSPMcType {
	
	IMG(1, "图片"), FLASH(2, "flash"), VIDEO(3, "视频");

	private Integer id;
	private String desc;

	private DSPMcType(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public static DSPMcType get(Number id) {
		if (null == id) {
			return null;
		}
		for (DSPMcType temp : DSPMcType.values()) {
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
		if (id.intValue() == IMG.id.intValue()) {
			return true;
		}
		
		if (id.intValue() == FLASH.id.intValue()) {
			return true;
		}
		
		if (id.intValue() == VIDEO.id.intValue()) {
			return true;
		}

		return false;
	}
	
	public static boolean needFlash(Number id) {
//		if (id.intValue() == FLASH.id.intValue()) {
//			return true;
//		}
//		
		return false;
	}
}
