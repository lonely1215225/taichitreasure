package edu.hunau.httpserver.core;

import edu.hunau.httpserver.utils.DateUtil;
import edu.hunau.httpserver.utils.Logger;
import org.dom4j.DocumentException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * httpserver程序入口
 *
 * @author zzy
 * @version 1.0
 * @since 1.0
 */
public class BooStarp {

    //主程序服务器启动
    public static void main(String[] args) {
        start();
    }

    private static void start( ) {
        //创建服务器套接字
        ServerSocket serverSocket=null;
        //用于接收客户端套接字
        Socket socket=null;
        //创建带有缓冲的输入字符流
        BufferedReader reader=null;
        ExecutorService executorService=Executors.newFixedThreadPool( 5 );
        try {
            Logger.log( "httpserver start" );
            long before=System.currentTimeMillis();
            //解析web.xml
            WebParser.parser( new String[]{"zzy"} );
            //解析server.xml，获得端口
            int port=ServerParser.getPort();
            Logger.log( "httpserver-port: " + port );
            //创建服务器套接字，并且绑定端口号8080
            serverSocket=new ServerSocket( 8080 );
            long after=System.currentTimeMillis();
            Logger.log( "httpserver started :" + (after - before) + "ms" );
            while (true) {
                //监听每一个客户端socket，监听到返回一个客户端socket
                socket=serverSocket.accept();
                //新起一个线程用于处理不同的客户端请求,利用线程池
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
