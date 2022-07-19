package com.ruoan.study.leetcode;

public class Solution509 {
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] fibArray = new int[3];
        int n_1 = 0;
        int n_2 = 1;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = n_1 + n_2;
            n_1 = n_2;
            n_2 = res;
        }
        return res;
    }
}
