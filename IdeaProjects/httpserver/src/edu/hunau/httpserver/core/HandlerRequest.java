package edu.hunau.httpserver.core;

import edu.hunau.httpserver.utils.Logger;

import javax.servlet.Servlet;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.Map;

/**
 * 用于处理客户端socket请求
 *
 * @author zzy
 * @version 1.0
 * @since 1.0
 */
public class HandlerRequest implements Runnable {
    private Socket socketClient;

    //接收客户端的socket
    public HandlerRequest(Socket socketClient) {
        this.socketClient=socketClient;
    }

    @Override
    public void run( ) {
        BufferedReader reader=null;
        PrintWriter out=null;
        Logger.log( "httpserver thread: " + Thread.currentThread().getName() );
        try {
            //获得客户端输入流（如：请求行:GET /zzy/user.html HTTP/1.1，请求头，请求体）
            reader=new BufferedReader( new InputStreamReader( socketClient.getInputStream() ) );
            out=new PrintWriter( socketClient.getOutputStream() );

//            String line=null;
//            while ((line=reader.readLine())!=null){
//                System.out.println(line);
//            }
            //获得请求行信息
            String requestLine=reader.readLine();
            String requestURI=requestLine.split( " " )[1];
            Logger.log( "获得请求行中的请求资源uri：" + requestURI );

            /**
             * 此判断条件处处理请求行中的资源
             * 分为静态资源结尾以html或htm结尾的文件(GET /zzy/index.html HTTP/1.1)
             * 动态资源(GET /zzy/login HTTP/1.1)或者(/zzy/user/save?username=zzy&gender=1)
             */
            if (requestURI.endsWith( "html" ) || requestURI.endsWith( "htm" )) {
                //静态资源直接打印流打印
                responseStaticPage( requestURI, out );
            } else {//动态资源处理
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
     * @param requestURI 此处的URI类似向一个servlet发送请求
     */
    private void ResponseDynamicResources(PrintWriter out, String requestURI) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        String servletPath=requestURI;
        //如果url中带有？说明有可能有get请求信息，如/zzy/userSave.html?username=zzy
        //将其分为类似：/zzy/user/save和username=zzy
        if (servletPath.contains( "?" ))
            servletPath=servletPath.split( "[?]" )[0];
        Logger.log( "该资源为动态资源uri：" +servletPath);
        //获得项目名如：/zzy/user/save里的zzy
        String webAppName=servletPath.split( "[/]" )[1];

        //已解析的web.xml对应的map，获得当前项目zzy下的urlpattern和classname
        Map<String, String> servletMap=WebParser.servletMaps.get( webAppName );
        //通过请求uri，拿到url-pattern
        String urlPattern=servletPath.substring( 1 + webAppName.length() );
        //通过urlpattern拿到对应得classname
        String servletClassName=servletMap.get( urlPattern );
        //如果classname不为空，则要进行响应信息
        if (servletClassName != null) {
            //响应对象封装
            ResponseObject responseObject=new ResponseObject();
            responseObject.setWriter( out );

            //请求对象封装,封装了请求行中uri的请求信息
            RequestObject requestObject=new RequestObject( requestURI );
            out.print( "HTTP/1.1 200 OK \n" );
            out.print( "Content-Type:text/html;charset=gbk \n\n" );

            //为了节约系统资源，同一urlPattern只需要创建一次Servlet实例就行了
            Servlet servlet=ServletCache.get( urlPattern );
            if (servlet == null) {
                Class c=Class.forName( servletClassName );
                Object obj=c.getDeclaredConstructor().newInstance();
                servlet=(Servlet) obj;
                ServletCache.put( urlPattern, servlet );
            }
            Logger.log( "Servlet 对象：" + servlet );
            servlet.service( requestObject, responseObject );
        } else {
            String html="HTTP/1.1 404 NotFound \n" +
                    "Content-Type:text/html;charset=gbk \n\n" +
                    "<html>" +
                    "<head>" +
                    "<title>404-错误</title>" +
//                    "<meta content=text/html;charset=gbk/>" +
                    "</head>" +
                    "<body>" +
                    "<center><font size='35px' color='red'>404-Not Found未找到资源</font></center>" +
                    "</body>" +
                    "</html>";
            out.print( html );
        }
    }

    private void responseStaticPage(String requestUri, PrintWriter out) {
        BufferedReader reader=null;
        try {
            //获取requestUri的相对路径，以便找到静态资源
            String uri=requestUri.substring( 1 );
            Logger.log( "该资源为静态资源：" +uri);
            reader=new BufferedReader( new FileReader( uri ) );

            String htmlLine;
            StringBuilder html=new StringBuilder();
            //设置响应头信息
            html.append( "HTTP/1.1 200 OK \n" );
            html.append( "Content-Type:text/html;charset=gbk\n\n" );
            //响应页面
            while ((htmlLine=reader.readLine()) != null) {
                html.append( htmlLine );
            }
            out.print( html );
        } catch (FileNotFoundException e) {
            //404找不到资源
            String html="HTTP/1.1 404 NotFound \n" +
                    "Content-Type:text/html;charset=gbk \n\n" +
                    "<html>" +
                    "<head>" +
                    "<title>404-错误</title>" +
//                    "<meta content=text/html;charset=gbk/>" +
                    "</head>" +
                    "<body>" +
                    "<center><font size='35px' color='red'>404-Not Found未找到资源</font></center>" +
                    "</body>" +
                    "</html>";
            out.print( html );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
