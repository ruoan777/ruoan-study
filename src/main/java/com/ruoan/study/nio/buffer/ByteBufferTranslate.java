package com.ruoan.study.nio.buffer;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author ruoan
 * @date 2022/6/24 4:52 下午
 */
public class ByteBufferTranslate {

    @Test
    public void testTranslate() {
        // 准备两个字符串
        String str1 = "hello";
        String str2 = "";

        ByteBuffer buffer1 = ByteBuffer.allocate(16);
        // 通过字符串的getByte方法获得字节数组，放入缓冲区中
        buffer1.put(str1.getBytes());
        ByteBufferUtil.debugAll(buffer1);

        // 将缓冲区中的数据转化为字符串
        // 切换模式
        buffer1.flip();
        // 通过StandardCharsets解码，获得CharBuffer，再通过toString获得字符串
        str2 = StandardCharsets.UTF_8.decode(buffer1).toString();
        System.out.println(str2);
        ByteBufferUtil.debugAll(buffer1);

        ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode("hello");
        ByteBufferUtil.debugAll(byteBuffer);


    }
}
