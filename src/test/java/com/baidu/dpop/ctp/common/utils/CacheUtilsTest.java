package com.baidu.dpop.ctp.common.utils;

import mockit.Mock;
import mockit.MockUp;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.common.cache.LocalCacheManager;
import com.baidu.dpop.frame.core.cache.CacheManager;
import com.baidu.dpop.frame.core.context.SpringContextUtil;

/**
 * CacheUtilsTest
 * 
 * @author cgd
 * @date 2015年5月14日 下午4:11:02
 */
public class CacheUtilsTest {

    @Test
    public void testget() {
        new MockUp<SpringContextUtil>() {
            @SuppressWarnings("unchecked")
            @Mock
            public <T> T getBean(Class<T> requiredType) {
                CacheManager manager = new LocalCacheManager();
                manager.set("name", "cgd");
                return (T) manager;
            }
        };

        String ret = CacheUtils.get("name");
        Assert.assertTrue(ret.equals("cgd"));
    }

    @Test
    public void testset() {
        new MockUp<SpringContextUtil>() {
            @SuppressWarnings("unchecked")
            @Mock
            public <T> T getBean(Class<T> requiredType) {
                CacheManager manager = new LocalCacheManager();
                return (T) manager;
            }
        };
        CacheUtils.set("name", "cgd");
        String name = CacheUtils.get("name");
        Assert.assertTrue(name.equals("cgd"));

        CacheUtils.set("name", "cgd2", 1000);
        name = CacheUtils.get("name");
        Assert.assertTrue(name.equals("cgd2"));
    }

}
