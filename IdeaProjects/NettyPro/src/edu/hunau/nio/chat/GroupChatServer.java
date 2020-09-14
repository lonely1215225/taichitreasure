package edu.hunau.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {
    private ServerSocketChannel listenChannel;
    private Selector selector;

    public GroupChatServer() {
        try {
            listenChannel = ServerSocketChannel.open();
            selector = Selector.open();
            listenChannel.configureBlocking(false);
            listenChannel.socket().bind(new InetSocketAddress(6767));
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen() throws IOException {
        while (true) {
            int keyNum = 0;
            keyNum = selector.select();
            if (keyNum == 0) {
                System.out.println("等待客户端连接......");
                continue;
            }
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            if (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) {
                    SocketChannel socketChannel = null;
                    socketChannel = listenChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println(socketChannel.getRemoteAddress() + " 上线了");
                }
                if (key.isReadable()) {
                    readData(key);
                }
                keyIterator.remove();
            }
        }
    }

    private void readData(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        int read = 0;
        SocketAddress remoteAddress = null;
        try {
            remoteAddress = socketChannel.getRemoteAddress();
            read = socketChannel.read(allocate);
        } catch (IOException e) {
            System.out.println(remoteAddress + " 离线了...");
            key.cancel();

            socketChannel.close();

        }
        if (read > 0) {
            String msg = new String(allocate.array(), 0, read);
            System.out.println("from " + remoteAddress + "：" + msg);
            //转发到其他客户端
            transferToOtherClients(msg, socketChannel);
        }
    }

    private void transferToOtherClients(String msg, SocketChannel ownChannel) {
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();
            if (channel instanceof SocketChannel && channel != ownChannel) {
                try {
                    SocketChannel socketChannel=(SocketChannel)channel;
                    ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                    socketChannel.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
           }
        }
    }

    public static void main(String[] args) throws IOException {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }

}
