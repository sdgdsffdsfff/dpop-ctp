/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.invoke.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.invoke.bo.DownloadInfo;
import com.baidu.dpop.ctp.invoke.dao.DownloadInfoDao;
import com.baidu.dpop.ctp.invoke.service.DownloadInfoService;

@Service
public class DownloadInfoServiceImpl extends BaseService<DownloadInfo, Long> implements DownloadInfoService {

    @Autowired
    private DownloadInfoDao invokeDao;

    @Override
    public GenericMapperDao<DownloadInfo, Long> getDao() {
        return invokeDao;
    }

    @Override
    public DownloadInfo getByUserAndTime(Date startTime, String startUser) {
        return this.invokeDao.selectByUserAndTime(startTime, startUser);
    }
}
