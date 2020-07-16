package edu.hunau.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

class MyData {
    int a = 0;// volatile��ǿ�����ڴ�͸��߳�֮��Ŀɼ���,������
    public void changeA() {
        this.a = 60;
    }
    public void addOne() {
        this.a++;//��һ���̻߳�ȡa;�ڶ����޸ļ�һ;������д��
        //java�ֽ���getfield--->iadd--->putfield
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void atomicAdd() {
        atomicInteger.getAndIncrement();
    }
}

public class VolatileDemo {

    public static void main(String[] args) {
        ///noAtomicAndAtomic();
        visiableOfVolatile();
    }

    private static void noAtomicAndAtomic() {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {//volatile����֤ԭ����
                    myData.addOne();
                    myData.atomicAdd();
                }
            }, String.valueOf(i) + "���߳�").start();
        }

        while (Thread.activeCount() > 2)//�����൱����main�߳����ִ�У�Ҳ����sleep������2���߳���main�̺߳�gc�߳�
            Thread.yield();//��main�߳����У�Ҳ�������ִ��
        System.out.println(Thread.currentThread().getName() + "�̵߳õ�Ϊa=" + myData.a);//����д����
        System.out.println(Thread.currentThread().getName() + "�̵߳õ�ΪatomicInteger=" + myData.atomicInteger);
    }

    private static void visiableOfVolatile() {
        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "�߳�����");
            System.out.println("ԭʼ����a=" + myData.a);
            try {
                Thread.sleep(2000);// �������߳�AAA˯��2s��Ϊ������main�߳�copy��Դa��Ȼ��AAA�߳���copy��Դa���޸�д�����ڴ�
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            myData.changeA();// AAA�߳���copy��Դa���޸�д�����ڴ�
            System.out.println("�߳��޸ĺ�a=" + myData.a);
            System.out.println("=======================================");
        }, "AAA").start();
        while (myData.a == 0) {// ��û��volatile�ؼ��ֵ�����£�main�̻߳�ȡ����û�޸�֮ǰ��a�����Ի���ѭ��
            System.out.println("û����volatile");//��һ��ײ���synchronized����֤�˿ɼ���
        } // ����volatile֮�󣬱�֤�����ݿɼ��ԣ�mainѸ�ٻ�ȡ�����޸ĵ�ֵa
        System.out.println(Thread.currentThread().getName() + "�߳�����");
        System.out.println("��ȡ��a=" + myData.a);
    }

}
