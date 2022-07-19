package com.ruoan.study.synchronize;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年06月25日 11:14:00
 */
public class EvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;
    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
