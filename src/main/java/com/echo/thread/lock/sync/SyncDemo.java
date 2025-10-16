package com.echo.thread.lock.sync;


import lombok.extern.slf4j.Slf4j;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 14:10
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: SyncDemo
 * 文件描述: [
 * 一段代码块内如果存在对共享资源的多线程读写操作，称这段代码块为临界区，其共享资源为临界资
 * 源。
 * 多个线程在临界区内执行，由于代码的执行序列不同而导致结果无法预测，称之为发生了竞态条件
 * 为了避免临界区的竞态条件发生，有多种手段可以达到目的：
 *      阻塞式的解决方案：synchronized，Lock
 *      非阻塞式的解决方案：原子变量
 * ]
 * version：1.0
 *
 ********************************************************/
@Slf4j
public class SyncDemo {

    private static int counter = 0;
    //private static AtomicInteger counter = new AtomicInteger(0);
    private Object lock = "";

    public void increment() {
        synchronized (lock) {
            counter++;
        }

        //counter.getAndIncrement();
    }

    public void decrement() {
        synchronized (this) {
            counter--;
        }

        //counter.getAndDecrement();
    }

    public static void main(String[] args) throws InterruptedException {
        SyncDemo syncDemo = new SyncDemo();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                syncDemo.increment();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                syncDemo.decrement();
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        //思考： counter=？
        log.info("counter={}", counter);
    }
}
