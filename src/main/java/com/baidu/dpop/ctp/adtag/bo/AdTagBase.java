/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.adtag.bo;

import java.util.Date;

public class AdTagBase implements java.io.Serializable {

    private static final long serialVersionUID = 8228907620564278736L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 关联的ad的主键id
     */
    private Long refId;

    /**
     * 关联group的主键id
     */
    private Long refGroupId;

    /**
     * 标注任务id
     */
    private Integer taskId;

    /**
     * 数据类型
     */
    private Integer dataType;

    /**
     * 标签
     */
    private String adTag;

    /**
     * 创意三级行业
     */
    private Integer adTradeIdLevel3;

    /**
     * 备注
     */
    private String comment;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 通用物料类型
     */
    private Integer generalWuliaoType;

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
     * 关联的ad的主键id
     * 
     * @param refId the value for ref_id
     */
    public void setRefId(Long refId) {
        this.refId = refId;
    }

    /**
     * 关联的ad的主键id
     * 
     * @return refId the value for ref_id
     */
    public Long getRefId() {
        return this.refId;
    }

    /**
     * 关联group的主键id
     * 
     * @param refGroupId the value for ref_group_id
     */
    public void setRefGroupId(Long refGroupId) {
        this.refGroupId = refGroupId;
    }

    /**
     * 关联group的主键id
     * 
     * @return refGroupId the value for ref_group_id
     */
    public Long getRefGroupId() {
        return this.refGroupId;
    }

    /**
     * 标注任务id
     * 
     * @param taskId the value for task_id
     */
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * 标注任务id
     * 
     * @return taskId the value for task_id
     */
    public Integer getTaskId() {
        return this.taskId;
    }

    /**
     * 数据类型
     * 
     * @param dataType the value for data_type
     */
    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    /**
     * 数据类型
     * 
     * @return dataType the value for data_type
     */
    public Integer getDataType() {
        return this.dataType;
    }

    /**
     * 标签
     * 
     * @param adTag the value for ad_tag
     */
    public void setAdTag(String adTag) {
        this.adTag = adTag;
    }

    /**
     * 标签
     * 
     * @return adTag the value for ad_tag
     */
    public String getAdTag() {
        return this.adTag;
    }

    /**
     * 创意三级行业
     * 
     * @param adTradeIdLevel3 the value for ad_trade_id_level3
     */
    public void setAdTradeIdLevel3(Integer adTradeIdLevel3) {
        this.adTradeIdLevel3 = adTradeIdLevel3;
    }

    /**
     * 创意三级行业
     * 
     * @return adTradeIdLevel3 the value for ad_trade_id_level3
     */
    public Integer getAdTradeIdLevel3() {
        return this.adTradeIdLevel3;
    }

    /**
     * 备注
     * 
     * @param comment the value for comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 备注
     * 
     * @return comment the value for comment
     */
    public String getComment() {
        return this.comment;
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
     * 通用物料类型
     * 
     * @param generalWuliaoType the value for general_wuliao_type
     */
    public void setGeneralWuliaoType(Integer generalWuliaoType) {
        this.generalWuliaoType = generalWuliaoType;
    }

    /**
     * 通用物料类型
     * 
     * @return generalWuliaoType the value for general_wuliao_type
     */
    public Integer getGeneralWuliaoType() {
        return this.generalWuliaoType;
    }

    /**
     * 是否入库成功
     * 
     * @param assigned the value for ASSIGNED
     */
    public void setAssigned(Byte assigned) {
        this.assigned = assigned;
    }

    /**
     * 是否入库成功
     * 
     * @return assigned the value for ASSIGNED
     */
    public Byte getAssigned() {
        return this.assigned;
    }
}
