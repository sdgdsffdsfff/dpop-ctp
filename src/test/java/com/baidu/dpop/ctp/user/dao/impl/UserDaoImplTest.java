package com.baidu.dpop.ctp.user.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.baidu.dpop.ctp.base.AbstractDAOTests;
import com.baidu.dpop.ctp.base.DefaultBOUtils;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.constant.UserRoleType;
import com.baidu.dpop.ctp.user.dao.UserDao;
import com.baidu.dpop.ctp.user.vo.UserQueryVo;

public class UserDaoImplTest extends AbstractDAOTests {

	@Resource
	private UserDao userDao;

	@Before
	public void setUp() {
		this.executeDatas("user/user_dataset_init.sql");
	}

	// @Test
	// public void testBathInsert() {
	// List<User> list = new ArrayList<User>();
	// Date d = new Date();
	// for (int i = 0; i < 1; i++) {
	// User u = new User();
	// u.setRoleType((byte) (i % 4));
	// u.setUserName("User_" + i);
	// u.setPassword("123456");
	// u.setAddTime(d);
	// u.setUpdateTime(d);
	// u.setUpdateUser(0);
	// u.setDeleteFlag(false);
	// list.add(u);
	// }
	// userDao.batchInsertUser(list);
	// Assert.assertEquals(, 10);
	// }

	@Test
	public void testGetUserByName() {
		User u = userDao.getUserByName("user_1");
		Assert.assertNotNull(u);
		Assert.assertEquals(u.getUserName(), "user_1");
		Assert.assertEquals(u.getId(), Integer.valueOf(1));
		Assert.assertEquals(u.getRoleType(), Byte.valueOf("0"));
		Assert.assertEquals(u.getPassword(), "123456");
	}

	@Test
	public void testGetUserByType() {
		List<User> list = userDao.getUserListByType(Byte.valueOf("0"));
		Assert.assertEquals(list.size(), 2);
	}

	@Test
	public void testGetPageUserList() {
		UserQueryVo queryVo = new UserQueryVo();
		queryVo.setUserName("cgd");
		List<User> ret = this.userDao.getPageUserList(queryVo);
		Assert.assertTrue(ret.size() == 0);

		queryVo.setUserName("user");
		queryVo.setRoleType("0");
		queryVo.setOrder("desc");
		queryVo = UserQueryVo.initialize(queryVo);
		ret = this.userDao.getPageUserList(queryVo);
		Assert.assertTrue(ret.size() > 0);
	}

	@Test
	public void testBatchInsertUserNull() {
		Assert.assertEquals(0, userDao.batchInsertUser(null));
	}

	@Test
	public void testUserNames() {
		Assert.assertEquals(6, userDao.getUserNames().size());
	}

	@Test
	public void testDeleteUserByIdList() {
		List<Integer> list = new ArrayList<Integer>();
		Assert.assertEquals(0, userDao.deleteUsersByIdList(list));

		list.add(1);
		Assert.assertEquals(1, userDao.deleteUsersByIdList(list));
		Assert.assertEquals(5, userDao.getUserNames().size());
	}

	@Test
	public void testUpdateUserByUserName() {
		User u = new User();
		u.setUserName("user_1");
		u.setPassword("000000");
		userDao.updateByUserName(u);

		u = userDao.getUserByName("user_1");
		Assert.assertEquals("000000", u.getPassword());
	}

	@Test
	public void testGetUserByIdList() {
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(1);

		List<User> ret = this.userDao.getUserByIdList(idList);
		Assert.assertNotNull(ret);
		Assert.assertTrue(ret.size() > 0);
	}

	@Test
	public void testThawByIdList() {
		User u = userDao.selectByPrimaryKey(1L);
		u.setLoginTryTimes(5);
		userDao.updateByPrimaryKey(u);
		userDao.thawByIdList(Arrays.asList(1));
		Assert.assertEquals(Integer.valueOf(0), userDao.selectByPrimaryKey(1L)
				.getLoginTryTimes());
	}
}
