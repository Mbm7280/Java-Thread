package com.echo.thread.lock.reentrant;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 17:20
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: ReentrantLockDemo5
 * 文件描述: [ ReentrantLock
 *      通过传参 fair 为 true 设置为公平锁
 * ]
 * version：1.0
 *
 ********************************************************/
@Slf4j
public class ReentrantLockDemo5 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(); //非公平锁
//        ReentrantLock lock = new ReentrantLock(true);//公平锁

        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug(Thread.currentThread().getName() + " running...");
                } finally {
                    lock.unlock();
                }
            }, "t" + i).start();
        }
        // 1s 之后去争抢锁
        Thread.sleep(500);
        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    log.debug(Thread.currentThread().getName() + " running...");
                } finally {
                    lock.unlock();
                }
            }, "强行插入" + i).start();
        }
    }
}