package com.ruoan.study.classloader;

import com.google.common.collect.Sets;
import com.ruoan.study.util.ExceptionUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年10月23日 20:34:00
 */
public class Client {
    public static void main(String[] args) {
        //创建一个2s执行一次的定时任务
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                //把swap文件放在resources目录下，发现并不能生效
//                String swapPath = MyClassLoader.class.getResource("/").getPath() + "swap/";
                String swapPath = "D:\\Users\\xh.gao\\Desktop\\工作产出\\爬虫\\test\\";
                String className = "com.ruoan.study.classloader.Test";

                //每次都实例化一个ClassLoader，这里传入swap路径，和需要特殊加载的类名
                MyClassLoader myClassLoader = new MyClassLoader(swapPath, Sets.newHashSet(className));
                try {
                    //使用自定义的ClassLoader加载类，并调用printVersion方法。
                    Object o = myClassLoader.loadClass(className).newInstance();
                    /*
                      为什么需要o.getClass().getMethod("printVersion").invoke(o);
                      这样通过反射获取method调用，不能先强转成Test，然后test.printVersion()吗？

                      因为如果你这么写
                      Test test = (Test)o;
                      o.printVersion();
                      Test.class会隐性的被加载当前类的ClassLoader加载，
                      当前Main方法默认的ClassLoader为AppClassLoader，而不是我们自定义的MyClassLoader。

                      然后就会抛出ClassCastException，
                      因为一个类，就算包路径完全一致，但是加载他们的ClassLoader不一样，那么这两个类也会被认为是两个不同的类。
                     */
//                    Test test = (Test) o;
//                    test.printVersion();
                    Client.class.getClassLoader();
                    o.getClass().getMethod("printVersion").invoke(o);
                } catch (Exception ignored) {
                    String s = ExceptionUtil.buildErrorMessage(ignored);
                    System.out.println(s);
                }
            }
        }, 0, 2000);
    }
}
