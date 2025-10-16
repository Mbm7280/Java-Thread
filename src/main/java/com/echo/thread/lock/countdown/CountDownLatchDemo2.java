package com.echo.thread.lock.countdown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 17:47
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: CountDownLatchDemo2
 * 文件描述: [ TODO ]
 * version：1.0
 *
 ********************************************************/
public class CountDownLatchDemo2 {
    public static void main(String[] args) throws Exception {
        // 定义CountDownLatch，指定计数器为5
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            final int index = i;
            new Thread(() -> {
                try {
                    // 模拟业务执行，比较耗时，这里用休眠替代
                    Thread.sleep(1000 + ThreadLocalRandom.current().nextInt(2000));
                    System.out.println("任务" + index + "执行完成");
                    //任务完成，计数器减1
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        // 主线程在阻塞，当计数器为0，就唤醒主线程往下执行
        countDownLatch.await();
        System.out.println("主线程:在所有任务运行完成后，进行结果汇总");
    }
}