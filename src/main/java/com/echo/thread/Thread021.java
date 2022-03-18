package com.echo.thread;

/**
 * @author Echo
 * @Description: volatile —— 双重检验锁为什么需要加上volatile
 *                  new操作    Thread021 = new Thread021()，存在重排序的问题。
 * 可以采用 javap -c 查看字节码
 * 1.分配对象的内存空间
 *   memory=allocate();
 * 2.调用构造函数初始化
 * 3.将对象复制给变量
 * 4.第二步和第三步流程存在重排序也有可能先执行我们的，将对象复制给变量，在执行
 *   调用构造函数初始化，导致另外一个线程获取到该对象不为空，但是该改造函数没有初始化，
 *   所以就报错了 。就是另外一个线程拿到的是一个不完整的对象。
 * @date 2022/3/18
 * @Version 1.0
 */
public class Thread021 {
    private static volatile Thread021 singleton;

    public static Thread021 getInstance() {
        // 第一次检查
        if (singleton == null) {
            //第二次检查
            synchronized (Thread021.class) {
                if (singleton == null) {
                    singleton = new Thread021();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        Thread021 instance1 = Thread021.getInstance();
        Thread021 instance2 = Thread021.getInstance();
        System.out.println(instance1==instance2);
    }
}
