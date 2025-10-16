package com.echo.thread.lock.exchanger;


import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Exchanger;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 18:13
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: ExchangerDemo3
 * 文件描述: [ Exchanger 模拟队列中数据交换场景
 * ]
 * version：1.0
 *
 ********************************************************/
public class ExchangerDemo3 {
    private static final ArrayBlockingQueue<String> fullQueue
            = new ArrayBlockingQueue<>(5);
    private static final ArrayBlockingQueue<String> emptyQueue
            = new ArrayBlockingQueue<>(5);
    private static final Exchanger<ArrayBlockingQueue<String>> exchanger
            = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }

    // 生产者
    static class Producer implements Runnable {
        @Override
        public void run() {
            ArrayBlockingQueue<String> current = emptyQueue;
            try {
                while (current != null) {
                    String str = UUID.randomUUID().toString();
                    try {
                        current.add(str);
                        System.out.println("producer：生产了一个序列：" + str + ">>>>>加入到交换区");
                        Thread.sleep(2000);
                    } catch (IllegalStateException e) {
                        System.out.println("producer：队列已满，换一个空的");
                        current = exchanger.exchange(current);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 消费者
    static class Consumer implements Runnable {
        @Override
        public void run() {
            ArrayBlockingQueue<String> current = fullQueue;
            try {
                while (current != null) {
                    if (!current.isEmpty()) {
                        String str = current.poll();
                        System.out.println("consumer：消耗一个序列：" + str);
                        Thread.sleep(1000);
                    } else {
                        System.out.println("consumer：队列空了，换个满的");
                        current = exchanger.exchange(current);
                        System.out.println("consumer：换满的成功~~~~~~~~~~~~~~~~~~~~~~");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
