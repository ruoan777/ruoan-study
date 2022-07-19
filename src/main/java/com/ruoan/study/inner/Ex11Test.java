package com.ruoan.study.inner;


/**
 * @author xh.gao
 * @Description
 * @createTime 2020年06月27日 12:25:00
 */
public class Ex11Test {
    public static void main(String[] args) {
        Ex11 t = new Ex11();
        t.f().say("hi");
        // Error: cannot find symbol: class Inner: 向下转型是不ok的
//        ((Inner)t.f()).say("hello");
    }
}
