package edu.hunau.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProjectMainClass {
    public static void main(String[] args) {
        SpringApplication.run(ProjectMainClass.class, args);
    }
}
