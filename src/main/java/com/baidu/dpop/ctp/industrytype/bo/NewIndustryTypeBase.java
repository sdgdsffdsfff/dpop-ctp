/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.industrytype.bo;

public class NewIndustryTypeBase implements java.io.Serializable {

    private static final long serialVersionUID = 1308254942505101125L;

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
     * 第三级行业id
     */
    private Integer thirdId;

    /**
     * 第三级行业名称
     */
    private String thirdName;

    /**
     * 完整名称
     */
    private String fullName;

    /**
     * 对应二级行业id
     */
    private Integer level2Id;

    /**
     * 对应二级行业名称
     */
    private String level2Name;

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
     * 第三级行业id
     * 
     * @param thirdId the value for third_id
     */
    public void setThirdId(Integer thirdId) {
        this.thirdId = thirdId;
    }

    /**
     * 第三级行业id
     * 
     * @return thirdId the value for third_id
     */
    public Integer getThirdId() {
        return this.thirdId;
    }

    /**
     * 第三级行业名称
     * 
     * @param thirdName the value for third_name
     */
    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    /**
     * 第三级行业名称
     * 
     * @return thirdName the value for third_name
     */
    public String getThirdName() {
        return this.thirdName;
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

    /**
     * 对应二级行业id
     * 
     * @param level2Id the value for level2_id
     */
    public void setLevel2Id(Integer level2Id) {
        this.level2Id = level2Id;
    }

    /**
     * 对应二级行业id
     * 
     * @return level2Id the value for level2_id
     */
    public Integer getLevel2Id() {
        return this.level2Id;
    }

    /**
     * 对应二级行业名称
     * 
     * @param level2Name the value for level2_name
     */
    public void setLevel2Name(String level2Name) {
        this.level2Name = level2Name;
    }

    /**
     * 对应二级行业名称
     * 
     * @return level2Name the value for level2_name
     */
    public String getLevel2Name() {
        return this.level2Name;
    }
}
