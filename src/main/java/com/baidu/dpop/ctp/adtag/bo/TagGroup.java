package com.baidu.dpop.ctp.adtag.bo;

import java.util.Map;
import java.util.TreeMap;

public class TagGroup implements java.io.Serializable{

	
	private static final long serialVersionUID = -5622069496846096410L;
	
	private String name;
	private String text;
	private Map<String, String> tags;

	public TagGroup(String name, String text) {
		this.name = name;
		this.text = text;
	}

	public void put(String tag, String value) {
		if (tags == null) {
			tags = new TreeMap<String, String>();
		}
		tags.put(tag, value);
	}

	public String get(String tag) {
		if (tags == null) {
			return null;
		}
		
		return tags.get(tag);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Map<String, String> getTags() {
		return tags;
	}

	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}

}
