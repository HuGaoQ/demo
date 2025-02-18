package com.ncamc.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * thread强引用gc测试
 */
public class TestThreadLocal {

    static ThreadLocal<String> LOCAL = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 线程1
        executorService.execute(() -> {
            // 存值
            LOCAL.set(Thread.currentThread().getName());
            try {
                // 停顿一秒，以便先gc，再get
                Thread.sleep(10001);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 获取值
            System.out.println(Thread.currentThread().getName() + "-->" + LOCAL.get());
        });
        // 线程2
        executorService.execute(() -> {
            LOCAL.set(Thread.currentThread().getName());
            try {
                Thread.sleep(10001);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "-->" + LOCAL.get());
        });
        // 主线程中触发gc
        System.gc();
        executorService.shutdown();
    }

}