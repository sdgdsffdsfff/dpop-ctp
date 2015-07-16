/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.industrytype.bo;

public class IndustryTypeBase implements java.io.Serializable {

    private static final long serialVersionUID = -8670742567522458692L;

    /**
     * 完整id
     */
    private Integer fullId;

    /**
     * 第一级行业id
     */
    private Integer firstId;

    /**
     * 第一级行业名称
     */
    private String firstName;

    /**
     * 第二级行业id
     */
    private Integer secondId;

    /**
     * 第二级行业名称
     */
    private String secondName;

    /**
     * 完整名称
     */
    private String fullName;

    /**
     * 完整id
     * 
     * @param fullId the value for full_id
     */
    public void setFullId(Integer fullId) {
        this.fullId = fullId;
    }

    /**
     * 完整id
     * 
     * @return fullId the value for full_id
     */
    public Integer getFullId() {
        return this.fullId;
    }

    /**
     * 第一级行业id
     * 
     * @param firstId the value for first_id
     */
    public void setFirstId(Integer firstId) {
        this.firstId = firstId;
    }

    /**
     * 第一级行业id
     * 
     * @return firstId the value for first_id
     */
    public Integer getFirstId() {
        return this.firstId;
    }

    /**
     * 第一级行业名称
     * 
     * @param firstName the value for first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 第一级行业名称
     * 
     * @return firstName the value for first_name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * 第二级行业id
     * 
     * @param secondId the value for second_id
     */
    public void setSecondId(Integer secondId) {
        this.secondId = secondId;
    }

    /**
     * 第二级行业id
     * 
     * @return secondId the value for second_id
     */
    public Integer getSecondId() {
        return this.secondId;
    }

    /**
     * 第二级行业名称
     * 
     * @param secondName the value for second_name
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * 第二级行业名称
     * 
     * @return secondName the value for second_name
     */
    public String getSecondName() {
        return this.secondName;
    }

    /**
     * 完整名称
     * 
     * @param fullName the value for full_name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 完整名称
     * 
     * @return fullName the value for full_name
     */
    public String getFullName() {
        return this.fullName;
    }
}
