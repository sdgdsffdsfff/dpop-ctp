package com.baidu.dpop.ctp.operationRecord.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.baidu.dpop.ctp.common.utils.CollectionDisplayUtil;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.constant.UserRoleType;

public class OperationRecordGenerator {

	public static String genModifyUserRecord(User[] userBeforeAfter) {
		if (userBeforeAfter == null) {
			return "修改用户失败！ ";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("修改用户: ");
		sb.append(userBeforeAfter[0].getUserName());
		sb.append(", id : ");
		sb.append(userBeforeAfter[0].getId());
		sb.append(", 用户类型: ");
		sb.append(UserRoleType.get(userBeforeAfter[0].getRoleType()).getDesc());
		sb.append(" -> ");
		sb.append(UserRoleType.get(userBeforeAfter[1].getRoleType()).getDesc());
		sb.append(", 密码: ");
		sb.append(userBeforeAfter[0].getPassword());
		sb.append(" -> ");
		sb.append(userBeforeAfter[1].getPassword());
		return sb.toString();
	}

	public static String genCreateUserRecord(List<String> names,
			List<String> errorNames, String userType) {

		StringBuilder sb = new StringBuilder("添加用户: " + userType + ";");

		List<String> addNames = new ArrayList<String>();
		for (String name : names) {
			if (!errorNames.contains(name)) {
				addNames.add(name);
			}
		}

		if (!CollectionUtils.isEmpty(names)) {
			sb.append(" 添加成功: "
					+ CollectionDisplayUtil.listToString(addNames, "[", "]",
							",") + ";");
		}

		if (!CollectionUtils.isEmpty(names)) {
			sb.append(" 添加失败(用户名重复): "
					+ CollectionDisplayUtil.listToString(errorNames, "[", "]",
							",") + ";");
		}

		return sb.toString();
	}

	public static String genDeleteUserRecord(List<User> deleteUser) {
		StringBuilder sb = new StringBuilder("删除用户： ");
		for (User u : deleteUser) {
			sb.append(u.getUserName() + "("
					+ UserRoleType.get(u.getRoleType()).getDesc() + ") ");
		}
		return sb.toString();
	}
	
	public static String genThawUserRecord(List<User> deleteUser) {
		StringBuilder sb = new StringBuilder("解冻用户： ");
		for (User u : deleteUser) {
			sb.append(u.getUserName() + "("
					+ UserRoleType.get(u.getRoleType()).getDesc() + ") ");
		}
		return sb.toString();
	}

}
