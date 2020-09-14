package edu.hunau.springboot.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello!Spring-boot";
    }

    @RabbitListener(queues = {"zzy.news"})
    public void listen(Object object){
        System.out.println(object);
    }
}
