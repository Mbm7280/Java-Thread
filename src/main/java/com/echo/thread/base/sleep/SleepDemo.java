package com.echo.thread.base.sleep;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/10/15 14:21
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: SleepDemo
 * 文件描述: [
 *      sleep(long n)
 *          让当前执行的线程休眠n毫秒，休眠时让出 cpu 的时间片给其它线程
 * ]
 * version：1.0
 *
 ********************************************************/
public class SleepDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("线程进入执行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("线程执行完毕");
        },"t1");
        t1.start();
        System.out.println("线程t1的状态：" + t1.getState());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("线程t1的状态：" + t1.getState());
        // 其它线程可以使用 interrupt 方法打断正在睡眠的线程，
        // 这时 sleep 方法会抛出InterruptedException
        // 使用场景(在执行任务时，优雅的提前结束/结束程序操作)，禁止使用stop操作
        t1.interrupt();
    }
}
