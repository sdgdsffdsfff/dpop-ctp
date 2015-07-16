
/*
* Copyright 2014 baidu dpop
* All right reserved.
*
*/
package com.baidu.dpop.ctp.statistics.bo;

import java.util.Date;

/**
* 
*/	
public class StatisticsDimHisBase implements java.io.Serializable{


   /**
    * 主键ID 
    */	
    private Long id;

   /**
    * 任务ID 
    */	
    private Integer taskId;

   /**
    * 美观度-高 
    */	
    private Integer highBeautyNum;

   /**
    * 美观度-中 
    */	
    private Integer normalBeautyNum;

   /**
    * 美观度-低 
    */	
    private Integer lowBeautyNum;

   /**
    * 低俗度-黑 
    */	
    private Integer blackVulgarNum;

   /**
    * 低俗度-灰 
    */	
    private Integer grayVulgarNum;

   /**
    * 低俗度-白 
    */	
    private Integer whiteVulgarNum;

   /**
    * 欺诈度-是 
    */	
    private Integer isCheatNum;

   /**
    * 欺诈度-否 
    */	
    private Integer notCheatNum;

   /**
    * 高危度-是 
    */	
    private Integer isHighDangerNum;

   /**
    * 高危度-否 
    */	
    private Integer notHighDangerNum;


	/**
    * 主键ID
	* @param id the value for id
    */	
	public void setId(Long id){
		this.id = id;
	}
	
	/**
    * 主键ID
	* @return id the value for id
    */
	public Long getId(){
		return this.id;
	}

	/**
    * 任务ID
	* @param taskId the value for task_id
    */	
	public void setTaskId(Integer taskId){
		this.taskId = taskId;
	}
	
	/**
    * 任务ID
	* @return taskId the value for task_id
    */
	public Integer getTaskId(){
		return this.taskId;
	}

	/**
    * 美观度-高
	* @param highBeautyNum the value for high_beauty_num
    */	
	public void setHighBeautyNum(Integer highBeautyNum){
		this.highBeautyNum = highBeautyNum;
	}
	
	/**
    * 美观度-高
	* @return highBeautyNum the value for high_beauty_num
    */
	public Integer getHighBeautyNum(){
		return this.highBeautyNum;
	}

	/**
    * 美观度-中
	* @param normalBeautyNum the value for normal_beauty_num
    */	
	public void setNormalBeautyNum(Integer normalBeautyNum){
		this.normalBeautyNum = normalBeautyNum;
	}
	
	/**
    * 美观度-中
	* @return normalBeautyNum the value for normal_beauty_num
    */
	public Integer getNormalBeautyNum(){
		return this.normalBeautyNum;
	}

	/**
    * 美观度-低
	* @param lowBeautyNum the value for low_beauty_num
    */	
	public void setLowBeautyNum(Integer lowBeautyNum){
		this.lowBeautyNum = lowBeautyNum;
	}
	
	/**
    * 美观度-低
	* @return lowBeautyNum the value for low_beauty_num
    */
	public Integer getLowBeautyNum(){
		return this.lowBeautyNum;
	}

	/**
    * 低俗度-黑
	* @param blackVulgarNum the value for black_vulgar_num
    */	
	public void setBlackVulgarNum(Integer blackVulgarNum){
		this.blackVulgarNum = blackVulgarNum;
	}
	
	/**
    * 低俗度-黑
	* @return blackVulgarNum the value for black_vulgar_num
    */
	public Integer getBlackVulgarNum(){
		return this.blackVulgarNum;
	}

	/**
    * 低俗度-灰
	* @param grayVulgarNum the value for gray_vulgar_num
    */	
	public void setGrayVulgarNum(Integer grayVulgarNum){
		this.grayVulgarNum = grayVulgarNum;
	}
	
	/**
    * 低俗度-灰
	* @return grayVulgarNum the value for gray_vulgar_num
    */
	public Integer getGrayVulgarNum(){
		return this.grayVulgarNum;
	}

	/**
    * 低俗度-白
	* @param whiteVulgarNum the value for white_vulgar_num
    */	
	public void setWhiteVulgarNum(Integer whiteVulgarNum){
		this.whiteVulgarNum = whiteVulgarNum;
	}
	
	/**
    * 低俗度-白
	* @return whiteVulgarNum the value for white_vulgar_num
    */
	public Integer getWhiteVulgarNum(){
		return this.whiteVulgarNum;
	}

	/**
    * 欺诈度-是
	* @param isCheatNum the value for is_cheat_num
    */	
	public void setIsCheatNum(Integer isCheatNum){
		this.isCheatNum = isCheatNum;
	}
	
	/**
    * 欺诈度-是
	* @return isCheatNum the value for is_cheat_num
    */
	public Integer getIsCheatNum(){
		return this.isCheatNum;
	}

	/**
    * 欺诈度-否
	* @param notCheatNum the value for not_cheat_num
    */	
	public void setNotCheatNum(Integer notCheatNum){
		this.notCheatNum = notCheatNum;
	}
	
	/**
    * 欺诈度-否
	* @return notCheatNum the value for not_cheat_num
    */
	public Integer getNotCheatNum(){
		return this.notCheatNum;
	}

	/**
    * 高危度-是
	* @param isHighDangerNum the value for is_high_danger_num
    */	
	public void setIsHighDangerNum(Integer isHighDangerNum){
		this.isHighDangerNum = isHighDangerNum;
	}
	
	/**
    * 高危度-是
	* @return isHighDangerNum the value for is_high_danger_num
    */
	public Integer getIsHighDangerNum(){
		return this.isHighDangerNum;
	}

	/**
    * 高危度-否
	* @param notHighDangerNum the value for not_high_danger_num
    */	
	public void setNotHighDangerNum(Integer notHighDangerNum){
		this.notHighDangerNum = notHighDangerNum;
	}
	
	/**
    * 高危度-否
	* @return notHighDangerNum the value for not_high_danger_num
    */
	public Integer getNotHighDangerNum(){
		return this.notHighDangerNum;
	}
}
