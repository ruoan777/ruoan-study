package com.ruoan.study.volatiletest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年08月31日 15:54:00
 */
//资源类
class MyList {

    // 临界资源
    private volatile List list = new ArrayList();

    // 对临界资源的访问
    public void add() {
        list.add("rico");
    }

    public int size() {
        return list.size();
    }
}

// 线程B
class ThreadB extends Thread {

    private MyList list;

    public ThreadB(MyList list) {
        super();
        this.list = list;
    }

    @Override
    public void run() { // 任务 B
        try {
            while (true) {
                if (list.size() == 2) {
                    System.out.println("list中的元素个数为2了，线程b要退出了！");
                    throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// 线程A
class ThreadA extends Thread {

    private MyList list;

    public ThreadA(MyList list) {
        super();
        this.list = list;
    }

    @Override
    public void run() { // 任务 A
        try {
            for (int i = 0; i < 3; i++) {
                list.add();
                System.out.println("添加了" + (i + 1) + "个元素");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class TestVolatile {
    public static void main(String[] args) {
        MyList service = new MyList();

        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();

        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
    }
}
