package com.example.demo.util;

/**
 * @author Administrator
 * @create 2021/7/20 0020 14:56
 */
public class RunnableDemo {
    public static void main(String[] args) {
        Task task=new Task();
        Thread thread=new Thread(task);
        thread.start();
    }
}
class Task implements Runnable{

    @Override
    public void run() {
        //编写自己的线程代码
        System.out.println(Thread.currentThread().getName());
    }
}
