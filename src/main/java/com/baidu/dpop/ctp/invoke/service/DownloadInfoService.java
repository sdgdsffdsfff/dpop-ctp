/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.invoke.service;

import java.util.Date;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.dpop.ctp.invoke.bo.DownloadInfo;

public interface DownloadInfoService extends GenericMapperService<DownloadInfo, Long> {

    /**
     * 根据开始时间和开始用户获取下载信息
     * 
     * @param startTime 开始时间
     * @param startUser 开始用户
     * @return 下载信息
     */
    public DownloadInfo getByUserAndTime(Date startTime, String startUser);

}
