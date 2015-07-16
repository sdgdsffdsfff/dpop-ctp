/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.industrytype.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.industrytype.bo.NewIndustryType;
import com.baidu.dpop.ctp.industrytype.dao.NewIndustryTypeDao;
import com.baidu.dpop.ctp.industrytype.dao.mapper.NewIndustryTypeMapper;

@Repository
public class NewIndustryTypeDaoImpl extends BaseDao<NewIndustryType, Long> implements NewIndustryTypeDao {

    @Autowired
    private NewIndustryTypeMapper industryTypeMapper;

    @Override
    public GenericMapper<NewIndustryType, Long> getMapper() {
        return this.industryTypeMapper;
    }

    @Override
    public List<NewIndustryType> selectLevel3Data() {
        return this.industryTypeMapper.selectLevel3Data();
    }

    @Override
    public List<NewIndustryType> batchSelect(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<NewIndustryType>();
        }

        return this.industryTypeMapper.batchSelect(list);
    }
    
    @Override
    public List<Integer> selectAllFirstTradeType() {
        return this.industryTypeMapper.selectAllFirstTradeType();
    }

    @Override
    public void batchUpdateLevel2IdAndName(List<NewIndustryType> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        this.industryTypeMapper.batchUpdateLevel2IdAndName(list);
    }
}
