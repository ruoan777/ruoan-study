package com.ruoan.study.excutor;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

/**
 * @author xh.gao
 * @Description Many {@code Executor} implementations impose some sort of
 * limitation on how and when tasks are scheduled.  The executor below
 * serializes the submission of tasks to a second executor, illustrating a composite executor.
 * @createTime 2020年08月26日 10:38:00
 */
public class SerialExecutor implements Executor {
    final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
    final Executor executor;
    Runnable active;

    SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public synchronized void execute(final Runnable r) {
        tasks.offer(new Runnable() {
            @Override
            public void run() {
                try {
                    r.run();
                } finally {
                    scheduleNext();
                }
            }
        });
        if (active == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            executor.execute(active);
        }
    }
}