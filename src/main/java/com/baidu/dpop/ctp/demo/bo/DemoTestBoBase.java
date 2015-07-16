/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.demo.bo;

import java.util.Date;

public class DemoTestBoBase implements java.io.Serializable {

    private static final long serialVersionUID = -8303959897462057160L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String demoName;

    /**
     * 点赞时间
     */
    private Date addTime;

    /**
     * 主键
     * 
     * @param id the value for id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 主键
     * 
     * @return id the value for id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 名称
     * 
     * @param demoName the value for demo_name
     */
    public void setDemoName(String demoName) {
        this.demoName = demoName;
    }

    /**
     * 名称
     * 
     * @return demoName the value for demo_name
     */
    public String getDemoName() {
        return this.demoName;
    }

    /**
     * 点赞时间
     * 
     * @param addTime the value for add_time
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 点赞时间
     * 
     * @return addTime the value for add_time
     */
    public Date getAddTime() {
        return this.addTime;
    }
}
