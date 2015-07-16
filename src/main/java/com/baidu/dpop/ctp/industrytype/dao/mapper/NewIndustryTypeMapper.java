/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.industrytype.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.industrytype.bo.NewIndustryType;

public interface NewIndustryTypeMapper extends GenericMapper<NewIndustryType, Long> {

    // SELECT--------------------------------------------------------

    /**
     * 获取所有三级行业数据
     * 
     * @return 所有三级行业数据
     */
    public List<NewIndustryType> selectLevel3Data();

    /**
     * 根据full_id批量获取数据
     * 
     * @param list full_id列表
     * @return 获取到的结果
     */
    public List<NewIndustryType> batchSelect(List<Integer> list);

    /**
     * 选择所有除了未标注意外的一级行业id
     * 
     * @return 一级行业id列表
     */
    public List<Integer> selectAllFirstTradeType();

    // INSERT--------------------------------------------------------

    // UPDATE--------------------------------------------------------

    /**
     * 批量更新三级行业数据，为其添加对应的二级行业
     * 
     * @param list 更新的数据
     */
    public void batchUpdateLevel2IdAndName(List<NewIndustryType> list);

    // DELETE--------------------------------------------------------

}