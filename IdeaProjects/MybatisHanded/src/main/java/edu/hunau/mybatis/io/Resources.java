package edu.hunau.mybatis.io;

import java.io.InputStream;

/**
 * ʹ�����������ȡ�����ļ�����
 */
public class Resources {
    public static InputStream getResourceAsStream(String filePath) {
        return Resources.class.getClassLoader().getResourceAsStream( filePath );
    }
}
