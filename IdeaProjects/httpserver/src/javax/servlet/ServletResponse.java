package javax.servlet;

import java.io.PrintWriter;

/**
 * 封装响应信息的接口规范
 */
public interface ServletResponse {
    void setWriter(PrintWriter out);
    PrintWriter getWriter();
}
