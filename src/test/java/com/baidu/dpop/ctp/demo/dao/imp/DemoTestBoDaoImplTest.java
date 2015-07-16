package com.baidu.dpop.ctp.demo.dao.imp;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.dpop.ctp.base.AbstractDAOTests;
import com.baidu.dpop.ctp.demo.bo.DemoTestBo;
import com.baidu.dpop.ctp.demo.dao.DemoTestBoDao;

/**
 * DemoTestBoDaoImplTest
 * 
 * @author cgd
 * @date 2014年12月9日 下午2:26:05
 */
@SuppressWarnings("restriction")
public class DemoTestBoDaoImplTest extends AbstractDAOTests {
   
    @Resource
    private DemoTestBoDao demoTestBoDao;
    
    @Before
    public void setUp() {
        this.executeDatas("demo/demo_dataset_init.sql");
    }
    
    @Test
    public void testFindAll() {
        List<DemoTestBo> data = this.demoTestBoDao.findAll();
        Assert.assertNotNull(data);
        Assert.assertTrue(data.size() == 2);
    }

}
