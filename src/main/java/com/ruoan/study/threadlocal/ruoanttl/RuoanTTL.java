package com.ruoan.study.threadlocal.ruoanttl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ruoan
 * @date 2022/5/17 12:44 上午
 */
public class RuoanTTL {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        ThreadLocal<Object> tl1 = new ThreadLocal<>();
        ThreadLocal<Object> tl2 = new ThreadLocal<>();


// ------------第一次调用 start -------------------
        tl1.set("1111");
        tl2.set("2222");

        executorService.execute(new MyTask(tl1, tl2));

        tl1.remove();
        tl2.remove();
// ------------第一次调用 end -------------------


// ------------第二次调用 start -------------------
        tl1.set("3333");
        tl2.set("4444");

        executorService.execute(new MyTask(tl1, tl2));

        tl1.remove();
        tl2.remove();
// ------------第二次调用 end -------------------

    }
}
