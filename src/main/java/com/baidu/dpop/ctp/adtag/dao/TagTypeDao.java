
/*
* Copyright 2014 baidu dpop
* All right reserved.
*
*/

package com.baidu.dpop.ctp.adtag.dao;

import java.util.List;
import java.util.Set;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.adtag.bo.TagType;

public interface TagTypeDao extends GenericMapperDao<TagType, Long> {
	
    // SELECT--------------------------------------------------------

    /**
     * 根据tradeId选出其相应的标签
     * 
     * @param tradeId 需要选择的tradeId
     * @return Set形式的标签集合
     */
    public Set<String> selectByTradeId(Integer tradeId);

    /**
     * 获取所有标签类型关联信息
     * 
     * @return 所有数据
     */
    public List<TagType> selectAll();

    // INSERT--------------------------------------------------------

    /**
     * 批量插入数据
     * 
     * @param list 插入的列表
     */
    public void batchInsertTagTypes(List<TagType> list);

    // UPDATE--------------------------------------------------------

    // DELETE--------------------------------------------------------
    
    /**
     * 根据id批量删除
     * @param list 要删除的id列表
     */
    public void deleteByIds(List<Long> list);
}
