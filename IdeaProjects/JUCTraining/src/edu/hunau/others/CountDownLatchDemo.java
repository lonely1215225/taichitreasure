package edu.hunau.others;

import java.lang.ref.SoftReference;
import java.util.concurrent.CountDownLatch;

enum CountEnum {
    ONE( 1, "齐" ), TWO( 2, "楚" ), THREE( 3, "燕" ), FOUR( 4, "赵" ), FIVE( 5, "魏" ), SIX( 6, "韩" );
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
//                System.out.println( Thread.currentThread().getName() + "国\t 被灭" );
//                countDownLatch.countDown();
//            }, CountEnum.foreachCountEnum( i ).getName() ).start();
//        }
//        countDownLatch.await();
//        System.out.println( "秦国 统一天下" );
        //byte[] b=new byte[1024*1024*2024];
        System.out.println("默认堆初始内存大小："+Runtime.getRuntime().totalMemory()/1024/1024+"MB");
        System.out.println("最大堆内存大小："+Runtime.getRuntime().maxMemory()/1024/1024+"MB");


    }
}
