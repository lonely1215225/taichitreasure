package edu.hunau.spring.aaa;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AOPTest {

    @Pointcut(value = "execution(public int edu.hunau.spring.aaa.TestAOP.*(..))")
    public void aaa(){

    }

    @Before("aaa()")

    public void logStart() {

        System.out.println("除法运行。。参数列表是:{}");

    }



    @After("aaa()")

    public void logEnd() {

        System.out.println("除法结束运行。。参数列表是:{}");

    }



    @AfterReturning("aaa()")
    public void logReturn() {

        System.out.println("除法正常返回。。运行结果是:{}");

    }

    @AfterThrowing("aaa()")
    public void logError(){
        System.out.println("除法不合法");
    }
}
