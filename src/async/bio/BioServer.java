package async.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务启动成功");
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                System.out.println("连接成功");
                System.out.println("准备接收数据");
                byte[] bytes = new byte[1024];
                try {
                    socket.getInputStream().read(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("接收到了数据： " + new String(bytes));
            }).start();
        }
    }
}
