package com.echo.thread;

/**
 * @author Echo
 * @Description: 线程安全问题 —— 跳号/多卖问题 —— 加锁解决 —— synchronized 静态方法/类锁
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread011 implements Runnable {
    private static int count = 100;


    @Override
    public void run() {
        while(count > 0) {
            ticket();
        }
    }

//    private static synchronized void ticket() { // 静态方法上加上synchronized 则使用当前类锁
//        if (count > 0) {
//            try {
//                Thread.sleep(30);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + ",正在开始出售:" + (100 - count + 1));
//            count--;
//        }
//    }


    private static void ticket() { // 静态方法上加上synchronized 则使用当前类锁
        synchronized (Thread011.class) {
            if (count > 0) {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ",正在开始出售:" + (100 - count + 1));
                count--;
            }
        }
    }

    public static void main(String[] args) {
        Thread010 ticketThread = new Thread010();
        Thread thread1 = new Thread(ticketThread, "窗口1");
        Thread thread2 = new Thread(ticketThread, "窗口2");
        thread1.start();
        thread2.start();
    }
}
