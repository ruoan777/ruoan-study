package com.ruoan.study.process;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author xh.gao
 * @version 1.0.0
 * @ClassName TestProcess.java
 * @Description TODO 在Java中，可以通过两种方式来创建进程，总共涉及到5个主要的类。
 *              TODO 第一种方式是通过Runtime.exec()方法来创建一个进程，
 *              TODO 第二种方法是通过ProcessBuilder的start方法来创建进程。
 * @createTime 2020年01月02日 16:55:00
 */
public class TestProcess {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("cmd","/c","ipconfig/all");
        Process process = pb.start();
        Scanner scanner = new Scanner(process.getInputStream());

        while(scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}
