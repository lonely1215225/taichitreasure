package edu.hunau.pv;

public class PCDemo {
    public static void main(String[] args) {
        PV pv=new PV();//共享资源
        /*
        mom and dad put 5 times fruits
        son and daughter take 5 times fruits
         */
        new Thread( ( ) -> {
            for (int i=0; i < 5; i++) {
                pv.take( 1 );//表示son从盘子里取出一个橘子
            }
        }, "son" ).start();//线程son

        new Thread( ( ) -> {
            for (int i=0; i < 5; i++) {
                pv.take( 0 );//表示daughter从盘子里取出一个苹果
            }
        }, "daughter" ).start();//线程daughter

        new Thread( ( ) -> {
            for (int i=0; i < 5; i++) {
                pv.put( 0 );//表示dad往盘子里放了一个苹果
            }
        }, "dad" ).start();//线程dad

        new Thread( ( ) -> {
            for (int i=0; i < 5; i++) {
                pv.put( 1 );//表示mom往盘子里放了一个橘子
            }
        }, "mom" ).start();//线程mom
    }
}

class PV {
    private int mutex=1;//表示盘子里是否有水果
    private int apple=0;//表示苹果的数量
    private int orange=0;//表示橘子数量

    public synchronized void put(int i) {//synchronized保证原子操作,参数i=0表示苹果，否则就是橘子
        while (mutex != 1) {
            try {
                System.out.println( Thread.currentThread().getName()+"\t来时发现盘子里有一个水果,等人取走~" );
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mutex=0;
        if (i == 0) {
            apple++;
            System.out.println( Thread.currentThread().getName() + "\t放入一个苹果" );
        } else {
            orange++;
            System.out.println( Thread.currentThread().getName() + "\t放入一个橘子" );
        }
        notifyAll();//尝试唤醒所有线程
    }

    public synchronized void take(int i) {//synchronized保证原子操作,参数i=0表示苹果，否则就是橘子
        if (i == 0) {
            while (apple == 0) {//苹果数量为0的时候，阻塞当前调用此方法的线程。。。
                try {
                    System.out.println( Thread.currentThread().getName() + "\t看了看盘子没有苹果，waiting~~~" );
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            apple--;
            System.out.println( Thread.currentThread().getName() + "\t从盘子取走了一个苹果,唤醒mom And dad" );

            mutex=1;//表示盘子解锁，mom和dad可以往盘子中添加水果了
            notifyAll();//尝试唤醒所有线程
        } else {
            while (orange == 0) {//橘子数量为0的时候，阻塞当前调用此方法的线程。。。
                try {
                    System.out.println( Thread.currentThread().getName() + "\t看了看盘子没有橘子，waiting~~~" );
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            orange--;
            System.out.println( Thread.currentThread().getName() + "\t从盘子取走了一个橘子,唤醒mom And dad" );
            mutex=1;//表示盘子解锁，mom和dad可以往盘子中添加水果了
            notifyAll();//尝试唤醒所有线程
        }
    }
}