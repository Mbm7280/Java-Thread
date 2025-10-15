package com.echo.thread.base.wait;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/10/15 15:20
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: WaitDemo
 * 文件描述: [ wait
 *      notify()：通知一个在对象上等待的线程,使其从wait方法返回,而返回的前提是该线程获取到了对象的锁，没有获得
 * 锁的线程重新进入WAITING状态。
 *
 *      notifyAll()：通知所有等待在该对象上的线程。尽可能用notifyAll()，谨慎使用notify()，因为notify()只会唤醒一个线
 * 程，我们无法确保被唤醒的这个线程一定就是我们需要唤醒的线程。
 *
 *      wait(): 调用该方法的线程进入 WAITING状态,只有等待另外线程的通知或被中断才会返回.需要注意,调用wait()方
 * 法后,会释放对象的锁
 *      wait(long): 超时等待一段时间,这里的参数时间是毫秒,也就是等待长达n毫秒,如果没有通知就超时返回
 *      wait (long,int): 对于超时时间更细粒度的控制,可以达到纳秒
 * ]
 * version：1.0
 *
 ********************************************************/
public class WaitDemo {

    public static void main(String[] args) throws InterruptedException {
        Object locker = new Object();
        Thread t1 = new Thread(() -> {
            try {
                synchronized (locker) {
                    System.out.println("wait开始");
                    locker.wait();
                }
                System.out.println("wait结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        // 保证t1先启动，wait()先执行
        Thread.sleep(1000);
        Thread t2 = new Thread(() -> {
            synchronized (locker) {
                System.out.println("notify开始");
                locker.notifyAll();
                System.out.println("notify结束");
            }
        });
        t2.start();
    }

}
