package com.echo.thread.lock.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2025/9/13 18:16
 * 项目名称: {Java-Design-Pattern}
 * 文件名称: PhaserDemo
 * 文件描述: [ Phaser
 *      Phaser（阶段协同器）是一个Java实现的并发工具类，用于协调多个线程的执行。它提供了一些方便
 * 的方法来管理多个阶段的执行，可以让程序员灵活地控制线程的执行顺序和阶段性的执行。Phaser可
 * 以被视为CyclicBarrier和CountDownLatch的进化版，它能够自适应地调整并发线程数，可以动态地增
 * 加或减少参与线程的数量。所以Phaser特别适合使用在重复执行或者重用的情况。
 *      构造方法
 *          Phaser(): 参与任务数0
 *          Phaser(int parties) :指定初始参与任务数
 *          Phaser(Phaser parent) :指定parent阶段器， 子对象作为一个整体加入parent对象， 当子对象中没有参与者时，
 * 会自动从parent对象解除注册
 *          Phaser(Phaser parent，int parties) : 集合上面两个方法
 *      增减参与任务数方法
 *          int register() 增加一个任务数，返回当前阶段号。
 *          int bulkRegister(int parties) 增加指定任务个数，返回当前阶段号。
 *          int arriveAndDeregister() 减少一个任务数，返回当前阶段号。
 *      到达、等待方法
 *          int arrive() 到达(任务完成)，返回当前阶段号。
 *          int arriveAndAwaitAdvance() 到达后等待其他任务到达，返回到达阶段号。
 *          int awaitAdvance(int phase) 在指定阶段等待(必须是当前阶段才有效)
 *          int awaitAdvanceInterruptibly(int phase) 阶段到达触发动作
 *          int awaitAdvanceInterruptiBly(int phase，long timeout，TimeUnit unit)
 *          protected boolean onAdvance(int phase，int registeredParties)类似CyclicBarrier的触发命令，通过重写该方法
 * 来增加阶段到达动作，该方法返回true将终结Phaser对象。
 * ]
 * version：1.0
 *
 ********************************************************/
public class PhaserDemo {
    public static void main(String[] args) {
        final Phaser phaser = new Phaser() {
            //重写该方法来增加阶段到达动作
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                // 参与者数量，去除主线程
                int staffs = registeredParties - 1;
                switch (phase) {
                    case 0:
                        System.out.println("大家都到公司了，出发去公园，人数：" + staffs);
                        break;
                    case 1:
                        System.out.println("大家都到公园门口了，出发去餐厅，人数：" + staffs);
                        break;
                    case 2:
                        System.out.println("大家都到餐厅了，开始用餐，人数：" + staffs);
                        break;
                }
                // 判断是否只剩下主线程（一个参与者），如果是，则返回true，代表终止
                return registeredParties == 1;
            }
        };
        // 注册主线程 ———— 让主线程全程参与
        phaser.register();
        final StaffTask staffTask = new StaffTask();
        // 3个全程参与团建的员工
        for (int i = 0; i < 3; i++) {
            // 添加任务数
            phaser.register();
            new Thread(() -> {
                try {
                    staffTask.step1Task();
                    //到达后等待其他任务到达
                    phaser.arriveAndAwaitAdvance();

                    staffTask.step2Task();
                    phaser.arriveAndAwaitAdvance();

                    staffTask.step3Task();
                    phaser.arriveAndAwaitAdvance();

                    staffTask.step4Task();
                    // 完成了，注销离开
                    phaser.arriveAndDeregister();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        // 两个不聚餐的员工加入
        for (int i = 0; i < 2; i++) {
            phaser.register();
            new Thread(() -> {
                try {
                    staffTask.step1Task();
                    phaser.arriveAndAwaitAdvance();

                    staffTask.step2Task();
                    System.out.println("员工【" + Thread.currentThread().getName() + "】回家了");
                    // 完成了，注销离开
                    phaser.arriveAndDeregister();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        while (!phaser.isTerminated()) {
            int phase = phaser.arriveAndAwaitAdvance();
            if (phase == 2) {
                // 到了去餐厅的阶段，又新增4人，参加晚上的聚餐
                for (int i = 0; i < 4; i++) {
                    phaser.register();
                    new Thread(() -> {
                        try {
                            staffTask.step3Task();
                            phaser.arriveAndAwaitAdvance();

                            staffTask.step4Task();
                            // 完成了，注销离开
                            phaser.arriveAndDeregister();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            }
        }
    }

    static final Random random = new Random();

    static class StaffTask {
        public void step1Task() throws InterruptedException {
            // 第一阶段：来公司集合
            String staff = "员工【" + Thread.currentThread().getName() + "】";
            System.out.println(staff + "从家出发了……");
            Thread.sleep(random.nextInt(5000));
            System.out.println(staff + "到达公司");
        }
        public void step2Task() throws InterruptedException {
            // 第二阶段：出发去公园
            String staff = "员工【" + Thread.currentThread().getName() + "】";
            System.out.println(staff + "出发去公园玩");
            Thread.sleep(random.nextInt(5000));
            System.out.println(staff + "到达公园门口集合");
        }
        public void step3Task() throws InterruptedException {
            // 第三阶段：去餐厅
            String staff = "员工【" + Thread.currentThread().getName() + "】";
            System.out.println(staff + "出发去餐厅");
            Thread.sleep(random.nextInt(5000));
            System.out.println(staff + "到达餐厅");
        }
        public void step4Task() throws InterruptedException {
            // 第四阶段：就餐
            String staff = "员工【" + Thread.currentThread().getName() + "】";
            System.out.println(staff + "开始用餐");
            Thread.sleep(random.nextInt(5000));
            System.out.println(staff + "用餐结束，回家");
        }
    }
}

