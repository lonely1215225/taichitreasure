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
            while (number !=0) {//生产者3次生产3件产品
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number++;
            System.out.println( Thread.currentThread().getName() + "\t生产" + number+"个" );
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
            System.out.println( Thread.currentThread().getName() + "\t消费" + number );
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
