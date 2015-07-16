/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.user.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.dao.UserDao;
import com.baidu.dpop.ctp.user.dao.mapper.UserMapper;
import com.baidu.dpop.ctp.user.vo.UserQueryVo;

@Repository
public class UserDaoImpl extends BaseDao<User, Long> implements UserDao {

	@Autowired
	private UserMapper userMapper;

	@Override
	public GenericMapper<User, Long> getMapper() {
		return this.userMapper;
	}

	public int batchInsertUser(List<User> users) {
		if (CollectionUtils.isEmpty(users)) {
			return 0;
		}
		return this.userMapper.batchInsertUser(users);
	}

	public User getUserByName(String userName) {
		return this.userMapper.getUserByName(userName);
	}

	public List<User> getUserListByType(Byte roleType) {
		return this.userMapper.getUserListByType(roleType);
	}

	@Override
	public int deleteUsersByIdList(List<Integer> userIdList) {
		if (CollectionUtils.isEmpty(userIdList)) {
			return 0;
		}
		return this.userMapper.deleteUsersByIdList(userIdList);
	}

	@Override
	public void updateByUserName(@Param("user") User user) {
		this.userMapper.updateByUserName(user);
	}

	@Override
	public List<User> getPageUserList(UserQueryVo queryVo) {
		Assert.notNull(queryVo);
		return this.userMapper.getPageUserList(queryVo);
	}

	@Override
	public List<String> getUserNames() {
		return this.userMapper.getUserNames();
	}

    @Override
    public List<User> getUserByIdList(List<Integer> idList) {
        Assert.notEmpty(idList);
        return this.userMapper.getUserByIdList(idList);
    }

	@Override
	public List<User> selectByUserNameLike(String name) {
		return this.userMapper.selectByUserNameLike(name);
	}

	@Override
	public void thawByIdList(List<Integer> idList) {
		this.userMapper.thawByIdList(idList);
	}
}
