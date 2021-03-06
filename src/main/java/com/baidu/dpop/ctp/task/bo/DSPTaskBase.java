/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.task.bo;

import java.util.Date;

public class DSPTaskBase implements java.io.Serializable {

    private static final long serialVersionUID = -6414515085946163348L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 创意ID
     */
    private Long adId;

    /**
     * 账户ID，此处作为group_id使用
     */
    private Long userId;

    /**
     * DSPid
     */
    private Long dspId;

    /**
     * 物料类型
     */
    private Integer wuliaoType;

    /**
     * 账户公司名称
     */
    private String nickname;

    /**
     * 账户公司网站
     */
    private String website;

    /**
     * 落地页URL
     */
    private String landingPage;

    /**
     * DSP名称
     */
    private String dspname;

    /**
     * 创意宽度
     */
    private Integer width;

    /**
     * 创意高度
     */
    private Integer height;

    /**
     * 创意二级行业，此处即三级行业的前两级
     */
    private Integer adTradeIdLevel2;

    /**
     * 创意三级行业
     */
    private Integer adTradeIdLevel3;

    /**
     * 原始标签
     */
    private String adTag;

    /**
     * 物料在UBMC中对应ID
     */
    private Integer mcId;

    /**
     * 物料在UBMC中的版本ID
     */
    private Integer mcVersionId;

    /**
     * 创意在平台上展现标注的优先级
     */
    private Integer priority;

    /**
     * 创意的可标注人（内部or外部）
     */
    private Integer moduserLevel;

    /**
     * 标注任务名称（区分不同数据对应的标注任务）
     */
    private String taskName;

    /**
     * 标注任务id
     */
    private Integer taskId;

    /**
     * 创意修改时间（回传北斗）
     */
    private Date chatime;

    /**
     * 新增时间
     */
    private Date addTime;

    /**
     * 新增人
     */
    private String addUser;

    /**
     * 二级优先级
     */
    private Integer secondPriority;

    /**
     * 任务类型
     */
    private Byte taskType;

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
     * 创意ID
     * 
     * @param adId the value for ad_id
     */
    public void setAdId(Long adId) {
        this.adId = adId;
    }

    /**
     * 创意ID
     * 
     * @return adId the value for ad_id
     */
    public Long getAdId() {
        return this.adId;
    }

    /**
     * 账户ID，此处作为group_id使用
     * 
     * @param userId the value for user_id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 账户ID，此处作为group_id使用
     * 
     * @return userId the value for user_id
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * DSPid
     * 
     * @param dspId the value for dsp_id
     */
    public void setDspId(Long dspId) {
        this.dspId = dspId;
    }

    /**
     * DSPid
     * 
     * @return dspId the value for dsp_id
     */
    public Long getDspId() {
        return this.dspId;
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
     * 账户公司名称
     * 
     * @param nickname the value for nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 账户公司名称
     * 
     * @return nickname the value for nickname
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * 账户公司网站
     * 
     * @param website the value for website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * 账户公司网站
     * 
     * @return website the value for website
     */
    public String getWebsite() {
        return this.website;
    }

    /**
     * 落地页URL
     * 
     * @param landingPage the value for landing_page
     */
    public void setLandingPage(String landingPage) {
        this.landingPage = landingPage;
    }

    /**
     * 落地页URL
     * 
     * @return landingPage the value for landing_page
     */
    public String getLandingPage() {
        return this.landingPage;
    }

    /**
     * DSP名称
     * 
     * @param dspname the value for dspname
     */
    public void setDspname(String dspname) {
        this.dspname = dspname;
    }

    /**
     * DSP名称
     * 
     * @return dspname the value for dspname
     */
    public String getDspname() {
        return this.dspname;
    }

    /**
     * 创意宽度
     * 
     * @param width the value for width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 创意宽度
     * 
     * @return width the value for width
     */
    public Integer getWidth() {
        return this.width;
    }

    /**
     * 创意高度
     * 
     * @param height the value for height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 创意高度
     * 
     * @return height the value for height
     */
    public Integer getHeight() {
        return this.height;
    }

    /**
     * 创意二级行业，此处即三级行业的前两级
     * 
     * @param adTradeIdLevel2 the value for ad_trade_id_level2
     */
    public void setAdTradeIdLevel2(Integer adTradeIdLevel2) {
        this.adTradeIdLevel2 = adTradeIdLevel2;
    }

    /**
     * 创意二级行业，此处即三级行业的前两级
     * 
     * @return adTradeIdLevel2 the value for ad_trade_id_level2
     */
    public Integer getAdTradeIdLevel2() {
        return this.adTradeIdLevel2;
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
     * 原始标签
     * 
     * @param adTag the value for ad_tag
     */
    public void setAdTag(String adTag) {
        this.adTag = adTag;
    }

    /**
     * 原始标签
     * 
     * @return adTag the value for ad_tag
     */
    public String getAdTag() {
        return this.adTag;
    }

    /**
     * 物料在UBMC中对应ID
     * 
     * @param mcId the value for mc_id
     */
    public void setMcId(Integer mcId) {
        this.mcId = mcId;
    }

    /**
     * 物料在UBMC中对应ID
     * 
     * @return mcId the value for mc_id
     */
    public Integer getMcId() {
        return this.mcId;
    }

    /**
     * 物料在UBMC中的版本ID
     * 
     * @param mcVersionId the value for mc_version_id
     */
    public void setMcVersionId(Integer mcVersionId) {
        this.mcVersionId = mcVersionId;
    }

    /**
     * 物料在UBMC中的版本ID
     * 
     * @return mcVersionId the value for mc_version_id
     */
    public Integer getMcVersionId() {
        return this.mcVersionId;
    }

    /**
     * 创意在平台上展现标注的优先级
     * 
     * @param priority the value for priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 创意在平台上展现标注的优先级
     * 
     * @return priority the value for priority
     */
    public Integer getPriority() {
        return this.priority;
    }

    /**
     * 创意的可标注人（内部or外部）
     * 
     * @param moduserLevel the value for moduser_level
     */
    public void setModuserLevel(Integer moduserLevel) {
        this.moduserLevel = moduserLevel;
    }

    /**
     * 创意的可标注人（内部or外部）
     * 
     * @return moduserLevel the value for moduser_level
     */
    public Integer getModuserLevel() {
        return this.moduserLevel;
    }

    /**
     * 标注任务名称（区分不同数据对应的标注任务）
     * 
     * @param taskName the value for task_name
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 标注任务名称（区分不同数据对应的标注任务）
     * 
     * @return taskName the value for task_name
     */
    public String getTaskName() {
        return this.taskName;
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
     * 创意修改时间（回传北斗）
     * 
     * @param chatime the value for chatime
     */
    public void setChatime(Date chatime) {
        this.chatime = chatime;
    }

    /**
     * 创意修改时间（回传北斗）
     * 
     * @return chatime the value for chatime
     */
    public Date getChatime() {
        return this.chatime;
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
     * 二级优先级
     * 
     * @param secondPriority the value for second_priority
     */
    public void setSecondPriority(Integer secondPriority) {
        this.secondPriority = secondPriority;
    }

    /**
     * 二级优先级
     * 
     * @return secondPriority the value for second_priority
     */
    public Integer getSecondPriority() {
        return this.secondPriority;
    }

    /**
     * 任务类型
     * 
     * @param taskType the value for task_type
     */
    public void setTaskType(Byte taskType) {
        this.taskType = taskType;
    }

    /**
     * 任务类型
     * 
     * @return taskType the value for task_type
     */
    public Byte getTaskType() {
        return this.taskType;
    }
}
