package com.echo.thread.container.collection;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 19:24
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: CopyOnWriteArrayListDemo
 * 文件描述: [ CopyOnWriteArrayList
 *          读多写少的并发场景。CopyOnWriteArrayList容器允许并发读，读
 * 操作是无锁的，性能较高。至于写操作，比如向容器中添加一个元素，则首先将当前容器复制一份，
 * 然后在新副本上执行写操作，结束之后再将原容器的引用指向新容器。
 *      线程安全的，多线程环境下可以直接使用，无需加锁；
 *      通过锁 + 数组拷贝 + volatile 关键字保证了线程安全；
 *      每次数组操作，都会把数组拷贝一份出来，在新数组上进行操作，操作成功之后再赋值回去。
 *      CopyOnWriteArrayList 在对数组进行操作的时候，基本会分四步走：
 *          加锁；
 *          从原数组中拷贝出新数组；
 *          在新数组上进行操作，并把新数组赋值给数组容器；
 *          解锁
 *          除了加锁之外，CopyOnWriteArrayList 的底层数组还被 volatile 关键字修饰
 *      缺点：
 *          内存占用问题，毕竟每次执行写操作都要将原容器拷贝一份。数据量大时，对内存压力较大，可能会引起频繁
 * GC；
 *          无法保证实时性，因为CopyOnWrite的写时复制机制，所以在进行写操作的时候，内存里会同时驻扎两个对象的
 * 内存，旧的对象和新写入的对象（注意：在复制的时候只是复制容器里的引用，只是在写的时候会创建新对象添
 * 加到新容器里，而旧容器的对象还在使用，所以有两份对象内存）
 *
 *      在 Java 中，迭代器（Iterator）在迭代的过程中，如果底层的集合被修改（添加或删除元素），不同的
 * 迭代器对此的表现行为是不一样的，可分为两类：Fail-Fast（快速失败）和 Fail-Safe（安全失败）。
 *      fail-fast 机制
 *          fail-fast 机制是java集合(Collection)中的一种错误机制。当多个线程对同一个集合的内容进行操作时，
 * 就可能会产生 fail-fast 事件。例如：当某一个线程A通过 iterator 去遍历某集合的过程中，若该集合的
 * 内容被其他线程所改变了；那么线程A访问集合时，就会抛出ConcurrentModificationException异常，
 * 产生 fail-fast 事件
 *          fail-fast解决方案
 *              方案一：在遍历过程中所有涉及到改变modCount 值的地方全部加上synchronized 或者直接使用
 * Collection#synchronizedList，这样就可以解决问题，但是不推荐，因为增删造成的同步锁可能会阻塞遍历操作。
 *              方案二：使用CopyOnWriteArrayList 替换 ArrayList，推荐使用该方案（即fail-safe）。
 *
 *      fail-safe机制
 *          任何对集合结构的修改都会在一个复制的集合上进行，因此不会抛出
 * ConcurrentModificationException。在 java.util.concurrent 包中的集合，如 CopyOnWriteArrayList、
 * ConcurrentHashMap 等，它们的迭代器一般都是采用 Fail-Safe 机制。
 *
 *          缺点：
 * 采用 Fail-Safe 机制的集合类都是线程安全的，但是它们无法保证数据的实时一致性，它们只能保证数据的最终
 * 一致性。在迭代过程中，如果集合被修改了，可能读取到的仍然是旧的数据。
 * Fail-Safe 机制还存在另外一个问题，就是内存占用。由于这类集合一般都是通过复制来实现读写分离的，因此它
 * 们会创建出更多的对象，导致占用更多的内存，甚至可能引起频繁的垃圾回收，严重影响性能。
 * ]
 * version：1.0
 *
 ********************************************************/
public class CopyOnWriteArrayListDemo {

    private static final CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    // 模拟初始化的黑名单数据
    static {
        copyOnWriteArrayList.add("ipAddr0");
        copyOnWriteArrayList.add("ipAddr1");
        copyOnWriteArrayList.add("ipAddr2");
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    // 模拟接入用时
                    Thread.sleep(new Random().nextInt(5000));
                } catch (Exception e) {}

                String currentIP = "ipAddr" + new Random().nextInt(6);
                // 判断请求ip是否命中黑名单
                if (copyOnWriteArrayList.contains(currentIP)) {
                    System.out.println(Thread.currentThread().getName() + " IP " + currentIP + "命中黑名单，拒绝接入处理");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " IP " + currentIP + "接入处理...");
            }
        };
        // 发送3个请求
//        new Thread(task, "请求1").start();
//        new Thread(task, "请求2").start();
//        new Thread(task, "请求3").start();
        for(int i=1;i<10;i++){
            new Thread(task, "请求"+i).start();
        }

        // 更新黑名单
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 模拟用时
                    Thread.sleep(new Random().nextInt(2000));
                } catch (Exception e) {}
                String newBlackIP = "ipAddr3";
                copyOnWriteArrayList.add(newBlackIP);
                System.out.println(Thread.currentThread().getName() + " 添加了新的非法IP " + newBlackIP);
            }
        }, "IP黑名单更新").start();
        Thread.sleep(1000000);
    }
}
