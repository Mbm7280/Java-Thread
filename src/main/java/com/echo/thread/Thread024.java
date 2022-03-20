package com.echo.thread;

/**
 * @author Echo
 * @Description:    Wait和Notify使用 —— 线程堵塞与唤醒
 * @date 2022/3/20
 * @Version 1.0
 */
public class Thread024 {
    private static Object lock = new Object();

    public static void main(String[] args) {
        new Thread( () -> {
            synchronized (lock) {
                System.out.println("1");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2");
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            System.out.println("3");
            lock.notify();
        }
    }
}
