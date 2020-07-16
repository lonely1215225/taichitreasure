package edu.hunau.httpserver.core;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * 负责封装响应参数对象
 */
public class ResponseObject implements ServletResponse {
    private PrintWriter out;
    @Override
    public void setWriter(PrintWriter out) {
        this.out=out;
    }

    @Override
    public PrintWriter getWriter( ) {
        return out;
    }
}
