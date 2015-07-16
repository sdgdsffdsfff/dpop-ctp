package com.baidu.dpop.ctp.index.web.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import mockit.Tested;

/**   
 * IndexControllerTest
 * @author cgd  
 * @date 2014年12月30日 下午4:46:42 
 */
public class IndexControllerTest {
    
    @Tested
    private IndexController indexController;
    
    @Test
    public void testShowIndex() {
        ModelAndView ret = this.indexController.showIndex();
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getViewName().equals("index"));
    }

}
