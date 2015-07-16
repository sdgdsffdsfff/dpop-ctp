/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.user.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.vo.UserQueryVo;

public interface UserService extends GenericMapperService<User, Long> {

	/**
	 * 通过用户名获取用户
	 * 
	 * @param userName
	 *            需要获取的用户名
	 * @return 获取到的用户
	 */
	public User getUserByName(String userName);

	/**
	 * 通过id列表获取用户
	 * 
	 * @param ids
	 *            需要获取的id
	 * @return 获取到的用户
	 */
	public List<User> getUserByIdList(List<Integer> ids);

	/**
	 * 批量插入数据
	 * 
	 * @param tasks
	 *            列表形式的数据内容
	 * @return 成功插入的条目数
	 */
	public int batchInsertUser(List<User> users);

	/**
	 * 获取所有指定用户类型的用户
	 * 
	 * @param roleType
	 *            用户类型
	 * @return 所有指定用户类型的用户的列表
	 */
	public List<User> getUserListByType(Byte roleType);

	/**
	 * 批量删除用户数据，逻辑删除
	 * 
	 * @param userIdList
	 *            用户ID列表;
	 * @return 影响行数
	 */
	public int deleteByIdList(List<Integer> userIdList, User updateUser);

	/**
	 * 校验是否为外部用户
	 * 
	 * @param userName
	 *            用户名
	 * @param pwd
	 *            密码
	 * @return 是否外部用户
	 * */
	public boolean isOutsideUser(String userName, String pwd);

	/**
	 * 校验是否为内部用户
	 * 
	 * @param userName
	 *            用户名
	 * @return 是否内部用户
	 */
	public boolean isInsideUser(String userName);

	/**
	 * 校验是否是合法用户(有用户信息的用户)
	 * 
	 * @param userName
	 *            用户名
	 * @return 是否为系统合法用户
	 */
	public boolean isVaildUser(String userName);

	/**
	 * 修改用户密码
	 * 
	 * @param oldPwd
	 *            旧密码
	 * @param newPwdFirst
	 *            新密码
	 * @param newPwdSecond
	 *            新密码（重复输入）
	 * @param user
	 *            需要修改密码的用户
	 * @return 是否成功
	 */
	public void updatePassword(String oldPwd, String newPwdFirst,
			String newPwdSecond, User user);

	/**
	 * 根据查询条件获取用户列表
	 * 
	 * @param queryVo
	 *            查询条件
	 * @return
	 */
	public List<User> getPageUserList(UserQueryVo queryVo);

	/**
	 * 尝试获取当前登录的用户
	 * 
	 * @return 获取成功则返回当前登录用户的实体类型，否则返回null
	 */
	public User getCurrentLoginUser();

	/**
	 * 创建用户
	 * 
	 * @param names
	 *            用户名，以list方式传入
	 * @param upDateUserId
	 *            操作人
	 * @param roleType
	 *            用户类型
	 * @param password
	 *            密码
	 * @return 创建失败的用户名
	 */
	public List<String> createUsers(List<String> names, User updateUser,
			Byte roleType, String password) throws IllegalArgumentException;

	/**
	 * 更新用户信息
	 * 
	 * @param id
	 *            待更新的用户id
	 * @param roleType
	 *            待更新的用户类型
	 * @param password
	 *            待更新的用户密码
	 * @param updateUser
	 *            操作人
	 * @return 操作前的user，操作后的user
	 */
	public User[] updateUser(Integer id, Byte roleType, String password,
			User updateUser);

	/**
	 * 根据给定名称模糊查询user
	 * 
	 * @param name
	 *            名称
	 * @return 获取到的用户列表
	 */
	public List<User> getByUserNameLike(String name);

	/**
	 * 批量解冻账户
	 * 
	 * @param idList
	 *            解冻的账户id列表
	 * @param updateUser
	 *            操作人
	 * @return 解冻后的状态
	 */
	public List<User> thawByIdList(List<Integer> idList, User updateUser);

	/**
	 * 判断给定用户是否被冻结
	 * 
	 * @param userName
	 *            需要判定的用户名
	 * @return 用户是否被冻结
	 */
	public Boolean isFrozen(String userName);
}
