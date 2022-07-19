package com.ruoan.study.classloader;

public class RuoanClassLoader extends ClassLoader {

    /**
     * Q:在编写自定义类加载器时，如果没有设定父加载器，那么父加载器是谁？
     * A:在不指定父类加载器的情况下，默认采用系统类加载器
     */
    public RuoanClassLoader() {
        super();
    }

    /**
     * Q:如果将父类加载器强制设置为null,那会有什么样子的影响？
     * A:那么会自动将启动类加载器设置为当前用户自定义类加载器的父类加载器
     */
    public RuoanClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("自定义的类加载器被调用了");
        //可以在下面实现自己的类加载逻辑，这里就不具体举例了，输出上面这句话说明自定义的逻辑已经被执行到了
        return super.findClass(name);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        test0();
        System.out.println("————————————————————————————————————————————");
        test1();
    }

    /**
     * 测试不指定父加载器时的行为
     */
    private static void test0() throws ClassNotFoundException {
        RuoanClassLoader ruoanClassLoader = new RuoanClassLoader();
        System.out.println(ruoanClassLoader.getClass().getClassLoader());
        System.out.println(ruoanClassLoader.getParent());
        ClassLoader classLoader = ruoanClassLoader.loadClass("java.lang.Object").getClassLoader();
        //注：jdk.internal.dynalink.support.AutoDiscovery 位于<Java_Runtime_Home>/lib/ext/nashorn.jar
        ClassLoader classLoader2 = ruoanClassLoader.loadClass("jdk.internal.dynalink.support.AutoDiscovery").getClassLoader();
        System.out.println("不指定父加载器时，父加载器默认是系统类加载器");
        System.out.println("所以利用双亲委派，<Java_Runtime_Home>/lib和<Java_Runtime_Home>/lib/ext目录下的类都可以被加载");
        System.out.println("Object的加载器当然是null：classLoader=" + classLoader);
        System.out.println(classLoader2);
    }

    /**
     * 测试指定父加载器为null时的行为
     */
    private static void test1() throws ClassNotFoundException {
        RuoanClassLoader ruoanClassLoader = new RuoanClassLoader(null);
        System.out.println(ruoanClassLoader.getClass().getClassLoader());
        System.out.println(ruoanClassLoader.getParent());
        ClassLoader classLoader = ruoanClassLoader.loadClass("java.lang.Object").getClassLoader();
        System.out.println("Object的加载器当然是null：classLoader=" + classLoader);
        System.out.println("父加载器被指定为null时,JVM会默认将其父加载器改为启动类加载器Bootstrap ClassLoader");
        System.out.println("而启动类加载器是没有办法加载<Java_Runtime_Home>/lib/ext目录下的类的");
        System.out.println("所以会报错ClassNotFoundException");
        ClassLoader classLoader2 = ruoanClassLoader.loadClass("jdk.internal.dynalink.support.AutoDiscovery").getClassLoader();
        System.out.println(classLoader2);
    }
}
