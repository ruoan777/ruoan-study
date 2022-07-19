package com.ruoan.study.async.demo;

import com.ruoan.study.async.BaseDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年09月05日 14:28:00
 */
public class Demo4 extends BaseDemo {

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void callback(long response) {
        System.out.println("得到结果");
        System.out.println(response);
        System.out.println("调用结束");
        countDownLatch.countDown();
    }

    public static void main(String[] args) {
        Demo4 demo4 = new Demo4();
        demo4.call();
        try {
            demo4.countDownLatch.await(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程内容");

    }
}