package edu.hunau.nio;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIOTraining {
    public static void main(String[] args) throws IOException {

    }

    public static void copyFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:\\新建文件夹\\1080P_4000K_292141262.mp4");
        FileOutputStream fileOutputStream = new FileOutputStream("test.mp4");
        FileChannel channel = fileOutputStream.getChannel();
        FileChannel inputStreamChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(fileInputStream.available() >> 4);

        int read = 0;
        long start = System.currentTimeMillis();
        while ((read = inputStreamChannel.read(byteBuffer)) != -1) {
            System.out.println(read);
            byteBuffer.flip();
            channel.write(byteBuffer);
            //byteBuffer.flip(); limit=position
            byteBuffer.clear(); //limit=capacity
        }
        channel.close();
        inputStreamChannel.close();
        fileOutputStream.close();
        fileInputStream.close();
        long end = System.currentTimeMillis();
        System.out.println("花费了" + (end - start) + "ms");
    }

    //这个是上面的优减版
    public static void quickCopyFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:\\新建文件夹\\1080P_4000K_292141262.mp4");
        FileOutputStream fileOutputStream = new FileOutputStream("test.mp4");
        FileChannel channel = fileOutputStream.getChannel();
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        long start = System.currentTimeMillis();

        channel.transferFrom(inputStreamChannel, 0, inputStreamChannel.size());
        long end = System.currentTimeMillis();
        System.out.println("花费了" + (end - start) + "ms");

    }

    public static void fileMapTest() throws IOException {

        FileChannel open = FileChannel.open(Paths.get("test.txt"), StandardOpenOption.READ, StandardOpenOption.WRITE);
        MappedByteBuffer map = open.map(FileChannel.MapMode.READ_WRITE, 0, open.size());
        map.put(0, (byte) 'H');
        open.close();

    }
}
