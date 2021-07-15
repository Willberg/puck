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
                while (true) {
                    try {
                        socket.getInputStream().read(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (bytes[0] != '\0') {
                        String s = new String(bytes);
                        System.out.println("接收到了数据： " + s.substring(0, s.indexOf("\0")));
                        bytes[0] = '\0';
                    }
                }
            }).start();
        }
    }
}
