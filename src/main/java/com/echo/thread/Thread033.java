package com.echo.thread;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Echo
 * @Description:    LinkedBlockingQueue(堵塞队列的使用)
 * @date 2022/3/24
 * @Version 1.0
 */
public class Thread033 {
    public static void main(String[] args) {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        linkedBlockingQueue.add("e");
        linkedBlockingQueue.add("c");
        linkedBlockingQueue.add("h");
        linkedBlockingQueue.add("o");

        //poll 取出成功的情况下 会改将元素从队列中删除
        //peek() 取出成功之后，不会删除该元素
//        System.out.println(linkedBlockingQueue.poll());
        System.out.println(linkedBlockingQueue.peek());
        System.out.println(linkedBlockingQueue.peek());
    }
}
