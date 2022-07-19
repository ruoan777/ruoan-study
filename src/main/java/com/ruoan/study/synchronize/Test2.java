package com.ruoan.study.synchronize;

/**
 * @author xh.gao
 * @version 1.0.0
 * @ClassName Test2.java
 * @Description TODO 类锁和对象锁不会互斥
 * 如果一个线程执行一个对象的非static synchronized方法，
 * 另外一个线程需要执行这个对象所属类的static synchronized方法，此时不会发生互斥现象，
 * 因为访问static synchronized方法占用的是类锁，
 * 而访问非static synchronized方法占用的是对象锁，所以不存在互斥现象。
 * @createTime 2020年01月02日 18:53:00
 */
public class Test2 {
    public static void main(String[] args)  {
        final InsertData2 insertData = new InsertData2();
        new Thread(){
            @Override
            public void run() {
                insertData.insert();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                insertData.insert1();
            }
        }.start();
    }
}

class InsertData2 {
    public synchronized void insert(){
        System.out.println("执行insert");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行insert完毕");
    }

    public synchronized static void insert1() {
        System.out.println("执行insert1");
        System.out.println("执行insert1完毕");
    }
}