package com.baidu.dpop.ctp.invoke.service;

import java.util.Date;

import com.baidu.dpop.ctp.user.bo.User;

public interface DownloadService {

    /**
     * 进行非阻塞的下载
     * 
     * @param fileName 下载的文件名
     * @param user 下载的执行者
     * @param startTime 下载的开始时间
     * @param fileType 下载的结束时间
     * @param action 下载的执行过程
     * @return 生成的下载信息的id
     */
    public Long doUnBlockedDownload(final String fileName, final User user, final Date startTime,
            final Integer fileType, final DownloadAction action);

}
