/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.task.bo;

import java.util.Date;

public class NewDSPTaskBase implements java.io.Serializable {

    private static final long serialVersionUID = 6005741529880987377L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 创意ID
     */
    private Long adId;

    /**
     * 账户ID
     */
    private Long userId;

    /**
     * 广告ID
     */
    private Long adverId;

    /**
     * 物料类型
     */
    private Integer wuliaoType;

    /**
     * 展示URL
     */
    private String showUrl;

    /**
     * 落地页URL
     */
    private String targetUrl;

    /**
     * 创意宽度
     */
    private Integer width;

    /**
     * 创意高度
     */
    private Integer height;

    /**
     * 创意标题
     */
    private String title;

    /**
     * 创意描述一
     */
    private String description;

    /**
     * 公司名称
     */
    private String company;

    /**
     * 公司网址
     */
    private String website;

    /**
     * 创意二级行业，此处即三级行业的前两级
     */
    private Integer adTradeIdLevel2;

    /**
     * 创意三级行业
     */
    private Integer adTradeIdLevel3;

    /**
     * 标注信息
     */
    private String adTag;

    /**
     * 图片显示url，只保存第一张，作缩略图显示用
     */
    private String url;

    /**
     * 版本号，回传即可
     */
    private Integer tagVersion;

    /**
     * 任务id
     */
    private Integer taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 创建时间
     */
    private Date createTime;

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
     * 账户ID
     * 
     * @param userId the value for user_id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 账户ID
     * 
     * @return userId the value for user_id
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * 广告ID
     * 
     * @param adverId the value for adver_id
     */
    public void setAdverId(Long adverId) {
        this.adverId = adverId;
    }

    /**
     * 广告ID
     * 
     * @return adverId the value for adver_id
     */
    public Long getAdverId() {
        return this.adverId;
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
     * 展示URL
     * 
     * @param showUrl the value for show_url
     */
    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }

    /**
     * 展示URL
     * 
     * @return showUrl the value for show_url
     */
    public String getShowUrl() {
        return this.showUrl;
    }

    /**
     * 落地页URL
     * 
     * @param targetUrl the value for target_url
     */
    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    /**
     * 落地页URL
     * 
     * @return targetUrl the value for target_url
     */
    public String getTargetUrl() {
        return this.targetUrl;
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
     * 创意标题
     * 
     * @param title the value for title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 创意标题
     * 
     * @return title the value for title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 创意描述一
     * 
     * @param description the value for description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 创意描述一
     * 
     * @return description the value for description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 公司名称
     * 
     * @param company the value for company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 公司名称
     * 
     * @return company the value for company
     */
    public String getCompany() {
        return this.company;
    }

    /**
     * 公司网址
     * 
     * @param website the value for website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * 公司网址
     * 
     * @return website the value for website
     */
    public String getWebsite() {
        return this.website;
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
     * 标注信息
     * 
     * @param adTag the value for ad_tag
     */
    public void setAdTag(String adTag) {
        this.adTag = adTag;
    }

    /**
     * 标注信息
     * 
     * @return adTag the value for ad_tag
     */
    public String getAdTag() {
        return this.adTag;
    }

    /**
     * 图片显示url，只保存第一张，作缩略图显示用
     * 
     * @param url the value for url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 图片显示url，只保存第一张，作缩略图显示用
     * 
     * @return url the value for url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 版本号，回传即可
     * 
     * @param tagVersion the value for tag_version
     */
    public void setTagVersion(Integer tagVersion) {
        this.tagVersion = tagVersion;
    }

    /**
     * 版本号，回传即可
     * 
     * @return tagVersion the value for tag_version
     */
    public Integer getTagVersion() {
        return this.tagVersion;
    }

    /**
     * 任务id
     * 
     * @param taskId the value for task_id
     */
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * 任务id
     * 
     * @return taskId the value for task_id
     */
    public Integer getTaskId() {
        return this.taskId;
    }

    /**
     * 任务名称
     * 
     * @param taskName the value for task_name
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 任务名称
     * 
     * @return taskName the value for task_name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * 创建时间
     * 
     * @param createTime the value for create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建时间
     * 
     * @return createTime the value for create_time
     */
    public Date getCreateTime() {
        return this.createTime;
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
