package com.echo.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/10/15 16:32
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: ThreadPoolDemo2
 * 文件描述: [  线程池（Thread Pool）是一种基于池化思想管理线程的工具
 * 线程池的核心参数
 * corePoolSize：核心线程数，线程池初始化时默认是没有线程的，当任务来临时才开始创建线程去执
 * 行任务
 * maximumPoolSize：最大线程数，在核心线程数已满，且队列已满时，如果池子里的工作线程数小于
 * maximumPoolSize，则会创建非核心线程执行任务
 * keepAliveTime：非核心线程数的空闲时间超过keepAliveTime就会被自动终止回收掉，但在
 * corePoolSize=maximumPoolSize时，该值无效，因为不存在非核心线程
 * unit：keepAliveTime的时间单位
 * workQueue：用于保存线程任务的队列，主要分为无界、有界、同步移交等队列，当池子里的工作线
 * 程数大于corePoolSize，就会将新进来的线程任务放入队列中
 *      ArrayBlockingQueue(有界队列)：队列长度有限，当队列满了就需要创建非核心线程执行任务，如果最大线程数
 * 已满，则执行拒绝策略　
 *      LinkedBlockingQueue(无界队列)：队列长度无限，当任务处理速度跟不上任务创建速度，可能会导致内存占用过
 * 多或OOM
 *      SynchronousQueue(同步队列)：队列不作为任务的缓冲处理，队列长度为0
 * threadFactory：
 * 创建线程的工厂接口，默认使用Executors.defaultThreadFactory()
 * 另外可以实现ThreadFactory接口，自定义线程工厂
 * handler：线程池无法继续接收任务时(workQueue已满和maximumPoolSize已满)的拒绝策略
 *      AbortPolicy：默认拒绝策略，中断抛出RejectedExecutionException异常
 *      CallerRunsPolicy：让提交任务的主线程来执行任务
 *      DiscardOldestPolicy：丢弃在队列中存在时间最久的任务，重复执行
 *      DiscardPolicy：丢弃任务，不进行任何通知
 *      另外可以实现RejectedExecutionHandler接口，自定义拒绝策略
 * ]
 * version：1.0
 *
 ********************************************************/
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        // 创建一个固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // 提交任务到线程池
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.submit(new Task2());
            executor.execute(() -> {
                System.out.println("Task " + taskId + " is running by " + Thread.currentThread().getName());
            });
        }
        // 关闭线程池
        // shutdownNow()：立即关闭线程池，正在执行中的任务和队列中的任务都会被中断，同时返回被中断的队列中的
        // shutdown()：关闭线程池，正在执行中的任务和队列中的任务都能执行完成，后续进来的新任务会被执行拒绝策
        // isTerminated()：当正在执行的任务和队列中的任务全部都执行完时返回true。
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("All tasks are done.");
    }
}

class Task2 implements Runnable {
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