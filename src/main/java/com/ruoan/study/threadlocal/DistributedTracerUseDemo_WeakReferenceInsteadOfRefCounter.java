package com.ruoan.study.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.google.common.cache.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * DistributedTracer(DT) use demo.
 *
 * @author Jerry Lee (oldratlee at gmail dot com)
 */
public class DistributedTracerUseDemo_WeakReferenceInsteadOfRefCounter {
    static ThreadFactory threadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, "Executors");
            thread.setDaemon(true);
            return thread;
        }
    };


    static ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(1, threadFactory));

    static {
        // 挤满线程, 保证线程不是用的时候new的, 确保验证MTC的传递功能
        // 挤满线程
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> System.out.println("i am working - " + Thread.currentThread().getName()));
        }
    }

    static class DtTransferInfo {
        public String traceId;
        public String baseSpanId;
        public LeafSpanIdInfo leafSpanIdInfo;

        public DtTransferInfo(String traceId, String baseSpanId, LeafSpanIdInfo leafSpanIdInfo) {
            this.traceId = traceId;
            this.baseSpanId = baseSpanId;
            this.leafSpanIdInfo = leafSpanIdInfo;
        }

        @Override
        public String toString() {
            return "DtTransferInfo{" +
                    "traceId='" + traceId + '\'' +
                    ", baseSpanId='" + baseSpanId + '\'' +
                    ", leafSpanIdInfo=" + leafSpanIdInfo +
                    '}';
        }
    }

    private static TransmittableThreadLocal<DtTransferInfo> transferInfo = new TransmittableThreadLocal<>();

    static class LeafSpanIdInfo {
        AtomicInteger current = new AtomicInteger(1);

        @Override
        public String toString() {
            return "LeafSpanIdInfo{current=" + current + '}';
        }
    }


    private static Cache<String, LeafSpanIdInfo> traceId2LeafSpanIdInfo = CacheBuilder.newBuilder().weakValues()
            .removalListener(new RemovalListener<String, LeafSpanIdInfo>() {
                @Override
                public void onRemoval(RemovalNotification<String, LeafSpanIdInfo> notification) {
                    if (notification.getCause() != RemovalCause.COLLECTED) {
                        System.err.println("ERROR: Bug!! Should COLLECTED");
                    }
                    System.out.printf("DEBUG: Remove traceId %s in com.ruoan.study.thread %s by cause %s: %s\n",
                            notification.getKey(), Thread.currentThread().getName(), notification.getCause(), notification.getValue());
                }
            }).build();

    static int increaseLeafSpanCurrentAndReturn() {
        DtTransferInfo dtTransferInfo = transferInfo.get();
        String traceId = dtTransferInfo.traceId;
        LeafSpanIdInfo leafSpanIdInfo = traceId2LeafSpanIdInfo.getIfPresent(traceId);
        if (leafSpanIdInfo == null) {
            throw new IllegalStateException("LeafSpanIdInfo is NOT present, Bug!!");
        }
        return leafSpanIdInfo.current.getAndIncrement();
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            rpcInvokeIn();
        }

        Thread.sleep(1000);
        System.gc();
        Thread.sleep(1000);
    }


    static AtomicLong traceIdCounter = new AtomicLong();

    static void rpcInvokeIn() {
        ////////////////////////////////////////////////
        // DistributedTracer Framework Code
        ////////////////////////////////////////////////

        // Get Trace Id and Span Id from RPC Context
        String traceId = "traceId_XXXYYY" + traceIdCounter.getAndIncrement();
        String baseSpanId = "1.1";

        LeafSpanIdInfo leafSpanIdInfo = new LeafSpanIdInfo();
        traceId2LeafSpanIdInfo.put(traceId, leafSpanIdInfo);
        transferInfo.set(new DtTransferInfo(traceId, baseSpanId, leafSpanIdInfo));


        ////////////////////////////////////////////////
        // Biz Code
        ////////////////////////////////////////////////
        syncMethod();


        ////////////////////////////////////////////////
        // DistributedTracer Framework Code
        ////////////////////////////////////////////////
        System.out.printf("Finished Rpc call %s with span %s.\n", traceId, leafSpanIdInfo);
    }

    static void syncMethod() {
        // com.ruoan.study.async call by MTC Executor, Test OK!
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //调用server2 模拟线程池，验证TTL的线程池wrap情况下，可以获取到父线程set的值
                asyncMethod();
            }
        });

        // com.ruoan.study.async call by new Thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用server 3 模拟new Thread的方式，首先TTL是个ITL，所以ITL有的功能它也有
                syncMethod_ByNewThread();
            }
        }, "Thread-by-new").start();

        //直接调用 首先TTL是个TL，所以TL有的功能它也有
        invokeServerWithRpc("server 1");
    }

    static void asyncMethod() {
        invokeServerWithRpc("server 2");
    }

    static void syncMethod_ByNewThread() {
        invokeServerWithRpc("server 3");
    }


    // RPC invoke
    static void invokeServerWithRpc(String server) {
        ////////////////////////////////////////////////
        // DistributedTracer Framework Code
        ////////////////////////////////////////////////

        int leafSpanCurrent = increaseLeafSpanCurrentAndReturn();

        // Set RpcContext
        // Mocked, should use RPC com.ruoan.study.util to get Rpc Context instead
        Map<String, String> rpcContext = new ConcurrentHashMap<String, String>();

        rpcContext.put("traceId", transferInfo.get().traceId);
        rpcContext.put("spanId", transferInfo.get().baseSpanId + "." + leafSpanCurrent);

        // Do Rpc
        // ...
        System.out.printf("Do Rpc invocation to server %s with %s\n", server, rpcContext);
    }
}