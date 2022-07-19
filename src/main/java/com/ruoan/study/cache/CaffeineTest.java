package com.ruoan.study.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年07月06日 14:01:00
 */
public class CaffeineTest {
    private static Cache<String, String> cache = Caffeine.newBuilder().maximumSize(100).build();

    static {
        cache.put("test_key", "test_value");
    }

    public static void main(String[] args) {
        String value = cache.getIfPresent("test_key");
        System.out.println(value);
    }
}
