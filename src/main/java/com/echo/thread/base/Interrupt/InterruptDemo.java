package com.echo.thread.base.Interrupt;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/10/15 15:08
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: InterruptDemo
 * 文件描述: [ interrupt
 *      通过中断机制来终止线程
 *      安全的中止则是其他线程通过调用某个线程A的interrupt()方法对其进行中断操作
 *      但是线程A不会立即停止自己的工作，同样的A线程完全可以不理会这种中断请求，
 *      线程通过检查自身的中断标志位是否被置为true来进行响应，线程通过方法isInterrupted获取中断标志位
 *      也可以调用静态方法Thread.interrupted()来进行判断当前线程是否被中断，
 *      不过Thread.interrupted()会同时将中断标识位改写为false
 * ]
 * version：1.0
 *
 ********************************************************/
public class InterruptDemo {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println("线程进入执行");
            Thread thread = Thread.currentThread();
            if (thread.isInterrupted()) {
                System.out.println(("中断状态:" + thread.isInterrupted()));
                System.out.println("线程被中断");
            }
            System.out.println("线程执行完毕");
        }, "t1");

        t1.start();
        t1.interrupt();
        System.out.println((" 中断状态:" + t1.isInterrupted()));
    }

}
