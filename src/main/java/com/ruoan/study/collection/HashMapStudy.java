package com.ruoan.study.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年06月24日 17:37:00
 */
public class HashMapStudy {

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("语文", 1);
        map.put("数学", 2);
        map.put("英语", 3);
        map.put("历史", 4);
        map.put("政治", 5);
        map.put("地理", 6);
        map.put("生物", 7);
        map.put("化学", 8);
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
        map.forEach((k, v) -> {
            k += "成绩";
            v += 1;
            System.out.println(k + "=" + v);
        });
        System.out.println("--------------");

        //上述写法等价于
        BiConsumer<String, Integer> consumer1 = (k, v) -> {
            k += "成绩";
            v += 1;
            System.out.println(k + "=" + v);
        };
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            consumer1.accept(entry.getKey(), entry.getValue());
        }
        System.out.println("--------------");

        BiConsumer<String, Integer> consumer2 = (k, v) -> {
            k += "是";
            v += 1;
            System.out.println(k + "=" + v);
        };
//        map.forEach(consumer1.andThen(consumer2));
        System.out.println("--------------");
    }
}
