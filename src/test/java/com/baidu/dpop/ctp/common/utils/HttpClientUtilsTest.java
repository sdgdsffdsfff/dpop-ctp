package com.baidu.dpop.ctp.common.utils;

import java.util.HashMap;
import java.util.Map;

import mockit.Tested;

import org.junit.Test;

/**
 * HttpClientUtilsTest
 * 
 * @author cgd
 * @date 2015年5月14日 下午4:44:35
 */
public class HttpClientUtilsTest {
    @Tested
    private HttpClientUtils utils;

    @Test(expected = RuntimeException.class)
    public void testdoGetRequestWithException() {
        this.utils.doGetRequest("", null);
    }

    @Test(expected=Exception.class)
    public void testdoGetRequest() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "cgd");
        this.utils.doGetRequest("adtag.baidu.com", params);
    }

    @Test(expected=Exception.class)
    public void testdoPostRequest() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "cgd");
        this.utils.doPostRequest("adtag.baidu.com", params);
    }

}
