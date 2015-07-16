
/*
* Copyright 2014 baidu dpop
* All right reserved.
*
*/
package com.baidu.dpop.ctp.review.bo;

import java.util.Date;

/**
* 
*/	
public class ReviewTaskConditionBase implements java.io.Serializable{


   /**
    * 主键 
    */	
    private Integer id;

   /**
    * 任务数据类型 
    */	
    private Integer dataType;

   /**
    * tag信息 
    */	
    private String adTagCondition;

   /**
    * 物料类型，使用逗号 
    */	
    private String wuliaoType;

   /**
    * 三级行业类型，可多选，用逗号分隔 
    */	
    private String adTradeType;


	/**
    * 主键
	* @param id the value for id
    */	
	public void setId(Integer id){
		this.id = id;
	}
	
	/**
    * 主键
	* @return id the value for id
    */
	public Integer getId(){
		return this.id;
	}

	/**
    * 任务数据类型
	* @param dataType the value for data_type
    */	
	public void setDataType(Integer dataType){
		this.dataType = dataType;
	}
	
	/**
    * 任务数据类型
	* @return dataType the value for data_type
    */
	public Integer getDataType(){
		return this.dataType;
	}

	/**
    * tag信息
	* @param adTagCondition the value for ad_tag_condition
    */	
	public void setAdTagCondition(String adTagCondition){
		this.adTagCondition = adTagCondition;
	}
	
	/**
    * tag信息
	* @return adTagCondition the value for ad_tag_condition
    */
	public String getAdTagCondition(){
		return this.adTagCondition;
	}

	/**
    * 物料类型，使用逗号
	* @param wuliaoType the value for wuliao_type
    */	
	public void setWuliaoType(String wuliaoType){
		this.wuliaoType = wuliaoType;
	}
	
	/**
    * 物料类型，使用逗号
	* @return wuliaoType the value for wuliao_type
    */
	public String getWuliaoType(){
		return this.wuliaoType;
	}

	/**
    * 三级行业类型，可多选，用逗号分隔
	* @param adTradeType the value for ad_trade_type
    */	
	public void setAdTradeType(String adTradeType){
		this.adTradeType = adTradeType;
	}
	
	/**
    * 三级行业类型，可多选，用逗号分隔
	* @return adTradeType the value for ad_trade_type
    */
	public String getAdTradeType(){
		return this.adTradeType;
	}
}
