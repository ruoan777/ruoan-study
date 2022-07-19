package com.ruoan.study.countdown2;

import java.util.concurrent.CountDownLatch;

/**
 * 注意这种阻塞方式，是CountDownLatch源码里提供的例子
 * 像一个发令枪一样，只有十个选手都准备好后，才可以一起出发
 * <p>
 * The first is a start signal that prevents any worker from proceeding
 * until the driver is ready for them to proceed;
 */
public class Driver {
    public void countDownLatchTest() throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(10);

        // create and start threads
        for (int i = 0; i < 10; ++i) {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }

        doSomethingElseBefore();            // don't let run yet
        startSignal.countDown();      // let all threads proceed
        doneSignal.await();           // wait for all to finish
        System.out.println("选手都到达终点 ");
    }


    private void doSomethingElseBefore() throws InterruptedException {
        System.out.println("起跑前准备");
    }

    public static void main(String[] args) throws InterruptedException {
        Driver driver = new Driver();
        driver.countDownLatchTest();
    }
}
