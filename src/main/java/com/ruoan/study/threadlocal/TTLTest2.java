package com.ruoan.study.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ruoan
 * @date 2022/5/22 11:43 下午
 */
public class TTLTest2 {

    private static final ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(10));
    private static final TransmittableThreadLocal<AtomicInteger> transmittableThreadLocal = new TransmittableThreadLocal<AtomicInteger>() {
        @Override
        protected void beforeExecute() {
            System.out.println("i am beforeExecute");
        }

        @Override
        protected void afterExecute() {
            System.out.println("i am afterExecute");
        }
    };//505

    public static void main(String[] args) {
        transmittableThreadLocal.set(new AtomicInteger(0));
        System.out.println("Parent-start at spanId " + transmittableThreadLocal.get().getAndIncrement());

        // 挤满线程
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("i am working - " + Thread.currentThread().getName());
                }
            });
        }

        // 提交任务，执行rpc调用
        for (int i = 0; i < 30; i++) {
            executorService.submit(() -> {
                // 打印输出子线程所需的spanId
                System.out.println("Sub start at spanId " + transmittableThreadLocal.get().incrementAndGet());
            });
        }
        // 打印父线程退出的spanId
        System.out.println("Parent-end at spanId " + transmittableThreadLocal.get().incrementAndGet());
    }
}
