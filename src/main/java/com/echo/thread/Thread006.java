package com.echo.thread;

/**
 * @author Echo
 * @Description: 优雅的停止一个线程——采用变量值的停止该线程
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread006 extends Thread{
    private volatile boolean flag = true;

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName());
        while (flag) {
//            System.out.println("I am still running");
        }
    }

    public void stopThread () {
        this.flag = false;
    }

    public static void main(String[] args) {

        Thread006 thread006 = new Thread006();
        thread006.start();
        try{
            Thread.sleep(1000);
            thread006.stopThread();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
