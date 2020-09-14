package edu.hunau.mybatis.sqlsession.proxy;

import edu.hunau.mybatis.cfg.Mapper;
import edu.hunau.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public class MapperProxy implements InvocationHandler {

    //map��key��ȫ�޶�����+������
    private Map<String, Mapper> mappers;
    private Connection conn;

    public MapperProxy(Map<String, Mapper> mappers, Connection conn) {
        this.mappers=mappers;
        this.conn=conn;
    }

    /**
     * ���ڶԷ���������ǿ�ģ����ǵ���ǿ��ʵ���ǵ���selectList����
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1.��ȡ������
        String methodName=method.getName();
        //2.��ȡ���������������
        String className=method.getDeclaringClass().getName();
        //3.���key
        String key=className + "." + methodName;
        //4.��ȡmappers�е�Mapper����
        Mapper mapper=mappers.get( key );
        //5.�ж��Ƿ���mapper
        if (mapper == null) {
            throw new IllegalArgumentException( "����Ĳ�������" );
        }
        //6.���ù�����ִ�в�ѯ����
        return new Executor().selectList( mapper, conn );
    }
}
