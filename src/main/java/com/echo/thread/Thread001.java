package com.echo.thread;


/**
 * @author Echo
 * @Description: 继承Thread类 创建线程
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread001 extends Thread{

    @Override
    public void run(){
        System.out.println("我是子线程" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("我是主线程" + Thread.currentThread().getName());
        Thread001 thread001 = new Thread001();
        thread001.start();
    }

}
