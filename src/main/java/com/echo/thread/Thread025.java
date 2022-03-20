package com.echo.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Echo
 * @Description:    LockSupport_Park_Unpark —— 线程堵塞与唤醒
 * @date 2022/3/20
 * @Version 1.0
 */
public class Thread025 {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            System.out.println("1");
            LockSupport.park();
            System.out.println("2");
        });

        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("3");
        LockSupport.unpark(thread);
    }
}
