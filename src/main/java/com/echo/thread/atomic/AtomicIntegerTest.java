package com.echo.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/11 20:57
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: AtomicIntegerTest
 * 文件描述: [ AtomicIntegerTest]
 * version：1.0
 *
 ********************************************************/
public class AtomicIntegerTest {

   private static AtomicInteger count = new AtomicInteger(0);
//    private static int count;
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    // 原子自增  CAS
                    count.incrementAndGet(); // 安全
//                    count++; // 不安全
                }
            });
            thread.start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }
}
