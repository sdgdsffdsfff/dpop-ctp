package com.baidu.dpop.ctp.review.vo;

import org.springframework.util.Assert;

import com.baidu.dpop.ctp.common.constants.Constants;

/**
 * 查询条件
 * 
 * @author cgd
 * @date 2015年3月17日 下午1:25:28
 */
public class QueryConditionVo {
    
    /** 未审核tab **/
    public static final Integer NOT_REVIEW_TAB = 1;
    /** 审核正确tab **/
    public static final Integer REVIEW_RIGHT_TAB = 2;
    /** 审核有误tab **/
    public static final Integer REVIEW_WRONG_TAB = 3;

    private Integer reviewTaskId; // 任务ID
    private Integer tabType; // 0:未审核tab, 1：审核正确tab, 2:审核有误tab
    private Integer pageNo; // 第几页
    private Integer pageSize; // 一page展示多少条记录
    private Integer order; // 排序方式
    
    /**
     * 初始化
     * */
    public void init(Integer taskId, Integer tabType, Integer size, Integer page) {
        Assert.notNull(taskId);

        this.setReviewTaskId(taskId);
        
        if(tabType == null) {
            this.setTabType(NOT_REVIEW_TAB);
        } else {
            this.setTabType(tabType);
        }

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

    }

    // --------------------------------------
    public Integer getTabType() {
        return tabType;
    }

    public Integer getReviewTaskId() {
        return reviewTaskId;
    }

    public void setReviewTaskId(Integer reviewTaskId) {
        this.reviewTaskId = reviewTaskId;
    }

    public void setTabType(Integer tabType) {
        this.tabType = tabType;
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

}
