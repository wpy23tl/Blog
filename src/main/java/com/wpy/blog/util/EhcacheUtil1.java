package com.wpy.blog.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wlx on 2017/2/19.
 */
public class EhcacheUtil1 {

    private CacheManager cacheManager;
    public EhcacheUtil1() {
        cacheManager = CacheManager.create(this.getClass().getResource("/ehcache.xml"));
    }

    public static void main(String[] args) {
        EhcacheUtil1 ehcacheUtil = new EhcacheUtil1();
        //ehcacheUtil.put("sampleCache1","a","ssssssssssssssssss");
        String a =(String)ehcacheUtil.get("sampleCache1","a");
        System.out.println(a);
    }
    public  void put(JoinPoint point) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        String cacheName="sampleCache1";
        String key="aa";
        Class clazz = point.getTarget().getClass();
        Constructor con =clazz.getConstructor();
        Object object =con.newInstance();
        Method method = clazz.getMethod("getAll",Map.class);
        Map<String,Object> map = new HashMap<>();
        Object object1 =method.invoke(clazz,map);
        Cache cache = cacheManager.getCache(cacheName);
        Element element = new Element(key,object1);
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
