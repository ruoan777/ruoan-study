package com.ruoan.study.thread;

/**
 * @author xh.gao
 * @version 1.0.0
 * @ClassName MyThread1.java
 * @Description TODO 在java中如果要创建线程的话，一般有两种方式：1）继承Thread类；2）实现Runnable接口。
 * @createTime 2020年01月02日 15:52:00
 */
public class MyThread1 extends Thread{
    private String name;

    public MyThread1(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("name:"+name+" 子线程ID:"+Thread.currentThread().getId());
    }

    public static void main(String[] args)  {
        System.out.println("主线程ID:"+Thread.currentThread().getId());
        MyThread1 thread1 = new MyThread1("thread1");
        thread1.start();
        MyThread1 thread2 = new MyThread1("thread2");
        thread2.run();
    }
}
/*
 *从输出结果可以得出以下结论：
 *
 * 1）thread1和thread2的线程ID不同，thread2和主线程ID相同，说明通过run方法调用并不会创建新的线程，而是在主线程中直接运行run方法，跟普通的方法调用没有任何区别；
 *
 * 2）虽然thread1的start方法调用在thread2的run方法前面调用，但是先输出的是thread2的run方法调用的相关信息，说明新线程创建的过程不会阻塞主线程的后续执行。
 */