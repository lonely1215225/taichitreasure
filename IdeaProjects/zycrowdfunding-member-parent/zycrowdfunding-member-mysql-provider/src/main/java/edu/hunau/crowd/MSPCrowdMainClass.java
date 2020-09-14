package edu.hunau.crowd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "edu.hunau.crowd.mapper")
@SpringBootApplication
public class MSPCrowdMainClass {
    public static void main(String[] args) {
        SpringApplication.run(MSPCrowdMainClass.class, args);
    }
}


