package com.baidu.dpop.ctp.user.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baidu.dpop.ctp.common.constants.Constants;

/**
 * UserQueryVo
 * 
 * @author cgd
 * @date 2015年1月21日 下午5:35:44
 */
public class UserQueryVo implements Serializable {

	private static final long serialVersionUID = -4786956711789398705L;

	private Integer page;
	private Integer size;
	private String order;
	private String orderBy;
	private String roleType;
	private List<Byte> roleTypeList;
	private String userName;

	/**
	 * 查询参数初始化
	 * */
	public static UserQueryVo initialize(UserQueryVo queryVo) {
		if (queryVo == null) {
			queryVo = new UserQueryVo();
		}
		if (queryVo.getPage() == null) {
			queryVo.setPage(Constants.DEFAULT_PAGE_NUM);
		}
		if (queryVo.getSize() == null) {
			queryVo.setSize(Constants.DEFAULT_PAGE_SIZE);
		}
		if (queryVo.getOrder() == null) {
			queryVo.setOrder(Constants.DEFAULT_ORDER_BY);
		}
		if (queryVo.getOrderBy() == null) {
			queryVo.setOrderBy(Constants.DEFAULT_USER_ORDER_BY);
		}
		// 用户角色查询前后端切换
		if (queryVo.getRoleType() != null) {
			String[] roleArr = queryVo.getRoleType().split(",");
			List<Byte> roleList = new ArrayList<Byte>();
			for (String item : roleArr) {
				roleList.add(Byte.valueOf(item));
			}
			queryVo.setRoleTypeList(roleList);
		}

		return queryVo;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public List<Byte> getRoleTypeList() {
		return roleTypeList;
	}

	public void setRoleTypeList(List<Byte> roleTypeList) {
		this.roleTypeList = roleTypeList;
	}
}
