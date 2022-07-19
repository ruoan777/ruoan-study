package com.ruoan.study.synchronize;

import java.util.ArrayList;

/**
 * @author xh.gao
 * @version 1.0.0
 * @ClassName Test.java
 * @Description TODO 说明两个线程在同时执行insert方法。
 * @createTime 2020年01月02日 18:41:00
 */
public class Test {

    public static void main(String[] args) {
        final InsertData insertData = new InsertData();

        new Thread() {
            public void run() {
                insertData.insert(Thread.currentThread());
            }

            ;
        }.start();


        new Thread() {
            public void run() {
                insertData.insert(Thread.currentThread());
            }

            ;
        }.start();
    }
}

class InsertData {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public synchronized void insert(Thread thread) {
        for (int i = 0; i < 5; i++) {
            System.out.println(thread.getName() + "在插入数据" + i);
            arrayList.add(i);
        }
    }
}

/*
 * 如果在insert方法前面不加关键字synchronized的话
 * Thread-0在插入数据0
 * Thread-1在插入数据0
 * Thread-1在插入数据1
 * Thread-1在插入数据2
 * Thread-1在插入数据3
 * Thread-1在插入数据4
 * Thread-0在插入数据1
 * Thread-0在插入数据2
 * Thread-0在插入数据3
 * Thread-0在插入数据4
 *
 * */


/*
 * 如果在insert方法前面加上关键字synchronized的话
 * Thread-0在插入数据0
 * Thread-0在插入数据1
 * Thread-0在插入数据2
 * Thread-0在插入数据3
 * Thread-0在插入数据4
 * Thread-1在插入数据0
 * Thread-1在插入数据1
 * Thread-1在插入数据2
 * Thread-1在插入数据3
 * Thread-1在插入数据4
 * */