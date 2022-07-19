package com.ruoan.study.concurrent;

public class Ticket implements Runnable {
    //当前拥有的票数
    private volatile int num = 100;

    public synchronized void run() {
        while (true) {
            if (num > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                //输出卖票信息
                System.out.println(Thread.currentThread().getName() + ".....sale...." + num--);
            }
        }
    }

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Thread t1 = new Thread(ticket);
        Thread t2 = new Thread(ticket);
        Thread t3 = new Thread(ticket);
        Thread t4 = new Thread(ticket);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}