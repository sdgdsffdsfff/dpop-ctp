/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.review.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baidu.dpop.ctp.adtag.bo.GeneralTag;
import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;

public class ReviewTaskCondition extends ReviewTaskConditionBase {

	private static final long serialVersionUID = 161393743752080568L;
	
	private Map<String, List<String>> tagValue;
	
	public Map<String, List<String>> getTagValue() {
		return tagValue;
	}
	
	public void setTagValue(Map<String, List<String>> tagValue) {
		this.tagValue = tagValue;
	}
	
	public List<Integer> getGeneralWuliaoType() {
		List<Integer> result = new ArrayList<Integer>();
		if (getWuliaoType() != null) {
			String[] wls = getWuliaoType().split(",");
			for (String wl : wls) {
				result.add(Integer.valueOf(wl));
			}
		}
		return result;
	}
	
	public void setAdTradeType(List<Integer> list) {

		StringBuilder level1 = new StringBuilder("");
		StringBuilder level2 = new StringBuilder("");
		StringBuilder level3 = new StringBuilder("");

		for (int i : list) {
			if (i < 100) {
				level1.append(i + ",");
			} else if (i < 10000) {
				level2.append(i + ",");
			} else {
				level3.append(i + ",");
			}
		}

		if (level1.length() > 1) {
			level1.deleteCharAt(level1.length() - 1);
		}

		if (level2.length() > 1) {
			level2.deleteCharAt(level2.length() - 1);
		}

		if (level3.length() > 1) {
			level3.deleteCharAt(level3.length() - 1);
		}

		this.setAdTradeType(level1.toString() + ";" + level2.toString() + ";"
				+ level3.toString());
	}

	public String getAdTradeLevel(int i) {
		if (this.getAdTradeType() == null || this.getAdTradeType().length() < 1
				|| i < 1 || i > 3) {
			return null;
		}
		String[] temp = this.getAdTradeType().split(";");
		if (temp.length < i) {
			return null;
		}
		return temp[i - 1].length() == 0 ? null : temp[i - 1];
	}

	public void genAdTagCondition() {
		if (this.tagValue == null) {
			this.tagValue = new HashMap<String, List<String>>();
		}
		
		StringBuilder sb = new StringBuilder();
		for (GeneralTag tag : AdTagUtils.TAGLIST) {
			List<String> list = tagValue.get(tag.getName());
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					sb.append(i == list.size() - 1 ? list.get(i) + ";" : (list.get(i) + ","));
				}
			} else {
				sb.append("0;");
			}
		}
		sb.deleteCharAt(sb.lastIndexOf(";"));
		super.setAdTagCondition(sb.toString());
	}
	
	public Map<Integer, List<String>> getTagInfos() {
		if (tagValue == null) {
			return null;
		}
		
		Map<Integer, List<String>> result = new HashMap<Integer, List<String>>();
		for (GeneralTag tag : AdTagUtils.TAGLIST) {
			if (tagValue.containsKey(tag.getName())) {
				result.put(tag.getIndex() + 1, tagValue.get(tag.getName()));
			}
		}
		return result.isEmpty() ? null : result;
	}
}