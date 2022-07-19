package com.ruoan.study.init;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xh.gao
 * @Description
 * 如果你在别的类调用getInstance，就会报错ExceptionInInitializerError。
 * 这是因为类加载时不会为实例变量赋值，对象创建时不会为静态变量赋值。
 * 我们调用getInstance时，此类就开始加载，加载的时候不会为实例变量赋值，但是会按顺序给静态变量赋值，
 * 所以先为example赋值，然后为test赋值即初始化。
 * 但为example赋值时出现了个小插曲，它会调用构造方法创建一个对象。
 * 对象创建时不会为静态变量test赋值，而构造器内却已经调用test，于是报错了。
 * 原文链接：https://blog.csdn.net/fykhlp/article/details/6236316
 *
 *
 * todo 改为这样就可以了
 * private static Map<Integer,Boolean> test =
 * 		new HashMap<Integer, Boolean>();
 * private static Example example = new Example();
 * @createTime 2020年07月07日 16:26:00
 */
public class Example {

    private static Example example = new Example();

    private static Map<Integer, Boolean> test = new HashMap<Integer, Boolean>();

    private Example() {
        test.put(1, true);
    }

    public static Example getInstance() {
        return example;
    }

    public static void main(String[] args) {
        System.out.println(1);
        Example.getInstance();
    }
}