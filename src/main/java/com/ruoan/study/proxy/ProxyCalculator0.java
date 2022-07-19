package com.ruoan.study.proxy;

/**
 * @author xh.gao
 * @Description 静态代理
 * @createTime 2020年07月03日 11:57:00
 */
public class ProxyCalculator0 implements Calculator {

    private Calculator realCalculator;

    public ProxyCalculator0(Calculator calculator) {
        this.realCalculator = calculator;
    }

    @Override
    public int add(int a, int b) {
        System.out.println("静态代理里面打印日志，in com.ruoan.study.proxy");
        return realCalculator.add(a, b);
    }


    public static void main(String[] args) {
        ProxyCalculator0 proxyCalculator0 = new ProxyCalculator0(new RealCalculator());
        int add = proxyCalculator0.add(2, 3);
        System.out.println(add);
    }
}
