package com.echo.thread.lock.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 17:31
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: SemaphoreByLockDemo
 * 文件描述: [ Semaphore
 *      Semaphore（信号量）是一种用于多线程编程的同步工具，主要用于在一个时刻允许多个线程对共享
 * 资源进行并行操作的场景
 *      public Semaphore(int permits)：定义Semaphore指定许可证数量（资源数），并且指定非公平的同步器，因此
 * new Semaphore(n)实际上是等价于new Semaphore(n，false)的。
 *      public Semaphore(int permits, boolean fair)：定义Semaphore指定许可证数量的同时给定非公平或是公平同步
 *
 *      acquire方法
 *          void acquire() throws InterruptedException：该方法会向Semaphore获取一个许可证，如果获取不到就会一
 * 直等待，直到Semaphore有可用的许可证为止，或者被其他线程中断。当然，如果有可用的许可证则会立即返回。
 *          void acquire(int permits) throws InterruptedException：该方法会向Semaphore获取指定数量的许可证，如
 * 果获取不到就会一直等待，直到Semaphore有可用的相应数量的许可证为止，或者被其他线程中断。同样，如果有可用的permits个许可证则会立即返回。
 *      tryAcquire方法
 *          tryAcquire方法尝试向Semaphore获取许可证，如果此时许可证的数量少于申请的数量，则对应的线程
 * 会立即返回，结果为false表示申请失败，
 * ]
 * version：1.0
 *
 ********************************************************/
public class SemaphoreByLockDemo {
    public static void main(String[] args) {
        final TryLock tryLock = new TryLock();
        // 启动一个线程，尝试获取tryLock，如果获取不成功则将进行其他的操作，该线程不用进入阻塞状态
        new Thread(() -> {
            boolean gotLock = tryLock.tryLock();
            if (!gotLock) {
                System.out.println(currentThread() + "can't get the lock, will do other thing.");
                return;
            }
            try {
                simulateWork();
            } finally {
                tryLock.unlock();
            }
        }).start();
        // main线程也会参与trylock的争抢，同样，如果抢不到trylock，则main线程不会进入阻塞状态
        boolean gotLock = tryLock.tryLock();
        if (!gotLock) {
            System.out.println(currentThread() + " can't get the lock, will do other thing.");

        } else {
            try {
                //TODO 模拟业务执行
                simulateWork();
            } finally {
                tryLock.unlock();
            }
        }
    }

    // 定义trylock类
    private static class TryLock {
        // 定义permit为1的semaphore
        private final Semaphore semaphore = new Semaphore(1);
        public boolean tryLock() {
            return semaphore.tryAcquire();
        }
        public void unlock() {
            semaphore.release();
            System.out.println(currentThread() + " release lock");
        }
    }

    private static void simulateWork() {
        try {
            System.out.println(currentThread() + " get the lock and do working...");
            TimeUnit.SECONDS.sleep(current().nextInt(10));
        } catch (InterruptedException e) {
            // ignore
        }
    }
}