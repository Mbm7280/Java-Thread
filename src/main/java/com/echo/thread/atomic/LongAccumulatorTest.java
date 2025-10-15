package com.echo.thread.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/10/15 20:16
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: LongAccumulatorTest
 * 文件描述: [ LongAccumulator
 *      accumulate(long x)	执行一次累加操作
 *      get()	获取当前累计值
 *      reset()	清零（重置为初始值）
 *      getThenReset()	获取当前值后重置
 *      longValue()	获取 long 值（实现 Number 接口）
 * ]
 * version：1.0
 *
 ********************************************************/
public class LongAccumulatorTest {
    public static void main(String[] args) throws InterruptedException {
        // 累加 x+y
        LongAccumulator accumulator = new LongAccumulator((x, y) -> x + y, 0);
        ExecutorService executor = Executors.newFixedThreadPool(8);
        // 1到9累加
        IntStream.range(1, 10).forEach(i -> executor.submit(() -> accumulator.accumulate(i)));
        Thread.sleep(2000);
        System.out.println(accumulator.getThenReset());
    }
}
