package edu.hunau.volatiles;

public class SingletonDemo {
    private static volatile SingletonDemo singleton;//��volatile��ָֹ������
    int i=0;
    boolean flag=true;
/*
    ��new SingletonDemoʱ�����Ϊ��������һ���������ڴ棻�ڶ�������ʼ�����󣻵�����������ʵ��ָ�������ڴ棬��ʱʵ����=null��
    ���ж����������ָ�����ţ�����ȵ�������ڶ�������ô�͵���ʵ����û�г�ʼ��������ʵ���Ѿ���Ϊnull�ˣ��˸��ʱȽ�С
 */
    private SingletonDemo( ) {
        System.out.println( "�ұ�ʵ������" );
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
