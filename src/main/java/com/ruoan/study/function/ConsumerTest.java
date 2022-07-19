package com.ruoan.study.function;

import java.util.function.Consumer;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年09月05日 16:27:00
 */
public class ConsumerTest {
    public static void main(String[] args) {
        System.out.println("------Consumer------");
        Consumer consumer = (m) -> System.out.println("consumer>" + m);
        Consumer consumer2 = (m) -> System.out.println("consumer2>" + m);

        consumer.accept("这是第一个consumer");
        consumer.andThen(consumer2).accept("This is second");
        System.out.println("------Consumer------");
    }
}
