package edu.hunau.pv;

public class PCDemo {
    public static void main(String[] args) {
        PV pv=new PV();//������Դ
        /*
        mom and dad put 5 times fruits
        son and daughter take 5 times fruits
         */
        new Thread( ( ) -> {
            for (int i=0; i < 5; i++) {
                pv.take( 1 );//��ʾson��������ȡ��һ������
            }
        }, "son" ).start();//�߳�son

        new Thread( ( ) -> {
            for (int i=0; i < 5; i++) {
                pv.take( 0 );//��ʾdaughter��������ȡ��һ��ƻ��
            }
        }, "daughter" ).start();//�߳�daughter

        new Thread( ( ) -> {
            for (int i=0; i < 5; i++) {
                pv.put( 0 );//��ʾdad�����������һ��ƻ��
            }
        }, "dad" ).start();//�߳�dad

        new Thread( ( ) -> {
            for (int i=0; i < 5; i++) {
                pv.put( 1 );//��ʾmom�����������һ������
            }
        }, "mom" ).start();//�߳�mom
    }
}

class PV {
    private int mutex=1;//��ʾ�������Ƿ���ˮ��
    private int apple=0;//��ʾƻ��������
    private int orange=0;//��ʾ��������

    public synchronized void put(int i) {//synchronized��֤ԭ�Ӳ���,����i=0��ʾƻ���������������
        while (mutex != 1) {
            try {
                System.out.println( Thread.currentThread().getName()+"\t��ʱ������������һ��ˮ��,����ȡ��~" );
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mutex=0;
        if (i == 0) {
            apple++;
            System.out.println( Thread.currentThread().getName() + "\t����һ��ƻ��" );
        } else {
            orange++;
            System.out.println( Thread.currentThread().getName() + "\t����һ������" );
        }
        notifyAll();//���Ի��������߳�
    }

    public synchronized void take(int i) {//synchronized��֤ԭ�Ӳ���,����i=0��ʾƻ���������������
        if (i == 0) {
            while (apple == 0) {//ƻ������Ϊ0��ʱ��������ǰ���ô˷������̡߳�����
                try {
                    System.out.println( Thread.currentThread().getName() + "\t���˿�����û��ƻ����waiting~~~" );
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            apple--;
            System.out.println( Thread.currentThread().getName() + "\t������ȡ����һ��ƻ��,����mom And dad" );

            mutex=1;//��ʾ���ӽ�����mom��dad���������������ˮ����
            notifyAll();//���Ի��������߳�
        } else {
            while (orange == 0) {//��������Ϊ0��ʱ��������ǰ���ô˷������̡߳�����
                try {
                    System.out.println( Thread.currentThread().getName() + "\t���˿�����û�����ӣ�waiting~~~" );
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            orange--;
            System.out.println( Thread.currentThread().getName() + "\t������ȡ����һ������,����mom And dad" );
            mutex=1;//��ʾ���ӽ�����mom��dad���������������ˮ����
            notifyAll();//���Ի��������߳�
        }
    }
}