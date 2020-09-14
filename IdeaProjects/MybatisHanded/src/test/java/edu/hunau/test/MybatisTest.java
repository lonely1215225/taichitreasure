package edu.hunau.test;

import edu.hunau.dao.IUserDao;
import edu.hunau.domain.User;
import edu.hunau.mybatis.io.Resources;
import edu.hunau.mybatis.sqlsession.SqlSession;
import edu.hunau.mybatis.sqlsession.SqlSessionFactory;
import edu.hunau.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws IOException {

        InputStream in=Resources.getResourceAsStream( "mybatis-config.xml" );
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build( in );
        SqlSession session=factory.openSession();
        IUserDao userDao=session.getMapper( IUserDao.class );
        List<User> users=userDao.findAll();
        for (User user : users) {
            System.out.println( user );
        }
        session.close();
        in.close();
    }
}
