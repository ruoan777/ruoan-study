package com.ruoan.study.subject;

/**
 * @description: 订阅者模式的实现
 * @author: xh.gao
 * @createDate: 2019/10/15
 */
public class TestClient {
    public static void main(String[] args) {
        SubscriptionSubject mSubscriptionSubject = new SubscriptionSubject();
        //创建微信用户
        WeiXinUser user1 = new WeiXinUser("张三");
        WeiXinUser user2 = new WeiXinUser("李四");
        WeiXinUser user3 = new WeiXinUser("王五");
        //订阅公众号
        mSubscriptionSubject.attach(user1);
        mSubscriptionSubject.attach(user2);
        mSubscriptionSubject.attach(user3);
        //公众号更新发出消息给订阅的微信用户
        mSubscriptionSubject.notify("微信官方公众号更新了");
    }
}