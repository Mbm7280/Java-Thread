package com.echo.thread.base.create;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 23:06
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: Demo2
 * 文件描述: [ 创建一个线程（使用 Thread 类）]
 *  在Java中有两种方式创建一个线程用以执行，一种是派生自Thread类，另一种是实现Runnable接口。
 *  当然本质上Java中实现线程只有一种方式，都是通过new Thread()创建线程对象，
 *  调用 Thread#start启动线程。
 *
 *  至于基于callable接口的方式，因为最终是要把实现了callable接口的对象通过FutureTask包装
 *  成Runnable，再交给Thread去执行，所以这个其实可以和实现Runnable接口看成同一类。
 *
 *  而线程池的方式，本质上是池化技术，是资源的复用，和新启线程没什么关系。
 *
 *  start():
 *      new Thread()其实只是new出一个Thread的实例,只有执行了start()方法后，才实现了真正意义上的启动线程
 *      start()方法让一个线程进入就绪队列等待分配cpu，分到cpu后才调用实现的run()方法
 *      start()方法不能重复调用，如果重复调用会抛出异常
 *
 *  run():
 *      而run方法是业务逻辑实现的地方，本质上和任意一个类的任意一个成员方法并没有任何区别，
 *      可以重复执行，也可以被单独调用
 * ]
 * version：1.0
 *
 ********************************************************/
public class Demo2 extends Thread{
    @Override
    public void run() {
        System.out.println("线程启动了");
    }
    public static void main(String[] args) {
        Demo2 t = new Demo2();
        t.start();
    }
}
