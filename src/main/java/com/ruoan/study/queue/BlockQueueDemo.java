package com.ruoan.study.queue;

import org.apache.commons.lang3.ThreadUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueueDemo {

    private static final ArrayBlockingQueue<Student> blockingQueue = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        new Thread(new Producer(blockingQueue)).start();
        new Thread(new Producer(blockingQueue)).start();
        new Thread(new Consumer(blockingQueue)).start();
        new Thread(new Consumer(blockingQueue)).start();
    }
}

class Student {

    public Student() {
    }
}

class Producer implements Runnable {

    private ArrayBlockingQueue<Student> blockingQueue;

    public Producer(ArrayBlockingQueue<Student> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            produce();
        }
    }

    private void produce() {
        try {
//            TimeUnit.SECONDS.sleep(2);
            Student student = new Student();
            blockingQueue.put(student);
            System.out.println("生产了一个对象" + student);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {

    private ArrayBlockingQueue<Student> blockingQueue;

    public Consumer(ArrayBlockingQueue<Student> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            consume();
        }
    }

    private void consume() {
        try {
//            TimeUnit.SECONDS.sleep(2);
            Student take = blockingQueue.take();
            System.out.println("消费了一个对象" + take);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
