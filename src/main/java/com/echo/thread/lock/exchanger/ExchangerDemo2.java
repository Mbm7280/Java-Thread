package com.echo.thread.lock.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 18:12
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: ExchangerDemo2
 * 文件描述: [ Exchanger
 *      模拟对账场景
 * ]
 * version：1.0
 *
 ********************************************************/
public class ExchangerDemo2 {

    private static final Exchanger<String> exchanger = new Exchanger();
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "12379871924sfkhfksdhfks";
                    exchanger.exchange(A);
                } catch (InterruptedException e) {
                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "32423423jknjkfsbfj";
                    String A = exchanger.exchange(B);
                    System.out.println("A和B数据是否一致：" + A.equals(B));
                    System.out.println("A= " + A);
                    System.out.println("B= " + B);
                } catch (InterruptedException e) {
                }
            }
        });
        threadPool.shutdown();
    }
}
