package com.baidu.dpop.ctp.review.constants;

public enum ReviewTaskModuserLevel {

	INSIDE(Integer.valueOf("0"), "内部任务"), OUTSIDE(Integer.valueOf("1"), "外部任务"), INOOUT(
			Integer.valueOf("2"), "全部任务");

	private Integer id;
	private String desc;

	private ReviewTaskModuserLevel(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public static ReviewTaskModuserLevel get(Number id) {
		if (null == id) {
			return null;
		}
		for (ReviewTaskModuserLevel temp : ReviewTaskModuserLevel.values()) {
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
