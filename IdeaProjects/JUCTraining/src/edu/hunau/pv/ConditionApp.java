package edu.hunau.pv;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionApp {
    // 1- ��4���������̣߳�һ��ֻ�����A��һ��ֻ�����L��һ��ֻ�����I, һ������ B��
// ���ĸ��߳�ͬʱ����������£����ú���ķ�ʽ�����ǰ�˳���ӡALIBABA
    private volatile int number = 0;
    private volatile  int flag;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionL = lock.newCondition();
    private Condition conditionI = lock.newCondition();
    private Condition conditionB = lock.newCondition();

    public void printA() {
        lock.lock();
        try {

            while (number != 0) {
                try {
                    conditionA.await();
                    if (number==2) {
                        number++;
                        flag++;
                        System.out.println(Thread.currentThread().getName());
                        conditionB.signal();
                    }
                    if (flag==2) return;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number++;
            System.out.println(Thread.currentThread().getName());
            conditionL.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printL() {
        lock.lock();
        try {
            while (number<1) {
                try {
                    conditionL.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName());
            number++;
            conditionI.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printI() {
        lock.lock();
        try {

            while (number<2) {
                try {
                    conditionI.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName());
            number++;
            conditionB.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (number<3) {
                try {
                    conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName());
            number--;
            conditionA.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionApp conditionApp = new ConditionApp();

        new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                conditionApp.printA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                conditionApp.printB();
            }
        }, "B").start();
        new Thread(() -> {

                conditionApp.printI();
        }, "I").start();
        new Thread(() -> {


                conditionApp.printL();
        }, "L").start();

    }
}
