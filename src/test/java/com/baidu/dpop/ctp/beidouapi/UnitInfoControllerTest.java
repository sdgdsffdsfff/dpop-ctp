package com.baidu.dpop.ctp.beidouapi;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.beidouapi.service.impl.UnitInfoServiceWrapper;
import com.baidu.dpop.ctp.beidouapi.web.controller.UnitInfoController;

import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;

/**   
 * UnitInfoControllerTest
 * @author cgd  
 * @date 2015年3月19日 下午4:08:07 
 */
public class UnitInfoControllerTest {
    
    @Tested
    private UnitInfoController unitInfoController;
    
    @Test
    public void testGetHtmlSnippet() {
        String ret = unitInfoController.getHtmlSnippet(null, null);
        Assert.assertTrue(ret.indexOf("ERROR") >= 0);
        
        new MockUp<UnitInfoServiceWrapper>() {
            @Mock
            public String getSmartUnitHtmlSnippet(Long userId, Long unitId, Integer flag) {
                return null;
            }
        };
        ret = unitInfoController.getHtmlSnippet(1L, 1L);
        Assert.assertTrue(ret.indexOf("ERROR") >= 0);
        
        new MockUp<UnitInfoServiceWrapper>() {
            @Mock
            public String getSmartUnitHtmlSnippet(Long userId, Long unitId, Integer flag) {
                return "HTML DATA";
            }
        };
        ret = unitInfoController.getHtmlSnippet(1L, 1L);
        Assert.assertTrue(ret.indexOf("ERROR") < 0);
    }

}
