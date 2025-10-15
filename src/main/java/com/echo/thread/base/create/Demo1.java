package com.echo.thread.base.create;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 23:06
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: Demo1
 * 文件描述: [ 创建一个线程
 *      使用 Thread 类
 * ]
 * version：1.0
 *
 ********************************************************/
public class Demo1 {
    public static void main(String[] args) {
        // 创建一个线程（使用Lambda方式）
        Thread t = new Thread(() -> System.out.println("线程启动了"));
        // 启动线程
        t.start();
    }

}
