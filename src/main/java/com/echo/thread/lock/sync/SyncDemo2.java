package com.echo.thread.lock.sync;

import lombok.extern.slf4j.Slf4j;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 14:13
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: SyncDemo2
 * 文件描述: [ synchronized
 *      synchronized是JVM内置锁，基于Monitor机制实现，依赖底层操作系统的互斥原语Mutex（互斥
 * 量），它是一个重量级锁，性能较低。
 * ]
 * version：1.0
 *
 ********************************************************/
@Slf4j
public class SyncDemo2 {

    private static int counter = 0;

    private static String lock = "";

    public static void increment() {
        synchronized (lock) {
            counter++;
        }
    }

    public static void decrement() {
        synchronized (lock) {
            counter--;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                increment();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                decrement();
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.info("counter={}", counter);
    }
}
