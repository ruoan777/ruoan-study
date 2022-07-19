package com.ruoan.study.function;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年06月24日 20:52:00
 */
public class FunctionTest {

    public final static ThreadLocal<DateFormatter> formatter =
            ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));

    public int compute(int a, Function<Integer, Integer> function) {
        int result = function.apply(a);
        return result;
    }

    public static void main(String[] args) {
        FunctionTest test = new FunctionTest();
        int res = test.compute(5, value -> value * value);
        System.out.println(res);
        int res2 = test.compute(5, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * integer;
            }
        });
        System.out.println(res2);
        System.out.println("------------");
        test.compute(5, new ComputeFunction());

        System.out.println("------------");

        Predicate<Integer> atLeasts = x -> x > 5;
        boolean test1 = atLeasts.test(6);

        Predicate<Object> isNull = Objects::isNull;
        isNull.test(null);
        String s = null;
        isNull.test(s);
        ArrayList<Object> objects = new ArrayList<>();
        objects.stream();

    }
}
