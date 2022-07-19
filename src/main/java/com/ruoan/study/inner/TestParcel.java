package com.ruoan.study.inner;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年06月27日 11:36:00
 */
public class TestParcel{
    public static void main(String[] args) {
        Parcel4 p =  new Parcel4();
        Contents contents = p.contents();
        Destination shanghai = p.destination("shanghai");
    }
}
