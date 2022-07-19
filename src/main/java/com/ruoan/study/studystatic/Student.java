package com.ruoan.study.studystatic;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年06月13日 18:25:00
 */
public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public static void method() {
        System.out.println("hello static");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
