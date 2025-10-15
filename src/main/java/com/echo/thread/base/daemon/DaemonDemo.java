package com.echo.thread.base.daemon;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/10/15 15:14
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: DaemonDemo
 * 文件描述: [ Daemon
 *      守护线程-只要其它非守护线程运行结束了，即使守护线程的代码没有执行完，也会强制结束
 * ]
 * version：1.0
 *
 ********************************************************/
public class DaemonDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("t1线程开始执行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t1线程执行结束");
        }, "t1");
        // 设置t1线程为守护线程
        t1.setDaemon(true);
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main线程执行结束");
    }

}
