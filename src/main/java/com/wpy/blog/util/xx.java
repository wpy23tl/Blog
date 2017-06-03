package com.wpy.blog.util;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by wlx on 2017/2/19.
 */
public class xx {
    @Resource
    EhcacheUtil ehcacheUtil;
    public static void main(String[] args) {
        //EhcacheUtil ehcacheUtil = new EhcacheUtil();
//        ehcacheUtil.put("sampleCache1","a","ffffffff");
//        String m =(String) ehcacheUtil.get("sampleCache1","a");
//        System.out.println(m);
        xx x = new xx();
        x.show();

    }

    public  void show(){
        ehcacheUtil.put("sampleCache1","a","ffffffff");
        String m =(String) ehcacheUtil.get("sampleCache1","a");
        System.out.println(m);
    }
}
