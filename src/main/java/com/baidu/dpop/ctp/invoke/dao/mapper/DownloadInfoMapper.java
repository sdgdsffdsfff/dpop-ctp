/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.invoke.dao.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.invoke.bo.DownloadInfo;

public interface DownloadInfoMapper extends GenericMapper<DownloadInfo, Long> {

    // SELECT--------------------------------------------------------

    /**
     * 根据开始时间和开始用户获取下载信息
     * 
     * @param startTime 开始时间
     * @param startUser 开始用户
     * @return 下载信息
     */
    public DownloadInfo selectByUserAndTime(@Param("startTime") Date startTime, @Param("startUser") String startUser);

    // INSERT--------------------------------------------------------

    // UPDATE--------------------------------------------------------

    // DELETE--------------------------------------------------------
}