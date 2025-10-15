package com.echo.thread.base.join;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/10/15 15:02
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: JoinDemo
 * 文件描述: [ Join
 *  等待调用join方法的线程结束之后，程序再继续执行，
 *  一般用于等待异步线程执行完结果之后才能继续运行的场景
 * ]
 * version：1.0
 *
 ********************************************************/
public class JoinDemo {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println("t1线程开始执行");
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t2线程开始执行");
        });
        t1.start();
        t2.start();
    }


}
