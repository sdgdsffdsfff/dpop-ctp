package com.baidu.dpop.ctp.user.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.base.DefaultBOUtils;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.constant.UserRoleType;
import com.baidu.dpop.ctp.user.dao.UserDao;
import com.baidu.dpop.ctp.user.vo.UserQueryVo;
import com.baidu.dpop.frame.core.user.UserInfoHelper;

import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;

public class UserServiceImplTest {

	@Tested
	private UserServiceImpl userServiceImpl;

	@Injectable
	private UserDao userDao;

	@SuppressWarnings("unchecked")
	@Test
	public void testDeleteByList() {
		new NonStrictExpectations() {
			{
				userDao.deleteUsersByIdList((List<Integer>) any);
				result = 10;
			}
		};

		User admin = DefaultBOUtils.getUser(1, "admin",
				UserRoleType.INNER_ADMIN_USER.getId());
		List<Integer> list = new ArrayList<Integer>();
		Assert.assertEquals(0, userServiceImpl.deleteByIdList(list, admin));

		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		Assert.assertEquals(10, userServiceImpl.deleteByIdList(list, admin));
	}

	@SuppressWarnings("unchecked")
	@Test(expected = BaseRuntimeException.class)
	public void testDeleteByListUpdateUserTypeWrong() {
		new NonStrictExpectations() {
			{
				userDao.deleteUsersByIdList((List<Integer>) any);
				result = 10;
			}
		};
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}

		User admin = DefaultBOUtils.getUser(1, "notAdmin",
				UserRoleType.OUTSIDE_AUDIT_USER.getId());
		userServiceImpl.deleteByIdList(list, admin);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = BaseRuntimeException.class)
	public void testDeleteByListUpdateUserWrong() {
		new NonStrictExpectations() {
			{
				userDao.deleteUsersByIdList((List<Integer>) any);
				result = 10;
			}
		};
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		userServiceImpl.deleteByIdList(list, null);
	}

	@Test
	public void testGetUserByName() {
		new NonStrictExpectations() {
			{
				userDao.getUserByName((String) any);
				result = DefaultBOUtils.getUser(1, "user",
						UserRoleType.INNER_AUDIT_USER.getId());
			}
		};
		User user = userServiceImpl.getUserByName("user");
		Assert.assertNotNull(user);
		Assert.assertEquals("user", user.getUserName());
	}

	@Test
	public void TestGetUserListByType() {
		new NonStrictExpectations() {
			{
				userDao.getUserListByType((Byte) any);
				List<User> list = new ArrayList<User>();
				for (int i = 0; i < 5; i++) {
					list.add(DefaultBOUtils.getUser(i, "user" + i,
							UserRoleType.OUTSIDE_AUDIT_USER.getId()));
				}
				result = list;
			}
		};
		List<User> uList = userServiceImpl
				.getUserListByType(UserRoleType.OUTSIDE_AUDIT_USER.getId());
		Assert.assertNotNull(uList);
		Assert.assertEquals(5, uList.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testBatchInsertUser() {
		new NonStrictExpectations() {
			{
				userDao.batchInsertUser((List<User>) any);
				result = 5;
			}
		};

		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 5; i++) {
			list.add(DefaultBOUtils.getUser(i, "name" + i,
					UserRoleType.OUTSIDE_AUDIT_USER.getId()));
		}
		Assert.assertEquals(5, userServiceImpl.batchInsertUser(list));
	}

	@Test
	public void testIsOutsideUser() {
		new NonStrictExpectations() {
			{
				userDao.getUserByName((String) any);
				result = DefaultBOUtils.getUser(1, "user",
						UserRoleType.OUTSIDE_AUDIT_USER.getId());
			}
		};

		Assert.assertTrue(userServiceImpl.isOutsideUser("user",
				DefaultBOUtils.DEFAULT_PASSWORD));
		Assert.assertFalse(userServiceImpl.isOutsideUser("user", "123"));

		new NonStrictExpectations() {
			{
				userDao.getUserByName((String) any);
				result = DefaultBOUtils.getUser(1, "user",
						UserRoleType.INNER_ADMIN_USER.getId());
			}
		};
		Assert.assertFalse(userServiceImpl.isOutsideUser("mading01",
				DefaultBOUtils.DEFAULT_PASSWORD));

		new NonStrictExpectations() {
			{
				userDao.getUserByName((String) any);
				result = null;
			}
		};
		Assert.assertFalse(userServiceImpl.isOutsideUser("mading01",
				DefaultBOUtils.DEFAULT_PASSWORD));
	}

	@Test
	public void testUpdatePassword() {
		new NonStrictExpectations() {
			{
				userDao.updateByUserName((User) any);
			}
		};
		User user = new User();
		user.setUserName("user");
		user.setPassword("123");
		user.setRoleType(UserRoleType.INNER_ADMIN_USER.getId());

		userServiceImpl.updatePassword("123", "456", "456", user);

	}

	@Test(expected = BaseRuntimeException.class)
	public void testUpdatePasswordOldWrong() {
		new NonStrictExpectations() {
			{
				userDao.updateByUserName((User) any);
			}
		};
		User user = new User();
		user.setUserName("user");
		user.setPassword("123");
		user.setRoleType(UserRoleType.INNER_ADMIN_USER.getId());
		userServiceImpl.updatePassword("111", "456", "456", user);
	}

	@Test(expected = BaseRuntimeException.class)
	public void testUpdatePasswordNewDifferent() {
		new NonStrictExpectations() {
			{
				userDao.updateByUserName((User) any);
			}
		};
		User user = new User();
		user.setUserName("user");
		user.setPassword("123");
		user.setRoleType(UserRoleType.INNER_ADMIN_USER.getId());
		userServiceImpl.updatePassword("123", "456", "789", user);

	}

	@Test(expected = BaseRuntimeException.class)
	public void testUpdatePasswordNewWrong() {
		new NonStrictExpectations() {
			{
				userDao.updateByUserName((User) any);
			}
		};
		User user = new User();
		user.setUserName("user");
		user.setPassword("123");
		user.setRoleType(UserRoleType.INNER_ADMIN_USER.getId());
		userServiceImpl.updatePassword("123", "123", "123", user);

	}

	@Test
	public void testIsInsideUser() {
		new NonStrictExpectations() {
			{
				userDao.getUserByName((String) any);
				result = DefaultBOUtils.getUser(1, "user",
						UserRoleType.INNER_ADMIN_USER.getId());
			}
		};
		Assert.assertTrue(userServiceImpl.isInsideUser("user"));

		new NonStrictExpectations() {
			{
				userDao.getUserByName((String) any);
				result = DefaultBOUtils.getUser(1, "user",
						UserRoleType.OUTSIDE_AUDIT_USER.getId());
			}
		};
		Assert.assertFalse(userServiceImpl.isInsideUser("user"));

		new NonStrictExpectations() {
			{
				userDao.getUserByName((String) any);
				result = null;
			}
		};
		Assert.assertFalse(userServiceImpl.isInsideUser("user"));
	}

	@Test
	public void testIsVaildUser() {

		new NonStrictExpectations() {
			{
				userDao.getUserByName((String) any);
				result = DefaultBOUtils.getUser(1, "user",
						UserRoleType.OUTSIDE_AUDIT_USER.getId());
			}
		};
		Assert.assertTrue(userServiceImpl.isVaildUser("user"));

		new NonStrictExpectations() {
			{
				userDao.getUserByName((String) any);
				User user = DefaultBOUtils.getUser(1, "user",
						UserRoleType.OUTSIDE_AUDIT_USER.getId());
				user.setDeleteFlag(true);
				result = user;
			}
		};
		Assert.assertFalse(userServiceImpl.isVaildUser("user"));

		new NonStrictExpectations() {
			{
				userDao.getUserByName((String) any);
				result = null;
			}
		};
		Assert.assertFalse(userServiceImpl.isVaildUser("user"));

		Assert.assertFalse(userServiceImpl.isVaildUser(null));
	}

	@Test
	public void testGetPageUserList() {
		UserQueryVo vo = new UserQueryVo();
		vo.setPage(1);
		vo.setSize(20);
		userServiceImpl.getPageUserList(vo);
		new Verifications() {
			{
				userDao.getPageUserList((UserQueryVo) any);
				times = 1;
			}
		};
	}

	@Test
	public void testGetCurrentLoginUser() {
		new MockUp<UserInfoHelper>() {
			@Mock
			public String getCurrentUserName() {
				return "mading";
			}
		};

		new NonStrictExpectations() {
			{
				userDao.getUserByName((String) any);
				User u = new User();
				result = u;
			}
		};
		Assert.assertNotNull(userServiceImpl.getCurrentLoginUser());
	}

	@Test
	public void testGetCurrentLoginUserNameNull() {
		new MockUp<UserInfoHelper>() {
			@Mock
			public String getCurrentUserName() {
				return null;
			}
		};

		new NonStrictExpectations() {
			{
				userDao.getUserByName((String) any);
				User u = new User();
				result = u;
			}
		};

		Assert.assertNull(userServiceImpl.getCurrentLoginUser());
	}

	@Test
	public void testGetCurrentLoginUserWrong() {
		new MockUp<UserInfoHelper>() {
			@Mock
			public String getCurrentUserName() {
				return "mading";
			}
		};

		new NonStrictExpectations() {
			{
				userDao.getUserByName((String) any);
				result = null;
			}
		};

		Assert.assertNull(userServiceImpl.getCurrentLoginUser());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCreateUsers() {
		new NonStrictExpectations() {
			{
				userDao.batchInsertUser((List<User>) any);
				result = 5;
			}
		};

		User updateUser = new User();
		updateUser.setId(1);
		updateUser.setRoleType(UserRoleType.INNER_ADMIN_USER.getId());
		List<String> names = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			names.add("user_" + i);
		}
		List<String> list = userServiceImpl.createUsers(names, updateUser,
				UserRoleType.OUTSIDE_AUDIT_USER.getId(), "123456");
		Assert.assertEquals(0, list.size());
	}

	@Test(expected = BaseRuntimeException.class)
	public void testCreateUsersWrongRoleType() {

		User updateUser = new User();
		updateUser.setId(1);
		updateUser.setRoleType(UserRoleType.INNER_ADMIN_USER.getId());
		List<String> names = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			names.add("user_" + i);
		}

		userServiceImpl.createUsers(names, updateUser, Byte.valueOf("40"),
				"123456");
	}

	@Test(expected = BaseRuntimeException.class)
	public void testCreateUsersNoLoginUser() {

		User updateUser = new User();
		updateUser.setId(1);
		updateUser.setRoleType(UserRoleType.INNER_ADMIN_USER.getId());
		List<String> names = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			names.add("user_" + i);
		}

		userServiceImpl.createUsers(names, null,
				UserRoleType.INNER_ADMIN_USER.getId(), "123456");
	}

	@Test(expected = BaseRuntimeException.class)
	public void testCreateUsersNotAdminUser() {

		User updateUser = new User();
		updateUser.setId(1);
		updateUser.setRoleType(UserRoleType.INNER_AUDIT_USER.getId());
		List<String> names = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			names.add("user_" + i);
		}

		userServiceImpl.createUsers(names, updateUser,
				UserRoleType.INNER_ADMIN_USER.getId(), "123456");
	}

	@Test
	public void testUpdateUser() {
		new NonStrictExpectations() {
			{
				userDao.updateByPrimaryKey((User) any);
				result = 1;

				userDao.selectByPrimaryKey((Long) any);
				User u = new User();
				u.setUserName("test");
				u.setRoleType(UserRoleType.OUTSIDE_TAG_USER.getId());
				result = u;
			}
		};
		User updateUser = new User();
		updateUser.setUserName("mading");
		updateUser.setId(1);
		updateUser.setRoleType(UserRoleType.INNER_ADMIN_USER.getId());

		User[] r = userServiceImpl.updateUser(1,
				UserRoleType.OUTSIDE_AUDIT_USER.getId(), "123456", updateUser);
		Assert.assertNotNull(r);
		Assert.assertEquals("test", r[1].getUserName());
		Assert.assertEquals(UserRoleType.OUTSIDE_AUDIT_USER.getId(),
				r[1].getRoleType());
		Assert.assertEquals("123456", r[1].getPassword());
		Assert.assertEquals(Integer.valueOf(1), r[1].getUpdateUser());
	}

	@Test(expected = BaseRuntimeException.class)
	public void testUpdateUserWrongRoleType() {
		userServiceImpl.updateUser(1, Byte.valueOf("44"), "123456", new User());
	}

	@Test(expected = BaseRuntimeException.class)
	public void testUpdateUserNoUpdateUser() {
		userServiceImpl.updateUser(1, UserRoleType.INNER_AUDIT_USER.getId(),
				"123456", null);
	}

	@Test(expected = BaseRuntimeException.class)
	public void testUpdateUserNotAdminUpdate() {
		User updateUser = new User();
		updateUser.setUserName("mading");
		updateUser.setId(1);
		updateUser.setRoleType(UserRoleType.INNER_TAG_USER.getId());
		userServiceImpl.updateUser(1, UserRoleType.INNER_AUDIT_USER.getId(),
				"123456", updateUser);
	}

	@Test(expected = BaseRuntimeException.class)
	public void testUpdateUserNoSuchUser() {
		User updateUser = new User();
		updateUser.setUserName("mading");
		updateUser.setId(1);
		updateUser.setRoleType(UserRoleType.INNER_ADMIN_USER.getId());

		new NonStrictExpectations() {
			{
				userDao.selectByPrimaryKey((Long) any);
				result = null;
			}
		};
		userServiceImpl.updateUser(1, UserRoleType.INNER_AUDIT_USER.getId(),
				"123456", updateUser);
	}

	@Test(expected = BaseRuntimeException.class)
	public void testUpdateUserWrongUserRoleType() {
		User updateUser = new User();
		updateUser.setUserName("mading");
		updateUser.setId(1);
		updateUser.setRoleType(UserRoleType.INNER_ADMIN_USER.getId());

		new NonStrictExpectations() {
			{
				userDao.selectByPrimaryKey((Long) any);
				User u = new User();
				u.setUserName("test");
				u.setRoleType(UserRoleType.OUTSIDE_TAG_USER.getId());
				result = u;
			}
		};
		userServiceImpl.updateUser(1, UserRoleType.INNER_AUDIT_USER.getId(),
				"123456", updateUser);
	}

	@Test(expected = BaseRuntimeException.class)
	public void testUpdateUserWrong() {
		User updateUser = new User();
		updateUser.setUserName("mading");
		updateUser.setId(1);
		updateUser.setRoleType(UserRoleType.INNER_ADMIN_USER.getId());

		new NonStrictExpectations() {
			{
				userDao.updateByPrimaryKey((User) any);
				result = new Exception("test");
			}
		};
		userServiceImpl.updateUser(1, UserRoleType.INNER_AUDIT_USER.getId(),
				"123456", updateUser);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetUserByIdList() {
		userServiceImpl.getUserByIdList(Arrays.asList(1, 2, 3));

		new Verifications() {
			{
				userDao.getUserByIdList((List<Integer>) any);
				times = 1;
			}
		};
	}

	@Test
	public void testGetByUserNameLike() {
		userServiceImpl.getByUserNameLike("user");

		new Verifications() {
			{
				userDao.selectByUserNameLike((String) any);
				times = 1;
			}
		};
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testThawByIdList() {
		User admin = DefaultBOUtils.getUser(1, "admin",
				UserRoleType.INNER_ADMIN_USER.getId());
		Assert.assertNull(userServiceImpl.thawByIdList(null, admin));

		userServiceImpl.thawByIdList(Arrays.asList(1, 2, 3), admin);
		new Verifications() {
			{
				userDao.thawByIdList((List<Integer>) any);
				times = 1;

				userDao.getUserByIdList((List<Integer>) any);
				times = 1;
			}
		};
	}

	@Test(expected = BaseRuntimeException.class)
	public void testThawByListUpdateUserNull() {
		userServiceImpl.thawByIdList(Arrays.asList(1, 2, 3), null);
	}

	@Test(expected = BaseRuntimeException.class)
	public void testThawByListUpdateUserWrong() {
		userServiceImpl.thawByIdList(Arrays.asList(1, 2, 3),
				DefaultBOUtils.getUser(1, "notAdmin",
						UserRoleType.OUTSIDE_AUDIT_USER.getId()));
	}
	
	@Test
	public void testIsFrozen() {
		new NonStrictExpectations() {
			{
				userDao.getUserByName((String) any);
				User user = DefaultBOUtils.getUser(1, "user", UserRoleType.INNER_ADMIN_USER.getId());
				user.setLoginTryTimes(5);
				result = user;
			}
		};
		Assert.assertTrue(userServiceImpl.isFrozen("user"));
		
		
		new NonStrictExpectations() {
			{
				userDao.getUserByName((String) any);
				User user = DefaultBOUtils.getUser(1, "user", UserRoleType.INNER_ADMIN_USER.getId());
				user.setLoginTryTimes(0);
				result = user;
			}
		};
		Assert.assertFalse(userServiceImpl.isFrozen("user"));
		
		new NonStrictExpectations() {
			{
				userDao.getUserByName((String) any);
				result = null;
			}
		};
		Assert.assertTrue(userServiceImpl.isFrozen("user"));
		
		Assert.assertFalse(userServiceImpl.isFrozen(null));
	}
}
