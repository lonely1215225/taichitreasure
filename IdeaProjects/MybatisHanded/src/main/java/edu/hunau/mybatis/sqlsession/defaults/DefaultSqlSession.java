package edu.hunau.mybatis.sqlsession.defaults;

import edu.hunau.mybatis.cfg.Configuration;
import edu.hunau.mybatis.sqlsession.SqlSession;
import edu.hunau.mybatis.sqlsession.proxy.MapperProxy;
import edu.hunau.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;

public class DefaultSqlSession implements SqlSession {
    private Configuration cfg;
    private Connection connection;

    public DefaultSqlSession(Configuration cfg){
        this.cfg = cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }

    /**
     * ���ڴ����������
     * @param daoInterfaceClass dao�Ľӿ��ֽ���
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers(),connection));
    }

    /**
     * �����ͷ���Դ
     */
    @Override
    public void close() {
        if(connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
