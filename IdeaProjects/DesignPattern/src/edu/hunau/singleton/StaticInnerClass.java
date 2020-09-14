package edu.hunau.singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaticInnerClass {
    public static void main(String[] args) {
//        for (int i=0; i < 10; i++) {
//            new Thread( ( ) -> {
//                StaticSingleton instance=StaticSingleton.getInstance();
//                System.out.println( instance.hashCode() );
//            }, "A" + i ).start();
//        }
//        for (int i=0; i < 10; i++) {
//            new Thread( ( ) -> {
//                StaticSingleton instance=StaticSingleton.getInstance();
//                System.out.println( instance.hashCode() );
//            }, "B" + i ).start();
//        }
        byte[] b={1,2,3,4};
        byte[] b2={5,6,7};
        List<byte[]> list=new ArrayList<>();
        list.add(b);
        list.add(b2);
        Object[] array = list.toArray();
        int length=0;
        //这里统计新数组长度
        for (Object o:array) {
            byte[] o1 = (byte[]) o;
            length+=o1.length;
        }
        byte[] total=new byte[length];
        int count = 0;
        //这里往新数组里塞
        for (int i = 0; i <list.size(); i++) {
            byte[] bytes = list.get(i);
            for (int j = 0; j <bytes.length; j++) {
                total[count]=bytes[j];
                count++;
            }
        }
        System.out.println(Arrays.toString(total));
    }
}

/**
 *推荐：
 *  静态内部类
 * 利用类加载器，即保证了线程池安全，又保证单例,由于内部类只有使用时才被加载所以：也是延迟加载
 */
class StaticSingleton{
    private StaticSingleton(){}
    private static class SingletonInstance{
        private static final StaticSingleton ss=new StaticSingleton();
    }
    public static StaticSingleton getInstance(){
        return SingletonInstance.ss;
    }
}