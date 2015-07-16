package com.baidu.dpop.ctp.adtag.bo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TagTypeTreeNode {
	
	private Set<String> names;
	private Integer id;
	private Map<Integer, TagTypeTreeNode> children;
	
	public Set<String> getNames() {
		return names;
	}
	
	public void setNames(Set<String> names) {
		this.names = names;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Map<Integer, TagTypeTreeNode> getChildren() {
		return children;
	}
	
	public void setChildren(Map<Integer, TagTypeTreeNode> children) {
		this.children = children;
	}
	
	public void putChild(Integer id, TagTypeTreeNode child) {
		if (children == null) {
			children = new HashMap<Integer, TagTypeTreeNode>();
		}
		
		children.put(id, child);
	}
	
	public TagTypeTreeNode getChild(Integer id) {
		if (children == null) {
			return null;
		}
		
		return children.get(id);
	}
	
	public void addName(String name) {
		if (names == null) {
			names = new HashSet<String>();
		}
		
		names.add(name);
	}
	
	public static Set<String> getType(Integer tradeId, Map<Integer, TagTypeTreeNode> tree) {
		Set<String> result = new HashSet<String>();
		
		Integer first = (tradeId / 10000) * 10000;
		Integer second = (tradeId / 100) * 100;
		Integer third = tradeId;
		int[] ids = new int[]{first, second, third};
		int now = 0;
		
		TagTypeTreeNode node = tree.get(ids[now]);
		
		while (node != null && now < ids.length) {
			if (node.getNames() != null) {
				result.addAll(node.getNames());
			}
			
			now++;
			node = node.getChild(ids[now]);
		}

		return result;
	}
	
	public static void main(String... args) {
		Set<String> a =  new HashSet<String>();
		a.addAll(null);
	}
}
