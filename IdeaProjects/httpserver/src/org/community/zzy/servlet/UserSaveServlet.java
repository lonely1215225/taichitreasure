package org.community.zzy.servlet;

import edu.hunau.httpserver.core.RequestObject;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;

public class UserSaveServlet implements Servlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) {
        String username=request.getParameterValue( "username" );
        String gender=request.getParameterValue( "gender" );
        String[] interests=request.getParameterValues( "interest" );
        String interest=Arrays.toString( interests );
        PrintWriter out=response.getWriter();
        String html=
                "<html>" +
                        "<head>" +
                        "<title>�û���Ϣ</title>" +
                    "<meta content=text/html;charset=gbk/>" +
                        "</head>" +
                        "<body>" +
                        "<center><font size='35px' color='blue'>�û�����"+username+"<br>" +
                        "�Ա�"+gender+"<br>��Ȥ��"+interest+"</font></center>" +
                        "</body>" +
                        "</html>";
        out.print( html );
    }
}
