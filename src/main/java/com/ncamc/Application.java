package com.ncamc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner, DisposableBean {
    private final static CountDownLatch LATCH = new CountDownLatch(1);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("demo ------>> 启动成功");
    }

    @Override
    public void destroy() throws Exception {
        LATCH.countDown();
        log.info("demo ------>> 关闭成功");
    }
}