package com.ruoan.study.leetcode;

public class Solution121 {
    public int maxProfit(int[] prices) {
        int max = 0;
        int min = prices[0];

        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        return max;
    }
}
