package edu.hunau.utils;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class TransactionManager {
    private ConnectionUtil connectionUtil;
    @Autowired
    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    public void beganTransacting() {
        try {
            connectionUtil.getConnection().setAutoCommit(false);
            System.out.println("开启事务");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            connectionUtil.getConnection().commit();
            System.out.println("提交");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        try {
            connectionUtil.getConnection().rollback();
            System.out.println("回滚");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connectionUtil.getConnection().close();
            connectionUtil.remove();
            System.out.println("回收");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
