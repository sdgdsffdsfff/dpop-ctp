package com.baidu.dpop.ctp.common.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.baidu.dpop.frame.core.cache.CacheManager;

/**
 * 
 * @author huhailiang RD环境本地cache
 * 
 */
public class LocalCacheManager implements CacheManager {

    private Map<String, Object> cache = new ConcurrentHashMap<String, Object>(1024);
    private static LocalCacheManager singleInstance;

    public static LocalCacheManager getInstance() {
        if (null == singleInstance) {
            singleInstance = new LocalCacheManager();
        }
        return singleInstance;
    }

    @Override
    public Object get(String key) {
        return cache.get(key);
    }

    @Override
    public void set(String key, Object value) {
        cache.put(key, value);
    }

    @Override
    public void set(String key, Object value, Integer expiration) {
        cache.put(key, value);
    }

    @Override
    public boolean exists(String key) {
        return cache.containsKey(key);
    }

    @Override
    public void delete(String key) {
        cache.remove(key);
    }

    @Override
    public void expire(String key, int seconds) {

    }

    @SuppressWarnings("unchecked")
    @Override
    public Object hget(String key, String field) {
        Map<String, Object> cacheItem = (Map<String, Object>) cache.get(key);
        return (null == cacheItem) ? null : cacheItem.get(field);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void hput(String mapName, String field, Serializable fieldValue) {
        Map<String, Object> cacheItem = (Map<String, Object>) cache.get(mapName);
        if (null != cacheItem) {
            cacheItem.put(field, fieldValue);
        } else {
            cacheItem = new HashMap<String, Object>();
            cacheItem.put(field, fieldValue);
            cache.put(mapName, cacheItem);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void hdel(String mapName, String field) {
        Map<String, Object> cacheItem = (Map<String, Object>) cache.get(mapName);
        if (null != cacheItem) {
            cacheItem.remove(field);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean hExists(String key, String field) {
        Map<String, Object> cacheItem = (Map<String, Object>) cache.get(key);
        if (null != cacheItem) {
            return cacheItem.containsKey(field);
        }
        return false;
    }

    @Override
    public Long hLen(String mapName) {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<String> hKeys(String mapName) {
        Map<String, Object> cacheItem = (Map<String, Object>) cache.get(mapName);
        return (null == cacheItem) ? null : cacheItem.keySet();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> hValues(String mapName) {
        Map<String, Object> cacheItem = (Map<String, Object>) cache.get(mapName);
        return (List<Object>) ((null == cacheItem) ? null : cacheItem.values());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void hmSet(String mapName, Map<String, Serializable> values) {
        Map<String, Object> cacheItem = (Map<String, Object>) cache.get(mapName);
        if (null != cacheItem) {
            cacheItem.putAll(values);
        }
    }

    @Override
    public List<Object> hmGet(String mapName, String...fields) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> hGetAll(String mapName) {
        Map<String, Object> cacheItem = (Map<String, Object>) cache.get(mapName);
        return cacheItem;
    }

    @Override
    public void shutdown() {

    }

}
