package javax.servlet;

import java.io.PrintWriter;

/**
 * ��װ��Ӧ��Ϣ�Ľӿڹ淶
 */
public interface ServletResponse {
    void setWriter(PrintWriter out);
    PrintWriter getWriter();
}
