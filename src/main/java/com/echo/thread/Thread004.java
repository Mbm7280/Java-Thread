package com.echo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Echo
 * @Description: 线程池 创建线程
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread004 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        // 内部类形式
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        });

        // Lambda 形式
        executorService.execute(() -> System.out.println(Thread.currentThread().getName()));
    }
}
