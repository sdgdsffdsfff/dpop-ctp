package com.baidu.dpop.ctp.common.utils;

import com.baidu.dpop.frame.core.cache.CacheManager;
import com.baidu.dpop.frame.core.context.SpringContextUtil;

/**
 * 缓存管理工具类
 * 
 * */
public class CacheUtils {
    
    private static CacheManager cacheManager;

    private static void initCacheManager() {
        if (null == cacheManager) {
            cacheManager = SpringContextUtil.getBean(CacheManager.class);
        }
    }

    @SuppressWarnings("unchecked")
    public static <E> E get(String key) {
        initCacheManager();
        return (E) cacheManager.get(key);
    }

    public static <E> void set(String key, E value) {
        initCacheManager();
        cacheManager.set(key, value);
    }

    public static <E> void set(String key, E value, Integer expirationTime) {
        initCacheManager();
        cacheManager.set(key, value, expirationTime);
    }

}
