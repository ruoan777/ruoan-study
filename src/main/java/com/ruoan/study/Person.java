package com.ruoan.study;

public class Person extends Object{
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(int age) {
        this.age = age;
        this.name = "ruoan";
    }

    @Override
    public String toString() {
        return this.name + "-" + this.age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
