package com.baidu.dpop.ctp.user.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import com.baidu.dpop.ctp.base.DefaultBOUtils;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.operationRecord.service.OperationRecordService;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.constant.UserRoleType;
import com.baidu.dpop.ctp.user.service.UserService;
import com.baidu.dpop.ctp.user.vo.PassForm;
import com.baidu.dpop.ctp.user.vo.UserQueryVo;
import com.baidu.dpop.frame.core.base.web.JsonResult;

public class UserControllerTest {

	@Tested
	private UserController userController;

	@Injectable
	private UserService userService;

	@Injectable
	private OperationRecordService operationRecordService;

	@Before
	public void setUp() {
		new NonStrictExpectations() {
			{
				operationRecordService.insertNewOperation((Integer) any,
						(String) any, (Long) any);
			}
		};
	}

	@Test
	public void testFind() {
		new NonStrictExpectations() {
			{
				userService.findById((Long) any);
				result = DefaultBOUtils.getUser(1, "mading01",
						UserRoleType.INNER_ADMIN_USER.getId());
			}
		};
		JsonResult result = userController.find(1L);
		Assert.assertEquals(result.getResultInfo(), "查找成功");
	}

	@Test
	public void testGetUserInfoListSuccess() {
		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = DefaultBOUtils.getUser(1, "mading01",
						UserRoleType.INNER_ADMIN_USER.getId());

				userService.getPageUserList((UserQueryVo) any);
				List<User> list = new ArrayList<User>();
				for (int i = 0; i < 5; i++) {
					list.add(DefaultBOUtils.getUser(100 + i, "user" + i,
							UserRoleType.OUTSIDE_TAG_USER.getId()));
				}
				result = list;
			}
		};
		JsonResult r = userController.getUserInfoList(new UserQueryVo());
		Assert.assertNotNull(r);
		Assert.assertEquals("true", r.getSuccess());
	}

	@Test
	public void testGetUserInfoListWrong() {
		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = DefaultBOUtils.getUser(1, "mading01",
						UserRoleType.INNER_AUDIT_USER.getId());
			}
		};
		JsonResult r = userController.getUserInfoList(new UserQueryVo());
		Assert.assertNotNull(r);
		Assert.assertEquals("false", r.getSuccess());
		Assert.assertEquals("只有管理员才能使用此操作", r.getResultInfo());

		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = DefaultBOUtils.getUser(1, "mading01",
						UserRoleType.INNER_ADMIN_USER.getId());

				userService.getPageUserList((UserQueryVo) any);
				result = new Exception("wrong!");
			}
		};
		r = userController.getUserInfoList(new UserQueryVo());
		Assert.assertNotNull(r);
		Assert.assertEquals("false", r.getSuccess());
		Assert.assertEquals("获取用户信息列表失败, 原因: wrong!", r.getResultInfo());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCreateSuccess() {
		new NonStrictExpectations() {
			{
				userService.createUsers((List<String>) any, (User) any,
						(Byte) any, (String) any);
				List<User> list = new ArrayList<User>();
				for (int i = 0; i < 5; i++) {
					list.add(DefaultBOUtils.getUser(100 + i, "user" + i,
							UserRoleType.OUTSIDE_TAG_USER.getId()));
				}
				result = list;

				userService.getCurrentLoginUser();
				result = DefaultBOUtils.getUser(1, "mading01",
						UserRoleType.INNER_ADMIN_USER.getId());
			}
		};

		List<String> names = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			names.add("user_" + i);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", names);
		map.put("password", "123456");
		map.put("roleType", UserRoleType.OUTSIDE_AUDIT_USER.getId());
		JsonResult r = userController.create(map);
		Assert.assertNotNull(r);
		Assert.assertEquals("true", r.getSuccess());
		Assert.assertEquals(5, ((List<User>) r.getData()).size());

		map = new HashMap<String, Object>();
		map.put("userName", names);
		map.put("roleType", UserRoleType.OUTSIDE_AUDIT_USER.getId());
		r = userController.create(map);
		Assert.assertNotNull(r);
		Assert.assertEquals("true", r.getSuccess());
		Assert.assertEquals(5, ((List<User>) r.getData()).size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCreateWrong() {
		
		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = DefaultBOUtils.getUser(1, "mading01",
						UserRoleType.INNER_ADMIN_USER.getId());

				userService.createUsers((List<String>) any, (User) any,
						(Byte) any, (String) any);
				result = null;
			}
		};

		List<String> names = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			names.add("user_" + i);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", names);
		map.put("password", "123456");
		map.put("roleType", UserRoleType.OUTSIDE_AUDIT_USER.getId());
		JsonResult r = userController.create(map);
		Assert.assertNotNull(r);
		Assert.assertEquals("false", r.getSuccess());

		new NonStrictExpectations() {
			{
				userService.createUsers((List<String>) any, (User) any,
						(Byte) any, (String) any);
				result = new Exception("test");
			}
		};
		r = userController.create(map);
		Assert.assertNotNull(r);
		Assert.assertEquals("false", r.getSuccess());
		Assert.assertEquals("创建用户失败, 原因: test", r.getResultInfo());
		
		// 角色类型错误
		map.put("roleType", 1000);
		Assert.assertEquals("添加错误：错误的用户类型", userController.create(map).getResultInfo());
		
		new NonStrictExpectations() {
			{
				userService.createUsers((List<String>) any, (User) any,
						(Byte) any, (String) any);
				result = null;
			}
		};
		map.put("roleType", UserRoleType.OUTSIDE_AUDIT_USER.getId());
		Assert.assertEquals("创建用户失败! 数据库错误", userController.create(map).getResultInfo());
	}

	@Test
	public void testUpdateSuccess() {
		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = DefaultBOUtils.getUser(1, "mading01",
						UserRoleType.INNER_ADMIN_USER.getId());

				userService.updateUser((Integer) any, (Byte) any, (String) any,
						(User) any);
				User u = DefaultBOUtils.getUser(1, "mading01",
						UserRoleType.INNER_ADMIN_USER.getId());
				u.setPassword("123");
				result = new User[] { u, u };

				operationRecordService.insertNewOperation((Integer) any,
						(String) any, (Long) any);
			}
		};
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1L);
		map.put("password", "123456");
		map.put("roleType", UserRoleType.OUTSIDE_TAG_USER.getId());
		JsonResult r = userController.update(map);
		Assert.assertNotNull(r);
		Assert.assertEquals("true", r.getSuccess());

		map = new HashMap<String, Object>();
		map.put("id", 1L);
		map.put("roleType", UserRoleType.OUTSIDE_TAG_USER.getId());
		r = userController.update(map);
		Assert.assertNotNull(r);
		Assert.assertEquals("true", r.getSuccess());
	}

	@Test
	public void testUpdateWrong() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1L);
		map.put("password", "123456");
		map.put("roleType", UserRoleType.OUTSIDE_TAG_USER.getId());

		new NonStrictExpectations() {
			{
				userService.updateUser((Integer) any, (Byte) any, (String) any,
						(User) any);
				result = null;
			}
		};

		JsonResult r = userController.update(map);
		Assert.assertNotNull(r);
		Assert.assertEquals("false", r.getSuccess());
		Assert.assertEquals("更新用户信息失败", r.getResultInfo());

		new NonStrictExpectations() {
			{
				userService.updateUser((Integer) any, (Byte) any, (String) any,
						(User) any);
				result = new Exception("test");
			}
		};

		r = userController.update(map);
		Assert.assertNotNull(r);
		Assert.assertEquals("false", r.getSuccess());
		Assert.assertEquals("更新用户信息失败, 原因: test", r.getResultInfo());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetCurrentUserInfo() {

		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = DefaultBOUtils.getUser(1, "mading",
						UserRoleType.OUTSIDE_AUDIT_USER.getId());
			}
		};

		// 获取到的情况
		JsonResult result = userController.getCurrentUserInfo();
		Map<String, String> map = (Map<String, String>) result.getData();
		Assert.assertNotNull(map);
		Assert.assertTrue(map.containsKey("userName"));
		Assert.assertEquals(map.get("userName"), "mading");
	}

	@Test
	public void testGetCurrentUserInfoException() {

		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = new Exception("test");
			}
		};

		// 获取到的情况
		JsonResult result = userController.getCurrentUserInfo();
		Assert.assertNotNull(result);
		Assert.assertEquals("false", result.getSuccess());
		Assert.assertEquals("获取当前登录用户失败, 原因: test", result.getResultInfo());
	}

	@Test
	public void testGetCurrentUserInfoNull() {
		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = null;
			}
		};
		JsonResult result = userController.getCurrentUserInfo();
		Assert.assertEquals(result.getData(), null);
	}

	@Test
	public void testModifyUserPwd() {

		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = DefaultBOUtils.getUser(1, "mading",
						UserRoleType.OUTSIDE_AUDIT_USER.getId());

				userService.updatePassword(anyString, anyString, anyString,
						(User) any);
				Map<String, String> map = new HashMap<String, String>();
				map.put("result", "TRUE");
				map.put("info",
						"User mading has successfully updated his password.");
				result = map;
			}
		};

		PassForm form = new PassForm();
		form.setNewAgainPass("123456");
		form.setNewPass("123456");
		form.setOldPass("111111");
		JsonResult result = userController.modifyUserPwd(form);
		Assert.assertEquals("success", result.getResultInfo());
	}

	@Test
	public void testModifyUserPwdNull() {

		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = null;
			}
		};

		PassForm form = new PassForm();
		form.setNewAgainPass("123456");
		form.setNewPass("123456");
		form.setOldPass("111111");
		JsonResult result = userController.modifyUserPwd(form);
		Assert.assertEquals("false", result.getSuccess());
		Assert.assertEquals("获取当前登录用户失败!", result.getResultInfo());

		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = DefaultBOUtils.getUser(1, "mading",
						UserRoleType.OUTSIDE_AUDIT_USER.getId());

				userService.updatePassword(anyString, anyString, anyString,
						(User) any);
				result = new BaseRuntimeException("更新密码失败");
			}
		};

		result = userController.modifyUserPwd(form);
		Assert.assertEquals("false", result.getSuccess());
		Assert.assertEquals("更新密码失败", result.getResultInfo());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testDeleteUser() {
		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = DefaultBOUtils.getUser(1, "mading",
						UserRoleType.INNER_ADMIN_USER.getId());

				userService.deleteByIdList((List<Integer>) any, (User) any);

				userService.getUserByIdList((List<Integer>) any);
				List<User> list = new ArrayList<User>();
				list.add(DefaultBOUtils.getUser(3, "user1",
						UserRoleType.INNER_AUDIT_USER.getId()));
				result = list;

				operationRecordService.insertNewOperation((Integer) any,
						(String) any, (Long) any);
			}
		};

		List<Integer> list = new ArrayList<Integer>();
		list.add(3);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIdList", list);
		JsonResult r = userController.delete(map);
		Assert.assertTrue(r.isHasSuccess());
	}
	
	@Test
	public void testDeleteWrong() {

		Map<String, Object> map = new HashMap<String, Object>();
		JsonResult r = userController.delete(map);
		Assert.assertFalse(r.isHasSuccess());
		Assert.assertEquals("用户列表为空", r.getResultInfo());
		
		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = new Exception("test");
			}
		};
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		map.put("userIdList", list);
		
		r = userController.delete(map);
		Assert.assertFalse(r.isHasSuccess());
		Assert.assertEquals("删除用户失败", r.getResultInfo());
	}
	
	@Test
	public void testGetUserByNameLike() {
		new NonStrictExpectations() {
			{
				userService.getByUserNameLike((String) any);
				List<User> users = new ArrayList<User>();
				for (int i = 0; i < 5; i++) {
					users.add(DefaultBOUtils.getUser(i, "user" + i, UserRoleType.INNER_ADMIN_USER.getId()));
				}
				result = users;
			}
		};
		
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>)userController.getUserByNameLike("user").getData();
		Assert.assertEquals(5,list.size());
		
		
		new NonStrictExpectations() {
			{
				userService.getByUserNameLike((String) any);
				result = new Exception("test");
			}
		};
		Assert.assertEquals("获取失败", userController.getUserByNameLike("user").getResultInfo());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testThawByIdList() {
		final List<User> list = new ArrayList<User>();
		list.add(DefaultBOUtils.getUser(100, "user100", UserRoleType.OUTSIDE_AUDIT_USER.getId()));
		
		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = DefaultBOUtils.getUser(1, "user1", UserRoleType.INNER_ADMIN_USER.getId());
				
				userService.thawByIdList((List<Integer>) any, (User) any);
				result = list;
				
				operationRecordService.insertNewOperation((Integer) any, (String) any, (Long) any);
			}
		};
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIdList", Arrays.asList(1));
		Assert.assertTrue(userController.thawByIdList(map).isHasSuccess());
		
		map.put("userIdList", null);
		Assert.assertEquals("用户列表为空", userController.thawByIdList(map).getResultInfo());
		
		new NonStrictExpectations() {
			{
				userService.getCurrentLoginUser();
				result = DefaultBOUtils.getUser(1, "user1", UserRoleType.INNER_ADMIN_USER.getId());
				
				userService.thawByIdList((List<Integer>) any, (User) any);
				result = new Exception();
				
				operationRecordService.insertNewOperation((Integer) any, (String) any, (Long) any);
			}
		};
		
		map.put("userIdList", Arrays.asList(1));
		Assert.assertEquals("解冻用户失败", userController.thawByIdList(map).getResultInfo());
		
	}
}
