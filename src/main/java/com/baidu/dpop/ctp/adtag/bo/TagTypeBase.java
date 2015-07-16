/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.adtag.bo;

public class TagTypeBase implements java.io.Serializable {

    private static final long serialVersionUID = -572365448781407298L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 行业id，一级行业后4位为0，二级行业后2位为0
     */
    private Integer tradeId;

    /**
     * 标签名称
     */
    private String tagType;

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
     * 行业id，一级行业后4位为0，二级行业后2位为0
     * 
     * @param tradeId the value for trade_id
     */
    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 行业id，一级行业后4位为0，二级行业后2位为0
     * 
     * @return tradeId the value for trade_id
     */
    public Integer getTradeId() {
        return this.tradeId;
    }

    /**
     * 标签名称
     * 
     * @param tagType the value for tag_type
     */
    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    /**
     * 标签名称
     * 
     * @return tagType the value for tag_type
     */
    public String getTagType() {
        return this.tagType;
    }
}
