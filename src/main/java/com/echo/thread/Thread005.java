package com.echo.thread;

/**
 * @author Echo
 * @Description:    停止线程 - 此方法已废弃
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread005 extends Thread{

    @Override
    public void run(){
        System.out.println("我是子线程" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("我是主线程" + Thread.currentThread().getName());
        Thread005 thread005 = new Thread005();
        thread005.start();
        thread005.stop();
    }


}
