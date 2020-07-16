package edu.hunau.lock;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class HoldLockThread implements Runnable {
String lockA;//��A
String lockB;//��B

    public HoldLockThread(String lockA, String lockB) {
        this.lockA=lockA;
        this.lockB=lockB;
    }

    @Override
    public void run( ) {
        //��ʱ������LA��LB���������ˡ�
        synchronized (lockA){//�õ���A,�����߳�ThreadAAA��������ΪLA�������߳�ThreadBBB��������ΪLB.
            System.out.println(Thread.currentThread().getName()+"\t���"+lockA+"\t���Ի�ȡ"+lockB);
            System.out.println("...");
            try { TimeUnit.MILLISECONDS.sleep( 3000 );} catch (InterruptedException e) { e.printStackTrace();}
            synchronized (lockB){//�˴���������������
                // ThreadAAA���Ի�ȡ��LB;�����߳�ThreadAAA�Ѿ������LA������ʱThreadBBB�Ѿ��������LB��
                System.out.println("___");
                System.out.println( Thread.currentThread().getName()+"\t���"+lockB+"\t���Ի�ȡ"+lockA );
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
