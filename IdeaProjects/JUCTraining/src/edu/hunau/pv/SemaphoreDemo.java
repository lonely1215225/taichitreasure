package edu.hunau.pv;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    public static void main(String[] args) {
        SemaphoreDemo semaphoreDemo=new SemaphoreDemo();
        Semaphore s1=new Semaphore( 3 );//三个妹子资源

        for (int i=1; i <= 100; i++) {//100个饿狼要去抢那三个妹子,做一些事情后，释放妹子
            new Thread( ( ) -> {
                try {
                    s1.acquire();
                    System.out.println(Thread.currentThread().getName()+"号\t抢到了妹子");
                    try {TimeUnit.MILLISECONDS.sleep( 500 );} catch (InterruptedException e) { e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName()+"号\t完事了，妹子空闲了但她还要~~~");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    s1.release();
                }
            }, String.valueOf( i ) ).start();
        }
    }
}
