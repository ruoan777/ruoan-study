package com.ruoan.study.concurrent;


import com.ruoan.study.Students;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapPutTest {

    public static Map<String, Students> cache = new ConcurrentHashMap<>();

    static {
        cache.put("s1", new Students("小明"));
        cache.put("s2", new Students("小红"));
    }

    public static void main(String[] args) throws InterruptedException {
        Students s1 = cache.get("s1");
        MapPutTest.cache.remove("s1");
        System.out.println(s1);
    }
}