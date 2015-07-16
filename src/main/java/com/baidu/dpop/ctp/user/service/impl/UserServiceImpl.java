/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.frame.core.user.UserInfoHelper;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.common.mybatis.page.PageHelper;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.constant.UserRoleType;
import com.baidu.dpop.ctp.user.dao.UserDao;
import com.baidu.dpop.ctp.user.service.UserService;
import com.baidu.dpop.ctp.user.vo.UserQueryVo;

@Service
public class UserServiceImpl extends BaseService<User, Long> implements
		UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public GenericMapperDao<User, Long> getDao() {
		return userDao;
	}

	public User getUserByName(String userName) {
		return userDao.getUserByName(userName);
	}

	public int batchInsertUser(List<User> users) {
		return userDao.batchInsertUser(users);
	}

	public List<User> getUserListByType(Byte roleType) {
		return userDao.getUserListByType(roleType);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public int deleteByIdList(List<Integer> userIdList, User updateUser) {

		if (CollectionUtils.isEmpty(userIdList)) {
			return 0;
		}

		if (updateUser == null) {
			// 获取当前登录用户失败
			throw new BaseRuntimeException("获取当前登录用户失败！");
		}

		if (!UserRoleType.isAdminRoleType(updateUser.getRoleType())) {
			// 当前登录用户并非管理员，不具有操作权限
			throw new BaseRuntimeException("只有管理员才有操作权限!");
		}
		return userDao.deleteUsersByIdList(userIdList);
	}

	@Override
	public boolean isOutsideUser(String userName, String pwd) {
		Assert.notNull(userName);
		Assert.notNull(pwd);

		User user = this.userDao.getUserByName(userName);
		if (user == null || !user.getPassword().equals(pwd)) {
			return false;
		}

		// 必需为外部用户
		if (UserRoleType.isOutUserRoleType(user.getRoleType())) {
			return true;
		}

		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updatePassword(String oldPwd, String newPwdFirst,
			String newPwdSecond, User user) {
		Assert.notNull(user);

		if (!user.getPassword().equals(oldPwd)) {// 新旧密码不匹配
			throw new BaseRuntimeException("旧密码输入错误");
		}

		if (!newPwdFirst.equals(newPwdSecond)) {// 新密码两次输入不一致
			throw new BaseRuntimeException("两次输入的密码不一致");
		}

		if (oldPwd.equals(newPwdFirst)) {// 新旧密码相同
			throw new BaseRuntimeException("新密码与旧密码相同");
		}
		
		user.setPassword(newPwdFirst);
		userDao.updateByUserName(user);
	}

	@Override
	public boolean isInsideUser(String userName) {
		Assert.notNull(userName);
		User user = this.userDao.getUserByName(userName);

		if (user == null || UserRoleType.isOutUserRoleType(user.getRoleType())) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isVaildUser(String userName) {
		if (userName == null) {
			return false;
		}
		
		User user = this.userDao.getUserByName(userName);

		if (user == null || user.getDeleteFlag()) {
			return false;
		}

		return true;
	}

	@Override
	public List<User> getPageUserList(UserQueryVo queryVo) {
		Assert.notNull(queryVo);
		PageHelper.startPage(queryVo.getPage(), queryVo.getSize());
		return this.userDao.getPageUserList(queryVo);
	}

	@Override
	public User getCurrentLoginUser() {
		String userName = UserInfoHelper.getCurrentUserName();
		if (userName == null) {
			return null;
		}

		return getUserByName(userName);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<String> createUsers(List<String> names, User updateUser,
			Byte roleType, String password) {

		if (UserRoleType.get(roleType) == null) {
			// 传入了错误的角色类型
			throw new BaseRuntimeException("角色类型错误: " + roleType);
		}

		if (updateUser == null) {
			// 获取当前登录用户失败
			throw new BaseRuntimeException("获取当前登录用户失败！");
		}

		if (!UserRoleType.isAdminRoleType(updateUser.getRoleType())) {
			// 当前登录用户并非管理员，不具有操作权限
			throw new BaseRuntimeException("只有管理员才有操作权限!");
		}
		
		Date d = new Date();
		List<String> errorNames = new ArrayList<String>();
		
		names = new ArrayList<String>(new HashSet<String>(names)); // 去重
		List<String> hasNames = userDao.getUserNames();

		errorNames.addAll(names);
		errorNames.retainAll(hasNames); // 取交集，得到系统已有的名称，不添加
		names.removeAll(hasNames); // 删除系统已有的名称，不添加

		List<User> addUsers = new ArrayList<User>();
		for (String name : names) {
			User u = new User();
			u.setAddTime(d);
			u.setDeleteFlag(false);
			u.setPassword(password);
			u.setRoleType(roleType);
			u.setUpdateTime(d);
			u.setUpdateUser(updateUser.getId());
			u.setUserName(name);
				addUsers.add(u);
		}
		userDao.batchInsertUser(addUsers);
		return errorNames;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public User[] updateUser(Integer id, Byte roleType, String password,
			User updateUser) {

		if (UserRoleType.get(roleType) == null) {
			// 传入角色类型有误
			throw new BaseRuntimeException("传入了错误的角色类型: " + roleType);
		}

		if (updateUser == null) {
			// 当前登录用户获取失败
			throw new BaseRuntimeException("无法获取当前登录用户!");
		}

		if (!UserRoleType.isAdminRoleType(updateUser.getRoleType())) {
			// 当前登录用户不是管理员
			throw new BaseRuntimeException("只有管理员才有操作权限!");
		}

		User userBefore = userDao
				.selectByPrimaryKey(Long.valueOf(id.toString()));

		if (userBefore == null) {
			// 被更新用户不存在
			throw new BaseRuntimeException("不存在这样的用户id: " + id);
		}

		if (!UserRoleType.isSameType(userBefore.getRoleType(), roleType)) {
			// 内外部用户不能相互更新
			throw new BaseRuntimeException("内(外)部用户只能修改为相同的类型!");
		}

		User userAfter = userBefore.clone();
		if (password != null) {
			userAfter.setPassword(password);
		}

		userAfter.setRoleType(roleType);
		userAfter.setUpdateTime(new Date());
		userAfter.setUpdateUser(updateUser.getId());
		
		userDao.updateByPrimaryKeySelective(userAfter);
		return new User[] {userBefore, userAfter};
	}

    @Override
    public List<User> getUserByIdList(List<Integer> idList) {
        Assert.notEmpty(idList);
        return this.userDao.getUserByIdList(idList);
    }

	@Override
	public List<User> getByUserNameLike(String name) {
		return this.userDao.selectByUserNameLike(name);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<User> thawByIdList(List<Integer> idList, User updateUser) {
		if (CollectionUtils.isEmpty(idList)) {
			return null;
		}

		if (updateUser == null) {
			// 获取当前登录用户失败
			throw new BaseRuntimeException("获取当前登录用户失败！");
		}

		if (!UserRoleType.isAdminRoleType(updateUser.getRoleType())) {
			// 当前登录用户并非管理员，不具有操作权限
			throw new BaseRuntimeException("只有管理员才有操作权限!");
		}
		
		this.userDao.thawByIdList(idList);
		return this.getUserByIdList(idList);
	}

	@Override
	public Boolean isFrozen(String userName) {
		if (userName == null) {
			return false;
		}
		
		User user = this.userDao.getUserByName(userName);

		if (user == null || user.getLoginTryTimes() >= 5) {
			return true;
		}

		return false;
	}
}
