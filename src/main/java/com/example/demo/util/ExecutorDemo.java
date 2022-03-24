package com.example.demo.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.IntStream;


/**
 * @author Administrator
 * @create 2021/7/20 0020 15:20
 */
public class ExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        /*ExecutorService executorService1 = Executors.newFixedThreadPool(5);
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ExecutorService executorService3 = Executors.newCachedThreadPool();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        Future<?> submit = executorService1.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("aaa");
            }
        });
        executorService1.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("bbb");
            }
        });

        Set<Callable<String>> callables = new HashSet<Callable<String>>();
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("ccc");
                return "ccc";
            }
        });

        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("ddd");
                return "ddd";
            }
        });
        try {
            String s = executorService1.invokeAny(callables);
            System.out.println("return: "+s);
            List<Future<String>> futures = executorService1.invokeAll(callables);
        } catch (Exception e) {
        }

        executorService1.shutdown();*/
        //L1、L2 阶段共用的线程池
        ExecutorService es = Executors.
                newFixedThreadPool(2);
        //L1 阶段的闭锁
        CountDownLatch countDownLatch1=new CountDownLatch(2);
        for (int i=0; i<2; i++){
            System.out.println("L1");
            // 执行 L1 阶段任务
            es.execute(()->{
                //L2 阶段的闭锁
                CountDownLatch countDownLatch2=new CountDownLatch(2);
                // 执行 L2 阶段子任务
                for (int j=0; j<2; j++){
                    es.execute(()->{
                        System.out.println("L2");
                        countDownLatch2.countDown();
                    });
                }
                // 等待 L2 阶段任务执行完
                try {
                    countDownLatch2.await(); //全部阻塞在这里
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                countDownLatch1.countDown();
            });
        }
        // 等着 L1 阶段任务执行完
        countDownLatch1.await();
        System.out.println("end");
    }

    /**
     * 测试FixedThreadPool
     */
    private static void testFixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        IntStream.rangeClosed(1, 1000000).forEach(i -> fixedThreadPool.execute(() -> {
            try {
                Thread.sleep(1000000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }

    /**
     * 测试CachedThreadPool
     */
    private static void testCachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        IntStream.rangeClosed(1, 1000000).forEach(i -> cachedThreadPool.execute(() -> {
            try {
                Thread.sleep(1000000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }
}
