package edu.hunau.httpserver.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 解析服务器中的web.xml配置文件
 */
public class WebParser {
    //servletMaps存入的key为项目名（zzy），value为（urlPattern和ClassName键值对）
    public static Map<String, Map<String, String>> servletMaps=new HashMap<>();

    /**
     * 对所有项目名进行解析
     *
     * @param webAppNames 所有的项目名数组
     * @throws DocumentException
     */
    public static void parser(String[] webAppNames) throws DocumentException {
        for (String webAppName : webAppNames) {
            //通过parser解析每一个项目名zzy/WEB-INF/web.xml或zyz/WEB-INF/web.xml
            Map<String, String> servletMap=parser( webAppName );
            //将项目名和从该项目名下解析出的xml信息，进行存储，
            // 如：put(zzy,(/login,org.community.zzy.servlet.LoginServlet))
            servletMaps.put( webAppName, servletMap );
        }
    }

    /**
     * 解析web.xml，并将其成对存入map中
     *
     * @param webAppName 项目名，如zzy，用于xml解析文件的目录zzy/WEB-INF/web.xml
     * @return 返回的map中，键值对分别为urlPattern和className,如(/login,org.community.zzy.servlet.LoginServlet)
     * @throws DocumentException
     */
    private static Map<String, String> parser(String webAppName) throws DocumentException {
        //通过服务器解析的项目名，来拼接sun规范的目录：项目名/WEB-INF/web.xml
        String webPath=webAppName + "/WEB-INF/web.xml";
        //实例化一个xml解析器
        SAXReader saxReader=new SAXReader();
        //读入xml文件，返回一个Document对象
        Document document=saxReader.read( new File( webPath ) );
        /**
         *此处专门处理/web-app/servlet下的所有节点内容
         * 将每个servlet-name的值作为key，servlet-class的值作为value保存到map集合以便后续操作
         */
        //dom树中获取xpath，从根节点选取/,获得所有servlet节点
        List<Node> servletNodes=document.selectNodes( "/web-app/servlet" );
        //servletInfoMap中存储键：servlet-name，值：servlet-class
        Map<String, String> servletInfoMap=new HashMap<>();
        for (Node servletNode : servletNodes) {
            //从每个相对servlet节点下，得到servlet-name节点，这里转成Element
            // （因为Node只是一个接口，它被Element，Document，CharacterData实现）
            Element servletNameElt=(Element) servletNode.selectSingleNode( "servlet-name" );
            //通过Element对象获取到servlet-name的值
            String servletName=servletNameElt.getStringValue();
            //同理，获取到servlet路径下的servlet-class节点的元素对象，并取值
            // 如：（org.community.zzy.servlet.LoginServlet）
            Element servletClassElt=(Element) servletNode.selectSingleNode( "servlet-class" );
            String servletClassName=servletClassElt.getStringValue();
            //将上面获得的servlet-name的值和servlet-class的值，分别作为map的key-value保存
            servletInfoMap.put( servletName, servletClassName );
        }
        /**
         * 此处处理/web-app/servlet-mapping下所有节点
         * 和上面操作相似，将所有servlet-name作为key，url-pattern作为value保存到map
         */
        List<Node> servletMappingNodes=document.selectNodes( "/web-app/servlet-mapping" );
        Map<String, String> servletMappingInfoMap=new HashMap<>();
        for (Node servletMappingNode : servletMappingNodes) {
            Element servletNameElt=(Element) servletMappingNode.selectSingleNode( "servlet-name" );
            String servletName=servletNameElt.getStringValue();
            Element urlPatternElt=(Element) servletMappingNode.selectSingleNode( "url-pattern" );
            String urlPattern=urlPatternElt.getStringValue();
            servletMappingInfoMap.put( servletName, urlPattern );
        }
        /**
         *解析操作最后一步，取出任意一个上面两个其中一个的map的key
         * 将相同key（servlet-name）的servlet-class和url-pattern为一对保存到map
         */
        //取出所有servlet-name
        Set<String> servletNames=servletInfoMap.keySet();
        //用于保存url-pattern对应的servlet-class
        Map<String, String> servletMap=new HashMap<>();
        //遍历所有servlet-name
        for (String servletName : servletNames) {
            //上面获得的map中，取出url-pattern
            String urlPattern=servletMappingInfoMap.get( servletName );
            //取出相同servlet-name下的servlet-class
            String servletClassName=servletInfoMap.get( servletName );
            //保存形如-> (/login  :  org.community.zzy.servlet.LoginServlet)
            servletMap.put( urlPattern, servletClassName );
        }

        return servletMap;
    }
}
