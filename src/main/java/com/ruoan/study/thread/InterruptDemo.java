package com.ruoan.study.thread;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //while在try中，通过异常中断就可以退出run循环
                try {
                    while (true) {
                        //当前线程处于阻塞状态，异常必须捕捉处理，无法往外抛出
                        TimeUnit.SECONDS.sleep(200);
                    }
                } catch (InterruptedException e) {
                    System.out.println("我在睡眠中被打断了Interruted When Sleep" + System.currentTimeMillis());
//                    boolean interrupt = this.isInterrupted();
                    //中断状态被复位
//                    System.out.println("interrupt:" + interrupt);
                }
            }
        };
//        Thread t1 = new Thread() {
//            @Override
//            public void run() {
//
//            }
//        };
        Thread t1 = new Thread(runnable, "sad");
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        //中断处于阻塞状态的线程
        System.out.println("我要准备打断t1线程了" + System.currentTimeMillis());
        t1.interrupt();

        /**
         * 输出结果:
         Interruted When Sleep
         interrupt:false
         */
    }
}
