package edu.hunau.volatiles;

public class SingletonDemo {
    private static volatile SingletonDemo singleton;//加volatile防止指令重排
    int i=0;
    boolean flag=true;
/*
    在new SingletonDemo时，会分为三部：第一步，申请内存；第二步，初始化对象；第三步，设置实例指向分配的内存，此时实例！=null。
    其中二三步会产生指令重排，如果先第三步后第二步，那么就导致实例还没有初始化，但是实例已经不为null了，此概率比较小
 */
    private SingletonDemo( ) {
        System.out.println( "我被实例化了" );
    }

    public static SingletonDemo getInstance( ) {
        if (singleton == null) {
            synchronized (SingletonDemo.class) {
                if (singleton == null) {
                    singleton=new SingletonDemo();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
//        for (int i=0; i < 200; i++)
//            new Thread( ( ) -> {
//                SingletonDemo.getInstance();
//            }, String.valueOf( i ) ).start();
        SingletonDemo singletonDemo = new SingletonDemo();
        System.out.println(singletonDemo.getClass().getClassLoader());

    }
}
