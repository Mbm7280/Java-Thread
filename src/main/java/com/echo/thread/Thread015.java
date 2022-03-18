package com.echo.thread;

/**
 * @author Echo
 * @Description: 锁对象应该锁共享对象 res 而不是各自的 this
 *                  但是应该是先写后读，而不是一直读，或者一直写
 *                  而且输出应该是 一男一女
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread015 {

    class Res{
        public String userName;
        private char sex;
    }

    class InputThread extends Thread {
        Thread015.Res res;

        public InputThread (Thread015.Res res) {
            this.res = res;
        }

        @Override
        public void run () {
            int count = 0;
            while (true) {
                synchronized (res){
                    if(count == 0){
                        res.userName = "Echo";
                        res.sex = '男';
                    } else {
                        res.userName = "X";
                        res.sex = '女';
                    }
                    count = (count + 1) % 2;
                }
            }
        }
    }

    class OutThread extends Thread {
        Thread015.Res res;
        public OutThread (Thread015.Res res) {
            this.res = res;
        }

        @Override
        public void run () {
            while (true) {
                synchronized (res) {
                    System.out.println(res.userName + "," + res.sex);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread015().start();
    }

    public void start() {
        Thread015.Res res = new Thread015.Res();
        new Thread015.InputThread(res).start();
        new Thread015.OutThread(res).start();
    }
}
