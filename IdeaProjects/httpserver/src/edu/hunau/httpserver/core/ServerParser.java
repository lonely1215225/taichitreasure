package edu.hunau.httpserver.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * ����server.xml�����ļ�
 *
 * @author zzy
 * @version 1.0
 * @since 1.0
 */
public class ServerParser {
    /**
     * ��ȡ�������˿�
     *
     * @return
     */
    public static int getPort( ) {
        int port=8080;
        try {
            SAXReader saxReader=new SAXReader();
            Document document=saxReader.read( "conf/server.xml" );
            //��ȡconnector�ڵ�Ԫ��xpath·��:/server/service/connector
            //����:server//connector  ����://connector
            Element connectorElt=(Element) document.selectSingleNode( "//connector" );
            //�õ�port����
            port=Integer.parseInt( connectorElt.attributeValue( "port" ) );
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return port;
    }
}
