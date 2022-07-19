package com.ruoan.study.async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author xh.gao
 * @Description 我们主要关心call方法，这个方法接收了一个demo参数，并且开启了一个线程，在线程中执行具体的任务，并利用demo的callback方法进行回调函数的调用。大家注意到了这里的返回结果就是一个[0,10)的长整型，并且结果是几，就让线程sleep多久——这主要是为了更好地观察实验结果，模拟异步调用过程中的处理时间。
 * 至于futureCall和shutdown方法，以及线程池tp都是为了demo3利用Future来实现做准备的。
 * demo的基类:
 * <p>
 * 作者：程序员Sunny
 * 链接：https://www.jianshu.com/p/f00aa6f66281
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @createTime 2020年09月05日 11:29:00
 */
public class AsyncCall {

    private Random random = new Random(System.currentTimeMillis());

    private static final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("ruoan-pool-%d").build();

    private static final ExecutorService tp = new ThreadPoolExecutor(10, 20, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * demo1,2,4,5调用方法
     *
     * @param demo
     */
    public void call(BaseDemo demo) {
        new Thread(() -> {
            long res = random.nextInt(10);
            try {
                Thread.sleep(res * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demo.callback(res);
        }).start();
    }

    /**
     * demo3调用方法
     *
     * @return
     */
    public Future<Long> futureCall() {
        return tp.submit(() -> {
            long res = random.nextInt(10);
            try {
                Thread.sleep(res * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return res;
        });
/*        Future<Long> submit = tp.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                long res = random.nextInt(10);
                try {
                    Thread.sleep(res * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return res;
            }
        });
        return submit;*/
    }

    public void shutdown() {
        tp.shutdown();
    }

}