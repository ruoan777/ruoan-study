package com.ruoan.study.proxy;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年07月02日 20:35:00
 */
public class RealCalculator implements Calculator {

    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
