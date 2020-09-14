package edu.hunau.pv;

public class PVDemo {

    public static void main(String[] args) {
        Producer p[]=new Producer[3];//3��������
        Consumer c[]=new Consumer[3];
        int i;

        for (i=0; i < 3; i++) {
            p[i]=new Producer( i + 1 );
        }
        for (i=0; i < 3; i++) {
            c[i]=new Consumer( i + 1 );
        }

        Thread pp[]=new Thread[3];
        Thread cp[]=new Thread[3];

        for (i=0; i < 3; i++) {
            pp[i]=new Thread( p[i] );
        }
        for (i=0; i < 3; i++) {
            cp[i]=new Thread( c[i] );
        }

        for (i=0; i < 3; i++) {
            pp[i].start();
        }
        for (i=0; i < 3; i++) {
            cp[i].start();
        }

    }
}

class Global {
    static syn empty=new syn( 8 );
    static syn full=new syn( 0 );
    static syn pMutex=new syn( 1 );//��֤������֮�以��
    static syn cMutex=new syn( 1 );//��֤������֮�以��
    static int buffer[]=new int[8];//������
    static int pCount=0;
    static int cCount=0;
}

//��������
class Producer implements Runnable {
    int ID=0;

    Producer( ) {
    }

    Producer(int id) {
        ID=id;
    }

    public void run( ) {
        while (Global.pCount < 20) {
            Global.empty.Wait();
            Global.pMutex.Wait();
            //�ٽ���
            int index=Global.pCount % 8;
            Global.buffer[index]=Global.pCount;
            System.out.println( "������" + ID + "�ڻ�����" + index + "����������Ʒ" + Global.pCount );
            Global.pCount++;
            try {
                Thread.sleep( 2000 );
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // end of �ٽ���
            Global.pMutex.Signal();
            Global.full.Signal();
        }
    }
}

//��������
class Consumer implements Runnable {
    int ID=0;

    Consumer( ) {
    }

    Consumer(int id) {
        ID=id;
    }

    public void run( ) {
        while (Global.cCount < 20) {
            Global.full.Wait();
            Global.cMutex.Wait();
            //�ٽ���
            int index=Global.cCount % 8;
            int value=Global.buffer[index];
            System.out.println( "������" + ID + "�ڻ�����" + index + "����������Ʒ" + value );
            Global.cCount++;
            try {
                Thread.sleep( 2000 );
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // end of �ٽ���
            Global.cMutex.Signal();
            Global.empty.Signal();
        }
    }
}

class syn {//PV������
    int count=0;//�ź���

    syn( ) {
    }

    syn(int a) {
        count=a;
    }

    public synchronized void Wait( ) { //�ؼ��� synchronized ��֤�˴˲�����һ����ԭ�
        count--;
        if (count < 0) {//����0 ����һ�����̽������ٽ���
            try {         //С��0��abs(count)=�����Ľ�����Ŀ
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void Signal( ) {   //�ؼ��� synchronized ��֤�˴˲�����һ����ԭ�
        count++;
        if (count <= 0) {//����н�������
            this.notify();//All
        }
    }
}