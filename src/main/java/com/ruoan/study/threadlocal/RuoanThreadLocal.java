package com.ruoan.study.threadlocal;

import com.ruoan.study.Person;

public class RuoanThreadLocal extends InheritableThreadLocal<Person> {
    /**
     * 重写以实现深拷贝
     * @param parentValue
     * @return
     */
    @Override
    protected Person childValue(Person parentValue) {
        return new Person(parentValue.getAge());
    }
}
