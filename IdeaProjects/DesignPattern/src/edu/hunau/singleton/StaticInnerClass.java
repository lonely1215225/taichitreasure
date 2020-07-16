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
        //����ͳ�������鳤��
        for (Object o:array) {
            byte[] o1 = (byte[]) o;
            length+=o1.length;
        }
        byte[] total=new byte[length];
        int count = 0;
        //����������������
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
 *�Ƽ���
 *  ��̬�ڲ���
 * �����������������֤���̳߳ذ�ȫ���ֱ�֤����,�����ڲ���ֻ��ʹ��ʱ�ű��������ԣ�Ҳ���ӳټ���
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