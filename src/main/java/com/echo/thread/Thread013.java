package com.echo.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Echo
 * @Description: 读写锁 —— 读读共享、写写互斥、读写互斥。
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread013 {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    // 如果多个线程同时写的情况下 读读共享
    public void read() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ",正在开始读取");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + ",正在结束读取");
        } catch (Exception e) {
            e.printStackTrace();
        }
        lock.readLock().unlock();
    }

    //  如果多个线程同时写的情况下 写写互斥
    public void write() {
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + ",开始写入数据");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ",结束写入数据");
        lock.writeLock().unlock();
    }


    public static void main(String[] args) {
        Thread013 thread = new Thread013();

        // 读
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                thread.read();
            }).start();
        }

        // 写
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                thread.write();
            }).start();
        }

    }
}
