package com.ruoan.study.countdown3;

import java.util.concurrent.CountDownLatch;

class WorkerRunnable implements Runnable {

    private final CountDownLatch doneSignal;

    private final int i;

    WorkerRunnable(CountDownLatch doneSignal, int i) {
        this.doneSignal = doneSignal;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            doWork(i);
            doneSignal.countDown();
        } catch (Exception e) {
        } // return;
    }

    void doWork(int i) {
        System.out.println(i + "执行中");
    }
}
