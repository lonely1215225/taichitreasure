package edu.hunau.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GroupChatClient {
    private SocketChannel socketChannel;
    private Selector selector;

    public GroupChatClient() {
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6767));
            socketChannel.configureBlocking(false);
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            System.out.println("服务器已断开");
        }
    }

    private void readData() {
        try {
            int select = selector.select();
            if (select==0) return;
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            if (key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                ByteBuffer allocate = ByteBuffer.allocate(1024);

                int read = 0;
                try {
                    read = socketChannel.read(allocate);
                    if (read > 0) {
                        System.out.println(new String(allocate.array(), 0, read));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                keyIterator.remove();
            }
        }
    }

    private void sendData(String msg,String name) {
        StringBuilder builder = new StringBuilder();
        try {
            builder.append(name);
            builder.append(" 说：");
            builder.append(msg);
            msg = builder.toString();
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startClient() {
        GroupChatClient groupChatClient = new GroupChatClient();
        new Thread(() -> {
            while (true) {
                groupChatClient.readData();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "readData").start();
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            String msg,name;
            System.out.println("请输入昵称：");
            name=scanner.nextLine();
            while (true) {
                System.out.println(">>");
                msg= scanner.nextLine();
                groupChatClient.sendData(msg,name);
            }
        }, "sendData").start();
    }

    public static void main(String[] args) {
        startClient();
    }
}
