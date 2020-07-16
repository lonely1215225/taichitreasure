package org.community.zzy.servlet;

import edu.hunau.httpserver.core.RequestObject;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * 处理登录业务的java程序，该程序由webApp开发，由web服务器人员负责调用
 */
public class LoginServlet implements Servlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) {
        System.out.println("正在验证身份...");
        PrintWriter out=response.getWriter();
        String html=
                "<html>" +
                "<head>" +
                "<title>处理中...</title>" +
//                    "<meta content=text/html;charset=gbk/>" +
                "</head>" +
                "<body>" +
                "<center><font size='35px' color='blue'>正在验证用户信息，请稍等...</font></center>" +
                "</body>" +
                "</html>";
        out.print( html );
    }
}
