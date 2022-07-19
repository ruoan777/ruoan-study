package com.ruoan.study.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xh.gao
 * @Description ProxyCalculator1的写法不够优雅，属于硬编码
 * 我这次代理A对象，下次代理B对象，还要进来改invoke()方法，太差劲了。
 * 改进一下，让调用者把目标对象作为参数传递进来
 * 这样，我们完美地跳过了代理类，直接创建了代理对象
 * @createTime 2020年07月03日 11:11:00
 */
public class ProxyCalculator2 {

    private static Object getProxy(final Object target) throws Exception {
        Class<?> proxyClazz = Proxy.getProxyClass(target.getClass().getClassLoader(), target.getClass().getInterfaces());
        Constructor<?> constructor = proxyClazz.getConstructor(InvocationHandler.class);
        Object proxy = constructor.newInstance(new InvocationHandler() {
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

    public static void main(String[] args) throws Exception {
        RealCalculator target = new RealCalculator();
        /**
         * 传入目标对象
         * 目的：1.根据它实现的接口生成代理对象 2.代理对象调用目标对象方法
         */
        Calculator calculatorProxy = (Calculator) getProxy(target);
        calculatorProxy.add(1,3);
    }

}
