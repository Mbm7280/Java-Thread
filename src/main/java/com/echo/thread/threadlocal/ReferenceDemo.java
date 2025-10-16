package com.echo.thread.threadlocal;

import java.lang.ref.WeakReference;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/11 20:02
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: ReferenceDemo
 * 文件描述: [ 强引用，弱引用测试 ]
 * version：1.0
 ********************************************************/
public class ReferenceDemo {
    public static void main(String[] args) {
        // 强引用 只要该变量存在，对象就不会被垃圾回收
        String s = new String("hello");
        System.gc();
        System.out.println(s);
        // 弱引用：只要系统执行完垃圾回收，无论内存是否足够，弱引用变量指向的对象都会被回收
        WeakReference<String> weakReference = new WeakReference<>(new String("hello"));
        // 执行垃圾回收
        System.out.println("执行垃圾回收之前");
        System.out.println(weakReference.get());
        System.gc();
        System.out.println("执行垃圾回收之后：");
        System.out.println(weakReference.get());
    }
}
