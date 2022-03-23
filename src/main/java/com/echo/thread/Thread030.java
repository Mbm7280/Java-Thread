package com.echo.thread;

import java.util.concurrent.Semaphore;

/**
 * @author Echo
 * @Description:    Semaphore 的基本使用
 * @date 2022/3/23
 * @Version 1.0
 */
public class Thread030 {
    public static void main(String[] args) {
        // 设置aqs状态为5  限流 最多可以容纳5个线程
        Semaphore semaphore = new Semaphore(5);
        for (int i = 1; i <= 6; i++) {
            new Thread( () -> {
                try {
                    // aqs状态会减去1 如果状态=0的情况下
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "进入");
                    // aqs 状态+1 同时唤醒aqs正在阻塞的线程
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
