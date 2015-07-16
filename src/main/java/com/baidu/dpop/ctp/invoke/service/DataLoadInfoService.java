/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.invoke.service;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.dpop.ctp.invoke.bo.DataLoadInfo;

public interface DataLoadInfoService extends GenericMapperService<DataLoadInfo, Long> {
    /**
     * 插入一条新的数据导入任务信息记录
     * 
     * @param item 数据导入任务信息
     * */
    public void inserDataInfo(DataLoadInfo item);

    /**
     * 通过文件对应的MD5值查找对应的最近的一次导入任务
     * 
     * @param md5 md5
     * */
    public DataLoadInfo getDataLoadInfoByMd5(String md5);

    /**
     * 更新指定的数据导入任务信息
     * 
     * @param item 数据导入任务信息
     * */
    public void updateDataInfo(DataLoadInfo item);
}
