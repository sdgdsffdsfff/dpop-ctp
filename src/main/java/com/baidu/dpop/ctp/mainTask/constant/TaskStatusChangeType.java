package com.baidu.dpop.ctp.mainTask.constant;

public enum TaskStatusChangeType {
	
	OPEN(-10, "关闭变为打开"), CLOSE(10, "打开变为关闭"), CANTOPEN(100, "关闭变为关闭锁定"), CANOPEN(-100, "关闭锁定变为关闭");

	private Integer id;
	private String desc;

	private TaskStatusChangeType(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	
	public static boolean isOPEN(Number id) {
		return OPEN.id.intValue() == id.intValue();
	}
	
	public static boolean isCLOSE(Number id) {
		return CLOSE.id.intValue() == id.intValue();
	}
	
	public static boolean isCANTOPEN(Number id) {
		return CANTOPEN.id.intValue() == id.intValue();
	}
	
	public static boolean isCANOPEN(Number id) {
		return CANOPEN.id.intValue() == id.intValue();
	}

	public static TaskStatusChangeType get(Number id) {
		if (null == id) {
			return null;
		}
		for (TaskStatusChangeType temp : TaskStatusChangeType.values()) {
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
