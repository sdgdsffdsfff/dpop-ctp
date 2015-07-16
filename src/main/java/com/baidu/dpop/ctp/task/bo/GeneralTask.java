package com.baidu.dpop.ctp.task.bo;

import java.util.Map;

import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.bo.TagInfo;
import com.baidu.dpop.ctp.adtag.bo.TagTypeTreeNode;

public interface GeneralTask {

	/**
	 * 根据一个数据行设置BO的各项属性
	 * 
	 * @param line
	 *            数据行
	 */
	public void setArgs(String line);
	
	// 以下为任务的getter函数，包含了各项可能需要的内容-------------------------------------
	
	public Number getId();

	public Number getAdId();
	
	public Number getGroupId();
	
	public Number getTaskId();
	
	public Byte getTaskType();
	
	public Long getGeneralPriority();
	
	public Number getModuserLevel();
	
	public Number getDataType();
	
	public Number getWuliaoType();
	
	public Number getGeneralWuliaoType();
	
	public Number getAdTradeIdLevel2();

	public Number getAdTradeIdLevel3();
	
	public Number getUserId();

	public Number getMcId();

	public Number getMcVersionId();
	
	public Number getWidth();
	
	public Number getHeight();
	
	// --------------------------------------------------------------------------
	
	public String getAdTradeNameLevel2();
	
	public String getAdTradeNameLevel3();
	
	public String getCompany();

	public String getWebsite();

	public String getTaskName();

	public String getTargetUrl();

	public String getShowUrl();

	public String getTitle();

	public String getDescription1();

	public String getDescription2();
	
	public Integer getTagVersion();
	
	
	// 一下是一些特殊功能 ------------------------------------------------------------
	
	public TagInfo genTagInfo();
	
	public void transformAdTag(Map<Integer, TagTypeTreeNode> map);

	public void setAdTagNew(AdTag adTag);
	
	public String genTagedString();
	
	public String genFullString(Number taskType);
}
