package com.ruoan.study.init;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xh.gao
 * https://blog.csdn.net/u010397369/article/details/16810863
 * @Description 学习静态初始化的一个很好的例子
 * todo 想让下面的程序运行，把    private static List<String> LIST_A = getListA();
 * todo 放在   private static StaticParams sp = buildStaticParams();之前就可以
 * @createTime 2020年07月07日 16:33:00
 */
public class StaticParams {

    private static StaticParams sp = buildStaticParams();

    private static int NUM_A = getA();
    private static int NUM_B = getB();
    private static List<String> LIST_A = getListA();

    private StaticParams() {
        System.out.println("初始化构造方法");
    }


    /**
     * sp的声明在其他几个静态变量之前
     *
     * @return
     */
    private static StaticParams buildStaticParams() {
        if (null == sp) {
            sp = new StaticParams();
        }
        // 基本类型有默认值，此处不会报错，但结果不正确
        int result = NUM_A * NUM_B;
        System.out.println("result is :" + result);
        //此时LIST_A还未初始化，到此有异常
        LIST_A.add("abcd");
        return sp;
    }

    public static StaticParams getInstance() {
        return sp;
    }

    private static int getA() {
        System.out.println("初始化A");
        return 10;
    }

    private static int getB() {
        System.out.println("初始化B");
        return 20;
    }

    private static List<String> getListA() {
        System.out.println("初始化List");
        return new ArrayList<String>();
    }

    public static void main(String[] args) {
        StaticParams.getInstance();
    }
}
