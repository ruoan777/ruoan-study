package com.ruoan.study.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年07月20日 20:31:00
 */
public class ThreadLocalDemo2 {
    private static int a = 0;
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> a);

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            pool.execute(() -> {
                a = threadLocal.get() + 1;
                threadLocal.set(a);
            });
        }

        pool.shutdownNow();
        System.out.println(a);
    }
}