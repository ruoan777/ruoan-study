package com.ruoan.study;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger(1);
        AtomicInteger b = new AtomicInteger(100);
        int c = a.addAndGet(5);
        b.addAndGet(4);
        System.out.println(a.get());
        System.out.println(c);
    }
}
