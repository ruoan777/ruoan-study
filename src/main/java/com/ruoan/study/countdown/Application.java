package com.ruoan.study.countdown;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年08月25日 15:41:00
 */
public class Application {
    private CountDownLatch latch;

    public void startUp() throws Exception {
        latch = new CountDownLatch(2);
        List<Service> services = new ArrayList<>();
        services.add(new DatabaseCheckerService(latch));
        services.add(new HealthCheckService(latch));
        Executor executor = Executors.newFixedThreadPool(services.size());
        for (Service service : services) {
            executor.execute(service);
        }
        latch.await();
        System.out.println("all service is start up");
    }

    public static void main(String[] args) throws Exception {
        Application application = new Application();
        application.startUp();
    }
}
