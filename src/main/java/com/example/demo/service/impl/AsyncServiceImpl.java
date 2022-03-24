package com.example.demo.service.impl;

import com.example.demo.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @create 2021/7/20 0020 13:41
 */
@Service
public class AsyncServiceImpl implements AsyncService {
    @Override
    @Async("asyncServiceExecutor")
    public void asyncDemo(Integer num) {
        System.out.println("start thread"+num);
        try{
            Thread.sleep(10000);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("end thread"+num);
    }
}
