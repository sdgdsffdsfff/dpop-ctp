/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.industrytype.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.industrytype.bo.IndustryType;

public interface IndustryTypeMapper extends GenericMapper<IndustryType, Long> {

    // SELECT--------------------------------------------------------

    /**
     * 获取所有二级行业数据
     * 
     * @return 所有二级行业数据
     */
    public List<IndustryType> selectLevel2Data();

    // INSERT--------------------------------------------------------

    // UPDATE--------------------------------------------------------

    // DELETE--------------------------------------------------------

}