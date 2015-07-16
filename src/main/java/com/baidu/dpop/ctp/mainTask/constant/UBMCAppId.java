package com.baidu.dpop.ctp.mainTask.constant;

import com.baidu.dpop.ctp.group.constant.GroupDataType;

public enum UBMCAppId {
	
	BEIDOU(3, "北斗"), QIUSHI(10, "秋实"), DSP(6, "DSP"), NEW_DSP(8, "NEW_DSP");

	private Integer id;
	private String desc;
	
	public static Integer getAppIdFromDataType(Number id) {
		if (GroupDataType.isBeidou(id)) {
			return BEIDOU.id;
		} else if (GroupDataType.isQiushi(id)) {
			return QIUSHI.id;
		} else if (GroupDataType.isDSP(id)) {
			return NEW_DSP.id;
		}
		
		return null;
	}

	private UBMCAppId(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public static UBMCAppId get(Number id) {
		if (null == id) {
			return null;
		}
		for (UBMCAppId temp : UBMCAppId.values()) {
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
