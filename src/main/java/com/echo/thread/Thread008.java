package com.echo.thread;

/**
 * @author Echo
 * @Description: 线程安全问题 —— 跳号问题 —— 加锁解决 —— synchronized 修饰实例方法/对象锁
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread008 implements Runnable{
    private static int count = 100;

    @Override
    public void run() {
        while (count > 0) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticket();
        }
    }

    // 修饰实例方法 相当于是对类的实例进行加锁，进入同步代码前需要获得当前实例的锁
    private synchronized void ticket() {
        // 多卖 101
//        System.out.println(Thread.currentThread().getName() + ",正在开始出售:" + (100 - count + 1));
//        count--;
        if (count > 0) {
            System.out.println(Thread.currentThread().getName() + ",正在开始出售:" + (100 - count + 1));
            count--;
        }
    }

    public static void main(String[] args) {
        Thread008 ticketThread = new Thread008();
        Thread thread1 = new Thread(ticketThread, "窗口1");
        Thread thread2 = new Thread(ticketThread, "窗口2");
        thread1.start();
        thread2.start();
    }
}
