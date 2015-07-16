package com.baidu.dpop.ctp.review.vo;

import java.util.List;

/**
 * 审核详情列表页Item信息
 * 
 * @author cgd
 * @date 2015年3月29日 下午12:16:27
 */
public class ReviewInfoItem {

    private Long groupId; // 推广组ID
    private Long reviewGroupId; // 审核组ID（主键）
    private String taskName; // 所属标注任务名
    private Integer adCount; // 创意数
    private Integer tagUserId; // 标注人ID
    private String tagUser; // 标注人
    private Integer reviewUserId; // 审核人ID
    private String reviewUser; // 审核人

    // 审核有误对应的明细
    private List<WrongTagDetailItem> wrongTagInfoList; // 标注错误信息List
    private List<WrongTradeDetailItem> wrongTradeInfoList; // 行业错误信息List

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getAdCount() {
        return adCount;
    }

    public void setAdCount(Integer adCount) {
        this.adCount = adCount;
    }

    public String getTagUser() {
        return tagUser;
    }

    public void setTagUser(String tagUser) {
        this.tagUser = tagUser;
    }

    public String getReviewUser() {
        return reviewUser;
    }

    public void setReviewUser(String reviewUser) {
        this.reviewUser = reviewUser;
    }

    public List<WrongTagDetailItem> getWrongTagInfoList() {
        return wrongTagInfoList;
    }

    public void setWrongTagInfoList(List<WrongTagDetailItem> wrongTagInfoList) {
        this.wrongTagInfoList = wrongTagInfoList;
    }

    public List<WrongTradeDetailItem> getWrongTradeInfoList() {
        return wrongTradeInfoList;
    }

    public void setWrongTradeInfoList(List<WrongTradeDetailItem> wrongTradeInfoList) {
        this.wrongTradeInfoList = wrongTradeInfoList;
    }

    public Integer getTagUserId() {
        return tagUserId;
    }

    public void setTagUserId(Integer tagUserId) {
        this.tagUserId = tagUserId;
    }

    public Integer getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(Integer reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public Long getReviewGroupId() {
        return reviewGroupId;
    }

    public void setReviewGroupId(Long reviewGroupId) {
        this.reviewGroupId = reviewGroupId;
    }

}
