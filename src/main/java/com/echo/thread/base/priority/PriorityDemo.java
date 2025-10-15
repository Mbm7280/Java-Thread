package com.echo.thread.base.priority;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/10/15 14:40
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: PriorityDemo
 * 文件描述: [ PriorityDemo
 *   yield():
 *      yield会释放CPU资源，让当前线程从 Running 进入 Runnable状态，让优先级更高（至少是相同）的线程获得执
 * 行机会，不会释放对象锁
 *      假设当前进程只有main线程，当调用yield之后，main线程会继续运行，因为没有比它优先级更高的线程
 *
 *   线程的优先级
 *      线程优先级会提示调度器优先调度该线程，但它仅仅是一个提示，调度器可以忽略它。如果cpu 比较忙，
 * 那么优先级高的线程会获得更多的时间片，但 cpu 闲时，优先级几乎没作用
 *
 *      通过一个整型成员变量priority来控制优先级，优先级的范围从1~10，在线程构建的时候可以通过
 * setPriority(int)方法来修改优先级，默认优先级是5，优先级高的线程分配时间片的数量要多于优先级低的
 * 线程
 * ]
 * version：1.0
 *
 ********************************************************/
public class PriorityDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("线程t1进入执行");
            int count = 0;
            for (; ; ) {
                count++;
                System.out.println("t1-count" + count);
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            System.out.println("线程t2进入执行");
            int count = 0;
            for (; ; ) {
                Thread.yield();
                count++;
                System.out.println("t2-count" + count);
            }

        }, "t2");

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }

}
