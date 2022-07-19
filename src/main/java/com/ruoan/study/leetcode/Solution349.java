package com.ruoan.study.leetcode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Solution349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> uniqueNum = new HashSet();
        for (int i : nums1) {
            uniqueNum.add(i);
        }
        Set<Integer> intersectionSet = new HashSet();
        for (int i : nums2) {
            if (uniqueNum.contains(i)) {
                intersectionSet.add(i);
            }
        }

        int[] res = new int[intersectionSet.size()];
        Iterator<Integer> iterator = intersectionSet.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            res[index++] = i;
        }
        return res;
    }
}
