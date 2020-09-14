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
                        "<title>用户信息</title>" +
                    "<meta content=text/html;charset=gbk/>" +
                        "</head>" +
                        "<body>" +
                        "<center><font size='35px' color='blue'>用户名："+username+"<br>" +
                        "性别："+gender+"<br>兴趣："+interest+"</font></center>" +
                        "</body>" +
                        "</html>";
        out.print( html );
    }
}
