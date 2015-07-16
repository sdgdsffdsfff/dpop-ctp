/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.invoke.dao;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.invoke.bo.DataLoadInfo;

public interface DataLoadInfoDao extends GenericMapperDao<DataLoadInfo, Long> {

    /**
     * 通过文件对应的MD5值查找对应的最近的一次导入任务
     * 
     * @param md5 md5
     * */
    public DataLoadInfo getDataLoadInfoByMd5(String md5);

}
