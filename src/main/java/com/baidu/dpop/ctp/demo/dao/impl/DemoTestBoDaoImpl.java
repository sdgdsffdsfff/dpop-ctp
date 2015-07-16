package com.baidu.dpop.ctp.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.demo.bo.DemoTestBo;
import com.baidu.dpop.ctp.demo.dao.DemoTestBoDao;
import com.baidu.dpop.ctp.demo.dao.mapper.DemoTestBoMapper;

@Repository
public class DemoTestBoDaoImpl extends BaseDao<DemoTestBo, Long> implements DemoTestBoDao {

    @Autowired
    private DemoTestBoMapper demoMapper;

    @Override
    public GenericMapper<DemoTestBo, Long> getMapper() {
        return this.demoMapper;
    }

    public List<DemoTestBo> findAll() {
        return this.demoMapper.findAll();
    }

    @Override
    public void updateBeidou() {
        this.demoMapper.updateBeidou();
    }

    @Override
    public void updateQiushi() {
        this.demoMapper.updateQiushi();
    }

    @Override
    public void updateDSP() {
        this.demoMapper.updateDSP();
    }

    @Override
    public void updateNewDSP() {
        this.demoMapper.updateNewDSP();
    }

    @Override
    public void updateAdTag() {
        this.demoMapper.updateAdTag();
    }

    @Override
    public void updateReviewAdTask() {
        this.demoMapper.updateReviewAdTask();
    }

    @Override
    public void updateReviewAdTask2() {
        this.demoMapper.updateReviewAdTask2();
    }
    
    public void updateTask() {
        this.demoMapper.updateTask();
    }
}
