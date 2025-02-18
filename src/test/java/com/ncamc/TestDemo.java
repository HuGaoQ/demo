package com.ncamc;

import com.ncamc.service.myMethodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDemo {

    @Resource
    private myMethodService myMethodService;

    @Test
    public void demo() {
        myMethodService.myMethod();
    }

}