package com.ruoan.study.collection;

import com.google.common.collect.Lists;

import java.util.*;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.addAll(Lists.newArrayList(1, 2, 3, 4, 5));
        list.clone();
//        func1(list);
    }

    private static void func1(LinkedList<Integer> list) {
        ListIterator<Integer> iterator = list.listIterator(2);
        Iterator<Integer> descendingIterator = list.descendingIterator();
        descendingIterator.forEachRemaining(x -> System.out.println(x));
        iterator.forEachRemaining(x -> System.out.println(x));
        while (iterator.hasNext()) {

            int i = iterator.nextIndex();
            Integer next = iterator.next();
            System.out.println("index=" + i + "next = " + next);
        }
        while (iterator.hasPrevious()) {
            Integer previous = iterator.previous();
            System.out.println(previous);
        }
    }
}
