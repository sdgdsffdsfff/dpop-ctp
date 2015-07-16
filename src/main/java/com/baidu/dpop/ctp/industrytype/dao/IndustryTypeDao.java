/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.industrytype.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.industrytype.bo.IndustryType;

public interface IndustryTypeDao extends GenericMapperDao<IndustryType, Long> {

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
