package com.ruoan.study.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年08月25日 15:40:00
 */
public class Service implements Runnable {

    private CountDownLatch latch;

    public Service(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            execute();
        } finally {
            if (latch != null) {
                latch.countDown();
            }
        }
    }

    public void execute() {
    }
}
