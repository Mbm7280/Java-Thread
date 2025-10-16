package com.echo.thread.cf;

import java.util.concurrent.*;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/10 20:47
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: FutureTaskDemo2
 * 文件描述: [ FutureTaskDemo2
 *      通过FutureTask 获取 商品基本信息、商品价格、商品库存、商品图片、商品销售状态 执行结果或者取消执行任务
 *      按照最长得时间结束
 *      Future代表异步执行，只提供了一个 get() 方法用来获取结果，且获取结果时，会阻塞当前线程，直到获取结果。
 *      如果在特定场景下，如果需要在任务(或多个任务)结束后执行特定的操作，就缺少这样的支撑能力
 *      且缺少异常处理
 * ]
 * version：1.0
 *
 ********************************************************/
public class FutureTaskDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> ft1 = new FutureTask<>(new T1Task());
        FutureTask<String> ft2 = new FutureTask<>(new T2Task());
        FutureTask<String> ft3 = new FutureTask<>(new T3Task());
        FutureTask<String> ft4 = new FutureTask<>(new T4Task());
        FutureTask<String> ft5 = new FutureTask<>(new T5Task());

        //构建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(ft1);
        executorService.submit(ft2);
        executorService.submit(ft3);
        executorService.submit(ft4);
        executorService.submit(ft5);
        //获取执行结果
        System.out.println(ft1.get());
        System.out.println(ft2.get());
        System.out.println(ft3.get());
        System.out.println(ft4.get());
        System.out.println(ft5.get());

        executorService.shutdown();
    }

    static class T1Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("T1:查询商品基本信息...");
            TimeUnit.MILLISECONDS.sleep(5000);
            return "商品基本信息查询成功";
        }
    }

    static class T2Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("T2:查询商品价格...");
            TimeUnit.MILLISECONDS.sleep(50);
            return "商品价格查询成功";
        }
    }

    static class T3Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("T3:查询商品库存...");
            TimeUnit.MILLISECONDS.sleep(50);
            return "商品库存查询成功";
        }
    }

    static class T4Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("T4:查询商品图片...");
            TimeUnit.MILLISECONDS.sleep(50);
            return "商品图片查询成功";
        }
    }

    static class T5Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("T5:查询商品销售状态...");
            TimeUnit.MILLISECONDS.sleep(50);
            return "商品销售状态查询成功";
        }
    }

}
