package com.ruoan.study.genertic;

import com.ruoan.study.genertic.pkg1.Apple;
import com.ruoan.study.genertic.pkg1.Fruit;
import com.ruoan.study.genertic.pkg1.Plate;

/**
 * @author xh.gao
 * @Description https://www.zhihu.com/question/20400700
 * 频繁往外读取内容的，适合用上界Extends。
 * 经常往里插入的，适合用下界Super。
 * @createTime 2020年09月05日 15:40:00
 */
public class Demo1 {
    public static void main(String[] args) {
        /**
         * <? extends Fruit>会使往盘子里放东西的set( )方法失效。但取东西get( )方法还有效。
         * 左边是一个 啥水果都能放的盘子，当然是一个苹果盘子了
         */
        Plate<? extends Fruit> plate = new Plate<Apple>(new Apple());//上界通配符
        //Plate<?>单纯的就表示：盘子里放了一个东西，是什么我不知道
        //plate.setItem(new Apple());//编译不通过

        /**
         * 使用下界<? super Fruit>会使从盘子里取东西的get( )方法部分失效，只能存放到Object对象里。set( )方法正常。
         */
        Plate<? super Fruit> plate2 = new Plate<Fruit>(new Fruit());//下界通配符
        plate2.setItem(new Fruit());
        Object item = plate2.getItem();
    }
}
