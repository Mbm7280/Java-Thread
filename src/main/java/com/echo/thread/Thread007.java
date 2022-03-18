package com.echo.thread;

/**
 * @author Echo
 * @Description: 线程安全问题 —— 跳号问题
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread007 implements Runnable{

    private  int count = 100;

    @Override
    public void run() {
        while (count > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticket();
        }
    }

    private void ticket() {
        if (count >= 0) {
            System.out.println(Thread.currentThread().getName() + ",正在开始出售:" + (100 - count));
            count--;
        }

    }

    public static void main(String[] args) {
        Thread007 ticketThread = new Thread007();
        Thread thread1 = new Thread(ticketThread, "窗口1");
        Thread thread2 = new Thread(ticketThread, "窗口2");
        thread1.start();
        thread2.start();
    }



}
