package edu.hunau.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueDemo {
    static BlockingQueue blockingQueue=new ArrayBlockingQueue( 10 );
    public static void main(String[] args) {
        boolean flag=true;
        AtomicInteger atomicInteger=new AtomicInteger();
        new Thread( ( ) -> {
            while (flag) {
                try {
                    blockingQueue.put( atomicInteger.getAndIncrement() );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println( "加入" + (atomicInteger.get()) );
            }
        }, "AAA" ).start();
        try {
            TimeUnit.MILLISECONDS.sleep( 5000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i=0; i < 5; i++) {
            new Thread( ( ) -> {
                while (flag) {
                    try {
                        blockingQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println( Thread.currentThread().getName()+"\t取走" + (atomicInteger.getAndDecrement()-1));
                }
            }, "BBB" + i ).start();
        }
    }
}
