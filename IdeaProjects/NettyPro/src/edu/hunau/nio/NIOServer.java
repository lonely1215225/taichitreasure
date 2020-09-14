package edu.hunau.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        //开放一个服务端套接字通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);
        //通道绑定本机的一个端口
        serverSocketChannel.bind(new InetSocketAddress(6666));
        //开放一个selector选择器，也就是多路复用器
        Selector selector = Selector.open();
        //将通道注册到此选择器上，并且传入结果键的兴趣集
        //Registers this channel with the given selector, returning a selection key.
        //The interest set for the resulting key
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            int select = selector.select(1000);//已经为io操作做好了准备的所有选择键个数
            if (select==0){ System.out.println("暂无连接...");continue;}

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                //Tests whether this key's channel is ready to accept a new socket connection.
                if (selectionKey.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    //将从服务端监听到的客户端socket通道注册到selector上，并且给此通道绑定选择键
                    // 第三个参数表示附属物，在后续其他事件中可以取到进行操作
                    socketChannel.register(selector,SelectionKey.OP_READ,ByteBuffer.allocate(1024));
                }
                if (selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer attachment = (ByteBuffer) selectionKey.attachment();
                    int read = socketChannel.read(attachment);
                    if (read==-1){iterator.remove(); continue;}
                    System.out.println(new String(attachment.array(),0,read));
                }
                iterator.remove();
            }
        }
    }
}
