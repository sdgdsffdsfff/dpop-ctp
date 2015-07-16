package com.baidu.dpop.ctp.mainTask.constant;

public enum ModuserLevel {

	INSIDE(Integer.valueOf("0"), "内部任务"), OUTSIDE(Integer.valueOf("1"), "外部任务");

	private Integer id;
	private String desc;

	private ModuserLevel(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public static ModuserLevel get(Number id) {
		if (null == id) {
			return null;
		}
		for (ModuserLevel temp : ModuserLevel.values()) {
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
