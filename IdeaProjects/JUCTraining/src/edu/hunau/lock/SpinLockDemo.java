package edu.hunau.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
/*
自旋锁，优点：不用阻塞；缺点：长时间不能获得，性能下降，cpu一直被占用
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicThread=new AtomicReference<>();

    public void myLock( ) {
        while (!atomicThread.compareAndSet( null, Thread.currentThread() )) {
            System.out.println( Thread.currentThread().getName() + "\t waitting......" );//3秒后AA释放锁，BB加锁成功
            try {
                TimeUnit.SECONDS.sleep( 1 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println( Thread.currentThread().getName() + "\t 我进去了(锁住)" );
    }

    public void unMyLock( ) {
        atomicThread.compareAndSet( Thread.currentThread(), null );
        System.out.println( Thread.currentThread().getName() + "\t invoke unMyLock()(解锁)" );
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
            TimeUnit.SECONDS.sleep( 1 );//为了让AA线程先加锁
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread( ( ) -> {
            spinLockDemo.myLock();//3秒后AA释放锁，BB加锁成功
            try {
                TimeUnit.SECONDS.sleep( 1 );//1s后BB解锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unMyLock();
        }, "BB" ).start();
        new Thread( ( ) -> {
            spinLockDemo.myLock();//3秒后AA释放锁，BB加锁成功
            try {
                TimeUnit.SECONDS.sleep( 1 );//1s后BB解锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unMyLock();
        }, "CC" ).start();
    }
}
