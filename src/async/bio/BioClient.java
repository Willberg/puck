package async.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class BioClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);
        System.out.println("连接成功");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String str = scanner.nextLine();
            if ("exit".equalsIgnoreCase(str)) {
                socket.close();
                System.exit(0);
            }
            socket.getOutputStream().write(str.getBytes());
            socket.getOutputStream().flush();
        }
    }
}
