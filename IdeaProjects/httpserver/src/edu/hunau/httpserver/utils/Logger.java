package edu.hunau.httpserver.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ��־��¼��
 *
 * @author zzy
 * @version 1.0
 * @since 1.0
 */
public class Logger {
    private Logger( ) {
    }

    /**
     * ����־��¼
     *
     * @param msg
     */
    public static void log(String msg) {
//        SimpleDateFormat dateFormat=new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
//        Date nowTime=new Date();
//        String nowTimeStr=dateFormat.format( nowTime );
        System.out.println( "[INFO]" + DateUtil
                .getCurrentTime() + " " + msg );
    }
}
