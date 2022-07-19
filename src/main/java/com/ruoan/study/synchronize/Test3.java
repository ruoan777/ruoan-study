package com.ruoan.study.synchronize;

/**
 * @author xh.gao
 * @version 1.0.0
 * @ClassName Test3.java
 * @Description TODO test2给出了 类锁和对象锁不会互斥 这样一个结论
 * 这样两个方法同时修改static变量，比如都进行++运算，会造成一个方法覆盖掉另一个方法的修改的情况吗？
 * @createTime 2020年01月02日 20:21:00
 */
public class Test3 {
    public static int i = 0;

    public static void write() {

        //类锁
        synchronized (Test.class) {
            System.out.println("静态方法");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < 10; j++) {
                i++;
            }
        }
    }

    // 对象锁
    public synchronized void read() {
        System.out.println("非静态方法");

        for (int j = 0; j < 10; j++) {
            i++;
        }
    }

    public static void main(String[] args) {
        final Test3 test1 = new Test3();
        new Thread() {
            @Override
            public void run() {
                write();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                test1.read();
            }
        }.start();

        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(i);
    }
}