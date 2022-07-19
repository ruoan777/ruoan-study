package com.ruoan.study.iter;

import java.util.*;

/**
 * @author xh.gao
 * @Description 适配器模式。添加一种或者多种在foreach语句中使用这个类的方法
 * @createTime 2020年06月26日 18:11:00
 */
public class MultiIterableClass extends IterableClass {
    public Iterable<String> reversed() {
        return () -> new Iterator<String>() {
            int current = words.length - 1;

            @Override
            public boolean hasNext() {
                return current > -1;
            }

            @Override
            public String next() {
                return words[current--];
            }
        };
    }

    public Iterable<String> randomized() {
        return () -> {
            List<String> shuffled = new ArrayList<String>(Arrays.asList(words));
            Collections.shuffle(shuffled, new Random(47));
            return shuffled.iterator();
        };
    }

    public static void main(String[] args) {
        MultiIterableClass mic = new MultiIterableClass();
        for (String s : mic.reversed()) {
            System.out.print(s + " ");
        }

        System.out.println();

        for (String s : mic.randomized()) {
            System.out.print(s + " ");
        }

        System.out.println();

        for (String s : mic) {
            System.out.print(s + " ");
        }
    }
}
