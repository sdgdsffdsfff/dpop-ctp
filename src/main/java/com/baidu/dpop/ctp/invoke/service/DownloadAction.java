package com.baidu.dpop.ctp.invoke.service;

import com.baidu.dpop.ctp.invoke.bo.DownloadInfo;

public interface DownloadAction {

    /**
     * 执行下载过程
     * 
     * @param info 分配的下载信息
     */
    public void download(DownloadInfo info);

}
