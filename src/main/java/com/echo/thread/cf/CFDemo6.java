package com.echo.thread.cf;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/10 21:33
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: CFDemo6
 * 文件描述: [
 *      applyToEither
 *          两个线程任务相比较，先获得执行结果的，就对该结果进行下一步的转化操作。
 *      acceptEither
 *          两个线程任务相比较，先获得执行结果的，就对该结果进行下一步的消费操作。
 *      runAfterEither
 *          两个线程任务相比较，有任何一个执行完成，就进行下一步操作，不关心运行结果
 *      runAfterBoth
 *          两个线程任务相比较，两个全部执行完成，才进行下一步操作，不关心运行结果。
 * ]
 * version：1.0
 *
 ********************************************************/
public class CFDemo6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture
                .supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        int number = new Random().nextInt(10);
                        System.out.println("第一阶段start：" + number);
                        try {
                            TimeUnit.SECONDS.sleep(number);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("第一阶段end：" + number);
                        return number;
                    }
                });
        CompletableFuture<Integer> future2 = CompletableFuture
                .supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        int number = new Random().nextInt(10);
                        System.out.println("第二阶段start：" + number);
                        try {
                            TimeUnit.SECONDS.sleep(number);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("第二阶段end：" + number);
                        return number;
                    }
                });

//        future1.applyToEither(future2, new Function<Integer, Integer>() {
//            @Override
//            public Integer apply(Integer number) {
//                System.out.println("最快结果：" + number);
//                return number * 2;
//            }
//        }).join();
//        future1.runAfterEither(future2, new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("已经有一个任务完成了");
//            }
//        }).join();

//        future1.acceptEither(future2, new Consumer<Integer>() {
//            @Override
//            public void accept(Integer number) {
//                System.out.println("最快结果：" + number);
//            }
//        }).join();

//        future1.runAfterBoth(future2, new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("上面两个任务都执行完成了。");
//            }
//        }).get();

        CompletableFuture<Void> combindFuture = CompletableFuture
                .allOf(future1, future2);
        try {
            combindFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("future1: " + future1.isDone() + "，future2: " + future2.isDone());


    }
}
