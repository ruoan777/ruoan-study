package com.ruoan.study.observer;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年09月22日 17:50:00
 */
public class ObserverTest {
    public static void main(String[] args) {
        // 被监听对象
        Thief thief = new Thief();
        // 监听器，直接new一个接口的匿名类对象
        ThiefListener thiefListener = new ThiefListener() {
            @Override
            public void shot(Event event) {
                System.out.println("shot shot shot！！！！");
            }
        };
        ThiefListener thiefListener2 = event -> System.out.println("shot shot shot！！！！");
        // 注册监听
        thief.registerListener(thiefListener);
        // 特定行为，触发监听器：内部调用listener.shot()
        thief.steal();
    }
}
