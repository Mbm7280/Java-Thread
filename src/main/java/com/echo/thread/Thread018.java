package com.echo.thread;

/**
 * @author Echo
 * @Description: Join 的用法 谁调用谁堵塞   A 调用 B —— A 则堵塞
 *  *                      基于 Run + 有参构造实现
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread018 implements Runnable {
    private Thread t;

    public Thread018(Thread t){
        this.t = t;
    }

    @Override
    public void run() {
        if(t != null) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + ",'" + i);
        }
    }

    public static void main(String[] args) {
        try{
            Thread t1 = new Thread(new Thread018(null), "t1");
            Thread t2 = new Thread(new Thread018(t1), "t2");
            Thread t3 = new Thread(new Thread018(t2), "t3");
            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + "," + i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
