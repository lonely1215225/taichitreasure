package edu.hunau.practice;

import java.io.*;

public class IOPractice {
    public static void main(String[] args) {
        //        BufferedInputStream bis=null;
//        BufferedOutputStream bos=null;
//        try {
//            bis=new BufferedInputStream( new FileInputStream( "C:\\Users\\Administrator\\Desktop\\QQ截图111.png" ) );
//            bos=new BufferedOutputStream( new FileOutputStream( "zzy/111.png" ) );
//            int read=0;
//            while ((read=bis.read()) != -1) {
//                System.out.print(read);
//                bos.write( read );
//            }
//            bos.flush();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//
//            try {
//                if (bis != null)
//                    bis.close();
//                if (bos != null)
//                    bos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        InputStream fis=null;
        OutputStream fos=null;
        try {
            long start=System.currentTimeMillis();
            fis=new FileInputStream( "C:\\Users\\Administrator\\Desktop\\QQ截图111.png" );
            fos=new FileOutputStream( "zzy/112.png" );
            int count=fis.available();
            byte[] read=new byte[count];
            while ((count=fis.read( read )) != -1) {
                fos.write( read );
            }
            fos.flush();
            long end=System.currentTimeMillis();
            System.out.println( "不带buffered复制花费的时间：" + (end - start) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        BufferedReader reader=null;
//        BufferedWriter writer=null;
//        try {
//            reader=new BufferedReader( new FileReader( "zzy/index.html" ) );
//            writer=new BufferedWriter( new FileWriter( "zzy/index2.htm" ) );
////            String line=null;
//            int line=0;
//            while ((line=reader.read()) != -1) {
//                System.out.print((char)line );
//                writer.write( (char)line);
//            }
//            writer.flush();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//
//            try {
//                if (reader != null)
//                    reader.close();
//                if (writer != null)
//                    writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
