package com.echo.thread;

/**
 * @author Echo
 * @Description: 线程安全问题 —— 跳号/多卖问题 —— 加锁解决 —— synchronized 修饰代码块
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread009 implements Runnable{

    private int count = 100;


    @Override
    public synchronized void run() {    // 修饰代码块 则只有一个线程可以进入运行且只有一个线程在运行, run方法运行结束/抛出异常的情况下自动释放资源
        while(count > 0) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ",正在出票第" + (100 - count + 1) + "张");
            count--;
        }
    }

    public static void main(String[] args) {
        Thread009 ticketThread = new Thread009();
        Thread thread1 = new Thread(ticketThread, "窗口1");
        Thread thread2 = new Thread(ticketThread, "窗口2");
        thread1.start();
        thread2.start();
    }

}
