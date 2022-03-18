package com.echo.thread;

/**
 * @author Echo
 * @Description:    使用 wait notify
 *                  实现 一写一读
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread016 {
    class Res{
        public String userName;
        private char sex;
        private boolean flag; // false 只能写  true 只能读
    }

    class InputThread extends Thread {
        Thread016.Res res;

        public InputThread (Thread016.Res res) {
            this.res = res;
        }

        @Override
        public void run () {
            int count = 0;
            while (true) {
                synchronized (res){
                    if(res.flag){
                        try {
                            res.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        if(count == 0){
                            res.userName = "Echo";
                            res.sex = '男';
                        } else {
                            res.userName = "X";
                            res.sex = '女';
                        }
                        res.flag = true;
                        res.notify();
                        count = (count + 1) % 2;
                    }
                }
            }
        }
    }

    class OutThread extends Thread {
        Thread016.Res res;
        public OutThread (Thread016.Res res) {
            this.res = res;
        }

        @Override
        public void run () {
            while (true) {
                synchronized (res) {
                    if(!res.flag){
                        try {
                            res.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(res.userName + "," + res.sex);
                    res.flag = false;
                    res.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread016().start();
    }

    public void start() {
        Thread016.Res res = new Thread016.Res();
        new Thread016.InputThread(res).start();
        new Thread016.OutThread(res).start();
    }
}
