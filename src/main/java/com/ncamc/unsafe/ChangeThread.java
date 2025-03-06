package com.ncamc.unsafe;

import lombok.Getter;

@Getter
public class ChangeThread implements Runnable {

    boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("subThread change flag to:" + flag);
        flag = true;
    }
}
