package com.ruoan.study.synchronize;

/**
 * @author xh.gao
 * @version 1.0.0
 * @ClassName Test3gxh.java
 * @Description TODO
 * @createTime 2020年01月02日 20:33:00
 */
public class Test3gxh {

    public static void main(String[] args)  {
        final InsertData3gxh insertData = new InsertData3gxh();
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

class InsertData3gxh {

    public static int i = 0;

    public synchronized void insert(){
        System.out.println("执行insert");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i++;
        System.out.println("执行insert i = " + i);
        System.out.println("执行insert完毕");
    }

    public synchronized static void insert1() {
        System.out.println("执行insert1");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i++;
        System.out.println("执行insert1 i =" + i);
        System.out.println("执行insert1完毕");
    }
}