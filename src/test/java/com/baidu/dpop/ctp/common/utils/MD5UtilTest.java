package com.baidu.dpop.ctp.common.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * MD5UtilTest
 * 
 * @author cgd
 * @date 2015年5月14日 下午5:49:16
 */
public class MD5UtilTest {

    @Test
    public void testgetMD5Value() {
        String key = "md5key";
        String ret = MD5Util.getMD5Value(key);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.length() > 0);
    }

}
