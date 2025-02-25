package com.ncamc.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            log.info("{} begin run", Thread.currentThread().getName());
            try {
                log.info("子线程开始沉睡 30 s");
                Thread.sleep(30000L);
            } catch (InterruptedException e) {
                log.info("子线程被打断: {}", e.getMessage());
            }
            log.info("{} end run", Thread.currentThread().getName());
        });
        thread.start();
        Thread.sleep(1000L);
        log.info("主线程等待 1s 后，发现子线程还没有运行成功，打断子线程");
        thread.interrupt();
    }
}
