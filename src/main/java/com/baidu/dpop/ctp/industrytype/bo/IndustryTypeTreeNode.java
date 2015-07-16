package com.baidu.dpop.ctp.industrytype.bo;

import java.util.HashMap;
import java.util.Map;

/**
 * 前段查询行业数据时返回给前端的数据结构，三级行业以树形结构返回。 此数据结构是树叶节点
 * 
 * @author mading01
 * 
 */
public class IndustryTypeTreeNode implements java.io.Serializable {

    private static final long serialVersionUID = -7397060764068886343L;

    private Integer id;
    private String name;
    private Map<Integer, IndustryTypeTreeNode> data;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, IndustryTypeTreeNode> getData() {
        return data;
    }

    public void addChild(Integer cid, IndustryTypeTreeNode child) {
        if (this.data == null) {
            this.data = new HashMap<Integer, IndustryTypeTreeNode>();
        }
        this.data.put(cid, child);
    }
}
