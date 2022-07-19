package com.ruoan.study.inner;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年06月27日 12:32:00
 */
public class Parcel8 {
    public Wrapping wrapping(int x){
        return new Wrapping(x){
            @Override
            public int value(){
                return super.value()*47;
            }
        };
    }

    public static void main(String[] args) {

        Parcel8 p = new Parcel8();
        p.wrapping(10);
    }
}
