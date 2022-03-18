package com.echo.thread;

/**
 * @author Echo
 * @Description: 线程安全问题 —— 跳号问题 —— 加锁解决 —— synchronized 修饰实例方法/对象锁
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread010 implements Runnable {

    private int count = 100;


    @Override
    public void run() {
        while(count > 0) {
            ticket();
        }
    }

    private void ticket() { // 实例/对象形式 方法上加上synchronized关键字默认使用this锁
        synchronized (this) {
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

//    private Object object = new Object();
//    private void ticket() { // 代码块形式 方法上加上synchronized关键字默认使用this锁
//        synchronized (object) {
//            if (count > 0) {
//                try {
//                    Thread.sleep(30);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + ",正在开始出售:" + (100 - count + 1));
//                count--;
//            }
//        }
//    }

    public static void main(String[] args) {
        Thread010 ticketThread = new Thread010();
        Thread thread1 = new Thread(ticketThread, "窗口1");
        Thread thread2 = new Thread(ticketThread, "窗口2");
        thread1.start();
        thread2.start();
    }

}
