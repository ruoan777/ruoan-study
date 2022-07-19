package com.ruoan.study.volatiletest;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年08月31日 16:14:00
 */
//线程类
class MyThread extends Thread {
    /**
     * volatile 共享静态变量，类成员
     */
    public volatile static int count;

    @Override
    public void run() {
        addCount();
    }

    private static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println("count=" + count);
    }
}

//测试类
public class TestVolatile3 {
    public static void main(String[] args) {
        //创建 100个线程并启动
        MyThread[] mythreadArray = new MyThread[100];
        for (int i = 0; i < 100; i++) {
            mythreadArray[i] = new MyThread();
        }

        for (int i = 0; i < 100; i++) {
            mythreadArray[i].start();
        }
    }
}