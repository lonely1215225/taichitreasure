package edu.hunau.practice;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class XMLTest {
    public static void main(String[] args) {
        try {
            SAXReader saxReader=new SAXReader();
            Document document=saxReader.read( new File( "zzy/WEB-INF/web.xml" ) );
//            Element rootElement=document.getRootElement();
//            System.out.println( rootElement.getXPathResult( 1 ).getPath() );
//            Node xPathResult=rootElement.getXPathResult( 2 );
//            System.out.println( xPathResult.getPath());
//            System.out.println( rootElement.getXPathResult( 3 ).getPath());
//            System.out.println( rootElement.getXPathResult( 5 ).getStringValue() );
            List<Node> nodes=document.selectNodes( "//servlet-mapping" );
            for (Node node:nodes){
                System.out.println( node.selectSingleNode( "url-pattern" ).getStringValue() );
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
}
