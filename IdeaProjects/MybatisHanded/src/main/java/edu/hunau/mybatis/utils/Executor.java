package edu.hunau.mybatis.utils;

import edu.hunau.mybatis.cfg.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Executor {
    public <E> List<E> selectList(Mapper mapper, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //1.ȡ��mapper�е�����
            String queryString = mapper.getQueryString();//select * from user
            String resultType = mapper.getResultType();//com.itheima.domain.User
            Class domainClass = Class.forName(resultType);
            //2.��ȡPreparedStatement����
            pstm = conn.prepareStatement(queryString);
            //3.ִ��SQL��䣬��ȡ�����
            rs = pstm.executeQuery();
            //4.��װ�����
            List<E> list = new ArrayList<E>();//���巵��ֵ
            while(rs.next()) {
                //ʵ����Ҫ��װ��ʵ�������
                E obj = (E)domainClass.getConstructor(  ).newInstance(  );

                //ȡ���������Ԫ��Ϣ��ResultSetMetaData
                ResultSetMetaData rsmd = rs.getMetaData();
                //ȡ��������
                int columnCount = rsmd.getColumnCount();
                //����������
                for (int i = 1; i <= columnCount; i++) {
                    //��ȡÿ�е����ƣ�����������Ǵ�1��ʼ��
                    String columnName = rsmd.getColumnName(i);
                    //���ݵõ���������ȡÿ�е�ֵ
                    Object columnValue = rs.getObject(columnName);
                    //��obj��ֵ��ʹ��Java��ʡ���ƣ�����PropertyDescriptorʵ�����Եķ�װ��
                    PropertyDescriptor pd = new PropertyDescriptor(columnName,domainClass);//Ҫ��ʵ��������Ժ����ݿ�����������һ��
                    //��ȡ����д�뷽��
                    Method writeMethod = pd.getWriteMethod();
                    //�ѻ�ȡ���е�ֵ��������ֵ
                    writeMethod.invoke(obj,columnValue);
                }
                //�Ѹ���ֵ�Ķ�����뵽������
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            release(pstm,rs);
        }
    }


    private void release(PreparedStatement pstm, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        if(pstm != null){
            try {
                pstm.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
