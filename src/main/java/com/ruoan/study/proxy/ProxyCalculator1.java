package com.ruoan.study.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xh.gao
 * @Description 静态代理的缺陷：程序员要手动为每一个目标类编写对应的代理类，这样工作量太大
 * 所以我们努力的方向是，如何少写或者不写代理类，却能完成代理功能 于是引入了动态代理
 * 代理类和目标类理应实现相同的接口，之所以实现相同接口，是为了尽可能保证代理对象的内部结构和目标对象一致，
 * 这样我们对代理对象的操作最终都可以转移到目标对象身上，代理对象只需专注于增强代码的编写。
 * @createTime 2020年07月02日 20:36:00
 */
public class ProxyCalculator1 {
    public static void main(String[] args) throws Exception {
        /**
         * 参数1:Calculator的类加载器(当初把Calculator加载进内存的类加载器)
         * 参数2:代理对象需要和目标对象实现相同的接口Calculator
         */
        Class<?> calculatorProxyClass = Proxy.getProxyClass(Calculator.class.getClassLoader(), Calculator.class);
        //得到有参构造器 $Proxy0(InvocationHandler h)
        Constructor<?> constructor = calculatorProxyClass.getConstructor(InvocationHandler.class);
        //反射创建代理实例
        Calculator calculatorProxyImpl = (Calculator) constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                RealCalculator realCalculator = new RealCalculator();
                System.out.println("动态代理里面打印日志，in com.ruoan.study.proxy");
                System.out.println(proxy.getClass().getName());
                return method.invoke(realCalculator, args);
            }
        });
        int add = calculatorProxyImpl.add(1, 2);
        System.out.println(add);
    }
}
