package edu.hunau.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@Aspect
public class TransactionManager {
    private ConnectionUtil connectionUtil;
    @Autowired
    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    @Pointcut(value = "execution(* edu.hunau.service.impl.*.*(..))")
    public void exec(){}

  //  @Before(value = "exec()")
    public void beganTransacting() {
        try {
            connectionUtil.getConnection().setAutoCommit(false);
            System.out.println("开启事务");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  //  @After(value = "exec()")
    public void commit() {
        try {
            connectionUtil.getConnection().commit();
            System.out.println("提交");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 //   @AfterThrowing(value = "exec()")
    public void rollback() {
        try {
            connectionUtil.getConnection().rollback();
            System.out.println("回滚");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  //  @AfterReturning(value = "exec()")
    public void close() {
        try {
            connectionUtil.getConnection().close();
            connectionUtil.remove();
            System.out.println("回收");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Around("exec()")
    public void around(ProceedingJoinPoint joinPoint){
        try {
            beganTransacting();
            joinPoint.proceed();
            commit();
        } catch (Throwable throwable) {
            rollback();
            throwable.printStackTrace();
        }finally {
            close();
        }
    }
}
