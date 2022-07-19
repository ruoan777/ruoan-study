package com.ruoan.study.threadlocal.ruoanttl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ruoan
 * @date 2022/5/17 12:45 上午
 */
public class MyTask implements Runnable {

    // key是ThreadLocal，value是对应父线程的线程私有数据
    private final Map<ThreadLocal<Object>, Object> threadLocalValues;

    public MyTask(ThreadLocal<Object>... threadLocals) {
        this.threadLocalValues = new HashMap<>(threadLocals.length);
        capture(threadLocals);
    }

    private void capture(ThreadLocal<Object>[] threadLocals) {
        for (ThreadLocal<Object> threadLocal : threadLocals) {
            threadLocalValues.put(threadLocal, threadLocal.get());
        }
    }

    @Override
    public void run() {
        Object backup = replay();
        try {
            // do biz
            doBiz();
        } finally {
            restore(backup);
        }
    }

    private Object replay() {
        // 保存当前的ThreadLocal值
        Map<ThreadLocal<Object>, Object> backup = new HashMap<>();
        for (ThreadLocal<Object> threadLocal : threadLocalValues.keySet()) {
            backup.put(threadLocal, threadLocal.get());
        }

        for (Map.Entry<ThreadLocal<Object>, Object> entry : threadLocalValues.entrySet()) {
            ThreadLocal<Object> threadLocal = entry.getKey();
            threadLocal.set(entry.getValue());
        }

        return backup;
    }


    private void doBiz() {
        // 打印父线程提交任务时的ThreadLocal值
        Set<ThreadLocal<Object>> threadLocals = threadLocalValues.keySet();
        for (ThreadLocal<Object> threadLocal : threadLocals) {
            System.out.println(threadLocal.get());
        }
    }

    private void restore(Object obj) {
        Map<ThreadLocal<Object>, Object> backup = (Map<ThreadLocal<Object>, Object>) obj;

        for (Map.Entry<ThreadLocal<Object>, Object> entry : backup.entrySet()) {
            ThreadLocal<Object> threadLocal = entry.getKey();
            threadLocal.set(entry.getValue());
        }
    }

}

