package edu.hunau.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
@Component
public class ConnectionUtil {
    private ThreadLocal<Connection> threadLocal=new ThreadLocal<Connection>();
    private DataSource dataSource;
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection(){
        try {
            Connection connection = threadLocal.get();
            if (connection == null) {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            }
            return connection;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void remove(){
        threadLocal.remove();
    }
}
