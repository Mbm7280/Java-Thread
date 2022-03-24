package com.echo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Echo
 * @Description:        线程池的使用
 * @date 2022/3/24
 * @Version 1.0
 */
public class Thread032 {
    public static void main(String[] args) {
        // 创建两个线程
        ExecutorService executorService = Executors.newFixedThreadPool(2);

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);


        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.execute(() -> System.out.println(Thread.currentThread().getName() + "," + finalI));
        }
    }
}
