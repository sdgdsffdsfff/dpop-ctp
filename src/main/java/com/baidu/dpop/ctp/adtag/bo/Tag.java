package com.baidu.dpop.ctp.adtag.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;

public class Tag {
	
	private Map<String, TagGroup> map;
	
	public Tag() {
	    if (map == null) {
            map = new HashMap<String, TagGroup>();
        }
	}
	
	public Map<String, TagGroup> getMap() {
		return map;
	}
	
	public void put(GeneralTag tag, String value) {
	    
		TagGroup group = map.get(tag.getGroup());
		if (group == null) {
			group = AdTagUtils.getGroup(tag.getGroup());
			map.put(group.getName(), group);
		}
		
		group.put(tag.getName(), value);
	}
	
	public String get(GeneralTag tag) {
		if (map == null) {
			return null;
		}
		
		TagGroup group = map.get(tag.getGroup());
		if (group == null) {
			return null;
		}
		
		return group.get(tag.getName());
	}
	
	public static Tag fromList(List<TagGroup> list) {
		Tag tag = new Tag();
		Map<String, TagGroup> map = new HashMap<String, TagGroup>();
		for (TagGroup group : list) {
			map.put(group.getName(), group);
		}
		tag.map = map;
		return tag;
	}
}
