package com.echo.thread.threadpool;

import java.util.concurrent.*;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/10/15 16:23
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: ThreadPoolDemo
 * 文件描述: [ 传统方式与线程池对比
 *      创建一个只有一个线程的线程池，提交任务到该线程池
 *      按照提交顺序执行，执行完毕后返回结果 ”执行完成“
 * ]
 * version：1.0
 *
 ********************************************************/
public class ThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建任务
        Task2 task1 = new Task2();
        Task2 task2 = new Task2();
        Task2 task3 = new Task2();
        // 创建线程 传统方式
//        Thread thread1 = new Thread(task1);
//        Thread thread2 = new Thread(task2);
//        Thread thread3 = new Thread(task3);
//       //启动线程
//        thread1.start();
//        thread2.start();
//        thread3.start();

        // 使用线程池
        // 创建一个只有一个线程的线程池
        ExecutorService threadpool = Executors.newSingleThreadExecutor();
        // 按照提交顺序执行
        threadpool.submit(task1);
        threadpool.submit(task2);
        Future<String> future = threadpool.submit(task3, "执行完成");
        System.out.println(future.get());
    }
}

class Task implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName());
    }
}
