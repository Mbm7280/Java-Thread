package com.echo.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Echo
 * @Description:    测试Thread026 中手写才Lock锁
 * @date 2022/3/20
 * @Version 1.0
 */
public class Thread027 extends Thread {

    private static int count = 0;
    private static Object lockObject = new Object();
    private static Lock lock = new ReentrantLock(true);
    private static Thread026 echoLock = new Thread026();

    @Override
    public void run() {
        while (count < 1000) {
            create3();
        }
    }

    private void create() {
        try {
            Thread.sleep(350);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "," + count++);
    }

    private void create2() {
        try {
            Thread.sleep(300);
        } catch (Exception e) {

        }
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "," + count++);
        lock.unlock();
    }

    private void create3() {
        try {
            Thread.sleep(300);
        } catch (Exception e) {

        }
        echoLock.lock();
        System.out.println(Thread.currentThread().getName() + "," + count++);
        echoLock.unLock();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new Thread027().start();
        }
    }



}
