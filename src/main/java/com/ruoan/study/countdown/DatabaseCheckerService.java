package com.ruoan.study.countdown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年08月25日 15:41:00
 */
public class DatabaseCheckerService extends Service {

    public DatabaseCheckerService(CountDownLatch latch) {
        super(latch);
    }

    @Override
    public void execute() {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("DatabaseCheckerService done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
