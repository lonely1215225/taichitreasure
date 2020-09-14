package edu.hunau.httpserver.core;

import javax.servlet.Servlet;
import java.util.HashMap;
import java.util.Map;

/**
 * ��֤ͬһurl��servlet����
 */
public class ServletCache {
    private static Map<String, Servlet> servletMap=new HashMap<>(  );
    public static void put(String urlPattern,Servlet servlet){
        servletMap.put( urlPattern,servlet );
    }
    public static Servlet get(String urlPattern){
        return servletMap.get( urlPattern );
    }
}
