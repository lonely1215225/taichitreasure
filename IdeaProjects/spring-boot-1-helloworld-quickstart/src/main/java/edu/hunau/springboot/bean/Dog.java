package edu.hunau.springboot.bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class Dog {
    private String name;
    private String gender;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
