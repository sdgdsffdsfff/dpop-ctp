
package com.baidu.dpop.ctp.demo.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.demo.bo.DemoTestBo;

public interface DemoTestBoDao extends GenericMapperDao<DemoTestBo, Long> {
    /**
     * 获取所有的demo表中数据
     * */
    public List<DemoTestBo> findAll();
    
    public void updateBeidou();

    public void updateQiushi();

    public void updateDSP();

    public void updateNewDSP();

    public void updateAdTag();

    public void updateReviewAdTask();

    public void updateReviewAdTask2();
    
    public void updateTask();
}
