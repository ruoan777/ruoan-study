package com.ruoan.study.function;

import java.util.function.Function;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年06月24日 20:56:00
 */
public class ComputeFunction implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer integer) {
        return integer * integer;
    }
}
