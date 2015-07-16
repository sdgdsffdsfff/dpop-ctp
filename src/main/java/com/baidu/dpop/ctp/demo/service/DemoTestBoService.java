
package com.baidu.dpop.ctp.demo.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.dpop.ctp.demo.bo.DemoTestBo;

public interface DemoTestBoService extends GenericMapperService<DemoTestBo, Long> {
    
    /**
     * 获取所有的demo表中数据
     * */
    public List<DemoTestBo> findAll();
}
