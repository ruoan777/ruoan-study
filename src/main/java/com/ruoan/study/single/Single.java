package com.ruoan.study.single;

import java.lang.reflect.Constructor;

/**
 * @author xh.gao
 * @Description 它真的线程安全吗？你忽略了指令重排
 * 指令重排是什么意思呢？比如java中简单的一句 instance = new Singleton，会被编译器编译成如下JVM指令：
 * memory =allocate(); //1：分配对象的内存空间
 * ctorInstance(memory); //2：初始化对象
 * instance =memory; //3：设置instance指向刚分配的内存地址
 * <p>
 * 但是这些指令顺序并非一成不变，有可能会经过JVM和CPU的优化，指令重排成下面的顺序：
 * memory =allocate(); //1：分配对象的内存空间
 * instance =memory; //3：设置instance指向刚分配的内存地址
 * ctorInstance(memory); //2：初始化对象
 * <p>
 * 当线程A执行完1,3,时，instance对象还未完成初始化，但已经不再指向null。
 * 此时如果线程B抢占到CPU资源，执行到第一个if(instance == null)的结果会是false，
 * 从而返回一个没有初始化完成的instance对象。
 * @createTime 2020年06月10日 11:01:00
 */
public class Single {
    private static Single instance;

    private Single() {
    }

    private static Single getInstance() {
        if (instance == null) {
            synchronized (Single.class) {
                if (instance == null) {
                    instance = new Single();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        //利用反射打破单例
        //获得构造器
        Constructor con = Single.class.getDeclaredConstructor();
        //设置为可访问
        con.setAccessible(true);
        //构造两个不同的对象
        Single singleton1 = (Single) con.newInstance();
        Single singleton2 = (Single) con.newInstance();
        //验证是否是不同对象
        System.out.println(singleton1.equals(singleton2));
//        new Thread() {
//            @Override
//            public void run() {
//                System.out.println(Single.getInstance());
//            }
//        }.start();
//
//        new Thread() {
//            @Override
//            public void run() {
//                System.out.println(Single.getInstance());
//            }
//        }.start();
    }
}
