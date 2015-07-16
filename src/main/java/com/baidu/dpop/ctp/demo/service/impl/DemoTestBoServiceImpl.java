/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.adtag.dao.AdTagDao;
import com.baidu.dpop.ctp.demo.bo.DemoTestBo;
import com.baidu.dpop.ctp.demo.dao.DemoTestBoDao;
import com.baidu.dpop.ctp.demo.service.DemoTestBoService;

@Service
public class DemoTestBoServiceImpl extends BaseService<DemoTestBo, Long>
        implements DemoTestBoService {

    @Autowired
    private DemoTestBoDao demoDao;
    
    @Autowired
    private AdTagDao adTagDao;

    @Override
    public GenericMapperDao<DemoTestBo, Long> getDao() {
        return demoDao;
    }

    public List<DemoTestBo> findAll() {
        return this.demoDao.findAll();
    }
}
