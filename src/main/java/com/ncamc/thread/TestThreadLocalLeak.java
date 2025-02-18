package com.ncamc.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * thread内存溢出测试
 */
public class TestThreadLocalLeak {
    final static ThreadLocal<byte[]> LOCAL = new ThreadLocal<>();

    final static int ONE_M = 1024 * 1024;

    // 运行时添加虚拟机（jvm）参数：-Xmx50m -XX:+PrintGCDetails
    public static void main(String[] args) {
        testUseThread();
//        testUseThreadPool();
    }

    // 使用线程
    private static void testUseThread() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> LOCAL.set(new byte[ONE_M])).start();
        }
    }

    // 使用线程池
    private static void testUseThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> LOCAL.set(new byte[ONE_M]));
        }
        executorService.shutdown();
        LOCAL.remove();
    }

}