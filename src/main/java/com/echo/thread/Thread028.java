package com.echo.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author Echo
 * @Description:    CountDownLatch使用方法
 *      允许一个或多个线程等待直到在其他线程中一组操作执行完成
 * @date 2022/3/23
 * @Version 1.0
 */
public class Thread028 {
    public static void main(String[] args) {
        //将aqs状态 state 设置为2
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread (() -> {
            System.out.println("1");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2");
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("3");
//        countDownLatch.countDown();// aqs 状态-1 只有aqs 的状态为0的情况下 才会唤醒子线程
//        countDownLatch.countDown();
//        countDownLatch.countDown();
    }
}
