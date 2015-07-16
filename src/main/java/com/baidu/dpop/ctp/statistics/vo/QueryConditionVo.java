package com.baidu.dpop.ctp.statistics.vo;

import org.springframework.util.Assert;

import com.baidu.dpop.ctp.common.constants.Constants;

/**
 * 统计相关查询条件
 * 
 * @author cgd
 * @date 2015年3月17日 下午1:25:28
 */
public class QueryConditionVo {

    private Integer taskId; // 任务ID
    private Integer pageNo; // 第几页
    private Integer pageSize; // 一page展示多少条记录
    private Integer order; // 排序方式
    private String orderBy; // 根据哪个字段排序
    private String groupBy; // desc or asc

    // 默认排序方式（group & ads 降序）
    private static final Integer DEFAULT_COMPARATOR = 0;

    // 已标注推广组 升序&降序
    private static final Integer GROUP_UP_COMPARATOR = 1;
    private static final Integer GROUP_DOWN_COMPARATOR = 2;

    // 已标注创意数 升序&降序
    private static final Integer ADS_UP_COMPARATOR = 3;
    private static final Integer ADS_DOWN_COMPARATOR = 4;

    // 排序方式（升序 & 降序）
    private static final String ASC_GROUP_BY = "asc";
    private static final String DESC_GROUP_BY = "desc";

    // 排序字段 - 已标注推广组数
    private static final String IS_DONE_GROUPS_ORDER_BY = "isDoneGroups";
    // 排序字段 - 已标注创意数
    private static final String IS_DONE_ADS_ORDER_BY = "isDoneAds";

    /**
     * 初始化
     * */
    public void init(Integer taskId, Integer size, Integer page, String orderBy, String groupBy) {
        Assert.notNull(taskId);

        this.setTaskId(taskId);

        if (size == null || size <= 0) {
            this.setPageSize(Constants.DEFAULT_PAGE_SIZE);
        } else {
            this.setPageSize(size);
        }
        if (page == null || page <= 0) {
            this.setPageNo(Constants.DEFAULT_PAGE_NUM);
        } else {
            this.setPageNo(page);
        }

        this.setOrder(orderBy, groupBy);
    }

    /**
     * 前端order到后端定义的order映射
     * */
    private void setOrder(String orderBy, String groupBy) {
        // 默认排序方法
        Integer tmpOrder = DEFAULT_COMPARATOR;
        if (orderBy == null || groupBy == null) {
            this.setOrder(tmpOrder);
        }

        // 设置排序方式
        if (IS_DONE_GROUPS_ORDER_BY.equals(orderBy) && ASC_GROUP_BY.equals(groupBy)) {
            tmpOrder = GROUP_UP_COMPARATOR;
        }
        if (IS_DONE_GROUPS_ORDER_BY.equals(orderBy) && DESC_GROUP_BY.equals(groupBy)) {
            tmpOrder = GROUP_DOWN_COMPARATOR;
        }

        if (IS_DONE_ADS_ORDER_BY.equals(orderBy) && ASC_GROUP_BY.equals(groupBy)) {
            tmpOrder = ADS_UP_COMPARATOR;
        }
        if (IS_DONE_ADS_ORDER_BY.equals(orderBy) && DESC_GROUP_BY.equals(groupBy)) {
            tmpOrder = ADS_DOWN_COMPARATOR;
        }

        this.setOrder(tmpOrder);
    }

    // --------------------------------------
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

}
