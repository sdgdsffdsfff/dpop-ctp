package com.baidu.dpop.ctp.user.constant;

/**
 * 用户类型常量定义
 * 
 * @author cgd
 * @date 2014年12月24日 下午4:50:25
 */
public enum UserRoleType {
	
	INNER_ADMIN_USER(Byte.valueOf("0"), "管理员"), INNER_TAG_USER(Byte
			.valueOf("1"), "内部标注用户"), INNER_AUDIT_USER(Byte.valueOf("2"),
			"内部审核用户"), OUTSIDE_TAG_USER(Byte.valueOf("10"), "外部标注用户"), OUTSIDE_AUDIT_USER(
			Byte.valueOf("11"), "外部审核用户");

	private Byte id;
	private String desc;

	private UserRoleType(Byte id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public static UserRoleType get(Number id) {
		if (null == id) {
			return null;
		}
		for (UserRoleType temp : UserRoleType.values()) {
			if (temp.getId().equals(id.byteValue())) {
				return temp;
			}
		}
		return null;
	}

	/**
	 * 判断是否为管理员用户
	 * 
	 * @param roleType
	 *            需要判定的数值
	 * @return 是否为管理员用户
	 */
	public static boolean isAdminRoleType(Number roleType) {

		if (INNER_ADMIN_USER.id.intValue() == roleType.intValue()) {
			return true;
		}

		return false;
	}

	/**
	 * 判断给定数值是否为内部用户，管理员也算内部用户
	 * 
	 * @param roleType
	 *            需要判定的数值
	 * @return 是否为内部用户
	 */
	public static boolean isInnerUserRoleType(Number roleType) {

		if (INNER_ADMIN_USER.id.intValue() == roleType.intValue()) {
			return true;
		}

		if (INNER_TAG_USER.id.intValue() == roleType.intValue()) {
			return true;
		}

		if (INNER_AUDIT_USER.id.intValue() == roleType.intValue()) {
			return true;
		}

		return false;
	}

	/**
	 * 判断给定数值是否为外部用户
	 * 
	 * @param roleType
	 *            需要判定的数值
	 * @return 是否为内部用户
	 */
	public static boolean isOutUserRoleType(Number roleType) {

		if (OUTSIDE_TAG_USER.id.intValue() == roleType.intValue()) {
			return true;
		}

		if (OUTSIDE_AUDIT_USER.id.intValue() == roleType.intValue()) {
			return true;
		}

		return false;
	}

	/**
	 * 判断两个给定的数值是否属于同种用户（内部或外部）
	 * 
	 * @param one
	 *            其中一个比较值
	 * @param another
	 *            另一个比较值
	 * @return 两者是否是同种类型
	 */
	public static boolean isSameType(Number one, Number another) {
		
		if (isOutUserRoleType(one) && isOutUserRoleType(another)) {
			return true;
		}

		if (isInnerUserRoleType(one) && isInnerUserRoleType(another)) {
			return true;
		}

		if (isAdminRoleType(one) && isAdminRoleType(another)) {
			return true;
		}

		return false;
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
}
