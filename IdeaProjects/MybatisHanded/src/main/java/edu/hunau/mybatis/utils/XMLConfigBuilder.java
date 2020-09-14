package edu.hunau.mybatis.utils;
import edu.hunau.mybatis.annotations.Select;
import edu.hunau.mybatis.cfg.Configuration;
import edu.hunau.mybatis.cfg.Mapper;
import edu.hunau.mybatis.io.Resources;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLConfigBuilder {
    /**
     * �����������ļ����������������䵽DefaultSqlSession����Ҫ�ĵط�
     * ʹ�õļ�����
     *      dom4j+xpath
     */
    public static Configuration loadConfiguration(InputStream config){
        try{
            //�����װ������Ϣ�����ö���mybatis�����ö���
            Configuration cfg = new Configuration();

            //1.��ȡSAXReader����
            SAXReader reader = new SAXReader();
            //2.�����ֽ���������ȡDocument����
            Document document = reader.read(config);
            //3.��ȡ���ڵ�
            Element root = document.getRootElement();
            //4.ʹ��xpath��ѡ��ָ���ڵ�ķ�ʽ����ȡ����property�ڵ�
            List<Element> propertyElements = root.selectNodes("//property");
            //5.�����ڵ�
            for(Element propertyElement : propertyElements){
                //�жϽڵ����������ݿ���Ĳ�����Ϣ
                //ȡ��name���Ե�ֵ
                String name = propertyElement.attributeValue("name");
                if("driver".equals(name)){
                    //��ʾ����
                    //��ȡproperty��ǩvalue���Ե�ֵ
                    String driver = propertyElement.attributeValue("value");
                    cfg.setDriver(driver);
                }
                if("url".equals(name)){
                    //��ʾ�����ַ���
                    //��ȡproperty��ǩvalue���Ե�ֵ
                    String url = propertyElement.attributeValue("value");
                    cfg.setUrl(url);
                }
                if("username".equals(name)){
                    //��ʾ�û���
                    //��ȡproperty��ǩvalue���Ե�ֵ
                    String username = propertyElement.attributeValue("value");
                    cfg.setUsername(username);
                }
                if("password".equals(name)){
                    //��ʾ����
                    //��ȡproperty��ǩvalue���Ե�ֵ
                    String password = propertyElement.attributeValue("value");
                    cfg.setPassword(password);
                }
            }
            //ȡ��mappers�е�����mapper��ǩ���ж�����ʹ����resource����class����
            List<Element> mapperElements = root.selectNodes("//mappers/mapper");
            //��������
            for(Element mapperElement : mapperElements){
                //�ж�mapperElementʹ�õ����ĸ�����
                Attribute attribute = mapperElement.attribute("resource");
                if(attribute != null){
                    System.out.println("ʹ�õ���XML");
                    //��ʾ��resource���ԣ��õ���XML
                    //ȡ�����Ե�ֵ
                    String mapperPath = attribute.getValue();//��ȡ���Ե�ֵ"com/itheima/dao/IUserDao.xml"
                    //��ӳ�������ļ������ݻ�ȡ��������װ��һ��map
                    Map<String, Mapper> mappers = loadMapperConfiguration(mapperPath);
                    //��configuration�е�mappers��ֵ
                    cfg.setMappers(mappers);
                }
                else{
                    System.out.println("ʹ�õ���ע��");
                    //��ʾû��resource���ԣ��õ���ע��
                    //��ȡclass���Ե�ֵ
                    String daoClassPath = mapperElement.attributeValue("class");
                    //����daoClassPath��ȡ��װ�ı�Ҫ��Ϣ
                    Map<String,Mapper> mappers = loadMapperAnnotation(daoClassPath);
                    //��configuration�е�mappers��ֵ
                    cfg.setMappers(mappers);
                }
            }
            //����Configuration
            return cfg;
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            try {
                config.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    /**
     * ���ݴ���Ĳ���������XML�����ҷ�װ��Map��
     * @param mapperPath    ӳ�������ļ���λ��
     * @return  map�а����˻�ȡ��Ψһ��ʶ��key����dao��ȫ�޶������ͷ�������ɣ�
     *          �Լ�ִ������ı�Ҫ��Ϣ��value��һ��Mapper���������ŵ���ִ�е�SQL����Ҫ��װ��ʵ����ȫ�޶�������
     */
    private static Map<String,Mapper> loadMapperConfiguration(String mapperPath)throws IOException {
        InputStream in = null;
        try{
            //���巵��ֵ����
            Map<String,Mapper> mappers = new HashMap<String,Mapper>();
            //1.����·����ȡ�ֽ�������
            in = Resources.getResourceAsStream(mapperPath);
            //2.�����ֽ���������ȡDocument����
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            //3.��ȡ���ڵ�
            Element root = document.getRootElement();
            //4.��ȡ���ڵ��namespace����ȡֵ
            String namespace = root.attributeValue("namespace");//�����map��key�Ĳ���
            //5.��ȡ���е�select�ڵ�
            List<Element> selectElements = root.selectNodes("//select");
            //6.����select�ڵ㼯��
            for(Element selectElement : selectElements){
                //ȡ��id���Ե�ֵ      ���map��key�Ĳ���
                String id = selectElement.attributeValue("id");
                //ȡ��resultType���Ե�ֵ  ���map��value�Ĳ���
                String resultType = selectElement.attributeValue("resultType");
                //ȡ���ı�����            ���map��value�Ĳ���
                String queryString = selectElement.getText();
                //����Key
                String key = namespace+"."+id;
                //����Value
                Mapper mapper = new Mapper();
                mapper.setQueryString(queryString);
                mapper.setResultType(resultType);
                //��key��value����mappers��
                mappers.put(key,mapper);
            }
            return mappers;
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            in.close();
        }
    }

    /**
     * ���ݴ���Ĳ������õ�dao�����б�selectע���ע�ķ�����
     * ���ݷ������ƺ��������Լ�������ע��value���Ե�ֵ�����Mapper�ı�Ҫ��Ϣ
     * @param daoClassPath
     * @return
     */
    private static Map<String,Mapper> loadMapperAnnotation(String daoClassPath)throws Exception{
        //���巵��ֵ����
        Map<String,Mapper> mappers = new HashMap<String, Mapper>();

        //1.�õ�dao�ӿڵ��ֽ������
        Class daoClass = Class.forName(daoClassPath);
        //2.�õ�dao�ӿ��еķ�������
        Method[] methods = daoClass.getMethods();
        //3.����Method����
        for(Method method : methods){
            //ȡ��ÿһ���������ж��Ƿ���selectע��
            boolean isAnnotated = method.isAnnotationPresent( Select.class);
            if(isAnnotated){
                //����Mapper����
                Mapper mapper = new Mapper();
                //ȡ��ע���value����ֵ
                Select selectAnno = method.getAnnotation(Select.class);
                String queryString = selectAnno.value();
                mapper.setQueryString(queryString);
                //��ȡ��ǰ�����ķ���ֵ����Ҫ�������з�����Ϣ
                Type type = method.getGenericReturnType();//List<User>
                //�ж�type�ǲ��ǲ�����������
                if(type instanceof ParameterizedType){
                    //ǿת
                    ParameterizedType ptype = (ParameterizedType)type;
                    //�õ������������е�ʵ�����Ͳ���
                    Type[] types = ptype.getActualTypeArguments();
                    //ȡ����һ��
                    Class domainClass = (Class)types[0];
                    //��ȡdomainClass������
                    String resultType = domainClass.getName();
                    //��Mapper��ֵ
                    mapper.setResultType(resultType);
                }
                //��װkey����Ϣ
                //��ȡ����������
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String key = className+"."+methodName;
                //��map��ֵ
                mappers.put(key,mapper);
            }
        }
        return mappers;
    }
}
