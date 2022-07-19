package com.ruoan.study.classloader;

import com.ruoan.study.classloader.MyClassLoader;

import java.util.Objects;

/**
 * 图灵课堂周瑜老师 手写的classLoader
 *
 * @author ruoan
 * @date 2022/5/15 12:33 上午
 * @link https://www.bilibili.com/video/BV1DL411T7HQ?p=38&spm_id_from=pageDriver
 */
public class ZhouYuClassLoader extends ClassLoader {

    public ZhouYuClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) {
        ClassLoader classLoader = getSystemClassLoader().getParent();
        Class clazz = null;
        try {
            clazz = classLoader.loadClass(name);
            return clazz;
        } catch (ClassNotFoundException e) {
        }
        try {
            clazz = findClass(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String swapPath = "/Users/ruoan/IdeaProjects/ruoan/target/classes/com/ruoan/study/";
        MyClassLoader util = new MyClassLoader(swapPath, null);
        byte[] classByte = util.getClassByte(name);
        Class<?> aClass = defineClass(name, classByte, 0, classByte.length);
        return aClass;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ZhouYuClassLoader zhouYuClassLoader = new ZhouYuClassLoader(ZhouYuClassLoader.class.getClassLoader());
        Class<?> aClass = zhouYuClassLoader.loadClass("com.ruoan.study.Person");
        System.out.println(aClass.getClassLoader());

        ZhouYuClassLoader zhouYuClassLoader2 = new ZhouYuClassLoader(ZhouYuClassLoader.class.getClassLoader());
        Class<?> aClass2 = zhouYuClassLoader2.loadClass("com.ruoan.study.Person");
        System.out.println(aClass2.getClassLoader());

        //false,因为全限定类名和类加载器->才能唯一确定一个类
        System.out.println(Objects.equals(aClass, aClass2));
    }
}
