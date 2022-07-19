package com.ruoan.study.thread;

/**
 * @author xh.gao
 * @version 1.0.0
 * @ClassName MyThread.java
 * @Description TODO 在java中如果要创建线程的话，一般有两种方式：1）继承Thread类；2）实现Runnable接口。
 * @createTime 2020年01月02日 15:44:00
 */
public class MyThread2 extends Thread {
    private static int num = 0;

    public MyThread2(){
        num++;
    }

    // 继承Thread类的话，必须重写run方法，在run方法中定义需要执行的任务。
    @Override
    public void run() {
        System.out.println("主动创建的第"+num+"个线程");
    }

    public static void main(String[] args)  {
        // 创建好了自己的线程类之后，就可以创建线程对象了，然后通过start()方法去启动线程。
        // 注意，不是调用run()方法启动线程，run方法中只是定义需要执行的任务，
        // 如果调用run方法，即相当于在主线程中执行run方法，跟普通的方法调用没有任何区别，
        // 此时并不会创建一个新的线程来执行定义的任务。
        MyThread2 thread = new MyThread2();
        thread.start();
    }
}