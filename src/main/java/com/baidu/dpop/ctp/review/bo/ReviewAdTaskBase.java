/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.review.bo;

import java.util.Date;

public class ReviewAdTaskBase implements java.io.Serializable {

    private static final long serialVersionUID = 3687269320631483690L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 数据类型
     */
    private Byte dataType;

    /**
     * 关联数据ID
     */
    private Long refAdId;

    /**
     * 推广组ID
     */
    private Long groupId;

    /**
     * 审核groupID
     */
    private Long groupIdReview;

    /**
     * 审核任务ID
     */
    private Long taskId;

    /**
     * 物料类型
     */
    private Integer wuliaoType;

    /**
     * 已标注创意三级行业
     */
    private Integer adTradeIdLevel3;

    /**
     * 审核注创意三级行业
     */
    private Integer adTradeIdLevel3Review;

    /**
     * 已标注标注信息
     */
    private String adTag;

    /**
     * 审核标注信息
     */
    private String adTagReview;

    /**
     * 已标注备注
     */
    private String comment;

    /**
     * 审核备注
     */
    private String commentReview;

    /**
     * 新增时间
     */
    private Date addTime;

    /**
     * 新增人
     */
    private String addUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 标注人
     */
    private String tagUser;

    /**
     * 标注时间
     */
    private Date tagTime;

    /**
     * 是否入库成功
     */
    private Byte assigned;

    /**
     * 主键ID
     * 
     * @param id the value for id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 主键ID
     * 
     * @return id the value for id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 数据类型
     * 
     * @param dataType the value for data_type
     */
    public void setDataType(Byte dataType) {
        this.dataType = dataType;
    }

    /**
     * 数据类型
     * 
     * @return dataType the value for data_type
     */
    public Byte getDataType() {
        return this.dataType;
    }

    /**
     * 关联数据ID
     * 
     * @param refAdId the value for ref_ad_id
     */
    public void setRefAdId(Long refAdId) {
        this.refAdId = refAdId;
    }

    /**
     * 关联数据ID
     * 
     * @return refAdId the value for ref_ad_id
     */
    public Long getRefAdId() {
        return this.refAdId;
    }

    /**
     * 推广组ID
     * 
     * @param groupId the value for group_id
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * 推广组ID
     * 
     * @return groupId the value for group_id
     */
    public Long getGroupId() {
        return this.groupId;
    }

    /**
     * 审核groupID
     * 
     * @param groupIdReview the value for group_id_review
     */
    public void setGroupIdReview(Long groupIdReview) {
        this.groupIdReview = groupIdReview;
    }

    /**
     * 审核groupID
     * 
     * @return groupIdReview the value for group_id_review
     */
    public Long getGroupIdReview() {
        return this.groupIdReview;
    }

    /**
     * 审核任务ID
     * 
     * @param taskId the value for task_id
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * 审核任务ID
     * 
     * @return taskId the value for task_id
     */
    public Long getTaskId() {
        return this.taskId;
    }

    /**
     * 物料类型
     * 
     * @param wuliaoType the value for wuliao_type
     */
    public void setWuliaoType(Integer wuliaoType) {
        this.wuliaoType = wuliaoType;
    }

    /**
     * 物料类型
     * 
     * @return wuliaoType the value for wuliao_type
     */
    public Integer getWuliaoType() {
        return this.wuliaoType;
    }

    /**
     * 已标注创意三级行业
     * 
     * @param adTradeIdLevel3 the value for ad_trade_id_level3
     */
    public void setAdTradeIdLevel3(Integer adTradeIdLevel3) {
        this.adTradeIdLevel3 = adTradeIdLevel3;
    }

    /**
     * 已标注创意三级行业
     * 
     * @return adTradeIdLevel3 the value for ad_trade_id_level3
     */
    public Integer getAdTradeIdLevel3() {
        return this.adTradeIdLevel3;
    }

    /**
     * 审核注创意三级行业
     * 
     * @param adTradeIdLevel3Review the value for ad_trade_id_level3_review
     */
    public void setAdTradeIdLevel3Review(Integer adTradeIdLevel3Review) {
        this.adTradeIdLevel3Review = adTradeIdLevel3Review;
    }

    /**
     * 审核注创意三级行业
     * 
     * @return adTradeIdLevel3Review the value for ad_trade_id_level3_review
     */
    public Integer getAdTradeIdLevel3Review() {
        return this.adTradeIdLevel3Review;
    }

    /**
     * 已标注标注信息
     * 
     * @param adTag the value for ad_tag
     */
    public void setAdTag(String adTag) {
        this.adTag = adTag;
    }

    /**
     * 已标注标注信息
     * 
     * @return adTag the value for ad_tag
     */
    public String getAdTag() {
        return this.adTag;
    }

    /**
     * 审核标注信息
     * 
     * @param adTagReview the value for ad_tag_review
     */
    public void setAdTagReview(String adTagReview) {
        this.adTagReview = adTagReview;
    }

    /**
     * 审核标注信息
     * 
     * @return adTagReview the value for ad_tag_review
     */
    public String getAdTagReview() {
        return this.adTagReview;
    }

    /**
     * 已标注备注
     * 
     * @param comment the value for comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 已标注备注
     * 
     * @return comment the value for comment
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * 审核备注
     * 
     * @param commentReview the value for comment_review
     */
    public void setCommentReview(String commentReview) {
        this.commentReview = commentReview;
    }

    /**
     * 审核备注
     * 
     * @return commentReview the value for comment_review
     */
    public String getCommentReview() {
        return this.commentReview;
    }

    /**
     * 新增时间
     * 
     * @param addTime the value for add_time
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 新增时间
     * 
     * @return addTime the value for add_time
     */
    public Date getAddTime() {
        return this.addTime;
    }

    /**
     * 新增人
     * 
     * @param addUser the value for add_user
     */
    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    /**
     * 新增人
     * 
     * @return addUser the value for add_user
     */
    public String getAddUser() {
        return this.addUser;
    }

    /**
     * 修改时间
     * 
     * @param updateTime the value for update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 修改时间
     * 
     * @return updateTime the value for update_time
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 修改人
     * 
     * @param updateUser the value for update_user
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 修改人
     * 
     * @return updateUser the value for update_user
     */
    public String getUpdateUser() {
        return this.updateUser;
    }

    /**
     * 标注人
     * 
     * @param tagUser the value for tag_user
     */
    public void setTagUser(String tagUser) {
        this.tagUser = tagUser;
    }

    /**
     * 标注人
     * 
     * @return tagUser the value for tag_user
     */
    public String getTagUser() {
        return this.tagUser;
    }

    /**
     * 标注时间
     * 
     * @param tagTime the value for tag_time
     */
    public void setTagTime(Date tagTime) {
        this.tagTime = tagTime;
    }

    /**
     * 标注时间
     * 
     * @return tagTime the value for tag_time
     */
    public Date getTagTime() {
        return this.tagTime;
    }

    /**
     * 是否入库成功
     * 
     * @param assigned the value for assigned
     */
    public void setAssigned(Byte assigned) {
        this.assigned = assigned;
    }

    /**
     * 是否入库成功
     * 
     * @return assigned the value for assigned
     */
    public Byte getAssigned() {
        return this.assigned;
    }
}
