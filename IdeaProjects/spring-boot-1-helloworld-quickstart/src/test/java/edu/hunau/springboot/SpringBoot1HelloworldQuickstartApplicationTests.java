package edu.hunau.springboot;

import edu.hunau.springboot.bean.Person;
import org.apache.http.client.HttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;

@SpringBootTest
class SpringBoot1HelloworldQuickstartApplicationTests {
    @Autowired
    Person person;
    @Test
    void contextLoads() {
        System.out.println(Runtime.getRuntime().availableProcessors());
        //译文：当线程数量大于核心时，这是多余空闲线程在终止前等待新任务的最大时间。
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,
                5,2L,
                TimeUnit.SECONDS,new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
        try{
            for (int i = 1; i <= 9; i++) {
                int finalI = i;
                executor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + ":" + finalI);
                    //这里不能加sleep去测，因为这里是看先抢到的线程，而不是具体任务
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(100 * finalI);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                });
           // System.out.println("活动的线程数量："+executor.getActiveCount());
//            System.out.println("完成任务的线程数量："+executor.getCompletedTaskCount());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            executor.shutdown();
        }
    }
//public HttpClient(URL url, String proxyHost, int proxyPort)
//    throws IOException {
//        this(url, proxyHost, proxyPort, false);
//    }
    @Test
    public void testFilter() throws IOException {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        
    }

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    public void rabbitMQ(){
        Map<String,Object> map=new HashMap<>();
        map.put("这是第二次","来自idea");
        List<String> list=Arrays.asList("华山论剑","东邪西毒","东成西就","铁血丹心");

        rabbitTemplate.convertAndSend("exchange.direct","zzy.news",list);
    }

    @Test
    public void receive(){
        List<String> o = (List)rabbitTemplate.receiveAndConvert("zzy.emps");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    @Autowired
    AmqpAdmin amqpAdmin;
    @Test
    public void testAmqpAdmin(){
        amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
        amqpAdmin.declareQueue(new Queue("amqpAdmin.queue"));
        //消息队列amqpAdmin.queue，通过路由键amqp.test来指明交换器要发送消息到哪个队列中。
        amqpAdmin.declareBinding(new Binding("amqpAdmin.queue",
                Binding.DestinationType.QUEUE,
                "amqpAdmin.exchange","amqp.test",null));

    }
}
