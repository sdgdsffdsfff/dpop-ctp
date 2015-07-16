/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.adtag.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baidu.dpop.ctp.adtag.bo.TagType;
import com.baidu.dpop.ctp.adtag.bo.TagTypeTreeNode;
import com.baidu.dpop.ctp.adtag.vo.TagTypeChangeResult;
import com.baidu.dpop.frame.core.base.GenericMapperService;

public interface TagTypeService extends GenericMapperService<TagType, Long> {

    /**
     * 根据tradeId选出其相应的标签
     * 
     * @param tradeId 需要选择的tradeId
     * @return Set形式的标签集合
     */
    public Set<String> getByTradeId(Integer tradeId);

    /**
     * 根据行业列表获取标签类型，取交集
     * 
     * @param tradeIds 行业列表
     * @param taskType 任务类型
     * @return 标签类型集合
     */
    public List<String> getByTradeIdList(List<Integer> tradeIds, Byte taskType);

    /**
     * 以树的形式获取所有类型关联数据
     * 
     * @return 所有类型关联数据
     */
    public Map<Integer, TagTypeTreeNode> getAll();

    /**
     * 获取TagType的全集数据，list类型
     * 
     * @return TagType的全集数据
     */
    public List<TagType> getAllList();

    /**
     * 批量插入
     * 
     * @param list 插入数据列表
     */
    public void batchInsertTagTypes(List<String> list);

    /**
     * 直接使用BO类进行批量插入
     * 
     * @param list 插入的数据列表
     */
    public void batchInsertTagTypesByBo(List<TagType> list);

    /**
     * 变换行业，并依据行业的变化生成标签列表
     * 
     * @param adTradeId 变化后的行业值
     * @return 重新生成的标签数据
     */
    public TagTypeChangeResult changeTradeType(Integer adTradeId, Integer taskType);

    /**
     * 根据id删除标签类型
     * 
     * @param ids 需要删除的id列表
     */
    public void deleteByIds(List<Long> ids);
}
