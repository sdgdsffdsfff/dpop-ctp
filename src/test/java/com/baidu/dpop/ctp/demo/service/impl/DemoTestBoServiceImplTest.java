package com.baidu.dpop.ctp.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import com.baidu.dpop.ctp.demo.bo.DemoTestBo;
import com.baidu.dpop.ctp.demo.dao.DemoTestBoDao;

/**   
 * DemoTestBoServiceImplTest
 * @author cgd  
 * @date 2014年12月9日 下午3:42:57 
 */
public class DemoTestBoServiceImplTest {
    @Tested
    private DemoTestBoServiceImpl demoTestBoService;
    
    @Injectable
    private DemoTestBoDao demoTestBoDao;
    
    @Test
    public void testFindAll() {
        new NonStrictExpectations() {
            {
                demoTestBoDao.findAll();
                List<DemoTestBo> data = new ArrayList<DemoTestBo>();
                DemoTestBo bo = new DemoTestBo();
                bo.setDemoName("demoName");
                bo.setAddTime(new Date());
                data.add(bo);
                result = data;
            }
        };
        
        List<DemoTestBo> ret = this.demoTestBoService.findAll();
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }
}
