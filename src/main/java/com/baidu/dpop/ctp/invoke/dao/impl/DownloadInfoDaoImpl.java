package com.baidu.dpop.ctp.invoke.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.baidu.dpop.ctp.invoke.bo.DownloadInfo;
import com.baidu.dpop.ctp.invoke.dao.DownloadInfoDao;
import com.baidu.dpop.ctp.invoke.dao.mapper.DownloadInfoMapper;
import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;

@Repository
public class DownloadInfoDaoImpl extends BaseDao<DownloadInfo, Long> implements DownloadInfoDao {
    
    @Autowired
    private DownloadInfoMapper downloadInfoMapper;

    @Override
    public GenericMapper<DownloadInfo, Long> getMapper() {
        // TODO Auto-generated method stub
        return downloadInfoMapper;
    }

    @Override
    public DownloadInfo selectByUserAndTime(Date startTime, String startUser) {
        Assert.notNull(startUser);
        Assert.notNull(startTime);
        return this.downloadInfoMapper.selectByUserAndTime(startTime, startUser);
    }

}
