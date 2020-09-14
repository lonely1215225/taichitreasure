package edu.hunau.httpserver.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static SimpleDateFormat dateFormat=new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss SSS" );
    private DateUtil(){}

    /**
     * 获取当前时间:yyyy-MM-dd HH:mm:ss SSS
     * @return
     */
    public static String getCurrentTime(){
        return dateFormat.format( new Date(  ) );
    }
}
