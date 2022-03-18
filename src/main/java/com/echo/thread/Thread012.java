package com.echo.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Echo
 * @Description: 线程安全问题 —— 跳号/多卖问题 —— 加锁解决 —— Lock锁的基本用法
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread012 implements Runnable{

    private static int count = 100;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (count > 0){
            ticket();
        }
    }

    public static void main(String[] args) {
        Thread012 thread012 = new Thread012();
        new Thread(thread012, "窗口1").start();
        new Thread(thread012, "窗口2").start();
    }

    public void ticket() {
        try{
            Thread.sleep(30);
            lock.lock(); // 加锁
            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + ",正在开始出售:" + (100 - count + 1));
                count--;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock(); // 解锁
        }
    }

}
