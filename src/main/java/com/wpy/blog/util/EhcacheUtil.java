package com.wpy.blog.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Created by wlx on 2017/2/19.
 */
public class EhcacheUtil {

    private CacheManager cacheManager;
    public EhcacheUtil() {
        cacheManager = CacheManager.create(this.getClass().getResource("/ehcache.xml"));
    }

    public static void main(String[] args) {
        EhcacheUtil ehcacheUtil = new EhcacheUtil();
        ehcacheUtil.put("sampleCache1","a","ssssssssssssssssss");
        String a =(String)ehcacheUtil.get("sampleCache1","a");
        System.out.println(a);
    }
    public  void put(String cacheName,String key,Object value){
        Cache cache = cacheManager.getCache(cacheName);
        Element element = new Element(key,value);
        cache.put(element);
    }

    public  Object get(String cacheName,String key){
        Cache cache = cacheManager.getCache(cacheName);
        Element element =cache.get(key);
        if (element==null){
            return null;
        }
        return element.getObjectValue();
    }
}
