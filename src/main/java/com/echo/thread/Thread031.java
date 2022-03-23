package com.echo.thread;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author Echo
 * @Description:        手写semaphore
 * @date 2022/3/23
 * @Version 1.0
 */
public class Thread031 {

    private Sync sync;

    public Thread031 (int count) {
        sync = new Sync(count);
    }

    public void acquire () {
        sync.acquireShared(1);
    }

    public void release() {
        sync.releaseShared(1);
    }



    class Sync extends AbstractQueuedSynchronizer {

        public Sync (int count) {
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            for (; ; ) {
                int oldState = getState();
                int newState = oldState - arg;
                if (compareAndSetState(oldState, newState)) {
                    return newState < 0 ? -1 : 1;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            // false newState>0 true <0
            for (; ; ) {
                int oldState = getState();
                //+1
                int newState = oldState + arg;
                if (newState < oldState) {
                    throw new Error("Maximum permit count exceeded");
                }
                if (compareAndSetState(oldState, newState)) {
                    return true;
                }
            }
        }
    }

    public static void main(String[] args) {
        // 设置aqs状态为5 只能限制有5线程执行代码  限流 做多可以容量5个人
        Thread031 semaphore = new Thread031(5);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                // aqs状态会减去1 如果状态=0的情况下
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + ",进入");
                // aqs 状态+1 同时唤醒aqs正在阻塞的线程
//                semaphore.release();
            }).start();

        }
    }

}
