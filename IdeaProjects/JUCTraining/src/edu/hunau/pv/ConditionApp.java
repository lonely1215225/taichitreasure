package edu.hunau.pv;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionApp {
    private int number=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public void increment( ) {
        lock.lock();
        try {
            while (number !=0) {//������3������3����Ʒ
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number++;
            System.out.println( Thread.currentThread().getName() + "\t����" + number+"��" );
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrement( ) {
        lock.lock();
        try {

            while (number ==0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println( Thread.currentThread().getName() + "\t����" + number );
            number--;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionApp conditionApp=new ConditionApp();

        new Thread( ( ) -> {
            for (int i=1; i <= 5; i++) {
                conditionApp.increment();
            }
        }, "AAA" ).start();
        new Thread( ( ) -> {
            for (int i=1; i <= 5; i++) {
                conditionApp.decrement();
            }
        }, "BBB" ).start();

    }
}
