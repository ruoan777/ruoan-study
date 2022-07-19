package com.ruoan.study.nio.write;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author ruoan
 * @date 2022/6/26 12:16 下午
 */
public class WriteClient {

    @Test
    public void client() throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 9999));

        int count = 0;
        while (true) {
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
            count += sc.read(buffer);
            System.out.println(count);
            buffer.clear();
        }

    }
}
