package com.echo.thread;

import com.echo.utils.UnSafeUtils;

/**
 * @author Echo
 * @Description:    重排序问题 —— 以及内存屏障的添加
 * @date 2022/3/18
 * @Version 1.0
 */
public class Thread020 {

    // 全局共享的变量
    private  static int a = 0, b = 0;
    private  static int x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true) {
            i++;
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    //插入一个内存写屏障
                    UnSafeUtils.getUnsafe().storeFence();
                    x = b;

                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    //插入一个内存屏障
                    UnSafeUtils.getUnsafe().storeFence();
                    y = a;
                }
            });
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            System.out.println("第" + i + "次(" + x + "," + y + ")");
            if (x == 0 && y == 0) {
                break;
            }
        }
    }


}
