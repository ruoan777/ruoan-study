package com.ruoan.study.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年06月27日 16:20:00
 */
public class InfiniteRecursion2 {
    @Override
    public String toString() {
        //super.toString()
        //使用this关键字，会带来Exception in com.ruoan.study.thread "main" java.lang.StackOverflowError
        //编译器试着将this转换成一个String。它怎么转换呢，正是通过调用this上的toString方法，于是就发生了递归调用
//        return " InfiniteRecursion address: " + this + "\n";
        return " InfiniteRecursion address: " + super.toString() + "\n";
    }
    public static void main(String[] args) {
        List<InfiniteRecursion2> v = new ArrayList<InfiniteRecursion2>();
        for(int i = 0; i < 10; i++) {
            v.add(new InfiniteRecursion2());
        }
        System.out.println(v);
    }
}