package com.ruoan.study.guava;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.FutureFallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

/**
 * @author ruoan
 * @date 2022/7/10 11:28 下午
 */
public class GuavaFutureFallBack {

    public static void main(String[] args) {
        test2();
    }

    public static void test1() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ListenableFuture<Integer> fetchCounterFuture = Futures.immediateFailedFuture(new RuntimeException("我是一个异常"));
        ListenableFuture<Integer> faultTolerantFuture = Futures.withFallback(
                fetchCounterFuture, new FutureFallback<Integer>() {
                    public ListenableFuture<Integer> create(Throwable t) {
                        return Futures.immediateFuture(0);
                    }
                });
        Futures.addCallback(faultTolerantFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.out.println("3, currentThread= " + Thread.currentThread().getName() + ", booleanTask result= " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(234);
                t.printStackTrace();
            }
        }, executorService);
        System.out.println("main done");
    }

    public static void test2() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ListenableFuture<Integer> fetchCounterFuture = Futures.immediateFailedFuture(new RuntimeException("我是一个异常"));
        ListenableFuture<Integer> fetchCounterFuture2 = Futures.immediateFailedFuture(new TimeoutException("我是一个异常"));

        ListenableFuture<Integer> faultTolerantFuture = Futures.withFallback(
                fetchCounterFuture2, new FutureFallback<Integer>() {
                    public ListenableFuture<Integer> create(Throwable t) {
                        if (t instanceof TimeoutException) {
                            return Futures.immediateFuture(999999999);
                        }
                        return Futures.immediateFailedFuture(t);
                    }
                });
        Futures.addCallback(faultTolerantFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.out.println("3, currentThread= " + Thread.currentThread().getName() + ", booleanTask result= " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(234);
                t.printStackTrace();
            }
        }, executorService);
        System.out.println("main done");
    }
}
