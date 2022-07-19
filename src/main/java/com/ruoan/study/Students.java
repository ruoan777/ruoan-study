package com.ruoan.study;

/**
 * @author ruoan
 * @date 2022/7/6 11:16 下午
 */
public class Students {
    private String name;

    public Students(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
