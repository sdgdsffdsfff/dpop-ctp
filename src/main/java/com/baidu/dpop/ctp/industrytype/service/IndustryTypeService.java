/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.industrytype.service;

import java.util.List;
import java.util.Map;

import com.baidu.dpop.ctp.industrytype.bo.IndustryTypeTreeNode;
import com.baidu.dpop.ctp.industrytype.bo.NewIndustryType;

public interface IndustryTypeService {

    /**
     * 获取所有三级行业数据
     * 
     * @return 所有三级行业数据，以树的形式组织
     */
    public Map<Integer, IndustryTypeTreeNode> getLevelThreeIndustryType();

    /**
     * 获取所有二级行业数据
     * 
     * @return 所有二级行业数据，map形式，映射为full_id->full_name
     */
    public Map<Integer, String> getLevelTwoIndustryType();

    /**
     * 获取所有三级行业数据
     * 
     * @return 所有三级行业数据
     */
    public List<NewIndustryType> getLevel3Data();

    /**
     * 以map形式批量获取三级行业
     * 
     * @param list 三级行业full_id列表
     * @return map形式的结果,key为full_id
     */
    public Map<Integer, NewIndustryType> batchGetMapped(List<Integer> list);
    
    /**
     * 根据full_id批量获取数据
     * 
     * @param list full_id列表
     * @return 获取到的结果
     */
    public List<Integer> getAllFirstTradeType();

    /**
     * 批量更新三级行业数据，添加二级行业id与名称
     * 
     * @param list 添加列表，String形式
     */
    public void batchUpdateNewIndustryType(List<String> list);

}
