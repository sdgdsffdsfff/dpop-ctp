/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.user.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.common.mybatis.page.PageInfo;
import com.baidu.dpop.ctp.operationRecord.constants.OperationType;
import com.baidu.dpop.ctp.operationRecord.service.OperationRecordService;
import com.baidu.dpop.ctp.operationRecord.utils.OperationRecordGenerator;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.constant.UserRoleType;
import com.baidu.dpop.ctp.user.service.UserService;
import com.baidu.dpop.ctp.user.vo.PassForm;
import com.baidu.dpop.ctp.user.vo.UserQueryVo;

@Controller
@RequestMapping(value = "/user")
public class UserController extends JsonBaseController {

	private static final Logger LOG = Logger.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	OperationRecordService operationRecordService;

	/**
	 * 用户管理页面，获取所有用户信息列表，查询为分页查询 只有管理员用户才能使用此接口，其他用户调用时会返回一个错误信息
	 * 
	 * @param vo
	 *            查询表单
	 * @return JsonResult格式的返回结果，如果成功则返回查询页的信息列表
	 */
	@RequestMapping(value = "/getInfoList.do", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult getUserInfoList(UserQueryVo vo) {
		try {
			// 参数初始化
			User curUser = userService.getCurrentLoginUser();

			if (!UserRoleType.isAdminRoleType(curUser.getRoleType())) {
				return this.markErrorResult("只有管理员才能使用此操作");
			}

			UserQueryVo queryVo = UserQueryVo.initialize(vo);
			List<User> userList = this.userService.getPageUserList(queryVo);
			return this.markSuccessResult(new PageInfo<User>(userList),
					"获取用户列表成功");
		} catch (Exception e) {
			LOG.error("Exception in getUserInfoList : ", e);
			return this.markErrorResult("获取用户信息列表失败, 原因: " + e.getMessage());
		}
	}

	/**
	 * 根据用户id查询某个用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/find.do", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult find(Long id) {
		User boData = userService.findById(id);
		return this.markSuccessResult(boData, "查找成功");
	}

	/**
	 * 批量添加用户，只有管理员才能使用此接口
	 * 
	 * @param list
	 *            新建用户的用户名列表
	 * @param password
	 *            新建用户的密码，只有新建外部用户时才需要，批量添加时所有人的初始密码是一致的
	 * @param roleType
	 *            用户类型
	 * @return JsonResult类型的结果
	 */
	@RequestMapping(value = "/add.do", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResult create(@RequestBody Map<String, Object> map) {
		try {
			UserRoleType roleType = UserRoleType.get(Integer.valueOf(map.get(
					"roleType").toString()));
			if (roleType == null) {
				return this.markErrorResult("添加错误：错误的用户类型");
			}

			String password = map.containsKey("password") ? map.get("password")
					.toString() : "";
			@SuppressWarnings("unchecked")
			List<String> userName = (List<String>) map.get("userName");
			User u = userService.getCurrentLoginUser();
			List<String> errorNames = userService.createUsers(userName, u,
					roleType.getId(), password);
			if (errorNames != null) {
				operationRecordService.insertNewOperation(
						OperationType.OP_USER_CREATE.getId(),
						OperationRecordGenerator.genCreateUserRecord(userName,
								errorNames, roleType.getDesc()), Long.valueOf(u
								.getId().toString()));
				return this.markSuccessResult(errorNames, "创建用户成功!");
			} else {
				return this.markErrorResult("创建用户失败! 数据库错误");
			}
		} catch (Exception e) {
			LOG.error("Batch insert user wrong: " + e.getLocalizedMessage());
			return this.markErrorResult("创建用户失败, 原因: "
					+ e.getLocalizedMessage());
		}
	}

	/**
	 * 修改某个用户的用户信息，只有管理员才能使用此接口
	 * 
	 * @param id
	 *            需要修改的用户id
	 * @param roleType
	 *            修改的用户类型，内部用户只能修改为内部用户，外部用户亦然
	 * @param password
	 *            修改的用户密码，只能修改外部用户的密码
	 * @return JsonResult类型的结果，修改成功时会返回修改后的用户信息
	 */
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(@RequestBody Map<String, Object> map) {
		try {
			User updateUser = userService.getCurrentLoginUser();
			Integer id = Integer.valueOf(map.get("id").toString());
			Byte roleType = Byte.valueOf(map.get("roleType").toString());
			String password = map.containsKey("password") ? map.get("password")
					.toString() : null;
			User[] userBeforeAfter = userService.updateUser(id, roleType,
					password, updateUser);
			if (userBeforeAfter != null) {
				operationRecordService.insertNewOperation(
						OperationType.OP_USER_EDIT.getId(),
						OperationRecordGenerator
								.genModifyUserRecord(userBeforeAfter), Long
								.valueOf(updateUser.getId().toString()));
				return this.markSuccessResult(userBeforeAfter[1], "更新用户信息成功");
			} else {
				return this.markErrorResult("更新用户信息失败");
			}
		} catch (Exception e) {
			LOG.error("更新用户信息失败", e);
			return this.markErrorResult("更新用户信息失败, 原因: "
					+ e.getLocalizedMessage());
		}
	}

	/**
	 * 获取当前登录的用户信息。
	 * 
	 * @return 当前登录的用户信息，包括用户名和用户类型。
	 */
	@RequestMapping(value = "/getCurrentUserInfo.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult getCurrentUserInfo() {
		try {
			User user = userService.getCurrentLoginUser();
			if (user == null) {
				return this.markErrorResult("获取当前登录用户失败");
			}
			
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("userName", user.getUserName());
			map.put("userRoleType", user.getRoleType().toString());
			return this.markSuccessResult(map, "当前登录用户信息");
		} catch (Exception e) {
			return this.markErrorResult("获取当前登录用户失败, 原因: "
					+ e.getLocalizedMessage());
		}
	}

	/**
	 * 更新用户密码。首先通过UserInfoHelper获取当前登录的用户名，然后再通过此用户名更新用户密码。
	 * 如果没有获取到当前登录用户，则会返回一个错误的结果。 更改密码的具体逻辑在service中处理。所有的异常也在service中处理过了。
	 * 
	 * @param oldPwd
	 *            旧密码
	 * @param newPwdFirst
	 *            新密码
	 * @param newPwdSecond
	 *            新密码
	 * @return JsonResult
	 */
	@RequestMapping(value = "/modifyUserPwd.action", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResult modifyUserPwd(@RequestBody PassForm form) {
		try {
			User user = userService.getCurrentLoginUser();
			if (user == null) {
				return this.markErrorResult("获取当前登录用户失败!");
			}
			userService.updatePassword(form.getOldPass(), form.getNewPass(),
					form.getNewAgainPass(), user);
			return this.markSuccessResult(null, "success");
		} catch (BaseRuntimeException e) {
			return this.markErrorResult(e.getLocalizedMessage());
		}
	}

	/**
	 * 批量删除接口 逻辑删除
	 * 
	 * @param userIdList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResult delete(@RequestBody Map<String, Object> map) {
		List<Integer> userIdList = (List<Integer>) map.get("userIdList");
		if (CollectionUtils.isEmpty(userIdList)) {
			return this.markErrorResult("用户列表为空");
		}
		try {
			User updateUser = userService.getCurrentLoginUser();
			userService.deleteByIdList(userIdList, updateUser);
			List<User> list = userService.getUserByIdList(userIdList);
			operationRecordService.insertNewOperation(
					OperationType.OP_USER_DELETE.getId(),
					OperationRecordGenerator.genDeleteUserRecord(list),
					Long.valueOf(updateUser.getId().toString()));
			return this.markSuccessResult();
		} catch (Exception e) {
			LOG.error("删除失败：", e);
			return this.markErrorResult("删除用户失败");
		}

	}

	@RequestMapping(value = "/getUserByNameLike.do")
	@ResponseBody
	public JsonResult getUserByNameLike(String name) {
		try {
			return this.markSuccessResult(userService.getByUserNameLike(name),
					"获取成功");
		} catch (Exception e) {
			LOG.error("获取失败", e);
			return this.markErrorResult("获取失败");
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/thawByIdList.do", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResult thawByIdList(@RequestBody Map<String, Object> map) {
		List<Integer> userIdList = (List<Integer>) map.get("userIdList");
		if (null == userIdList) {
			return this.markErrorResult("用户列表为空");
		}
		try {
			User updateUser = userService.getCurrentLoginUser();
			List<User> list = userService.thawByIdList(userIdList, updateUser);
			operationRecordService.insertNewOperation(
					OperationType.OP_USER_THAW.getId(),
					OperationRecordGenerator.genThawUserRecord(list),
					Long.valueOf(updateUser.getId().toString()));
			return this.markSuccessResult();
		} catch (Exception e) {
			LOG.error("解冻失败：", e);
			return this.markErrorResult("解冻用户失败");
		}

	}
}
