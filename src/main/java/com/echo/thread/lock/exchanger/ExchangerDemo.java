package com.echo.thread.lock.exchanger;

import java.util.concurrent.Exchanger;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 18:09
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: ExchangerDemo
 * 文件描述: [ Exchanger
 *      Exchanger是一个用于线程间协作的工具类，用于两个线程间交换数据。具体交换数据是通过
 * exchange方法来实现的，如果一个线程先执行exchange方法，那么它会同步等待另一个线程也执行
 * exchange方法，这个时候两个线程就都达到了同步点，两个线程就可以交换数据。
 *
 *       public V exchange(V x) throws InterruptedException
 *       V exchange(V v)：等待另一个线程到达此交换点（除非当前线程被中断），然后将给定的对象传送给该线程，并
 * 接收该线程的对象。
 *       public V exchange(V x, long timeout, TimeUnit unit) throws InterruptedException,
 * TimeoutException
 *       V exchange(V v, long timeout, TimeUnit unit)：等待另一个线程到达此交换点，或者当前线程被中断——抛出中
 * 断异常；又或者是等候超时——抛出超时异常，然后将给定的对象传送给该线程，并接收该线程的对象。
 * ]
 * version：1.0
 *
 ********************************************************/
public class ExchangerDemo {
    private static final Exchanger exchanger = new Exchanger();
    static String goods = "电脑";
    static String money = "$4000";
    public static void main(String[] args) throws InterruptedException {
        System.out.println("准备交易，一手交钱一手交货...");
        // 卖家
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("卖家到了，已经准备好货：" + goods);
                try {
                    String money = (String) exchanger.exchange(goods);
                    System.out.println("卖家收到钱：" + money);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(3000);
        // 买家
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("买家到了，已经准备好钱：" + money);
                    String goods = (String) exchanger.exchange(money);
                    System.out.println("买家收到货：" + goods);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}