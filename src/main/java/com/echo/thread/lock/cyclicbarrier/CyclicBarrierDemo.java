package com.echo.thread.lock.cyclicbarrier;

import java.util.concurrent.*;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 18:00
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: CyclicBarrierDemo
 * 文件描述: [ CyclicBarrier
 *      CyclicBarrier（回环栅栏或循环屏障），是 Java 并发库中的一个同步工具，通过它可以实现让一组线
 * 程等待至某个状态（屏障点）之后再全部同时执行。叫做回环是因为当所有等待线程都被释放以后，
 * CyclicBarrier可以被重用。CyclicBarrier也非常适合用于某个串行化任务被分拆成若干个并行执行的子
 * 任务，当所有的子任务都执行结束之后再继续接下来的工作。
 *
 *      // parties表示屏障拦截的线程数量，每个线程调用 await 方法告诉 CyclicBarrier 我已经到达了屏
 * 障，然后当前线程被阻塞。
 *      public CyclicBarrier(int parties)
 *     // 用于在线程到达屏障时，优先执行 barrierAction，方便处理更复杂的业务场景(该线程的执行时机是
 * 在到达屏障之后再执行)
 *      public CyclicBarrier(int parties, Runnable barrierAction)
 *
 *     // 指定数量的线程全部调用await()方法时，这些线程不再阻塞
 *    // BrokenBarrierException 表示栅栏已经被破坏，破坏的原因可能是其中一个线程 await() 时被中断
 * 或者超时
 *      public int await() throws InterruptedException, BrokenBarrierException
 *      public int await(long timeout, TimeUnit unit) throws InterruptedException,BrokenBarrierException, TimeoutException
 *
 *   // 循环 通过reset()方法可以进行重置
 *      public void reset()
 * ]
 * version：1.0
 *
 ********************************************************/
public class CyclicBarrierDemo {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,
                () -> System.out.println("人齐了，准备发车"));
        for (int i = 0; i < 10; i++) {
            final int id = i+1;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(id+"号马上就到");
                        int sleepMills = ThreadLocalRandom.current().nextInt(2000);
                        Thread.sleep(sleepMills);
                        System.out.println(id + "号到了，上车");
                        // 阻塞
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }catch(BrokenBarrierException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}