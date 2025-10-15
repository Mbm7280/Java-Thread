package com.echo.thread.threadpool;

import java.util.concurrent.*;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/10/15 16:43
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: ThreadPoolExecutorDemo
 * 文件描述: [ ThreadPoolExecutor
 *      自定义线程工厂
 * ]
 * version：1.0
 *
 ********************************************************/
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        // 线程池的核心线程数
        int corePoolSize = 3;
        // 线程池的最大线程数
        int maximumPoolSize = 10;
        // 线程池的任务队列
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(3);
        // 线程池中工作线程（默认是非核心线程）保持空闲的时间
        long keepAliveTime = 60L;
        // 时间单位
        TimeUnit unit = TimeUnit.SECONDS;
        //线程工厂
        ThreadFactory threadFactory = new CustomThreadFactory();
        // 线程池的拒绝策略
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        // 创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler
        );

        // 提交任务到线程池
        for (int i = 0; i < 9; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    //模拟业务办理时间
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Task " + taskId + " is running by " + Thread.currentThread().getName());
            });
        }

        // 关闭线程池
        executor.shutdown();
        try {
            // 等待所有任务完成，超时时间为60秒
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                // 如果超时后任务仍未完成，则强制关闭线程池
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            // 如果等待过程中被中断，也强制关闭线程池
            executor.shutdownNow();
        }
        System.out.println("All tasks are done or interrupted.");
    }

}
