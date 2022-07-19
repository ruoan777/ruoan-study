package com.ruoan.study.countdown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年08月25日 15:41:00
 */
public class HealthCheckService extends Service {

    public HealthCheckService(CountDownLatch latch) {
        super(latch);
    }

    @Override
    public void execute() {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("HealthCheckService done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
