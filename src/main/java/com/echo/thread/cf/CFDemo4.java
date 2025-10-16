package com.echo.thread.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/10 21:26
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: CFDemo4
 * 文件描述: [ thenCompose()
 *      thenCompose 的参数为一个返回 CompletableFuture 实例的函数，该函数的参数是先前计算步骤的结
 *
 *      thenApply 转换的是泛型中的类型，返回的是同一个CompletableFuture；
 *      thenCompose 将内部的 CompletableFuture 调用展开来并使用上一个CompletableFutre 调用的结果在下一步的
 * CompletableFuture 调用中进行运算，是生成一个新的CompletableFuture。
 * 果
 * ]
 * version：1.0
 *
 ********************************************************/
public class CFDemo4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<Integer> future = CompletableFuture
//                .supplyAsync(new Supplier<Integer>() {
//                    @Override
//                    public Integer get() {
//                        int number = new Random().nextInt(30);
//                        System.out.println("第一阶段：" + number);
//                        return number;
//                    }
//                })
//                .thenCompose(new Function<Integer, CompletionStage<Integer>>() {
//                    @Override
//                    public CompletionStage<Integer> apply(Integer param) {
//                        return CompletableFuture.supplyAsync(new Supplier<Integer>() {
//                            @Override
//                            public Integer get() {
//                                int number = param * 2;
//                                System.out.println("第二阶段：" + number);
//                                return number;
//                            }
//                        });
//                    }
//                });
//        System.out.println("最终结果: " + future.get());
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> result1 = future.thenApply(param -> param + " World");
        CompletableFuture<String> result2 = future
                .thenCompose(param -> CompletableFuture.supplyAsync(() -> param + " World"));
        System.out.println(result1.get());
        System.out.println(result2.get());
    }
}
