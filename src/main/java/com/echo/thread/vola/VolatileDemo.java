package com.echo.thread.vola;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/10/15 15:41
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: VolatileDemo
 * 文件描述: [ volatile
 *  volatile 保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值
 * 对其他线程来说是立即可见的
 *  但是volatile不能保证数据在多个线程下同时写时的线程安全，volatile最适用的场景：
 *  一个线程写，多个线程读。
 * ]
 * version：1.0
 *
 ********************************************************/
public class VolatileDemo {

    private static volatile boolean stop = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("t1线程开始执行");
            while (!stop) {
                // 线程t1会一直执行
                System.out.println("stop = " + stop);
            }
        });
        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        stop = true;
        System.out.println("主线程修改stop=true");
    }

}
