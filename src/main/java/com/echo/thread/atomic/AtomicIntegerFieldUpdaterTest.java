package com.echo.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/11 21:05
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: AtomicIntegerFieldUpdaterTest
 * 文件描述: [ AtomicIntegerFieldUpdater
 *      可以线程安全地更新对象中的整型变量
 *      使用限制
 *          （1）字段必须是volatile类型的，在线程之间共享变量时保证立即可见.eg:volatile int value = 3
 *          （2）字段的描述类型（修饰符public/protected/default/private）与调用者与操作对象字段的关系一
 * 致。也就是说调用者能够直接操作对象字段，那么就可以反射进行原子操作。但是对于父类的字段，
 * 子类是不能直接操作的，尽管子类可以访问父类的字段。
 *          （3）只能是实例变量，不能是类变量，也就是说不能加static关键字。
 *          （4）只能是可修改变量，不能使final变量，因为final的语义就是不可修改。实际上final的语义和
 * volatile是有冲突的，这两个关键字不能同时存在。
 *          （5）对于AtomicIntegerFieldUpdater和AtomicLongFieldUpdater只能修改int/long类型的字段，不能修
 * 改其包装类型（Integer/Long）。如果要修改包装类型就需要使用AtomicReferenceFieldUpdater。
 * ]
 * version：1.0
 *
 ********************************************************/
public class AtomicIntegerFieldUpdaterTest {
    public static class Candidate {
        //字段必须是volatile类型
        volatile int score = 0;
    }

    public static final AtomicIntegerFieldUpdater<Candidate> scoreUpdater =
            AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    public static void main(String[] args) throws InterruptedException {
        final Candidate candidate = new Candidate();
        Thread[] t = new Thread[10000];
        for (int i = 0; i < 10000; i++) {
            t[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (Math.random() > 0.4) {
                        scoreUpdater.incrementAndGet(candidate);
                    }
                }
            });
            t[i].start();
        }
        for (int i = 0; i < 10000; i++) {
            t[i].join();
        }
        System.out.println("AtomicIntegerFieldUpdater Score=" + candidate.score);
    }
}