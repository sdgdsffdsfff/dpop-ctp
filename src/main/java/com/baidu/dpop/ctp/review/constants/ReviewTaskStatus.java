package com.baidu.dpop.ctp.review.constants;


public enum ReviewTaskStatus {

	NOT_STARTED(Byte.valueOf("0"), "任务未开始"), STARTED(Byte.valueOf("1"), "任务进行中"), FINISHED(
			Byte.valueOf("2"), "任务已完成");

	private Byte id;
	private String desc;

	private ReviewTaskStatus(Byte id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public static ReviewTaskStatus get(Number id) {
		if (null == id) {
			return null;
		}
		for (ReviewTaskStatus temp : ReviewTaskStatus.values()) {
			if (temp.getId().equals(id.byteValue())) {
				return temp;
			}
		}
		return null;
	}

	public Byte getId() {
		return id;
	}

	public void setId(Byte id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static boolean canConfirm(Byte id) {
		if (id.equals(STARTED.getId()) || id.equals(FINISHED.getId())) {
			return true;
		}
		return false;
	}
}
