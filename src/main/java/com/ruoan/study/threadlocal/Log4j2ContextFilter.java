package com.ruoan.study.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author ruoan
 * @date 2022/5/23 11:42 下午
 */
@Log4j2
public class Log4j2ContextFilter {

    // log4j2 com.ruoan.study.context
    static TransmittableThreadLocal<Map<String, String>> mtc = new TransmittableThreadLocal<Map<String, String>>() {
        @Override
        protected void beforeExecute() {
            final Map<String, String> log4j2Context = get();
            for (Map.Entry<String, String> entry : log4j2Context.entrySet()) {
                ThreadContext.put(entry.getKey(), entry.getValue());
            }
        }

        @Override
        protected void afterExecute() {
            ThreadContext.clearAll();
        }

        @Override
        protected Map<String, String> initialValue() {
            return new HashMap<String, String>();
        }
    };

    public static void main(String[] args) throws Exception {
        // Init Log Context, set MTC
        // More KV if needed
        final String TRACE_ID = "trace-id";
        final String TRACE_ID_VALUE = "XXX-YYY-ZZZ";
        ThreadContext.put(TRACE_ID, TRACE_ID_VALUE);
        mtc.get().put(TRACE_ID, TRACE_ID_VALUE);

        // Log in Main Thread
        log.info("Log in main!");

        // Run task in com.ruoan.study.thread pool
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        final Runnable task = new Runnable() {
            @Override
            public void run() {
                // Log in com.ruoan.study.thread pool
                log.info("Log in Runnable!");
            }
        };
        final Future<?> submit = executorService.submit(TtlRunnable.get(task));
        submit.get();

        executorService.shutdown();
    }
}