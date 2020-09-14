package edu.hunau.singleton;

import java.lang.reflect.InvocationTargetException;

public class Hungry {
    public static void main(String[] args) throws Exception {
        //静态变量
//        Singleton singleton=Singleton.getInstance();
//        Singleton singleton1=Singleton.getInstance();
//        System.out.println( singleton + "\n" + singleton1 );
//        System.out.println( singleton == singleton1 );


        //测试多线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Singleton singleton = Singleton.getInstance();
                System.out.println(singleton.hashCode());
            }, "A" + i).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Singleton singleton = Singleton.getInstance();
                System.out.println(singleton.hashCode());
            }, "B" + i).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Singleton singleton = Singleton.getInstance();
                System.out.println(singleton.hashCode());
            }, "C" + i).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Singleton singleton = Singleton.getInstance();
                System.out.println(singleton.hashCode());
            }, "D" + i).start();
        }

//        Class<?> aClass=Class.forName( "edu.hunau.singleton.Singleton" );
//        Object o=aClass.getDeclaredConstructor().newInstance();
//        Object o1=aClass.getDeclaredConstructor().newInstance();
//        System.out.println(o==o1);
        //静态代码块
//        Singleton1 singleton11=Singleton1.getSingleton1();
//        Singleton1 singleton12=Singleton1.getSingleton1();
//        System.out.println(singleton11==singleton12);
    }
}

/**
 * 优点：写法简单，在类加载的时候就完成实例化，避免了线程同步问题。（同一个类加载器不存在线程安全问题）
 * 缺点：没有达到懒加载效果，在不使用的情况下效率不高占用内存
 * Runtime底层就是静态变量的饿汉式
 */
//饿汉式(静态变量)
class Singleton {
    private Singleton() {
    }

    private static  Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
}

/**
 * 饿汉式（静态代码块）
 * 优缺点和上面一样。只是形式不同罢了
 */
class Singleton1 {
    private static final Singleton1 singleton1;

    private Singleton1() {
    }

    static {
        singleton1 = new Singleton1();
    }

    public static Singleton1 getSingleton1() {
        return singleton1;
    }
}