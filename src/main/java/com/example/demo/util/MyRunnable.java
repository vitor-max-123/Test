package com.example.demo.util;

import java.util.Date;

/**
 * @author Administrator
 * @create 2021/7/20 0020 15:51
 */
public class MyRunnable implements Runnable{
    private Integer index;
    public MyRunnable (Integer index){
        this.index=index;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
    }
}
