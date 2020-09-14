package edu.hunau.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class SocketNioTest {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel=SocketChannel.open(new InetSocketAddress("127.0.0.1",9898));
        socketChannel.configureBlocking(false);
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        allocate.put("我是你爸爸".getBytes());
        allocate.flip();
        socketChannel.write(allocate);
        socketChannel.close();
    }
}
class ServerTestNio{
    public static void main(String[] args) throws IOException {
        ServerSocketChannel open = ServerSocketChannel.open();
        open.configureBlocking(false);
        open.bind(new InetSocketAddress(9898));
        Selector selector = Selector.open();
        open.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select()>0){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                if (next.isAcceptable()){
                    //获取到客户端的连接
                    SocketChannel socketChannel = open.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(next.isReadable()){
                    SocketChannel socketChannel= (SocketChannel) next.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len=0;
                    while ((len=socketChannel.read(buffer))>0){
                        buffer.flip();
                        System.out.println(new String(buffer.array()));
                        buffer.clear();
                    }
                }
            }
            iterator.remove();
        }
    }
}