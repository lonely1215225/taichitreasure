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
 * �����������е�web.xml�����ļ�
 */
public class WebParser {
    //servletMaps�����keyΪ��Ŀ����zzy����valueΪ��urlPattern��ClassName��ֵ�ԣ�
    public static Map<String, Map<String, String>> servletMaps=new HashMap<>();

    /**
     * ��������Ŀ�����н���
     *
     * @param webAppNames ���е���Ŀ������
     * @throws DocumentException
     */
    public static void parser(String[] webAppNames) throws DocumentException {
        for (String webAppName : webAppNames) {
            //ͨ��parser����ÿһ����Ŀ��zzy/WEB-INF/web.xml��zyz/WEB-INF/web.xml
            Map<String, String> servletMap=parser( webAppName );
            //����Ŀ���ʹӸ���Ŀ���½�������xml��Ϣ�����д洢��
            // �磺put(zzy,(/login,org.community.zzy.servlet.LoginServlet))
            servletMaps.put( webAppName, servletMap );
        }
    }

    /**
     * ����web.xml��������ɶԴ���map��
     *
     * @param webAppName ��Ŀ������zzy������xml�����ļ���Ŀ¼zzy/WEB-INF/web.xml
     * @return ���ص�map�У���ֵ�Էֱ�ΪurlPattern��className,��(/login,org.community.zzy.servlet.LoginServlet)
     * @throws DocumentException
     */
    private static Map<String, String> parser(String webAppName) throws DocumentException {
        //ͨ����������������Ŀ������ƴ��sun�淶��Ŀ¼����Ŀ��/WEB-INF/web.xml
        String webPath=webAppName + "/WEB-INF/web.xml";
        //ʵ����һ��xml������
        SAXReader saxReader=new SAXReader();
        //����xml�ļ�������һ��Document����
        Document document=saxReader.read( new File( webPath ) );
        /**
         *�˴�ר�Ŵ���/web-app/servlet�µ����нڵ�����
         * ��ÿ��servlet-name��ֵ��Ϊkey��servlet-class��ֵ��Ϊvalue���浽map�����Ա��������
         */
        //dom���л�ȡxpath���Ӹ��ڵ�ѡȡ/,�������servlet�ڵ�
        List<Node> servletNodes=document.selectNodes( "/web-app/servlet" );
        //servletInfoMap�д洢����servlet-name��ֵ��servlet-class
        Map<String, String> servletInfoMap=new HashMap<>();
        for (Node servletNode : servletNodes) {
            //��ÿ�����servlet�ڵ��£��õ�servlet-name�ڵ㣬����ת��Element
            // ����ΪNodeֻ��һ���ӿڣ�����Element��Document��CharacterDataʵ�֣�
            Element servletNameElt=(Element) servletNode.selectSingleNode( "servlet-name" );
            //ͨ��Element�����ȡ��servlet-name��ֵ
            String servletName=servletNameElt.getStringValue();
            //ͬ����ȡ��servlet·���µ�servlet-class�ڵ��Ԫ�ض��󣬲�ȡֵ
            // �磺��org.community.zzy.servlet.LoginServlet��
            Element servletClassElt=(Element) servletNode.selectSingleNode( "servlet-class" );
            String servletClassName=servletClassElt.getStringValue();
            //�������õ�servlet-name��ֵ��servlet-class��ֵ���ֱ���Ϊmap��key-value����
            servletInfoMap.put( servletName, servletClassName );
        }
        /**
         * �˴�����/web-app/servlet-mapping�����нڵ�
         * ������������ƣ�������servlet-name��Ϊkey��url-pattern��Ϊvalue���浽map
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
         *�����������һ����ȡ������һ��������������һ����map��key
         * ����ͬkey��servlet-name����servlet-class��url-patternΪһ�Ա��浽map
         */
        //ȡ������servlet-name
        Set<String> servletNames=servletInfoMap.keySet();
        //���ڱ���url-pattern��Ӧ��servlet-class
        Map<String, String> servletMap=new HashMap<>();
        //��������servlet-name
        for (String servletName : servletNames) {
            //�����õ�map�У�ȡ��url-pattern
            String urlPattern=servletMappingInfoMap.get( servletName );
            //ȡ����ͬservlet-name�µ�servlet-class
            String servletClassName=servletInfoMap.get( servletName );
            //��������-> (/login  :  org.community.zzy.servlet.LoginServlet)
            servletMap.put( urlPattern, servletClassName );
        }

        return servletMap;
    }
}
