package com.echo.thread.cf;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/10 21:30
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: CFDemo5
 * 文件描述: [
 *      thenCombine
 *          将多个任务合并执行，有返回值
 *      thenAccept
 *          对单个结果进行消费
 *      thenAccepetBoth:
 *          两个任务执行完成后，将结果交给thenAccepetBoth消耗，无返回值。
 *      thenRun系列：
 *          不关心结果，只对结果执行Action
 * ]
 * version：1.0
 *
 ********************************************************/
public class CFDemo5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        CompletableFuture<Void> future = CompletableFuture
//                .supplyAsync(() -> {
//                    int number = new Random().nextInt(10);
//                    System.out.println("第一阶段：" + number);
//                    return number;
//                }).thenAccept(number ->
//                        System.out.println("第二阶段：" + number * 5));
//
//        System.out.println("最终结果：" + future.get());

//        CompletableFuture<Integer> futrue1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                int number = new Random().nextInt(3) + 1;
//                try {
//                    TimeUnit.SECONDS.sleep(number);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("第一阶段：" + number);
//                return number;
//            }
//        });
//
//        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                int number = new Random().nextInt(3) + 1;
//                try {
//                    TimeUnit.SECONDS.sleep(number);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("第二阶段：" + number);
//                return number;
//            }
//        });
//
//        futrue1.thenAcceptBoth(future2, new BiConsumer<Integer, Integer>() {
//            @Override
//            public void accept(Integer x, Integer y) {
//                System.out.println("最终结果：" + (x + y));
//            }
//        }).join();

//        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
//            int number = new Random().nextInt(10);
//            System.out.println("第一阶段：" + number);
//            return number;
//        }).thenRun(() ->
//                System.out.println("thenRun 执行"));
//        System.out.println("最终结果：" + future.get());
        CompletableFuture<Integer> future1 = CompletableFuture
                .supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        int number = new Random().nextInt(10);
                        System.out.println("第一阶段：" + number);
                        return number;
                    }
                });
        CompletableFuture<Integer> future2 = CompletableFuture
                .supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        int number = new Random().nextInt(10);
                        System.out.println("第二阶段：" + number);
                        return number;
                    }
                });
        CompletableFuture<Integer> result = future1
                .thenCombine(future2, new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer x, Integer y) {
                        return x + y;
                    }
                });
        System.out.println("最终结果：" + result.get());
    }
}
