package edu.hunau.pv;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//java多线程顺序执行问题，不能用join
public class MultiConditions {
    private Lock lock=new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();
    private int flag=1;//A:1 B:2  C:3

    public void print2( ) throws InterruptedException {
        lock.lock();
        while (flag != 1)
            c1.await();
        for (int i=0; i < 5; i++) {
            System.out.println( Thread.currentThread().getName() + "\t来第" + i + "次" );
        }
        flag=3;
        c3.signal();
        lock.unlock();
    }

    private void print3( ) throws InterruptedException {
        lock.lock();
        while (flag != 2)
            c2.await();
        for (int i=0; i < 10; i++) {
            System.out.println( Thread.currentThread().getName() + "\t来第" + i + "次" );
        }
        flag=3;
        c3.signal();
        lock.unlock();
    }

    private void print4( ) throws InterruptedException {
        lock.lock();
        while (flag != 3)
            c3.await();
        for (int i=0; i < 15; i++) {
            System.out.println( Thread.currentThread().getName() + "\t来第" + i + "次" );
        }
        flag=2;
        c2.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        MultiConditions multiConditions=new MultiConditions();
        new Thread( ( ) -> {
           // for (int i=0; i <2; i++) {
                try {
                    multiConditions.print2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
           // }
        }, "A" ).start();
        new Thread( ( ) -> {
            for (int i=0; i <3; i++) {
                try {
                    multiConditions.print3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "B" ).start();
        new Thread( ( ) -> {
            for (int i=0; i < 4; i++) {
                try {
                    multiConditions.print4();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "C" ).start();
    }
}
