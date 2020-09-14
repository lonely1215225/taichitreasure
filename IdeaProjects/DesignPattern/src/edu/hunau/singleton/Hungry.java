package edu.hunau.singleton;

import java.lang.reflect.InvocationTargetException;

public class Hungry {
    public static void main(String[] args) throws Exception {
        //��̬����
//        Singleton singleton=Singleton.getInstance();
//        Singleton singleton1=Singleton.getInstance();
//        System.out.println( singleton + "\n" + singleton1 );
//        System.out.println( singleton == singleton1 );


        //���Զ��߳�
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
        //��̬�����
//        Singleton1 singleton11=Singleton1.getSingleton1();
//        Singleton1 singleton12=Singleton1.getSingleton1();
//        System.out.println(singleton11==singleton12);
    }
}

/**
 * �ŵ㣺д���򵥣�������ص�ʱ������ʵ�������������߳�ͬ�����⡣��ͬһ����������������̰߳�ȫ���⣩
 * ȱ�㣺û�дﵽ������Ч�����ڲ�ʹ�õ������Ч�ʲ���ռ���ڴ�
 * Runtime�ײ���Ǿ�̬�����Ķ���ʽ
 */
//����ʽ(��̬����)
class Singleton {
    private Singleton() {
    }

    private static  Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
}

/**
 * ����ʽ����̬����飩
 * ��ȱ�������һ����ֻ����ʽ��ͬ����
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