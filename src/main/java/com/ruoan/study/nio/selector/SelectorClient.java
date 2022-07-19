package com.ruoan.study.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author ruoan
 * @date 2022/6/25 8:07 下午
 */
public class SelectorClient {
    public static void main(String[] args) {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            // 建立连接
            socketChannel.connect(new InetSocketAddress("localhost", 9999));
            int hello = socketChannel.write(Charset.defaultCharset().encode("0123456789abcdef3333\n"));
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
