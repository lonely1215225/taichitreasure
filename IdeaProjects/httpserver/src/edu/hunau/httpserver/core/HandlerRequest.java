package edu.hunau.httpserver.core;

import edu.hunau.httpserver.utils.Logger;

import javax.servlet.Servlet;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.Map;

/**
 * ���ڴ���ͻ���socket����
 *
 * @author zzy
 * @version 1.0
 * @since 1.0
 */
public class HandlerRequest implements Runnable {
    private Socket socketClient;

    //���տͻ��˵�socket
    public HandlerRequest(Socket socketClient) {
        this.socketClient=socketClient;
    }

    @Override
    public void run( ) {
        BufferedReader reader=null;
        PrintWriter out=null;
        Logger.log( "httpserver thread: " + Thread.currentThread().getName() );
        try {
            //��ÿͻ������������磺������:GET /zzy/user.html HTTP/1.1������ͷ�������壩
            reader=new BufferedReader( new InputStreamReader( socketClient.getInputStream() ) );
            out=new PrintWriter( socketClient.getOutputStream() );

//            String line=null;
//            while ((line=reader.readLine())!=null){
//                System.out.println(line);
//            }
            //�����������Ϣ
            String requestLine=reader.readLine();
            String requestURI=requestLine.split( " " )[1];
            Logger.log( "����������е�������Դuri��" + requestURI );

            /**
             * ���ж������������������е���Դ
             * ��Ϊ��̬��Դ��β��html��htm��β���ļ�(GET /zzy/index.html HTTP/1.1)
             * ��̬��Դ(GET /zzy/login HTTP/1.1)����(/zzy/user/save?username=zzy&gender=1)
             */
            if (requestURI.endsWith( "html" ) || requestURI.endsWith( "htm" )) {
                //��̬��Դֱ�Ӵ�ӡ����ӡ
                responseStaticPage( requestURI, out );
            } else {//��̬��Դ����
                ResponseDynamicResources( out, requestURI );
            }
            out.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
                if (socketClient != null)
                    socketClient.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     *
     * @param out
     * @param requestURI �˴���URI������һ��servlet��������
     */
    private void ResponseDynamicResources(PrintWriter out, String requestURI) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        String servletPath=requestURI;
        //���url�д��У�˵���п�����get������Ϣ����/zzy/userSave.html?username=zzy
        //�����Ϊ���ƣ�/zzy/user/save��username=zzy
        if (servletPath.contains( "?" ))
            servletPath=servletPath.split( "[?]" )[0];
        Logger.log( "����ԴΪ��̬��Դuri��" +servletPath);
        //�����Ŀ���磺/zzy/user/save���zzy
        String webAppName=servletPath.split( "[/]" )[1];

        //�ѽ�����web.xml��Ӧ��map����õ�ǰ��Ŀzzy�µ�urlpattern��classname
        Map<String, String> servletMap=WebParser.servletMaps.get( webAppName );
        //ͨ������uri���õ�url-pattern
        String urlPattern=servletPath.substring( 1 + webAppName.length() );
        //ͨ��urlpattern�õ���Ӧ��classname
        String servletClassName=servletMap.get( urlPattern );
        //���classname��Ϊ�գ���Ҫ������Ӧ��Ϣ
        if (servletClassName != null) {
            //��Ӧ�����װ
            ResponseObject responseObject=new ResponseObject();
            responseObject.setWriter( out );

            //��������װ,��װ����������uri��������Ϣ
            RequestObject requestObject=new RequestObject( requestURI );
            out.print( "HTTP/1.1 200 OK \n" );
            out.print( "Content-Type:text/html;charset=gbk \n\n" );

            //Ϊ�˽�Լϵͳ��Դ��ͬһurlPatternֻ��Ҫ����һ��Servletʵ��������
            Servlet servlet=ServletCache.get( urlPattern );
            if (servlet == null) {
                Class c=Class.forName( servletClassName );
                Object obj=c.getDeclaredConstructor().newInstance();
                servlet=(Servlet) obj;
                ServletCache.put( urlPattern, servlet );
            }
            Logger.log( "Servlet ����" + servlet );
            servlet.service( requestObject, responseObject );
        } else {
            String html="HTTP/1.1 404 NotFound \n" +
                    "Content-Type:text/html;charset=gbk \n\n" +
                    "<html>" +
                    "<head>" +
                    "<title>404-����</title>" +
//                    "<meta content=text/html;charset=gbk/>" +
                    "</head>" +
                    "<body>" +
                    "<center><font size='35px' color='red'>404-Not Foundδ�ҵ���Դ</font></center>" +
                    "</body>" +
                    "</html>";
            out.print( html );
        }
    }

    private void responseStaticPage(String requestUri, PrintWriter out) {
        BufferedReader reader=null;
        try {
            //��ȡrequestUri�����·�����Ա��ҵ���̬��Դ
            String uri=requestUri.substring( 1 );
            Logger.log( "����ԴΪ��̬��Դ��" +uri);
            reader=new BufferedReader( new FileReader( uri ) );

            String htmlLine;
            StringBuilder html=new StringBuilder();
            //������Ӧͷ��Ϣ
            html.append( "HTTP/1.1 200 OK \n" );
            html.append( "Content-Type:text/html;charset=gbk\n\n" );
            //��Ӧҳ��
            while ((htmlLine=reader.readLine()) != null) {
                html.append( htmlLine );
            }
            out.print( html );
        } catch (FileNotFoundException e) {
            //404�Ҳ�����Դ
            String html="HTTP/1.1 404 NotFound \n" +
                    "Content-Type:text/html;charset=gbk \n\n" +
                    "<html>" +
                    "<head>" +
                    "<title>404-����</title>" +
//                    "<meta content=text/html;charset=gbk/>" +
                    "</head>" +
                    "<body>" +
                    "<center><font size='35px' color='red'>404-Not Foundδ�ҵ���Դ</font></center>" +
                    "</body>" +
                    "</html>";
            out.print( html );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
