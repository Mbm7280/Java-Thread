package com.echo.thread.threadlocal;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/11 20:10
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: ThreadLocalDemo2
 * 文件描述: [ ThreadLocalDemo ]
 * version：1.0
 *
 ********************************************************/
public class ThreadLocalDemo2 {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    // ThreadLocal实例通常来说都是 private static类型的
    private static String content;

    private String getContent() {
        return threadLocal.get();
    }

    private void setContent(String content) {
        threadLocal.set(content);
    }

    public static void main(String[] args) {
        ThreadLocalDemo2 demo = new ThreadLocalDemo2();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 设置线程1自己的content
                    demo.setContent(Thread.currentThread().getName() + "的数据");
                    System.out.println(Thread.currentThread().getName() + "--->" + demo.getContent());
                }
            });
            thread.setName("线程" + i);
            thread.start();
        }
    }
}