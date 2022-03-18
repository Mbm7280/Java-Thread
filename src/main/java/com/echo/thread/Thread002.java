package com.echo.thread;

/**
 * @author Echo
 * @Description: 实现Runnable接口 创建线程
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread002 implements Runnable{
    @Override
    public void run() {
        System.out.println("我是子线程" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("我是主线程" + Thread.currentThread().getName());
        new Thread (new Thread002()).start();
    }
}
