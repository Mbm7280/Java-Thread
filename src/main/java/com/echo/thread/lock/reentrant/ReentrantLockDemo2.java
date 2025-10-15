package com.echo.thread.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 15:39
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: ReentrantLockDemo2
 * 文件描述: [ ReentrantLock
 *      可重入锁又名递归锁，是指在同一个线程在外层方法获取锁的时候，再进入该线程的内层方法会自动获取锁
 * （前提锁对象得是同一个对象），不会因为之前已经获取过还没释放而阻塞。Java中ReentrantLock和
 * synchronized都是可重入锁，可重入锁的一个优点是可一定程度避免死锁。在实际开发中，可重入锁常常应
 * 用于递归操作、调用同一个类中的其他方法、锁嵌套等场景中。
 * ]
 * version：1.0
 *
 ********************************************************/
public class ReentrantLockDemo2 {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter(); // 创建计数器对象
        // 测试递归调用
        counter.recursiveCall(10);
    }

}

class Counter {
    private final ReentrantLock lock = new ReentrantLock(); // 创建 ReentrantLock 对象
    private final int count = 0; // 计数器

    public void recursiveCall(int num) {
//        lock.lock(); // 获取锁
        try {
            if (num == 0) {
                return;
            }
            System.out.println("执行递归，num = " + num);
            recursiveCall(num - 1);
        } finally {
//            lock.unlock(); // 释放锁
        }
    }
}