package edu.hunau.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) {
        startBIO();
    }

    public static void startBIO() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket accept = serverSocket.accept();//阻塞等待客户端连接
                System.out.println(accept.getLocalAddress() + ":" + "客户端已连接");
                executorService.execute(() -> {
                    handlerIO(accept);
                });
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void handlerIO(Socket socket) {
        System.out.println(Thread.currentThread().getName() + "连接上了");
        try (InputStream inputStream = socket.getInputStream()) {
            byte[] bytes = new byte[1024];
            int len = 0;
            //read阻塞，This method blocks until input data is available, end of file is detected, or an exception is thrown.
            while ((len = inputStream.read(bytes)) != -1) System.out.println(new String(bytes, 0, len));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
