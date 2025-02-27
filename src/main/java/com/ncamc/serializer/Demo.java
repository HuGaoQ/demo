package com.ncamc.serializer;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Demo {
    public static void main(String[] args) {
        KryoSerializer ser = new KryoSerializer();
        for (int i = 0; i < 10; i++) {
            Msg msg = new Msg();
            msg.setName("zhangsan" + i);
            msg.setAge(i);
            long start = System.nanoTime();
            byte[] bytes = ser.serialize(msg);
            log.info("序列化耗时: {} 微秒", (System.nanoTime() - start) / 1000);
            System.out.printf("msg: %s%n", msg);
            System.out.printf("bytes: %s%n", Arrays.toString(bytes));

            start = System.nanoTime();
            Msg newMsg = ser.deserialize(bytes, Msg.class);
            log.info("反序列化耗时: {} 微秒", (System.nanoTime() - start) / 1000);
            System.out.printf("newMsg: %s%n", newMsg);
        }
    }
}
