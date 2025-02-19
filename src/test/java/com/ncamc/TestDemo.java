package com.ncamc;

import com.ncamc.service.TransactionService;
import com.ncamc.service.myMethodService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDemo {

    @Resource
    private myMethodService myMethodService;

    @Resource
    private TransactionService transactionService;

    @Test
    public void demo() {
        long startTime = System.currentTimeMillis();
        myMethodService.myMethod();
        transactionService.transferMoney("AccountA", "AccountB", 500);
        transactionService.transferMoney("AccountA", "AccountB", 100);
        log.info("执行时间：{}", (System.currentTimeMillis() - startTime) / 1000);
    }

}