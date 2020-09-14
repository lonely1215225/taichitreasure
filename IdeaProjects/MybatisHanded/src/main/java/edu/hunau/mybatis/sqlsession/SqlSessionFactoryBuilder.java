package edu.hunau.mybatis.sqlsession;

import edu.hunau.mybatis.cfg.Configuration;
import edu.hunau.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import edu.hunau.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 用于创建一个SqlSessionFactoryBuilder对象
 */
public class SqlSessionFactoryBuilder {
    /**
     * 根据参数的字节输入流来构建一个sqlSessionFactory工厂
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config){
        Configuration cfg=XMLConfigBuilder.loadConfiguration( config );

        return new DefaultSqlSessionFactory(cfg);
    }
}
