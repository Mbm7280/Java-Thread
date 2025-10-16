package com.echo.thread.lock.sync;

import lombok.extern.slf4j.Slf4j;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 14:11
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: SyncDemo1
 * 文件描述: [
 *      使用 synchronized 加锁
 *      synchronized 同步块是 Java 提供的一种原子性内置锁，Java 中的每个对象都可以把它当作一个
 * 同步锁来使用，这些 Java 内置的使用者看不到的锁被称为内置锁，也叫作监视器锁。
 *
 * ]
 * version：1.0
 *
 ********************************************************/
@Slf4j
public class SyncDemo1 {

    private static int counter = 0;

    public static synchronized void increment() {
        counter++;
    }

    public static synchronized void decrement() {
        counter--;
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
