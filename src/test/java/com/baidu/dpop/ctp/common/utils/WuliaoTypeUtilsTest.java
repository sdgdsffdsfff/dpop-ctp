package com.baidu.dpop.ctp.common.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * WuliaoTypeUtilsTest
 * 
 * @author cgd
 * @date 2015年5月15日 上午11:29:42
 */
public class WuliaoTypeUtilsTest {

    @Test
    public void testisTextAndPictureAd() {
        Assert.assertTrue(WuliaoTypeUtils.isTextAndPictureAd((byte) 0, 2));
        Assert.assertTrue(WuliaoTypeUtils.isTextAndPictureAd((byte) 0, 5));

        Assert.assertTrue(WuliaoTypeUtils.isTextAndPictureAd((byte) 1, 2));
        Assert.assertTrue(WuliaoTypeUtils.isTextAndPictureAd((byte) 1, 1));
        Assert.assertTrue(WuliaoTypeUtils.isTextAndPictureAd((byte) 1, 5));
        Assert.assertTrue(WuliaoTypeUtils.isTextAndPictureAd((byte) 1, 6));
        Assert.assertTrue(WuliaoTypeUtils.isTextAndPictureAd((byte) 1, 100));

        Assert.assertTrue(WuliaoTypeUtils.isTextAndPictureAd((byte) 2, 1));

        Assert.assertTrue(!WuliaoTypeUtils.isTextAndPictureAd((byte) 30, 1));
    }

}
