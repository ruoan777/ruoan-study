package com.ruoan.study.countdown3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Driver2 {

    void countDownLatchTest2() throws InterruptedException {
        CountDownLatch doneSignal = new CountDownLatch(10);
        Executor e = Executors.newFixedThreadPool(10);
        // create and start threads
        for (int i = 0; i < 10; ++i) {
            e.execute(new WorkerRunnable(doneSignal, i));
        }
        // wait for all to finish
        doneSignal.await();
        System.out.println("全部执行结束");
    }

    public static void main(String[] args) throws InterruptedException {
        Driver2 driver = new Driver2();
        driver.countDownLatchTest2();
    }
}