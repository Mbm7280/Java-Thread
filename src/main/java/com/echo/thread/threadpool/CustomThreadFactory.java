package com.echo.thread.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/10/15 16:44
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: CustomThreadFactory
 * 文件描述: [ 实现线程工厂 ]
 * version：1.0
 *
 ********************************************************/
public class CustomThreadFactory implements ThreadFactory {

    private final AtomicInteger i = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        // 创建线程
        Thread thread = new Thread(r);
        // 设置线程名称
        thread.setName("线程" + i.getAndIncrement());
        return thread;
    }
}
