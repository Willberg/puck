package async.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NioServer {
    private static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);
        System.out.println("服务启动成功");

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                System.out.println("连接成功");
                socketChannel.configureBlocking(false);
                channelList.add(socketChannel);
            }

            Iterator<SocketChannel> iterable = channelList.iterator();
            while (iterable.hasNext()) {
                SocketChannel o = iterable.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                int read = o.read(byteBuffer);
                if (read > 0) {
                    // 如果有数据
                    System.out.println("接受到的数据： " + new String(byteBuffer.array()));
                } else if (read == -1) {
                    // 客户端断开
                    iterable.remove();
                    System.out.println("客户端断开");
                }
            }
        }
    }
}
