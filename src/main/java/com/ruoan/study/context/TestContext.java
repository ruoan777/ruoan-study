package com.ruoan.study.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.Map;

/**
 * @author xh.gao
 * @version 1.0.0
 * @ClassName TestContext.java
 * @Description 测试getBeansOfType方法 以及 appContext.xml文件的读取
 * @createTime 2019年12月30日 20:42:00
 */
public class TestContext {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");
        String[] beanNames = context.getBeanNamesForType(Date.class);  //获取指定类型的所有JavaBean对象
        Map beans = context.getBeansOfType(Date.class);  //获取容器中指定类
        for(String name : beanNames){
            Date bean = (Date)beans.get(name);
            System.out.println("名称为" + name + "的JavaBean输出结果为:" + bean);
        }



    }
}
