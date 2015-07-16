package com.baidu.dpop.ctp.mainTask.vo;

public class PagedTaskQueryVo {

	private Integer page;
	private Integer size;
	private String orderBy;
	private String order;
	private Integer navigatePages;
	private Integer id;
	private String taskName;
	private String addUser;
	private Long beginTime;
	private Long endTime;
	private Byte status;

	public PagedTaskQueryVo() {
	};

	public PagedTaskQueryVo(Integer page, Integer size, String orderBy,
			String order, Integer navigatePages, Integer id, String taskName,
			String addUser, Long beginTime, Long endTime, Byte status) {
		this.page = page;
		this.size = size;
		this.orderBy = orderBy;
		this.order = order;
		this.navigatePages = navigatePages;
		this.id = id;
		this.taskName = taskName;
		this.addUser = addUser;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.status = status;

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

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getNavigatePages() {
		return navigatePages;
	}

	public void setNavigatePages(Integer navigatePages) {
		this.navigatePages = navigatePages;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public Long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
}
