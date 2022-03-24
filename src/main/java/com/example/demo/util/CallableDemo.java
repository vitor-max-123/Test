package com.example.demo.util;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author Administrator
 * @create 2021/7/20 0020 15:01
 */
public class CallableDemo {
    public static void main(String[] args) {
        MyCallable<Object> myCallable = new MyCallable<>();
        FutureTask<Object> futureTask = new FutureTask<>(myCallable);
        Thread thread=new Thread(futureTask);
        thread.start();
    }

}
class MyCallable<Object>  implements Callable<Object> {
    //重写call方法
    @Override
    public Object call() throws Exception {
        // TODO Auto-generated method stub
        System.out.println(Thread.currentThread().getName());
        return null;
    }
}