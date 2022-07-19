package com.ruoan.study.inner.pkg2;

import com.ruoan.study.inner.pkg1.Ex6Interface;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年06月27日 11:43:00
 */
public class Ex6Base {
    protected class Ex6BaseInner implements Ex6Interface {
        public Ex6BaseInner() {
        }

        // need public constructor to create one in Ex6Base child:
        @Override
        public String say() {
            return "Hi";
        }
    }
}
