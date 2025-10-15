package com.echo.thread.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/10/15 16:54
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: RejectedPolicyDemo
 * 文件描述: [ 线程池拒绝策略 ]
 * version：1.0
 *
 ********************************************************/
public class RejectedPolicyDemo {
    public static void main(String[] args) {
        //创建线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1,
                1,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            //提交任务
            threadPool.submit(new MyTask(1));
            threadPool.submit(new MyTask(2));
            threadPool.submit(new MyTask(3));
            threadPool.execute(() -> {
                System.out.println("线程池执行中!");
            });
        } catch (RejectedExecutionException e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            threadPool.shutdown();
        }
    }
}

class MyTask implements Runnable {
    private int index;

    MyTask(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":" + index);
    }
}

