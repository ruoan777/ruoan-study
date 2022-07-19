package com.ruoan.study.async.demo;

import com.ruoan.study.async.BaseDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年09月05日 14:13:00
 */
public class Demo2 extends BaseDemo {
    private final Lock lock = new ReentrantLock();
    private final Condition con = lock.newCondition();

    @Override
    public void callback(long response) {
        System.out.println("得到结果");
        System.out.println(response);
        System.out.println("调用结束");
        lock.lock();
        try {
            con.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();
        demo2.call();
        demo2.lock.lock();
        try {
            demo2.con.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            demo2.lock.unlock();
        }
        System.out.println("主线程内容");
    }
}