package com.ruoan.study.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @author ruoan
 * @date 2022/6/17 12:02 上午
 */
public class WeakRefTest {
    public static void main(String[] args) throws InterruptedException {
        firstStack();
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        Thread thread = Thread.currentThread();
        TimeUnit.SECONDS.sleep(1);
        System.gc();
        System.out.println(thread); // 在这里打断点，观察thread对象里的ThreadLocalMap数据

    }

    // 通过是否获取返回值观察A对象里的local对象是否被回收
    private static A firstStack() {
        A a = new A();
        System.out.println("value: " + a.get());
        return a;
    }

    private static class A {
        private ThreadLocal<String> local = ThreadLocal.withInitial(() -> "in class A");

        public String get() {
            return local.get();
        }

        public void set(String str) {
            local.set(str);
        }

    }
}
