package edu.hunau.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
/*
���������ŵ㣺����������ȱ�㣺��ʱ�䲻�ܻ�ã������½���cpuһֱ��ռ��
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicThread=new AtomicReference<>();

    public void myLock( ) {
        while (!atomicThread.compareAndSet( null, Thread.currentThread() )) {
            System.out.println( Thread.currentThread().getName() + "\t waitting......" );//3���AA�ͷ�����BB�����ɹ�
            try {
                TimeUnit.SECONDS.sleep( 1 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println( Thread.currentThread().getName() + "\t �ҽ�ȥ��(��ס)" );
    }

    public void unMyLock( ) {
        atomicThread.compareAndSet( Thread.currentThread(), null );
        System.out.println( Thread.currentThread().getName() + "\t invoke unMyLock()(����)" );
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo=new SpinLockDemo();
        new Thread( ( ) -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep( 3 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unMyLock();
        }, "AA" ).start();

        try {
            TimeUnit.SECONDS.sleep( 1 );//Ϊ����AA�߳��ȼ���
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread( ( ) -> {
            spinLockDemo.myLock();//3���AA�ͷ�����BB�����ɹ�
            try {
                TimeUnit.SECONDS.sleep( 1 );//1s��BB����
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unMyLock();
        }, "BB" ).start();
        new Thread( ( ) -> {
            spinLockDemo.myLock();//3���AA�ͷ�����BB�����ɹ�
            try {
                TimeUnit.SECONDS.sleep( 1 );//1s��BB����
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unMyLock();
        }, "CC" ).start();
    }
}
