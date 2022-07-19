package com.ruoan.study.guava;

import com.google.common.util.concurrent.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ruoan
 * @date 2022/7/7 12:19 上午
 */
public class GuavaListenableFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ListeningExecutorService service = MoreExecutors.listeningDecorator(executorService);
        long begin = System.currentTimeMillis();
        ListenableFuture<Boolean> booleanTask = service.submit(() -> {
            Thread.sleep(1000);
            System.out.println("1, currentThread= " + Thread.currentThread().getName());
            System.out.println("".substring(0, 1));
            return true;
        });
        System.out.println("booleanTask 的类型是ListenableFutureTask:" + (booleanTask instanceof ListenableFutureTask));
//        booleanTask.addListener(() -> {
//            try {
//                booleanTask.get();
//                Thread.sleep(2000);
//                System.out.println("2, currentThread= " + Thread.currentThread().getName());
//            } catch (Exception e) {
//                System.out.println(123);
//                e.printStackTrace();
//            }
//        }, executorService);
        Futures.addCallback(booleanTask, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                System.out.println("3, currentThread= " + Thread.currentThread().getName() + ", booleanTask result= " + result);
            }

            @Override
            public void onFailure(Throwable e) {
                System.out.println(234);
                e.printStackTrace();
            }
        }, executorService);
        System.out.println("cost " + (System.currentTimeMillis() - begin) + "ms");
    }
}
