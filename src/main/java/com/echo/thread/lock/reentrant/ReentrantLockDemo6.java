package com.echo.thread.lock.reentrant;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁超时例子
 */

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 17:21
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: ReentrantLockDemo6
 * 文件描述: [ tryLock
 *      尝试获取锁 —— 不会无限等待，拿不到就立即返回或等待指定时间。
 * ]
 * version：1.0
 *
 ********************************************************/
@Slf4j
public class ReentrantLockDemo6 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);
        Thread t1 = new Thread(() -> {
            log.debug("t1启动...");
            // 注意： 即使是设置的公平锁，此方法也会立即返回获取锁成功或失败，公平策略不生效
            if (!lock.tryLock()) {
                log.debug("t1获取锁失败，立即返回false");
                return;
            }
            //超时
            try {
                if (!lock.tryLock(3, TimeUnit.SECONDS)) {
                    log.debug("等待 3s 后获取锁失败，返回");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            try {
                log.debug("t1获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");
        lock.lock();
        try {
            log.debug("main线程获得了锁");
            t1.start();
            //先让线程t1执行
            Thread.sleep(3000);
        } finally {
            lock.unlock();
        }

    }

}
