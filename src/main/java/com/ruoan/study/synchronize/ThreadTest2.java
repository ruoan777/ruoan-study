package com.ruoan.study.synchronize;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年06月24日 16:48:00
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        ExampleTest exampleTest = new ExampleTest();
        Thread t1 = new Thread11(exampleTest);
        Thread t2 = new Thread22(exampleTest);
        t1.start();
        t2.start();
    }
}

class ExampleTest {
    //private Object object = new Object();

    public static void execute() {
        System.out.println("this is out of synchronized");
        synchronized (ExampleTest.class) {
            for (int i = 0; i < 20; ++i) {
                try {
                    Thread.sleep((long) Math.random() * 1000);
                    Thread.yield();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hello: " + i);
            }
        }
    }

    public static void execute2() {
        System.out.println("this is out of synchronized2");
        synchronized (ExampleTest.class) {
            for (int i = 0; i < 20; ++i) {
                try {
                    Thread.sleep((long) Math.random() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("World: " + i);
            }
        }
    }
}


class Thread11 extends Thread {
    private ExampleTest exampleTest;

    public Thread11(ExampleTest exampleTest) {
        this.exampleTest = exampleTest;
    }

    @Override
    public void run() {
        exampleTest.execute();
    }
}


class Thread22 extends Thread {
    private ExampleTest exampleTest;

    public Thread22(ExampleTest exampleTest) {
        this.exampleTest = exampleTest;
    }

    @Override
    public void run() {
        exampleTest.execute2();
    }
}