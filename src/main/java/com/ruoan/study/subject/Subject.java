package com.ruoan.study.subject;

public interface Subject {
    /**
     * 增加订阅者
     *
     * @param observer
     */
    void attach(TestObserver observer);

    /**
     * 删除订阅者
     *
     * @param observer
     */
    void detach(TestObserver observer);

    /**
     * 通知订阅者更新消息
     */
    void notify(String message);
}