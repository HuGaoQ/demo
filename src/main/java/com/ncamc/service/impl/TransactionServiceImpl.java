package com.ncamc.service.impl;

import com.ncamc.service.TransactionService;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class TransactionServiceImpl implements TransactionService {

    // 事务管理，操作成功提交，操作失败回滚
    @Transient
    @Override
    public void transferMoney(String fromAccount, String toAccount, double amount) {
        // 模拟数据库操作
        System.out.println("Transferring money from " + fromAccount + " to " + toAccount + " amount: " + amount);
        if (amount > 1000) {
            throw new RuntimeException("Amount exceeds limit, rolling back");
        }
    }

    // 普通方法，不需要事务管理
    @Override
    public void logTransaction(String message) {
        System.out.println(message);
    }
}