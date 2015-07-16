/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.invoke.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.invoke.bo.DataLoadInfo;
import com.baidu.dpop.ctp.invoke.dao.DataLoadInfoDao;
import com.baidu.dpop.ctp.invoke.dao.mapper.DataLoadInfoMapper;

@Repository
public class DataLoadInfoDaoImpl extends BaseDao<DataLoadInfo, Long> implements DataLoadInfoDao {

    @Autowired
    private DataLoadInfoMapper invokeMapper;

    @Override
    public GenericMapper<DataLoadInfo, Long> getMapper() {
        return this.invokeMapper;
    }

    @Override
    public DataLoadInfo getDataLoadInfoByMd5(String md5) {
        Assert.notNull(md5);
        return this.invokeMapper.getDataLoadInfoByMd5(md5);
    }

}
