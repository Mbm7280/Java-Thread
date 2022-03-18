package com.echo.thread;

/**
 * @author Echo
 * @Description: 数据混乱
 * @date 2022/3/17
 * @Version 1.0
 */
public class Thread014 {

    class Res{
        public String userName;
        private char sex;
    }

    class InputThread extends Thread {
        Res res;

        public InputThread (Res res) {
            this.res = res;
        }

        @Override
        public void run () {
            int count = 0;
            while (true) {
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

    class OutThread extends Thread {
        Res res;
        public OutThread (Res res) {
            this.res = res;
        }

        @Override
        public void run () {
            while (true) {
                System.out.println(res.userName + "," + res.sex);
            }
        }
    }

    public static void main(String[] args) {
        new Thread014().start();
    }
    public void start() {
        Res res = new Res();
        new InputThread(res).start();
        new OutThread(res).start();
    }

}
