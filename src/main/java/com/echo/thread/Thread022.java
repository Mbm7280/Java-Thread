package com.echo.thread;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Echo
 * @Description:
 *       New  ReentramtLock()(true)---公平锁
 *       New  ReentramtLock()(false)---非公平锁
 *      公平锁：就是比较公平，根据请求锁的顺序排列，先来请求的就先获取锁，后来获取锁就最后获取到， 采用队列存放 类似于吃饭排队。
 *      非公平锁：不是根据根据请求的顺序排列， 通过争抢的方式获取锁。
 * @date 2022/3/18
 * @Version 1.0
 */
public class Thread022 implements Runnable{

    private static int count = 0;
    private static Lock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (count < 200) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {

            }
            lock.lock();
            createCount();
            lock.unlock();
        }
    }

    public void createCount() {
        System.out.println(Thread.currentThread().getName() + ",count:" + count);
        count++;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Thread022());
            thread.start();
            threads.add(thread);
        }
        threads.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

}
