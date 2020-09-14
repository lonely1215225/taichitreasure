package edu.hunau.mybatis.sqlsession.defaults;

import edu.hunau.mybatis.cfg.Configuration;
import edu.hunau.mybatis.sqlsession.SqlSession;
import edu.hunau.mybatis.sqlsession.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg){
        this.cfg = cfg;
    }

    /**
     * 用于创建一个新的操作数据库对象
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
