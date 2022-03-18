package com.echo.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Echo
 * @Description:
 *          独占锁：在多线程中，只允许有一个线程获取到锁，其他线程都会等待。
 *          共享锁：多个线程可以同时持有锁，例如ReentrantLock读写锁。读读可以共享、
 *          写写互斥、读写互斥、写读互斥。
 * @date 2022/3/18
 * @Version 1.0
 */
public class Thread023 extends Thread{
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        lock.readLock().lock();
        System.out.println(">>>" + Thread.currentThread().getName() + ",正在读取锁开始");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        System.out.println(">>>" + Thread.currentThread().getName() + ",正在读取锁结束");
        lock.readLock().unlock();
    }

    public void write() {
        lock.writeLock().lock();
        System.out.println(">>>" + Thread.currentThread().getName() + ",正在写入锁开始");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        System.out.println(">>>" + Thread.currentThread().getName() + ",正在写入锁结束");
        lock.writeLock().unlock();
    }

    public static void main(String[] args) {
        Thread023 myTask = new Thread023();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> myTask.read()).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> myTask.write()).start();
        }
//        lock.writeLock()
    }

}
