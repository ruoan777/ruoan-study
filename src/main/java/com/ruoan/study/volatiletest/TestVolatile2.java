package com.ruoan.study.volatiletest;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年08月31日 16:02:00
 */

class ThreadDemo implements Runnable {

    public boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        flag = true;
        System.out.println("线程 " + Thread.currentThread().getName() + " 执行完毕： " + "置  flag= " + flag + " ...");
    }
}

public class TestVolatile2 {

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td, "ThreadDemo").start();

        while (true) {
            // 加上下面三句代码的任意一句，程序都会正常结束：
             System.out.println("!!");                              //...语句1
//             synchronized (TestVolatile2.class) {}                     //...语句2
//            TestVolatile2.test2();                                    //...语句3

            // 若只加上下面一句代码，程序都会死循环：
            TestVolatile2.test1();                                  //...语句4
            if (td.flag) {
                System.out.println("线程 " + Thread.currentThread().getName() + " 即将跳出while循环体... ");
                break;
            }
        }
    }

    public static void test1() {
    }

    public synchronized static void test2() {
    }
}