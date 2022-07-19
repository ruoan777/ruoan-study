package com.ruoan.study.excutor;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class CompletionServiceTest {
    public void solve(Collection<Callable<Result>> solvers) throws InterruptedException, ExecutionException {
        int numThread = solvers.size();
        ExecutorService executor = Executors.newFixedThreadPool(numThread);
        List<Future<Result>> futureList = new ArrayList<>();
        for (Callable<Result> solver : solvers) {
            Future<Result> future = executor.submit(solver);
            futureList.add(future);
        }
        while (numThread > 0) {
            for (Future<Result> future : futureList) {
                Result result = null;
                try {
                    result = future.get(0, TimeUnit.SECONDS);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
                if (null != result) {
                    futureList.remove(future);
                    numThread--;
                    System.out.println(result);
                    //此处必须break，否则会抛出并发修改异常。（也可以通过将futureList声明为CopyOnWriteArrayList类型解决）
                    break;
                }
            }
        }
    }

    public void executorOutputExceptionDemo() {
        //定义线程池
        ExecutorService service = new ThreadPoolExecutor(
                1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(10)) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                //这个是excute提交的时候
                if (t != null) {
                    System.out.println("afterExecute里面获取到异常信息" + t.getMessage());
                }
                //如果r的实际类型是FutureTask 那么是submit提交的，所以可以在里面get到异常
                if (r instanceof FutureTask) {
                    try {
                        Future<?> future = (Future<?>) r;
                        future.get();
                    } catch (Exception e) {
                        log.error("future里面取执行异常", e);
                    }
                }
            }
        };
        service.submit(() -> {
            int i = 1 / 0;
        });
    }
}

class Result {
}