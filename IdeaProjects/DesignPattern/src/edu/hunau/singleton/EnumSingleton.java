package edu.hunau.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EnumSingleton {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        SingletonEnum instance=SingletonEnum.INSTANCE;
//        SingletonEnum instance2=SingletonEnum.INSTANCE;
//        System.out.println(instance==instance2);
//
//        //∑¥…‰∆∆ªµµ•¿˝ ß∞‹
//        Constructor<SingletonEnum> constructor=SingletonEnum.class.getDeclaredConstructor();
//        constructor.setAccessible( true );
//        SingletonEnum singletonEnum=constructor.newInstance();
//        SingletonEnum singletonEnum1=constructor.newInstance();
//        System.out.println("∑¥…‰√∂æŸ£∫");
//        System.out.println(singletonEnum==singletonEnum1);
        EnumSingleton enumSingleton = new EnumSingleton();
    }
}
enum SingletonEnum{
    INSTANCE;
}