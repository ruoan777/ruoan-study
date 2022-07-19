package com.ruoan.study.async;

/**
 * @author xh.gao
 * @Description 5种必会的Java异步调用转同步的方法你会几种
 * @createTime 2020年09月05日 11:30:00
 */
public abstract class BaseDemo {

    protected AsyncCall asyncCall = new AsyncCall();

    public abstract void callback(long response);

    public void call(){
        System.out.println("发起调用");
        asyncCall.call(this);
        System.out.println("调用返回");
    }

}
