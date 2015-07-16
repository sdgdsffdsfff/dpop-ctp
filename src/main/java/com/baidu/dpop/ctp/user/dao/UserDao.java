/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.vo.UserQueryVo;

public interface UserDao extends GenericMapperDao<User, Long> {

	/**
	 * 批量插入数据
	 * 
	 * @param tasks
	 *            列表形式的数据内容
	 * @return 成功插入的条目数
	 */
	public int batchInsertUser(@Param("users") List<User> users);

	/**
	 * 通过用户名获取用户
	 * 
	 * @param userName
	 *            需要获取的用户名
	 * @return 获取到的用户
	 */
	public User getUserByName(@Param("userName") String userName);

	/**
	 * 获取所有指定用户类型的用户
	 * 
	 * @param roleType
	 *            用户类型
	 * @return 所有指定用户类型的用户的列表
	 */
	public List<User> getUserListByType(@Param("roleType") Byte roleType);

	/**
	 * 批量删除制定用户
	 * 
	 * @param userIdList
	 */
	public int deleteUsersByIdList(List<Integer> userIdList);

	/**
	 * 根据用户名更新用户信息
	 * 
	 * @param user
	 *            带更新的用户实体
	 */
	public void updateByUserName(@Param("user") User user);

	/**
	 * 根据查询条件查询用户
	 * 
	 * @param queryVo
	 *            查询条件
	 * @return 分页的用户信息
	 */
	public List<User> getPageUserList(UserQueryVo queryVo);

	/**
	 * 获取系统中所有用户的用户名
	 * 
	 * @return 系统所有用户的用户名列表
	 */
	public List<String> getUserNames();

	/**
	 * 批量获取用户信息
	 * 
	 * @param idList
	 *            user id list
	 * */
	public List<User> getUserByIdList(List<Integer> idList);

	/**
	 * 根据给定名称模糊查询user
	 * 
	 * @param name
	 *            名称
	 * @return 获取到的用户列表
	 */
	public List<User> selectByUserNameLike(String name);
	
	/**
	 * 批量解冻账户
	 * 
	 * @param idList
	 *            解冻的账户id列表
	 */
	public void thawByIdList(List<Integer> idList);
}
