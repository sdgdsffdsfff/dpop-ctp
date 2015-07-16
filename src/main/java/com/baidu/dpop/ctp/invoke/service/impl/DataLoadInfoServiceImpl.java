
/*
* Copyright 2014 baidu dpop
* All right reserved.
*
*/

package com.baidu.dpop.ctp.invoke.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.invoke.bo.DataLoadInfo;
import com.baidu.dpop.ctp.invoke.dao.DataLoadInfoDao;
import com.baidu.dpop.ctp.invoke.service.DataLoadInfoService;

@Service
public class DataLoadInfoServiceImpl extends BaseService<DataLoadInfo, Long> implements DataLoadInfoService {

	@Autowired
	private DataLoadInfoDao invokeDao;
	
	@Override
	public GenericMapperDao<DataLoadInfo, Long> getDao() {
		return invokeDao;
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void inserDataInfo(DataLoadInfo item) {
        Assert.notNull(item);
        this.invokeDao.insertSelective(item);
    }
    
    @Override
    public DataLoadInfo getDataLoadInfoByMd5(String md5) {
        Assert.notNull(md5);
        return this.invokeDao.getDataLoadInfoByMd5(md5);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void updateDataInfo(DataLoadInfo item) {
        Assert.notNull(item);
        
        String md5 = item.getMd5Value();
        DataLoadInfo data = this.getDataLoadInfoByMd5(md5);
        if(data != null) {
            data.setStatus(item.getStatus());
            data.setInsertRecord(item.getInsertRecord());
            data.setScanRecord(item.getScanRecord());
        }
        this.invokeDao.updateByPrimaryKeySelective(data);
    }

}
