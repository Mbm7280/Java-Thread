package com.echo.thread.cf;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/10 20:28
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: FutureDemo
 * 文件描述: [
 *      Future就是对于具体的Runnable或者Callable任务的执行结果进行取消、查询是否完成、获取结
 * 果(可以通过get方法获取执行结果，该方法会阻塞直到任务返回结果)
 *
 * ]
 * version：1.0
 *
 ********************************************************/
@Slf4j
public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("通过Runnable方式执行任务");
            }
        }).start();


        FutureTask task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                log.debug("通过Callable方式执行任务");
                Thread.sleep(3000);
                return "返回任务结果";
            }
        });

        new Thread(task).start();
        log.debug("结果：{}", task.get());

    }
}
