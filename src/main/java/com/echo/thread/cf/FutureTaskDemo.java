package com.echo.thread.cf;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/10 20:46
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: FutureTaskDemo
 * 文件描述: [ FutureTaskDemo
 *    创建一个Callable接口的实现类，并实现call()方法
 * ]
 * version：1.0
 *
 ********************************************************/
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Task task = new Task();
        //构建futureTask
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        //作为Runnable入参
        new Thread(futureTask).start();
        System.out.println("task运行结果：" + futureTask.get());
    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程正在计算");
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
