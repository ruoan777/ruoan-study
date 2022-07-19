package com.ruoan.study.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年09月08日 19:40:00
 */
public class TestConcurrentHashMap {

    private Map<String,String> concurrentHashMap = new ConcurrentHashMap<String, String>();

    public void pringMap(){
        concurrentHashMap.put("key1","value1");
        concurrentHashMap.forEach((s, s2) -> System.out.println(s+s2));
    }

    public static void main(String[] args) {

        HashMap<String, String> stringStringHashMap = new HashMap<>(10);
        stringStringHashMap.put("keyyy","valueee");
        TestConcurrentHashMap testConcurrentHashMap = new TestConcurrentHashMap();
        new Thread(new Runnable() {
            @Override
            public void run() {
                testConcurrentHashMap.pringMap();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                testConcurrentHashMap.pringMap();
            }
        }).start();
//        Collections.synchronizedMap(new HashMap());
    }
}
