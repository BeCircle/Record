package concurrency;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ThreadPearMessage {
    static void server() throws IOException {
        final ServerSocketChannel ssc =
                ServerSocketChannel.open().bind(
                        new InetSocketAddress(8080));
        //处理请求
        try {
            while (true) {
                // 接收请求
                SocketChannel sc = ssc.accept();
                // 每个请求都创建一个线程
                new Thread(() -> {
                    try {
                        // 读Socket
                        ByteBuffer rb = ByteBuffer.allocateDirect(1024);
                        sc.read(rb);
                        //模拟处理请求
                        Thread.sleep(2000);
                        // 写Socket
                        ByteBuffer wb = (ByteBuffer) rb.flip();
                        sc.write(wb);
                        // 关闭Socket
                        sc.close();
                    } catch (Exception e) {
                        throw new UncheckedIOException((IOException) e);
                    }
                }).start();
            }
        } finally {
            ssc.close();
        }
    }

    public static void main(String[] args) throws IOException {
        server();
    }
}

