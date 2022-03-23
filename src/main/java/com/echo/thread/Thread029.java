package com.echo.thread;

import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

/**
 * @author Echo
 * @Description:        手写CountDownLatch
 * @date 2022/3/23
 * @Version 1.0
 */
public class Thread029 {
    private Sync sync;

    public Thread029 (int count) {
        sync = new Sync(count);
    }

    public void await() {
        sync.acquireShared(1);
    }

    public void countDown() {
        sync.releaseShared(1);
    }

    class Sync extends AbstractQueuedLongSynchronizer {
        public Sync (int count) {
            setState(count);
        }

        /**
         * 如果该返回结果是<0的情况下，则让当前线程放入到aqs双向链表中
         * @param arg
         * @return
         */
        @Override
        protected long tryAcquireShared(long arg) {
            return getState() == 0 ? 1 : -1;
        }

        /**
         * 如果该方法返回true的情况下，就唤醒刚才阻塞的线程
         * @param arg
         * @return
         */
        @Override
        protected boolean tryReleaseShared(long arg) {
            for (;;) {
                long oldState = getState();
                if (oldState == 0) {
                    return false;
                }
                long newState = oldState - arg;
                if (compareAndSetState(oldState,newState)) {
                    return newState == 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread029 echoCountDownLatch = new Thread029(2);
        new Thread(() -> {
            System.out.println("1");
            try {
                echoCountDownLatch.await();
            } catch (Exception e) {
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
        echoCountDownLatch.countDown();
        echoCountDownLatch.countDown();
    }

}
