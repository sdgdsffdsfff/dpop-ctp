/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.industrytype.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.industrytype.bo.IndustryType;
import com.baidu.dpop.ctp.industrytype.dao.IndustryTypeDao;
import com.baidu.dpop.ctp.industrytype.dao.mapper.IndustryTypeMapper;

@Repository
public class IndustryTypeDaoImpl extends BaseDao<IndustryType, Long> implements IndustryTypeDao {

    @Autowired
    private IndustryTypeMapper industryTypeMapper;

    @Override
    public GenericMapper<IndustryType, Long> getMapper() {
        return this.industryTypeMapper;
    }

    @Override
    public List<IndustryType> selectLevel2Data() {
        return this.industryTypeMapper.selectLevel2Data();
    }

}
