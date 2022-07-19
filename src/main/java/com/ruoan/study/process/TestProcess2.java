package com.ruoan.study.process;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author xh.gao
 * @version 1.0.0
 * @ClassName TestProcess2.java
 * @Description TODO 可以发现，事实上通过Runtime类的exec创建进程的话，最终还是通过ProcessBuilder类的start方法来创建的。
 * @createTime 2020年01月02日 17:27:00
 */
public class TestProcess2 {
    public static void main(String[] args) throws IOException {
        String cmd = "cmd "+"/c "+"ipconfig/all";
        Process process = Runtime.getRuntime().exec(cmd);
        Scanner scanner = new Scanner(process.getInputStream());

        while(scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}
