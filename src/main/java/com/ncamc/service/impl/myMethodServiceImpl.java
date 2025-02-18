package com.ncamc.service.impl;

import com.ncamc.service.myMethodService;
import org.springframework.stereotype.Service;

@Service
public class myMethodServiceImpl implements myMethodService {

    @Override
    public void myMethod() {
        // 模拟方法执行，耗时操作
        try {
            // 让方法睡眠 2 秒，模拟耗时操作
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Method executed!");
    }
}
