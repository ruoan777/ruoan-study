package com.ruoan.study.synchronize;

/**
 * @author xh.gao
 * @Description 如果一个对象有多个synchronized方法，某一时刻某个线程已经进入到了某个synchronized方法，
 * 那么在该方法没有执行完毕前，其他线程是无法访问该对象的任何synchronized方法的。
 * @createTime 2020年06月24日 10:39:00
 */
public class ThreadTest {
    public static void main(String[] args) {
        Example ex = new Example();
        Example ex2 = new Example();
        Thread t1 = new Thread1(ex);
        Thread t2 = new Thread2(ex2);

        //执行结果永远是执行完一个线程的输出再执行另一个线程的。　　
        t1.start();
        t2.start();
    }
}

/**
 * Java中的每个对象都有一个锁（lock），或者叫做监视器（monitor），
 * 当一个线程访问某个对象的synchronized方法时，将该对象上锁，
 * 其他任何线程都无法再去访问该对象的synchronized方法了
 * （这里是指所有的同步方法，而不仅仅是同一个方法），
 * 直到之前的那个线程执行方法完毕后（或者是抛出了异常），
 * 才将该对象的锁释放掉，其他线程才有可能再去访问该对象的synchronized方法。
 *
 * 此处即便传入不同的对象，静态方法同步仍然不允许多个线程同时执行
 */
class Example {
    /**
     * 非静态的同步方法会将对象上锁
     * 但是静态方法不属于对象，而是属于类，它会将这个方法所在的类的Class对象上锁。
     */
    public synchronized static void execute() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep((long) Math.random() * 1000);
                Thread.yield();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello: execute " + Thread.currentThread() + i);
        }
    }

    /**
     * 如果某个synchronized方法是static的，
     * 那么当线程访问该方法时，它锁的并不是synchronized方法所在的对象，
     * 而是synchronized方法所在的类所对应的Class对象。
     * Java中，无论一个类有多少个对象，这些对象会对应唯一一个Class对象，
     * 因此当线程分别访问同一个类的两个对象的两个static，
     * synchronized方法时，它们的执行顺序也是顺序的，
     * 也就是说一个线程先去执行方法，执行完毕后另一个线程才开始。
     */
    public synchronized static void execute2() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep((long) Math.random() * 1000);
                Thread.yield();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("world:execute2 " + Thread.currentThread() + i);
        }
    }
}

class Thread1 extends Thread {
    private Example example;

    public Thread1(Example example) {
        this.example = example;
    }

    @Override
    public void run() {
        Example.execute();
    }
}

class Thread2 extends Thread {
    private Example example;

    public Thread2(Example example) {
        this.example = example;
    }

    @Override
    public void run() {
        Example.execute2();
    }
}
