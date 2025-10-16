package com.echo.thread.threadlocal;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/11 20:02
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: ThreadLocalDemo
 * 文件描述: [
 *      ThreadLocal类用来提供线程内部的局部变量
 *      这种变量在多线程环境下访问（通过get和set方法访问）时能保证各个线程的变量相对独立于其他线程内的变量
 *
 *      特性：
 *          线程安全: 在多线程并发的场景下保证线程安全
 *          传递数据: 我们可以通过ThreadLocal在同一线程，不同组件中传递公共变量
 *          线程隔离: 每个线程的变量都是独立的，不会互相影响
 *
 *      常用方法：
 *          public void set( T value) 设置当前线程绑定的局部变量
 *          public T get() 获取当前线程绑定的局部变量
 *          public void remove() 移除当前线程绑定的局部变量
 *
 *      synchronized
 *          同步机制采用'以时间换空间'的方式, 只提供了一份变量,让不同的线程排队访问
 *          侧重点 多个线程之间访问资源的同步
 *      ThreadLocal
 *          采用'以空间换时间'的方式, 为每一个线程都提供了一份变量的副本,从而实现同时访问而相不干扰
 *          侧重点 多线程中让每个线程之间的数据相互隔离
 * ]
 * version：1.0
 *
 ********************************************************/
public class ThreadLocalDemo {

    private String content;

    private String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) {
        ThreadLocalDemo demo = new ThreadLocalDemo();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public  void run() {
                    // 采用加锁实现多个线程之间访问资源的同步
//                    synchronized (demo) {
//                        demo.setContent(Thread.currentThread().getName() + "的数据");
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                        System.out.println(Thread.currentThread().getName() + "--->" + demo.getContent());
//                    }
                    // 变量对于线程来言不存在隔离
                    demo.setContent(Thread.currentThread().getName() + "的数据");
                    System.out.println(Thread.currentThread().getName() + "--->" + demo.getContent());
                }
            });
            thread.setName("线程" + i);
            thread.start();
        }
    }
}