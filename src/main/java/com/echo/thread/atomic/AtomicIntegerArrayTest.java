package com.echo.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/11 20:59
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: AtomicIntegerArrayTest
 * 文件描述: [ AtomicIntegerArray ]
 * version：1.0
 *
 ********************************************************/
public class AtomicIntegerArrayTest {

    static int[] value = new int[]{ 1, 2, 3, 4, 5 };
    static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(value);
    public static void main(String[] args) throws InterruptedException {
        //设置索引1的元素为100
        atomicIntegerArray.set(1, 100);
        System.out.println(atomicIntegerArray.get(1));
        //以原子更新的方式将数组中索引为1的元素与输入值相加
        atomicIntegerArray.getAndAdd(1,5);
        System.out.println(atomicIntegerArray);
    }
}
