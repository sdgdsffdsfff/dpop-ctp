package com.baidu.dpop.ctp.invoke.service.impl;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.ctp.invoke.bo.DownloadInfo;
import com.baidu.dpop.ctp.invoke.service.DownloadAction;
import com.baidu.dpop.ctp.invoke.service.DownloadInfoService;
import com.baidu.dpop.ctp.invoke.service.DownloadService;
import com.baidu.dpop.ctp.user.bo.User;

@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    private DownloadInfoService downloadInfoService;

    private ExecutorService pool = Executors.newFixedThreadPool(4);

    @Override
    public Long doUnBlockedDownload(final String fileName, final User user, final Date startTime,
            final Integer fileType, final DownloadAction action) {

        // TODO 插入一条下载记录
        DownloadInfo downloadInfo = new DownloadInfo();
        downloadInfo.setFileName(fileName);
        downloadInfo.setFileType(fileType);
        downloadInfo.setStartUser(user.getUserName());
        downloadInfo.setStartTime(startTime);
        downloadInfoService.insertSelective(downloadInfo);

        final DownloadInfo info = downloadInfoService.getByUserAndTime(startTime, user.getUserName());
        pool.execute(new Runnable() {

            @Override
            public void run() {
                action.download(info);
            }
        });

        return info.getId();
    }

}
