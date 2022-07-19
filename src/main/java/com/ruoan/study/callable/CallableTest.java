package com.ruoan.study.callable;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class CallableTest {

    static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            log.debug("com.ruoan.study.thread [{}] occur exception", t.getName(), e);
        }
    }

    private static AtomicInteger atomicInteger = new AtomicInteger();


    private static ThreadFactory threadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "ruoan-Thread-" + atomicInteger.getAndIncrement());
            thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            return thread;
        }
    };

    private static ExecutorService executorService = new ThreadPoolExecutor(5,
            50, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<>(20),
            threadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws Exception {
//        demo3();
        demo2();
    }

    private static void demo1() {
        MyRunnable myRunnable = new MyRunnable();
        log.debug("i am begin");
        executorService.execute(myRunnable);
        log.debug("i am done");
    }

    private static void demo2() {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        MyCallable myCallable = new MyCallable();
        log.debug("i am begin");
        FutureTask<String> futureTask = new FutureTask<String>(myCallable) {
            @Override
            protected void done() {
                super.done();
                log.debug("i am done");
            }
        };
        executorService.execute(futureTask);
        log.debug("i am doing");
        String s = null;
        try {
            s = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            log.debug("exception {}", e);
//        } catch (TimeoutException e) {
//            log.debug("exception {}", e);
//        } finally {
            futureTask.cancel(true);
        }
        log.debug("i am done {}", s);
    }

    private static void demo3() {
        MyRunnable myRunnable = new MyRunnable();
        log.debug("i am begin");
        Future<?> submit = executorService.submit(myRunnable);
        try {
            submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        log.debug("i am done");
    }

}

class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
//        TimeUnit.SECONDS.sleep(300);
        int i = 1 / 0;
        return "hello";
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
//        TimeUnit.SECONDS.sleep(3);
        int i = 1 / 0;
        return;
    }
}
