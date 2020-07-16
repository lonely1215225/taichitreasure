package edu.hunau.httpserver.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * 解析server.xml配置文件
 *
 * @author zzy
 * @version 1.0
 * @since 1.0
 */
public class ServerParser {
    /**
     * 获取服务器端口
     *
     * @return
     */
    public static int getPort( ) {
        int port=8080;
        try {
            SAXReader saxReader=new SAXReader();
            Document document=saxReader.read( "conf/server.xml" );
            //获取connector节点元素xpath路径:/server/service/connector
            //或者:server//connector  或者://connector
            Element connectorElt=(Element) document.selectSingleNode( "//connector" );
            //拿到port属性
            port=Integer.parseInt( connectorElt.attributeValue( "port" ) );
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return port;
    }
}
