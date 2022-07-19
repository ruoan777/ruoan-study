package com.ruoan.study.nio.buffer;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

import static com.ruoan.study.nio.buffer.ByteBufferUtil.debugAll;

/**
 * @author ruoan
 * @date 2022/6/24 4:03 下午
 */
public class ByteBufferReadTest {

    @Test
    public void testByteBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        // 向buffer中写入1个字节的数据
        buffer.put((byte) 97);
        // 使用工具类，查看buffer状态
        debugAll(buffer);
        System.out.println("\n");

        // 向buffer中写入4个字节的数据
        buffer.put(new byte[]{98, 99, 100, 101});
        debugAll(buffer);
        System.out.println("\n");

        // 获取数据
        buffer.flip();
        debugAll(buffer);
        System.out.println("\n");

        System.out.println(buffer.get());
        System.out.println(buffer.get());
        debugAll(buffer);
        System.out.println("\n");

        // 使用compact切换模式
        buffer.compact();
        debugAll(buffer);
        System.out.println("\n");

        // 再次写入
        buffer.put((byte) 102);
        buffer.put((byte) 103);
        debugAll(buffer);
    }

    @Test
    public void testRead() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        buffer.flip();
        buffer.get(new byte[4]);
        debugAll(buffer);

        buffer.rewind();
        System.out.println((char) buffer.get());
        debugAll(buffer);
    }

    @Test
    public void testMark() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        buffer.flip();
        buffer.get(new byte[4]);
        debugAll(buffer);

        buffer.rewind();
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        buffer.mark();
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        buffer.reset();
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        debugAll(buffer);
    }
}
