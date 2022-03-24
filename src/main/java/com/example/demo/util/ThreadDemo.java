package com.example.demo.util;

/**
 * @author Administrator
 * @create 2021/7/20 0020 14:50
 */
public class ThreadDemo extends Thread{
    @Override
    public void run(){
        //编写自己的线程代码
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        ThreadDemo threadDemo=new ThreadDemo();
        threadDemo.start();
    }
}
