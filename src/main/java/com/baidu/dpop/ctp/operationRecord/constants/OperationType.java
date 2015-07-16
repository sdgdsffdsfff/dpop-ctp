package com.baidu.dpop.ctp.operationRecord.constants;

/**
 * 操作类型枚举 操作类型使用一个Integer保存，为两级结构，前16位为一级类型，后16位为二级类型
 * 
 * @author mading01
 * 
 */
public enum OperationType {

	OP_USER_CREATE(0x00010001, "创建用户"), OP_USER_DELETE(0x00010002, "删除用户"), OP_USER_EDIT(
			0x00010003, "修改用户"), OP_USER_THAW(0x00010004, "解冻账户"), OP_TEMP_OP(0x00FF0001, "临时操作"),
			OP_REVIEW_CREATE(0x00020001, "新建审核任务"), OP_REVIEW_DELETE(0x00020002, "删除审核任务");

	private Integer id;
	private String desc;

	private OperationType(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public static OperationType get(Number id) {
		if (null == id) {
			return null;
		}
		for (OperationType temp : OperationType.values()) {
			if (temp.getId().equals(id)) {
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
