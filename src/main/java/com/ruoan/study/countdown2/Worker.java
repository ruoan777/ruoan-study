package com.ruoan.study.countdown2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {

    private final CountDownLatch startSignal;

    private final CountDownLatch doneSignal;

    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            doWork();
            doneSignal.countDown();
        } catch (InterruptedException ex) {
        }
    }

    void doWork() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName() + "执行结束");
    }
}