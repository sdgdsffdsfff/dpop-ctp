package com.baidu.dpop.ctp.adtag.bo;

import java.util.ArrayList;
import java.util.List;

public class TagItemList {
	
	private List<TagItem> itemList;
	private Integer defaultValue;
	
	public TagItemList(Integer defaultValue, TagItem... tagItems) {
		this.defaultValue = defaultValue;
		itemList = new ArrayList<TagItem>();
		for (TagItem item : tagItems) {
			itemList.add(item);
		}
	}
	
	public List<TagItem> getItemList() {
		return itemList;
	}
	
	public void setItemList(List<TagItem> itemList) {
		this.itemList = itemList;
	}
	
	public Integer getDefaultValue() {
		return defaultValue;
	}
	
	public void setDefaultValue(Integer defaultValue) {
		this.defaultValue= defaultValue;
	}
}
