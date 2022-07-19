package com.ruoan.study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xh.gao
 * @version 1.0.0
 * @ClassName ThreadTest.java
 * @Description TODO
 * @createTime 2020年01月02日 15:23:00
 */
public class ThreadTest {
    public static void testThread(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for(int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        ThreadTest.testThread();
    }
}
