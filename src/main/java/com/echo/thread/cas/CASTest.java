package com.echo.thread.cas;

import com.echo.thread.cas.factory.UnsafeFactory;
import sun.misc.Unsafe;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/11 20:23
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: CASTest
 * 文件描述: [ CAS
 *      CAS 操作是由 Unsafe 类提供支持的
 *      public final native boolean compareAndSwapObject(Object var1, long var2, Object var4, Object var5);
 *      public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
 *      public final native boolean compareAndSwapLong(Object var1, long var2, long var4, long var6);
 *      接收 4 个参数，分别是：对象实例、内存偏移量、字段期望值、字段新值。该方法会针对指定对象实例中的相应偏移量的字段执行CAS 操作。
 *      缺陷：
 *          CAS 虽然高效地解决了原子操作，但是还是存在一些缺陷的，主要表现在三个方面：
 *          自旋 CAS 长时间不成功，则会给 CPU 带来非常大的开销且只能保证一个共享变量原子操作
 *          ABA 问题
 * ]
 * version：1.0
 *
 ********************************************************/
public class CASTest {

    public static void main(String[] args) {
        Entity entity = new Entity();
        Unsafe unsafe = UnsafeFactory.getUnsafe();
        // 获取x在内存中的偏移地址
        long offset = UnsafeFactory.getFieldOffset(unsafe, Entity.class, "x");
        System.out.println(offset);
        boolean successful;
        // 4个参数分别是：对象实例、字段的内存偏移量、字段期望值、字段更新值
        successful = unsafe.compareAndSwapInt(entity, offset, 0, 3);
        System.out.println(successful + "\t" + entity.x);
        successful = unsafe.compareAndSwapInt(entity, offset, 3, 5);
        System.out.println(successful + "\t" + entity.x);
        successful = unsafe.compareAndSwapInt(entity, offset, 5, 8);
        System.out.println(successful + "\t" + entity.x);
    }

}

class Entity{
    int x;
}
