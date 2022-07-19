package com.ruoan.study.nio.selector;

import com.ruoan.study.nio.buffer.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author ruoan
 * @date 2022/6/25 6:22 下午
 */
@Slf4j
public class SelectorServerNew {
    @Test
    public void server() throws IOException {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(9999));
            ssc.configureBlocking(false);
            Selector selector = Selector.open();
            SelectionKey sscKey = ssc.register(selector, 0, null);
            sscKey.interestOps(SelectionKey.OP_ACCEPT);
            log.debug("register sk {}", sscKey);
            while (true) {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                log.debug("sk number {}", selector.selectedKeys().size());
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    log.debug("sk out {}", key);
                    if (key.isAcceptable()) {
                        //可以简化为ssc.accept()
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        SocketChannel sc = channel.accept();
                        sc.configureBlocking(false);
                        ByteBuffer buffer = ByteBuffer.allocate(16);
                        SelectionKey sk = sc.register(selector, 0, buffer);
                        sk.interestOps(SelectionKey.OP_READ);
                        log.debug("sc {}", sc);
                        log.debug("sk in {}", sk);
                    }
                    if (key.isReadable()) {
                        try {
                            SocketChannel channel = (SocketChannel) key.channel();
                            ByteBuffer buffer = (ByteBuffer) key.attachment();
                            int read = channel.read(buffer);
                            if (read == -1) {
                                key.cancel();
                            } else {
                                split(buffer);
                                if (buffer.limit() == buffer.position()) {
                                    ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                                    buffer.flip();
                                    newBuffer.put(buffer);
                                    key.attach(newBuffer);
                                }
                            }
                        } catch (IOException e) {
                            key.cancel();
                            e.printStackTrace();
                        }
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void split(ByteBuffer buffer) {
        // 切换为读模式
        buffer.flip();
        for (int i = 0; i < buffer.limit(); i++) {
            // 遍历寻找分隔符
            // get(i)不会移动position
            if (buffer.get(i) == '\n') {
                // 缓冲区长度
                int length = i + 1 - buffer.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                // 将前面的内容写入target缓冲区
                for (int j = 0; j < length; j++) {
                    // 将buffer中的数据写入target中
                    target.put(buffer.get());
                }
                // 打印查看结果
                ByteBufferUtil.debugAll(target);
            }
        }
        // 切换为写模式，但是缓冲区可能未读完，这里需要使用compact
        buffer.compact();
    }
}
