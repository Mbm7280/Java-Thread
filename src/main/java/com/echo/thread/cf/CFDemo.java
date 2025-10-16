package com.echo.thread.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/10 20:28
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: CFDemo
 * 文件描述: [ CompletableFuture
 *      Future 适用于简单的任务
 *      CompletableFuture 类似于 Future，但是它提供了更多的功能，如链式调用，任务编排、异常处理等
 *      创建方式：
 *          public static CompletableFuture<Void> runAsync(Runnable runnable)
 *          public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
 *          public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
 *          public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
 *              runAsync 方法以Runnable函数式接口类型为参数，没有返回结果，supplyAsync 方法Supplier函数式接口类型为
 * 参数，返回结果类型为U；Supplier 接口的 get() 方法是有返回值的（会阻塞）
 *              没有指定Executor的方法会使用ForkJoinPool.commonPool() 作为它的线程池执行异步代码。如果指定线程池，
 * 则使用指定的线程池运行。
 *               CompletableFuture 会使用公共的 ForkJoinPool 线程池，这个线程池默认创建的线程数是 CPU 的核数
 *               如果所有 CompletableFuture 共享一个线程池，那么一旦有任务执行一些很慢的 I/O 操作，就会
 * 导致线程池中所有线程都阻塞在 I/O 操作上，从而造成线程饥饿，进而影响整个系统的性能。所以要根据不同的业务类型创建不同的线程池，
 * 以避免互相干扰
 *
 *      描述依赖关系：
 *          1. thenApply() 把前面异步任务的结果，交给后面的Function
 *          2. thenCompose()用来连接两个有依赖关系的任务，结果由第二个任务返回
 *      描述and聚合关系：
 *          1. thenCombine:任务合并，有返回值
 *          2. thenAccept系列：对单个结果进行消费
 *          3. thenAccepetBoth:两个任务执行完成后，将结果交给thenAccepetBoth消耗，无返回值。
 *          4. runAfterBoth:两个任务都执行完成后，执行下一步操作（Runnable）。
 *      描述or聚合关系：
 *          1. applyToEither:两个任务谁执行的快，就使用那一个结果，有返回值。
 *          2. acceptEither: 两个任务谁执行的快，就消耗那一个结果，无返回值。
 *          3. runAfterEither: 任意一个任务执行完成，进行下一步操作(Runnable)。
 *      并行执行：
 *          CompletableFuture类自己也提供了anyOf()和allOf()用于支持多个CompletableFuture并行执行
 * ]
 * version：1.0
 *
 ********************************************************/
public class CFDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> {
            System.out.println("执行无返回结果的异步任务");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        //提交一个异步任务
        CompletableFuture.runAsync(runnable);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行有返回值的异步任务");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello World";
        });
        String result = future.get();
        System.out.println(result);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
