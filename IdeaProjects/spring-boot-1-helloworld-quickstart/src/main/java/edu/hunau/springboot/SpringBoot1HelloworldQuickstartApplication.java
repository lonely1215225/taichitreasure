package edu.hunau.springboot;

import edu.hunau.springboot.bean.Dog;
import edu.hunau.springboot.bean.Person;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableRabbit
public class SpringBoot1HelloworldQuickstartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot1HelloworldQuickstartApplication.class, args);
    }

}
