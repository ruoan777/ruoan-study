package com.ruoan.study.inner;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年06月27日 12:23:00
 */
class Ex11 {
    private class Inner implements Ex11Interface {
        @Override
        public void say(String s) {
            System.out.println(s);
        }
    }
    Ex11Interface f() {
        //向上转型是ok的
        return new Inner();
    }
}