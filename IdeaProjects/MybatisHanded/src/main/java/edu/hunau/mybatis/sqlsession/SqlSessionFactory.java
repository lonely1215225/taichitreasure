package edu.hunau.mybatis.sqlsession;

public interface SqlSessionFactory {
    /**
     * ���ڴ�һ���µ�SqlSession����
     * @return
     */
    SqlSession openSession();
}
