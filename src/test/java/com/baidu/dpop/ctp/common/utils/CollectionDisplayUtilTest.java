package com.baidu.dpop.ctp.common.utils;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * CollectionDisplayUtilTest
 * 
 * @author cgd
 * @date 2015年5月14日 下午4:35:33
 */
public class CollectionDisplayUtilTest {

    @Test
    public void testlistToString() {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        String ret = CollectionDisplayUtil.listToString(data, "-", "-", ",");
        Assert.assertTrue(ret.equals("-1,2,3,4,5-"));
    }

    @Test
    public void testgetIntegerListFromString() {
        List<Integer> ret = CollectionDisplayUtil.getIntegerListFromString("1,2,3", ",");
        Assert.assertTrue(ret.size() == 3);
        Assert.assertTrue(ret.get(0) == 1);
    }

}
