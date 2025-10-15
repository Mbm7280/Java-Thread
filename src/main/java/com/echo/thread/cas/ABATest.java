package com.echo.thread.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/11 20:37
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: ABATest
 * 文件描述: [ ABA问题
 *      当有多个线程对一个原子类进行操作的时候，某个线程在短时间内将原子类的值A修改为B，又马上将
 * 其修改为A，此时其他线程不感知，还是会修改成功。
 *      可以通过 AtomicStampedReference 解决
 * ]
 * version：1.0
 *
 ********************************************************/
@Slf4j
public class ABATest {

    // ABA  问题复现
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        new Thread(()->{
            int value = atomicInteger.get();
            log.debug("Thread1 read value: " + value);
            // 阻塞1s
            LockSupport.parkNanos(1000000000L);
            // Thread1通过CAS修改value值为3
            if (atomicInteger.compareAndSet(value, 3)) {
                log.debug("Thread1 update from " + value + " to 3");
            } else {
                log.debug("Thread1 update fail!");
            }
        },"Thread1").start();

        new Thread(()->{
            int value = atomicInteger.get();
            log.debug("Thread2 read value: " + value);
            // Thread2通过CAS修改value值为2
            if (atomicInteger.compareAndSet(value, 2)) {
                log.debug("Thread2 update from " + value + " to 2");
                // do something
                value = atomicInteger.get();
                log.debug("Thread2 read value: " + value);
                // Thread2通过CAS修改value值为1
                if (atomicInteger.compareAndSet(value, 1)) {
                    log.debug("Thread2 update from " + value + " to 1");
                }
            }
        },"Thread2").start();
    }

    // ABA 问题解决
//    public static void main(String[] args) {
//        // 定义AtomicStampedReference    Pair.reference值为1, Pair.stamp为1
//        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(1,1);
//        new Thread(()->{
//            int[] stampHolder = new int[1];
//            int value = (int) atomicStampedReference.get(stampHolder);
//            int stamp = stampHolder[0];
//            log.debug("Thread1 read value: " + value + ", stamp: " + stamp);
//            // 阻塞1s
//            LockSupport.parkNanos(1000000000L);
//            // Thread1通过CAS修改value值为3   stamp是版本，每次修改可以通过+1保证版本唯一性
//            if (atomicStampedReference.compareAndSet(value, 3,stamp,
//                    stamp+1)) {
//                log.debug("Thread1 update from " + value + " to 3");
//            } else {
//                log.debug("Thread1 update fail!");
//            }
//        },"Thread1").start();
//
//        new Thread(()->{
//            int[] stampHolder = new int[1];
//            int value = (int)atomicStampedReference.get(stampHolder);
//            int stamp = stampHolder[0];
//            log.debug("Thread2 read value: " + value+ ", stamp: " + stamp);
//            // Thread2通过CAS修改value值为2
//            if (atomicStampedReference.compareAndSet(value, 2,stamp,
//                    stamp+1)) {
//                log.debug("Thread2 update from " + value + " to 2");
//                value = (int) atomicStampedReference.get(stampHolder);
//                stamp = stampHolder[0];
//                log.debug("Thread2 read value: " + value+ ", stamp: " + stamp);
//                // Thread2通过CAS修改value值为1
//                if (atomicStampedReference.compareAndSet(value, 1,stamp,
//                        stamp+1)) {
//                    log.debug("Thread2 update from " + value + " to 1");
//                }
//            }
//        },"Thread2").start();
//    }

}
