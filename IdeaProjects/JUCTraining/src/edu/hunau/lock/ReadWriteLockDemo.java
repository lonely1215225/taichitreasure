package edu.hunau.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    private volatile Map<String, Object> map=new HashMap<>();
    private ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        reentrantReadWriteLock.writeLock().lock();//写锁
        try {
            System.out.println( Thread.currentThread().getName() + "\t 正在写入..." );
            try {
                TimeUnit.MILLISECONDS.sleep( 300 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put( key, value );
            System.out.println( Thread.currentThread().getName() + "\t 写入完成: " + key );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }

    }

    public void get(String key) {
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println( Thread.currentThread().getName() + "\t 正在读取..." );
            try {
                TimeUnit.MILLISECONDS.sleep( 300 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object s=map.get( key );
            System.out.println( Thread.currentThread().getName() + "\t 读取成功: " + s );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }
}

class ReadWriteLock {

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockDemo readWriteLockDemo=new ReadWriteLockDemo();
        for (int i=1; i <= 5; i++) {
            final int finalI=i;
            new Thread( ( ) -> {
                readWriteLockDemo.put( finalI + "", finalI + "" );
            }, String.valueOf( i ) ).start();
        }
        try {TimeUnit.MILLISECONDS.sleep( 300 );} catch (InterruptedException e) { e.printStackTrace();}
        for (int i=1; i <= 5; i++) {
            final int finalI=i;
            new Thread( ( ) -> {
                readWriteLockDemo.get( finalI + "" );
            }, String.valueOf( i ) ).start();
        }
    }
}
