package com.ruoan.study.thread;

/**
 * @author xh.gao
 * @version 1.0.0
 * @ClassName MyRunnable.java
 * @Description TODO 在java中如果要创建线程的话，一般有两种方式：1）继承Thread类；2）实现Runnable接口。
 *              TODO 实现Runnable接口必须重写其run方法。
 * @createTime 2020年01月02日 16:06:00
 */
public class MyRunnable implements Runnable {

    public MyRunnable() {
    }

    @Override
    public void run() {
        System.out.println("子线程ID："+Thread.currentThread().getId());
    }

    public static void main(String[ ] args)  {
        System.out.println("主线程ID："+Thread.currentThread().getId());
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
    }

}
