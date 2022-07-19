package com.ruoan.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xh.gao
 * @Description 不过实际编程中，一般不用getProxyClass()，
 * 而是使用Proxy类的另一个静态方法：Proxy.newProxyInstance()，直接返回代理实例，连中间得到代理Class对象的过程都帮你隐藏
 * @createTime 2020年07月03日 11:24:00
 */
public class ProxyCalculator3 {

    public static void main(String[] args) throws Exception {
        RealCalculator target = new RealCalculator();
        Calculator calculatorProxy = (Calculator) getProxy(target);
        calculatorProxy.add(1,3);
    }
    private static Object getProxy(final Object target) throws Exception {
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(method.getName() + "方法开始执行");
                        Object result = method.invoke(target, args);
                        System.out.println(result);
                        System.out.println(method.getName() + "方法执行结束");
                        return result;
                    }
                });
        return proxy;
    }
}
