package com.ruoan.study.inner.pkg3;

import com.ruoan.study.inner.pkg1.Ex6Interface;
import com.ruoan.study.inner.pkg2.Ex6Base;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年06月27日 11:45:00
 */
public class Ex6 extends Ex6Base {
    Ex6Interface getBaseInner() {
        return this.new Ex6BaseInner();
    }
    public static void main(String[] args) {
        Ex6 ex = new Ex6();
        System.out.println(ex.getBaseInner().say());
    }
}