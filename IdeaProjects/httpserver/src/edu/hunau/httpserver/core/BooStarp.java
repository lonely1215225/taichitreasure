package edu.hunau.httpserver.core;

import edu.hunau.httpserver.utils.DateUtil;
import edu.hunau.httpserver.utils.Logger;
import org.dom4j.DocumentException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * httpserver�������
 *
 * @author zzy
 * @version 1.0
 * @since 1.0
 */
public class BooStarp {

    //���������������
    public static void main(String[] args) {
        start();
    }

    private static void start( ) {
        //�����������׽���
        ServerSocket serverSocket=null;
        //���ڽ��տͻ����׽���
        Socket socket=null;
        //�������л���������ַ���
        BufferedReader reader=null;
        ExecutorService executorService=Executors.newFixedThreadPool( 5 );
        try {
            Logger.log( "httpserver start" );
            long before=System.currentTimeMillis();
            //����web.xml
            WebParser.parser( new String[]{"zzy"} );
            //����server.xml����ö˿�
            int port=ServerParser.getPort();
            Logger.log( "httpserver-port: " + port );
            //�����������׽��֣����Ұ󶨶˿ں�8080
            serverSocket=new ServerSocket( 8080 );
            long after=System.currentTimeMillis();
            Logger.log( "httpserver started :" + (after - before) + "ms" );
            while (true) {
                //����ÿһ���ͻ���socket������������һ���ͻ���socket
                socket=serverSocket.accept();
                //����һ���߳����ڴ���ͬ�Ŀͻ�������,�����̳߳�
                executorService.execute( new HandlerRequest( socket ) );
                //new Thread( new HandlerRequest( socket ) ).start();
            }
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null)
                    serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
