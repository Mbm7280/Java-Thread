package com.echo.thread.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/10 21:25
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: CFDemo3
 * 文件描述: [ thenApply()
 *      thenApply 接收一个函数作为参数，使用该函数处理上一个CompletableFuture 调用的结果，并返回一
 * 个具有处理结果的Future对象
 * ]
 * version：1.0
 *
 ********************************************************/
public class CFDemo3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("执行有返回值的异步任务");
            return "Hello World";
        });

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = 100;
            System.out.println(Thread.currentThread().getName()+"一阶段：" + result);
            return result;
        }).thenApplyAsync(number -> {
            int result = number * 3;
            System.out.println(Thread.currentThread().getName()+"二阶段：" + result);
            return result;
        });
        System.out.println("最终结果：" + future.get());
    }
}
