package com.echo.thread;

import java.util.concurrent.*;

/**
 * @author Echo
 * @Description:    自定义 newFixedThreadPool (定长线程池)
 * @date 2022/3/24
 * @Version 1.0
 */
public class Thread034 {

    public static void main(String[] args) {
        ExecutorService executorService = Thread034.newFixedThreadPool(10, 15);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    Thread.sleep(50);
                    System.out.println(Thread.currentThread().getName() + "," + finalI);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static ExecutorService newFixedThreadPool(int corePoolSize, int maximumPoolSize) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("线程池队列满了，请求被拒绝！" + r.toString());
            }
        });
    }
}
