package edu.hunau.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ZuulMainClass {
    public static void main(String[] args) {
        SpringApplication.run(ZuulMainClass.class,args);
    }
}
