package edu.hunau.pv;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    public static void main(String[] args) {
        SemaphoreDemo semaphoreDemo=new SemaphoreDemo();
        Semaphore s1=new Semaphore( 3 );//����������Դ

        for (int i=1; i <= 100; i++) {//100������Ҫȥ������������,��һЩ������ͷ�����
            new Thread( ( ) -> {
                try {
                    s1.acquire();
                    System.out.println(Thread.currentThread().getName()+"��\t����������");
                    try {TimeUnit.MILLISECONDS.sleep( 500 );} catch (InterruptedException e) { e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName()+"��\t�����ˣ����ӿ����˵�����Ҫ~~~");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    s1.release();
                }
            }, String.valueOf( i ) ).start();
        }
    }
}
