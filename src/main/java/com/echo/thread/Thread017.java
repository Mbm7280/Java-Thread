package com.echo.thread;

/**
 * @author Echo
 * @Description:    Join 的用法 谁调用谁堵塞   A 调用 B —— A 则堵塞
 *                      底层基于 wait 和 notify
 *
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread017 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + ",'" + i);
        }
    }

    public static void main(String[] args) {
        try{
            Thread t1 = new Thread(new Thread017(), "t1");
            Thread t2 = new Thread(new Thread017(), "t2");
            Thread t3 = new Thread(new Thread017(), "t3");
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + "," + i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
