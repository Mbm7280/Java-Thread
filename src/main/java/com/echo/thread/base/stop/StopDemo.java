package com.echo.thread.base.stop;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/10/15 14:35
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: StopDemo
 * 文件描述: [ stop方法
 *      stop()方法已经被jdk废弃，调用stop方法无论run()中的逻辑是否执行完，都会释放CPU资源，释放锁
 * 资源。这会导致线程不安全
 * ]
 * version：1.0
 *
 ********************************************************/
public class StopDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("线程进入执行");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("业务执行中");
            System.out.println("线程执行完毕");
        }, "t1");
        t1.start();
        System.out.println("线程t1的状态：" + t1.getState());

        t1.stop();
        System.out.println("线程t1的状态：" + t1.getState());
    }

}
