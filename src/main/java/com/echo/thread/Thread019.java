package com.echo.thread;

/**
 * @author Echo
 * @Description:    volatile —— 能够保证线程可见性，当一个线程修改共享变量时，能够保证对另外一个线程可见性
 * @date 2022/3/18
 * @Version 1.0
 */
public class Thread019 extends Thread{
    private static volatile boolean FLAG = true;

    @Override
    public void run() {
        while (FLAG) {

        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread019().start();
        Thread.sleep(1000);
        FLAG = false;
        System.out.println("主线程已经停止啦~~...");
    }

}
