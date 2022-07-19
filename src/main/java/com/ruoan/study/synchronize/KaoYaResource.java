package com.ruoan.study.synchronize;

import java.util.concurrent.TimeUnit;

public class KaoYaResource {

    private String name;

    private int count;

    private volatile boolean flag;

    public synchronized void product(String name) {
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name + count;
        count++;
        System.out.println(Thread.currentThread().getName() + "生产者" + this.name);
        flag = true;
        this.notifyAll();
    }

    public synchronized void consume() {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "消费者" + this.name);
        flag = false;
        this.notifyAll();
    }

    public static void main(String[] args) {
        KaoYaResource kaoYaResource = new KaoYaResource();
        Thread producer1 = new Thread(new Producer(kaoYaResource));
        Thread producer2 = new Thread(new Producer(kaoYaResource));
        Thread consumer1 = new Thread(new Consumer(kaoYaResource));
        Thread consumer2 = new Thread(new Consumer(kaoYaResource));
        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }

}

class Producer implements Runnable {

    private KaoYaResource kaoYaResource;

    public Producer(KaoYaResource kaoYaResource) {
        this.kaoYaResource = kaoYaResource;
    }

    @Override
    public void run() {
        while (true) {
            kaoYaResource.product("烤鸭");
        }
    }
}

class Consumer implements Runnable {

    private KaoYaResource kaoYaResource;

    public Consumer(KaoYaResource kaoYaResource) {
        this.kaoYaResource = kaoYaResource;
    }

    @Override
    public void run() {
        while (true) {
            kaoYaResource.consume();
        }
    }
}
