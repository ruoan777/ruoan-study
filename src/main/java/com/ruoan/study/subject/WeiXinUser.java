package com.ruoan.study.subject;

/**
 * @description:
 * @author: xh.gao
 * @createDate: 2019/10/15
 */
public class WeiXinUser implements TestObserver {
    /**
     * 微信用户名
     */
    private String name;

    public WeiXinUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + "-" + message);
    }
}