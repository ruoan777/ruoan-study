package com.ruoan.study.guava;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import com.mysql.cj.conf.url.FailoverConnectionUrl;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author ruoan
 * @date 2022/7/12 12:05 上午
 */
public class SuccessfulAsListFuture {
    @Test
    public void test() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        ListenableFuture<String> lf1 = pool.submit(() -> {
            TimeUnit.SECONDS.sleep(2);
            return "listFuture1";
        });
        ListenableFuture<String> lf2 = pool.submit(() -> {
            TimeUnit.SECONDS.sleep(3);
            return "listFuture2";
        });
        ListenableFuture<String> lf3 = pool.submit(() -> {
            TimeUnit.SECONDS.sleep(1);
            return "listFuture3";
        });
        Pair<Integer, ListenableFuture<String>> pair1 = Pair.of(111, lf1);
        Pair<Integer, ListenableFuture<String>> pair2 = Pair.of(222, lf1);
        Pair<Integer, ListenableFuture<String>> pair3 = Pair.of(333, lf1);


        List<Pair<Integer, ListenableFuture<String>>> listenableFutures = Lists.newArrayList(pair1, pair2, pair3);

//        Futures.successfulAsList(F)
//        List<ListenableFuture<String>> listenableFutures = Lists.newArrayList(lf1, lf2, lf3);
//        ListenableFuture<List<String>> listListenableFuture = Futures.successfulAsList(listenableFutures);
//        Thread thread = new Thread(() -> {
//            while (true) {
//                System.out.println(Thread.currentThread().getName() + "并发工作中.....");
//                if (listListenableFuture.isDone()) {
//                    break;
//                }
//            }
//        }, "test-thread");
//        Futures.transform(listListenableFuture, new Function<List<String>, Object>() {
//            @Override
//            public Object apply(List<String> input) {
//                System.out.println("走到了转换的逻辑当中" + (System.currentTimeMillis() - start));
//                System.out.println("走到了转换的逻辑当中" + input);
//                return null;
//            }
//        });
//        Futures.transform(listListenableFuture, new AsyncFunction<List<String>, Object>() {
//            @Override
//            public ListenableFuture<Object> apply(List<String> input) throws Exception {
//                return null;
//            }
//        })
//        ListenableFuture<Object> listenableFutureAsync = Futures.transformAsync(listListenableFuture, new AsyncFunction<List<String>, Object>() {
//            @Override
//            public ListenableFuture<Object> apply(List<String> input) throws Exception {
//                System.out.println("走到了转换的逻辑当中，耗时" + (System.currentTimeMillis() - start));
//                System.out.println("走到了转换的逻辑当中" + input);
//                return Futures.immediateFuture("result after transformAsync");
//            }
//        }, pool);
//        System.out.println("主线程最后的输出是" + listenableFutureAsync.get());
//        System.out.println("主线程耗时" + (System.currentTimeMillis() - start));
    }
}
