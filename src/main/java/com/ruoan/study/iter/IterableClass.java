package com.ruoan.study.iter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年06月26日 17:35:00
 */
public class IterableClass implements Iterable<String> {

    protected String[] words = ("my name is gaoxh ").split(" ");

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < words.length;
            }

            @Override
            public String next() {
                return words[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
    }

    public static void main(String[] args) {
        final List<Integer> list = Arrays.asList(1, 2, 3);
        for (String s : new IterableClass()) {
            System.out.print(s + " ");
        }
        for (Map.Entry entry : System.getenv().entrySet()) {
            System.out.println(entry.getKey() +":"+entry.getValue());

        }
    }
}
