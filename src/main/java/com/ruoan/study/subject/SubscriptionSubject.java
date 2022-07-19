package com.ruoan.study.subject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xh.gao
 */
public class SubscriptionSubject implements Subject {
    /**
     * 储存订阅公众号的微信用户
     */
    private List<TestObserver> weChatUserList = new ArrayList<TestObserver>();

    @Override
    public void attach(TestObserver observer) {
        weChatUserList.add(observer);
    }

    @Override
    public void detach(TestObserver observer) {
        weChatUserList.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (TestObserver observer : weChatUserList) {
            observer.update(message);
        }
    }
}


