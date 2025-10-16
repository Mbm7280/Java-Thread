package com.echo.thread.lock.countdown;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;


/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 17:44
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: CountDownLatchDemo
 * 文件描述: [ CountDownLatch
 *      CountDownLatch（闭锁）是一个同步协助类，可以用于控制一个或多个线程等待多个任务完成后再执
 * 行。
 *      当某项工作需要由若干项子任务并行地完成，并且只有在所有的子任务结束之后（正常结束或者
 * 异常结束），当前主任务才能进入下一阶段，CountDownLatch工具将是非常好用的工具。
 * CountDownLatch 内部维护了一个计数器，该计数器初始值为 N，代表需要等待的线程数目，当一个
 * 线程完成了需要等待的任务后，就会调用 countDown() 方法将计数器减 1，当计数器的值为 0 时，等
 * 待的线程就会开始执行
 *       // 调用 await() 方法的线程会被挂起，它会等待直到 count 值为 0 才继续执行
 *      public void await() throws InterruptedException { };
 *      // 和 await() 类似，若等待 timeout 时长后，count 值还是没有变为 0，不再等待，继续执行
 *      public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };
 *      // 会将 count 减 1，直至为 0
 *      public void countDown() { };
 *
 *      不足
 *          CountDownLatch是一次性的，计算器的值只能在构造方法中初始化一次，之后没有任何机制再次对其
 * 设置值，当CountDownLatch使用完毕后，它不能再次被使用。
 * ]
 * version：1.0
 *
 ********************************************************/
public class CountDownLatchDemo {
    // begin 代表裁判 初始为 1
    private static final CountDownLatch begin = new CountDownLatch(1);
    // end 代表玩家 初始为 8
    private static final CountDownLatch end = new CountDownLatch(8);
    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 8; i++) {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    // 预备状态
                    System.out.println("参赛者" + Thread.currentThread().getName() + "已经准备好了");
                    // 等待裁判吹哨
                    begin.await();
                    // 开始跑步
                    System.out.println("参赛者" + Thread.currentThread().getName() + "开始跑步");
                    Thread.sleep(3000);
                    // 跑步结束, 跑完了
                    System.out.println("参赛者" + Thread.currentThread().getName() + "到达终点");
                    // 跑到终点, 计数器就减一
                    end.countDown();
                }
            }).start();
        }
        // 等待 5s 就开始吹哨
        Thread.sleep(5000);
        System.out.println("开始比赛");
        // 裁判吹哨, 计数器减一
        begin.countDown();
        // 等待所有玩家到达终点
        end.await();
        System.out.println("比赛结束");
    }
}