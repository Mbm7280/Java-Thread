package com.echo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Echo
 * @Description: 实现Callable接口 创建线程
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread003 implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("我是子线程" + Thread.currentThread().getName());
        return "Echo";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<String>(new Thread003());
        Thread thread = new Thread(futureTask);
        thread.start();
        String result = futureTask.get();
        System.out.println(Thread.currentThread().getName() +"---result---" + result);

    }

}
