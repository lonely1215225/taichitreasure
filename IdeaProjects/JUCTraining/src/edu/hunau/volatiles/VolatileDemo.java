package edu.hunau.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

class MyData {
    int a = 0;// volatile增强了主内存和各线程之间的可见性,轻量级
    public void changeA() {
        this.a = 60;
    }
    public void addOne() {
        this.a++;//第一步线程获取a;第二步修改加一;第三步写回
        //java字节码getfield--->iadd--->putfield
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
                for (int j = 0; j < 1000; j++) {//volatile不保证原子性
                    myData.addOne();
                    myData.atomicAdd();
                }
            }, String.valueOf(i) + "号线程").start();
        }

        while (Thread.activeCount() > 2)//这里相当于让main线程最后执行，也可以sleep。其中2个线程是main线程和gc线程
            Thread.yield();//讲main线程让行，也就是最后执行
        System.out.println(Thread.currentThread().getName() + "线程得到为a=" + myData.a);//出现写覆盖
        System.out.println(Thread.currentThread().getName() + "线程得到为atomicInteger=" + myData.atomicInteger);
    }

    private static void visiableOfVolatile() {
        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "线程启动");
            System.out.println("原始数据a=" + myData.a);
            try {
                Thread.sleep(2000);// 这里让线程AAA睡眠2s是为了先让main线程copy资源a，然后AAA线程在copy资源a并修改写回主内存
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            myData.changeA();// AAA线程在copy资源a并修改写回主内存
            System.out.println("线程修改后a=" + myData.a);
            System.out.println("=======================================");
        }, "AAA").start();
        while (myData.a == 0) {// 在没有volatile关键字的情况下，main线程获取的是没修改之前的a，所以会死循环
            System.out.println("没有用volatile");//这一句底层有synchronized，保证了可见性
        } // 加了volatile之后，保证了数据可见性，main迅速获取到被修改的值a
        System.out.println(Thread.currentThread().getName() + "线程启动");
        System.out.println("获取到a=" + myData.a);
    }

}
