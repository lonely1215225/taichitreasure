package edu.hunau.mybatis.sqlsession;

import edu.hunau.mybatis.cfg.Configuration;
import edu.hunau.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import edu.hunau.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * ���ڴ���һ��SqlSessionFactoryBuilder����
 */
public class SqlSessionFactoryBuilder {
    /**
     * ���ݲ������ֽ�������������һ��sqlSessionFactory����
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config){
        Configuration cfg=XMLConfigBuilder.loadConfiguration( config );

        return new DefaultSqlSessionFactory(cfg);
    }
}
