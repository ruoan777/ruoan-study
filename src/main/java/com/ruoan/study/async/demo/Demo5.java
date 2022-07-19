package com.ruoan.study.async.demo;

import com.ruoan.study.async.BaseDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年09月05日 14:33:00
 */
public class Demo5 extends BaseDemo {

    /**
     * 初始化的时候，参数数字要设为2，因为异步调用这里是一个线程，而主线程是一个线程，
     * 两个线程都await的时候才能继续执行，这也是和CountDownLatch区别的部分
     */
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    @Override
    public void callback(long response) {
        System.out.println("得到结果");
        System.out.println(response);
        System.out.println("调用结束");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Demo5 demo5 = new Demo5();
        demo5.call();
        try {
            demo5.cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("主线程内容");
    }
}