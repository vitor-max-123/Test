package com.example.demo.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author Administrator
 * @create 2021/7/20 0020 14:20
 */
@Slf4j
public class MyThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    @Override
    public void execute(Runnable task) {

        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {

        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        logger.info("Runnable");
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        logger.info("Callable");
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {

        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {

        return super.submitListenable(task);
    }
}
