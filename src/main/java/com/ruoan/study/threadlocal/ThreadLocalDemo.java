package com.ruoan.study.threadlocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年07月20日 20:14:00
 */
public class ThreadLocalDemo {
    /**
     * ThreadLocal一般权限声明为private static
     * ThreadLocal对象的目的：用于防止对可变的单实例变量或全局变量进行共享
     * 但请记住，这里无论多少个线程，ThreadLocal<Person> aPerson对象只有一份
     */
    private static final ThreadLocal<Person> A_PERSON = new ThreadLocal<Person>() {
        // 默认返回null，在此override初始化方法
        @Override
        protected Person initialValue() {
            return new Person("A Baby", 0);
        }
    };

    Random rand = new Random();

    // 辅助演示的javabean
    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return this.name + "-" + this.age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    // 辅助演示的Runnable实现类
    class MyTask implements Runnable {

        private boolean needSet;

        MyTask(boolean needSet) {
            this.needSet = needSet;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Start......");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (needSet) {
                // 向ThreadLocal设置一个值
                A_PERSON.set(new Person(Thread.currentThread().getName(), rand.nextInt(60)));
            }
            System.out.println("Thread Name: " + Thread.currentThread().getName() + "."
                    + "This com.ruoan.study.thread's ThreadLocal: " + A_PERSON.get().toString());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalDemo foo = new ThreadLocalDemo();
        Thread t1 = new Thread(foo.new MyTask(true), "Henry");
        Thread t2 = new Thread(foo.new MyTask(true), "Cevin");
        // 该线程不设置ThreadLocal，直接使用初始值
        Thread t3 = new Thread(foo.new MyTask(false), "Jessica");
        t1.start();
        t2.start();
        t3.start();
    }
} /* Output:
Jessica Start......
Henry Start......
Cevin Start......
This com.ruoan.study.thread's ThreadLocal: A Baby-0
This com.ruoan.study.thread's ThreadLocal: Cevin-24
This com.ruoan.study.thread's ThreadLocal: Henry-12
*///:~