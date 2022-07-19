package com.ruoan.study.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年10月23日 19:23:00
 */
public class ProxyTest {
    public static void main(String[] args) {
        ProImp proImp = new ProImp();
        PorxyInte porxyInte = (PorxyInte) Proxy.newProxyInstance(ProImp.class.getClassLoader(), ProImp.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //输出proxy对象的字节码文件名
                System.out.println("..." + proxy.getClass().getName());
                Method[] declaredMethods = this.getClass().getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    System.out.println(declaredMethod.getName());
                }

//                method.invoke(proImp, null);
                return null;
            }
        });
        //输出实现类的字节码文件名
        System.out.println(porxyInte.getClass().getName());
        porxyInte.test();
    }

}
