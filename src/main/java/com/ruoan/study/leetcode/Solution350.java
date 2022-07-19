package com.ruoan.study.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> numMap = new HashMap();
        for (int i : nums2) {
            if (!numMap.containsKey(i)) {
                numMap.put(i, 1);
                continue;
            }
            numMap.put(i, numMap.get(i) + 1);
        }

        List<Integer> intersectList = new ArrayList();
        for (int i : nums1) {
            if (numMap.containsKey(i) && numMap.get(i) > 0) {
                intersectList.add(i);
                numMap.put(i, numMap.get(i) - 1);
            }
        }

        int[] res = new int[intersectList.size()];
        int index = 0;
        for (int i : intersectList) {
            res[index] = intersectList.get(index);
            index++;
        }
        return res;
    }
}
