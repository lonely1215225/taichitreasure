package edu.hunau.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Lazy {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        Single single=Single.getSingle();
//        Single single1=Single.getSingle();
//        System.out.println(single==single1);
//        for (int i=0; i < 10; i++) {
//            new Thread( ( ) -> {
//                Single single2=Single.getSingle();
//                System.out.println( single2.hashCode() );
//            }, "A" + i ).start();
//        }
//        for (int i=0; i < 10; i++) {
//            new Thread( ( ) -> {
//                Single single3=Single.getSingle();
//                System.out.println( single3.hashCode() );
//            }, "B" + i ).start();
//        }
        for (int i=0; i < 10; i++) {
            new Thread( ( ) -> {
                DCSingleton single=DCSingleton.getSingle();
                System.out.println( single.hashCode() );
            }, "A" + i ).start();
        }        for (int i=0; i < 10000; i++) {
            new Thread( ( ) -> {
                DCSingleton single=DCSingleton.getSingle();
                System.out.println( single.hashCode() );
            }, "A" + i ).start();
        }        for (int i=0; i < 10000; i++) {
            new Thread( ( ) -> {
                DCSingleton single=DCSingleton.getSingle();
                System.out.println( single.hashCode() );
            }, "A" + i ).start();
        }
        for (int i=0; i < 100; i++) {
            new Thread( ( ) -> {
                DCSingleton single=DCSingleton.getSingle();
                System.out.println( single.hashCode() );
            }, "B" + i ).start();
        }
        //�����ƻ���������
//        Constructor<Single> constructor=Single.class.getDeclaredConstructor();
//        constructor.setAccessible( true );
//        Single single=constructor.newInstance();
//        Single single1=constructor.newInstance();
//        System.out.println(single==single1);

       // System.out.println("test");
    }
}

/**
 * ����ʽ���̲߳���ȫ����֤����
 * ��������¿���ʹ�á�
 * ���߳��²�Ҫʹ�ã���DC�������
 */
class Single {
    private static Single single;

    private Single( ) {
    }

    public static Single getSingle( ) {
        if (single == null)
            single=new Single();
        return single;
    }
}

/**
 * *****�Ƽ�ʹ��*******
 * ����ʽ��double check���̰߳�ȫ
 * ˫�ؼ��
 */
class DCSingleton {
    private static  DCSingleton dcSingleton;//volatile��֤�ɼ���

    private DCSingleton( ) {
    }

    public static DCSingleton getSingle( ) {
        if (dcSingleton == null)
            synchronized (DCSingleton.class) {
                if (dcSingleton == null)
                    dcSingleton=new DCSingleton();
            }
        return dcSingleton;
    }
}