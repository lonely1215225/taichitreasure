package edu.hunau.others;

import java.lang.ref.SoftReference;
import java.util.concurrent.CountDownLatch;

enum CountEnum {
    ONE( 1, "��" ), TWO( 2, "��" ), THREE( 3, "��" ), FOUR( 4, "��" ), FIVE( 5, "κ" ), SIX( 6, "��" );
    private Integer id;
    private String name;

    CountEnum(Integer id, String name) {
        this.id=id;
        this.name=name;
    }

    public Integer getId( ) {
        return id;
    }

    public String getName( ) {
        return name;
    }

    public static CountEnum foreachCountEnum(int index) {
        for (CountEnum value : CountEnum.values()) {
            if (index == value.getId())
                return value;
        }
        return null;
    }
}

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
//        CountDownLatch countDownLatch=new CountDownLatch( 6 );
//        for (int i=1; i <= 6; i++) {
//            new Thread( ( ) -> {
//                System.out.println( Thread.currentThread().getName() + "��\t ����" );
//                countDownLatch.countDown();
//            }, CountEnum.foreachCountEnum( i ).getName() ).start();
//        }
//        countDownLatch.await();
//        System.out.println( "�ع� ͳһ����" );
        //byte[] b=new byte[1024*1024*2024];
        System.out.println("Ĭ�϶ѳ�ʼ�ڴ��С��"+Runtime.getRuntime().totalMemory()/1024/1024+"MB");
        System.out.println("�����ڴ��С��"+Runtime.getRuntime().maxMemory()/1024/1024+"MB");


    }
}
