package com.baidu.dpop.ctp.task.bo;

public interface DownloadInfo {

    /**
     * 生成下载数据时的一行数据
     * 
     * @param taskType 任务类型
     * @return 数据行
     */
    public String genFullString(Number taskType);

}
