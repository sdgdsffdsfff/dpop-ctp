package com.baidu.dpop.ctp.beidouapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Mock;
import mockit.MockUp;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.common.utils.BnsUtils;

/**   
 * UnitInfoServiceWrapper Test
 * @author cgd  
 * @date 2015年3月13日 下午5:31:06 
 */
public class UnitInfoServiceWrapperTest {
    
    @Test
    public void testGetSmartUnitHtmlSnippet() {
        new MockUp<BnsUtils>() {
            @Mock
            public List<String> getIpPortList(String name) {
                return null;
            }
        };
        String ret = UnitInfoServiceWrapper.getSmartUnitHtmlSnippet(1L, 3L, 0);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.indexOf("ERROR") >= 0);
        
        new MockUp<BnsUtils>() {
            @Mock
            public List<String> getIpPortList(String name) {
                List<String> ipPortList = new ArrayList<String>();
                ipPortList.add("10.52.86.55:8080");
                return ipPortList;
            }
        };
        
       /* new NonStrictExpectations(BnsUtils.class) {
            {
                BnsUtils.getIpPortList(anyString);
                List<String> ipPortList = new ArrayList<String>();
                ipPortList.add("10.52.86.55:8080");
                result = ipPortList;
            }
        };*/
        
        ret = UnitInfoServiceWrapper.getSmartUnitHtmlSnippet(1L, 3L, 0);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.indexOf("ERROR") >= 0);
        
//        ret = UnitInfoServiceWrapper.getSmartUnitHtmlSnippet(1086925, 378749L, 0);
//        Assert.assertNotNull(ret);
//        Assert.assertTrue(ret.indexOf("ERROR") == -1);
    }
    
    @Test
    public void testGetUbmcTmpUrl() {
        new MockUp<BnsUtils>() {
            @Mock
            public List<String> getIpPortList(String name) {
                return null;
            }
        };
        String ret = UnitInfoServiceWrapper.getUbmcTmpUrl(1L, 1);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.indexOf("ERROR") >= 0);
        
        
        new MockUp<BnsUtils>() {
            @Mock
            public List<String> getIpPortList(String name) {
                List<String> ipPortList = new ArrayList<String>();
                ipPortList.add("10.52.86.55:8080");
                return ipPortList;
            }
        };
        ret = UnitInfoServiceWrapper.getUbmcTmpUrl(1L, 1);
        Assert.assertNull(ret);
    }

}
