package com.echo.thread.cf;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/10 21:15
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: CFDemo2
 * 文件描述: [
 *      join
 *      get
 *          都是用来获取CompletableFuture异步之后的返回值
 *          join()方法抛出的是uncheck异常（即未经检查的异常),不会强制开发者抛出
 *          get()方法抛出的是经过检查的异常，ExecutionException,InterruptedException 需要用户手动处理（抛出或者 try catch）
 *      whenComplete(BiConsumer<? super T,? super Throwable> action):
 *          在CompletableFuture执行完毕后调用，无论成功还是失败都会执行。
 *          参数action是一个BiConsumer，接收两个参数：一个是正常执行结果T，另一个是可能抛出的异常Throwable。
 *          该方法不会阻塞主线程，属于同步非阻塞回调。
 *
 *      whenCompleteAsync(BiConsumer<? super T,? super Throwable> action):
 *          功能与whenComplete相同，但回调操作会异步执行，默认使用ForkJoinPool.commonPool()线程池。
 *          如果任务已完成，则使用当前线程执行回调。
 *
 *      whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor):
 *          功能与whenCompleteAsync相同，但可以指定自定义的Executor来执行回调操作。
 *
 *      exceptionally(Function<Throwable,? extends T> fn):
 *          用于处理CompletableFuture执行过程中发生的异常。
 *          只有当CompletableFuture执行失败时才会调用此方法，参数fn是一个Function函数接口，
 *          接收Throwable类型的异常作为输入，并返回一个类型为T的结果。
 * ]
 * version：1.0
 *
 ********************************************************/
public class CFDemo2 {

    public static void main(String[] args) {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            if (new Random().nextInt(10) % 2 == 0) {
                int i = 12 / 0;
            }
            System.out.println("执行结束！");
            return "test";
        });
        future.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String t, Throwable action) {
                System.out.println(t+" 执行完成！");
            }
        });
        future.exceptionally(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable t) {
                System.out.println("执行失败：" + t.getMessage());
                return "异常xxxx";
            }
        }).join();
    }
}
