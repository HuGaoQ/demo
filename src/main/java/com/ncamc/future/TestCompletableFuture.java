package com.ncamc.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class TestCompletableFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
//            try {
//                Thread.sleep(2000L);
//            } catch (InterruptedException e) {
//                log.info("e: {}", e.getMessage());
//            }
//            log.info("子线程: {}", Thread.currentThread().getName());
//        }, executorService);

        //实现了Supplier的get()方法
//        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(2000);
//            } catch (Exception e) {
//                log.info("e: {}", e.getMessage());
//            }
//            log.info("supplyAsync: {}", Thread.currentThread().getName());
//            return "hello ";
//            //实现了Comsumper的accept()方法
//        }, executorService).thenAccept(s -> {
//            try {
//                System.out.println(s + "world");
//            } catch (Exception e) {
//                log.info("e: {}", e.getMessage());
//            }
//        });

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.info("e: {}", e.getMessage());
            }
            if (1 == 1) {
                throw new RuntimeException("测试exceptionally...");
            }
            return "s1";
        }, executorService).exceptionally(e -> {
            log.info("e: {}", e.getMessage());
            return "helloWorld " + e.getMessage();
        });
        cf.thenAcceptAsync(s -> {
            System.out.println("thenAcceptAsync: " + s);
        });

        log.info("主线程: {}", Thread.currentThread().getName());
        while (true) {
            if (cf.isDone()) {
                System.out.println("CompletedFuture...isDown");
                break;
            }
        }
    }
}
