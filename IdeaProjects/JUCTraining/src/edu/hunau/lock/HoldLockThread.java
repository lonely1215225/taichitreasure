package edu.hunau.lock;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class HoldLockThread implements Runnable {
String lockA;//锁A
String lockB;//锁B

    public HoldLockThread(String lockA, String lockB) {
        this.lockA=lockA;
        this.lockB=lockB;
    }

    @Override
    public void run( ) {
        //此时两把锁LA，LB都被拿走了。
        synchronized (lockA){//拿到锁A,对于线程ThreadAAA的锁对象为LA；对于线程ThreadBBB的锁对象为LB.
            System.out.println(Thread.currentThread().getName()+"\t获得"+lockA+"\t尝试获取"+lockB);
            System.out.println("...");
            try { TimeUnit.MILLISECONDS.sleep( 3000 );} catch (InterruptedException e) { e.printStackTrace();}
            synchronized (lockB){//此处产生死锁！！！
                // ThreadAAA尝试获取锁LB;对于线程ThreadAAA已经获得锁LA，而此时ThreadBBB已经获得了锁LB；
                System.out.println("___");
                System.out.println( Thread.currentThread().getName()+"\t获得"+lockB+"\t尝试获取"+lockA );
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread( new HoldLockThread( "LA","LB" ),"ThreadAAA").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread( new HoldLockThread( "LB","LA" ),"ThreadBBB").start();
//        String a="zzy";
//        a=a+"love";
//        a+="pretty";
    }
}
