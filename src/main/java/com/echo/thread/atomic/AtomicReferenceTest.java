package com.echo.thread.atomic;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/11 21:03
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: AtomicReferenceTest
 * 文件描述: [ AtomicReference
 *  get()                              获取当前引用的值                                 
 *  set(V newValue)                    设置新引用（非原子）                               
 *  compareAndSet(V expect, V update)  **CAS原子更新**：如果当前值等于expect，就更新为update 
 *  getAndSet(V newValue)              原子地设置新值并返回旧值                             
 *  weakCompareAndSet(...)             类似 CAS，但性能优化（非强保证）                       
 * ]
 * version：1.0
 *
 ********************************************************/
public class AtomicReferenceTest {

    public static void main(String[] args) {
        User user1 = new User("张三", 23);
        User user2 = new User("李四", 25);
        User user3 = new User("王五", 20);
        //初始化为 user1
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(user1);

        //把 user2 赋给 atomicReference
        atomicReference.compareAndSet(user2, user1);
        System.out.println(atomicReference.get());

        //把 user3 赋给 atomicReference
        atomicReference.compareAndSet(user3, user2);
        System.out.println(atomicReference.get());
    }
}


@Data
@AllArgsConstructor
class User {
    private String name;
    private Integer age;
}
