package org.community.zzy.servlet;

import edu.hunau.httpserver.core.RequestObject;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * �����¼ҵ���java���򣬸ó�����webApp��������web��������Ա�������
 */
public class LoginServlet implements Servlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) {
        System.out.println("������֤���...");
        PrintWriter out=response.getWriter();
        String html=
                "<html>" +
                "<head>" +
                "<title>������...</title>" +
//                    "<meta content=text/html;charset=gbk/>" +
                "</head>" +
                "<body>" +
                "<center><font size='35px' color='blue'>������֤�û���Ϣ�����Ե�...</font></center>" +
                "</body>" +
                "</html>";
        out.print( html );
    }
}
