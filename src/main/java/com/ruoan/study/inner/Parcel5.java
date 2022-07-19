package com.ruoan.study.inner;

/**
 * @author xh.gao
 * @Description 在方法的作用域内（而不是在其它类的作用域内）创建一个完整的类。即局部内部类
 * @createTime 2020年06月27日 12:11:00
 */
public class Parcel5 {

    public Destination destination(String s) {
        class PDestination implements Destination {

            private String label;

            private PDestination(String whereTo) {
                label = whereTo;
            }

            @Override
            public String readLabel() {
                return label;
            }
        }
        return new PDestination(s);
    }

    public static void main(String[] args) {
        Parcel5 p = new Parcel5();
        //进行向上转型,因为PDestination是destination()方法的一部分，而不是Parcel5类的一部分，所以，在destination()之外不能访问PDestination
        Destination shanghai = p.destination("shanghai");
    }
}
